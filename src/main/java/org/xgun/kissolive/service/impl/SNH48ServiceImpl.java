package org.xgun.kissolive.service.impl;

import com.github.pagehelper.PageHelper;
import org.apache.catalina.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.xgun.kissolive.common.ServerResponse;
import org.xgun.kissolive.dao.SNH48Mapper;
import org.xgun.kissolive.pojo.*;
import org.xgun.kissolive.service.ISNH48Service;
import org.xgun.kissolive.utils.DateUtil;
import org.xgun.kissolive.vo.ListOrder;
import org.xgun.kissolive.vo.ListOrderItem;
import org.xgun.kissolive.vo.ListStock;
import org.xgun.kissolive.vo.OrderGoods;

import java.math.BigDecimal;
import java.util.*;

@Service("iSNH48Service")
public class SNH48ServiceImpl implements ISNH48Service {

    @Autowired
    private SNH48Mapper mapper;

    @Override
    public ServerResponse<List<Supplier>> listSupplier() {

        List<Supplier> listSupplier = mapper.listSupplier();
        if (listSupplier == null || listSupplier.size() == 0)
            return ServerResponse.createBySuccess("供应商为空",null);
        return ServerResponse.createBySuccess(listSupplier);
    }

    @Override
    public ServerResponse addSupplier(String name) {
        if (mapper.addSupplier(name) > 0)
            return ServerResponse.createBySuccess("添加供应商成功");
        return ServerResponse.createByErrorMessage("添加供应商失败");
    }

    @Override
    public ServerResponse removeSupplier(Integer id) {
        if (mapper.removeSupplier(id) > 0)
            return ServerResponse.createBySuccess();
        return ServerResponse.createByErrorMessage("删除供应商失败");
    }

    @Override
    public ServerResponse updateSupplier(Integer id, String name) {

        if (mapper.updateSupplier(id, name) > 0)
            return ServerResponse.createBySuccess();
        return ServerResponse.createByErrorMessage("修改供应商信息失败");
    }

    @Override
    public ServerResponse addStock(Stock stock) {

        try {
            if (mapper.addStock(stock) > 0)
                return ServerResponse.createBySuccessMessage("入库成功");
            return ServerResponse.createByErrorMessage("入库失败");
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage("入库失败");
        }
    }

    @Override
    public ServerResponse<List<ListStock>> listStock() {

        List<ListStock> listStock = mapper.listStock();
        if (listStock == null || listStock.size() == 0) {
            return ServerResponse.createBySuccess("库存信息为空", null);
        }
        for (ListStock stock : listStock) {
            stock.setStringShelfdate(DateUtil.formatTime(stock.getShelfdate()));
        }
        return ServerResponse.createBySuccess(listStock);
    }

    @Transactional
    @Override
    public ServerResponse<ListOrder> addOrder(ListOrderItem OrderItems) {

        ListOrder result = new ListOrder();

        //TODO 用户ID
        //生成订单
        Map map = generateOrder(1, OrderItems.getPrice());
        if (map == null) {
            return ServerResponse.createByErrorMessage("生成订单失败");
        }
        result.setOrderId((Integer)map.get("orderID"));
        result.setOrderNumber((String)map.get("orderNumber"));
        result.setPrice(OrderItems.getPrice());

        List<OrderGoods> orderGoods = new ArrayList<>();
        //检查每个商品的库存
        for (OrderItem oi : OrderItems.getItems()) {
            //找出该商品下的各个库存
            List<Stock> stocks = mapper.listStockByGoodsID(oi.getGoodsId());
            if (stocks == null || stocks.size() == 0) {
                //回滚事务
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return ServerResponse.createByErrorMessage("库存不足");
            }

            //购买的商品数量
            int num = oi.getNumber();
            //所有库存的总量
            int stockNum = 0;
            for (Stock stock : stocks) {
                stockNum += stock.getNumber();
                if (stockNum >= num) {
                    break;
                }
            }
            if (stockNum < oi.getNumber()) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return ServerResponse.createByErrorMessage("库存不足");
            }

            //将此商品存订单项目信息表
            oi.setOrderId((Integer)map.get("orderID"));
            if (mapper.insertOrderItem(oi) == 0) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return ServerResponse.createByErrorMessage("存订单项目信息表错误，下订单失败");
            }

            //开始扣除库存
            if (decrStock(stocks, oi) == false) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return ServerResponse.createByErrorMessage("扣除库存错误，下订单失败");
            }

            //获取商品名称
            String goodsName = getGoodsName(oi.getGoodsId());
            OrderGoods og = new OrderGoods(oi, goodsName);
            orderGoods.add(og);
        }
        result.setGoods(orderGoods);
      return ServerResponse.createBySuccess(result);
    }

    @Override
    public ServerResponse updateOrderStatus(Integer orderID, Integer status) {

        if (status < -1 || status > 4) {
            return ServerResponse.createByErrorMessage("订单状态码错误");
        }
        if (mapper.updateOrderStatus(orderID, status) == 0) {
            return ServerResponse.createByErrorMessage("更新订单状态失败");
        }
        return ServerResponse.createBySuccess();
    }

    //生成订单，返回订单ID和编号（此时不存地址）
    Map generateOrder(Integer userID, BigDecimal price) {

        Map map = new HashMap();
        Order order = new Order();
        String number = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        order.setNumber(number);
        order.setUserId(userID);
        //订单状态0：下订单未付款
        int status = 0;
        order.setStatus(status);
        order.setPrice(price);
        order.setTime(new Date());
        Integer result = mapper.addOrder(order);
        if (result != null) {
            map.put("orderID", order.getId());
            map.put("orderNumber", number);
            return map;
        }
        return null;
    }

    boolean decrStock(List<Stock> stocks, OrderItem orderItem) {

        //购买的商品数量
        int num = orderItem.getNumber();
        int result, n;
        //遍历此商品的所有批次的库存
        for (Stock stock : stocks) {
            n = stock.getNumber();
            if (n == 0) {
                continue;
            }
            num = n - num;
            //此批次库存不够，全部出库
            if (num < 0) {
                stock.setNumber(0);
            } else {
                stock.setNumber(num);
            }
            //更新库存
            result = mapper.updateStock(stock);
            if (result == 0) {
                return false;
            }

            //存订单项目出货信息表
            OrderItemShipment ois = new OrderItemShipment();
            if (num < 0) {
                ois.setNumber(n);
                num = -num;
            } else {
                ois.setNumber(n - num);
                num = 0;
            }
            ois.setStockId(stock.getId());
            ois.setOrderItemId(orderItem.getId());
            if (mapper.insertOrderItemShipment(ois) == 0) {
                return false;
            }
            if (num == 0) {
                break;
            }
        }
        return true;
    }

    //根据商品ID查订单所需的商品名称（名称+色号名称）
    String getGoodsName(Integer goodsID) {
        return mapper.getGoodsName(goodsID);
    }

    @Override
    public ServerResponse<List<ListOrder>> getOrders(Integer status, Integer page, Integer size) {

        //TODO 用户ID
        Integer userID = 1;
        PageHelper.startPage(page,size);
        List<Order> orders = mapper.listOrder(userID, status);
        if (orders == null || orders.size() == 0) {
            return ServerResponse.createBySuccess("订单为空", null);
        }
        List<ListOrder> listOrder = new ArrayList<>();
        //遍历每一个订单
        for (Order order : orders) {
            ListOrder lo = new ListOrder();
            Integer orderID = order.getId();
            lo.setOrderId(orderID);
            lo.setOrderNumber(order.getNumber());
            lo.setPrice(order.getPrice());

            List<OrderGoods> listOG = new ArrayList<>();
            List<OrderItem> listOrderItem = mapper.listOrderItem(orderID);
            for (OrderItem oi : listOrderItem) {
                String name = getGoodsName(oi.getGoodsId());
                OrderGoods og = new OrderGoods(oi, name);
                listOG.add(og);
            }
            lo.setGoods(listOG);
            listOrder.add(lo);
        }
        return ServerResponse.createBySuccess(listOrder);
    }

    @Override
    public ServerResponse<List<Permit>> getPermits() {

        List<Permit> listPermit = mapper.listPermit();
        if (listPermit == null || listPermit.size() == 0) {
            return ServerResponse.createBySuccess("权限表为空",null);
        }
        return ServerResponse.createBySuccess(listPermit);
    }

    @Override
    public ServerResponse updatePermit(Permit permit) {

        int result = mapper.updatePermit(permit);
        if (result == 0) {
            return ServerResponse.createByErrorMessage("更新权限失败");
        }
        return ServerResponse.createBySuccess();
    }
}
