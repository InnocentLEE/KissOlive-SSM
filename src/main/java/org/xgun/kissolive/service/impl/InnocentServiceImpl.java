package org.xgun.kissolive.service.impl;

import ch.qos.logback.core.joran.event.SaxEventRecorder;
import com.alipay.api.AlipayResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.demo.trade.config.Configs;
import com.alipay.demo.trade.model.ExtendParams;
import com.alipay.demo.trade.model.GoodsDetail;
import com.alipay.demo.trade.model.builder.AlipayTradePrecreateRequestBuilder;
import com.alipay.demo.trade.model.result.AlipayF2FPrecreateResult;
import com.alipay.demo.trade.service.AlipayTradeService;
import com.alipay.demo.trade.service.impl.AlipayTradeServiceImpl;
import com.alipay.demo.trade.utils.ZxingUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.omg.PortableServer.SERVANT_RETENTION_POLICY_ID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.xgun.kissolive.common.Const;
import org.xgun.kissolive.common.ServerResponse;
import org.xgun.kissolive.dao.InnocentMapper;
import org.xgun.kissolive.pojo.*;
import org.xgun.kissolive.service.IInnocentService;
import org.xgun.kissolive.utils.BigDecimalUtil;
import org.xgun.kissolive.utils.DateTimeUtil;
import org.xgun.kissolive.utils.FTPSSMLoad;
import org.xgun.kissolive.utils.IKAnalyzerUtil;
import org.xgun.kissolive.vo.ProductionAssess;
import org.xgun.kissolive.vo.ProductionDetails;
import org.xgun.kissolive.vo.ProductionSame;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by Lee on 2018/9/7.
 */
@Service("iInnocentService")
public class InnocentServiceImpl implements IInnocentService {

    private static AlipayTradeService tradeService;
    static {

        /** 一定要在创建AlipayTradeService之前调用Configs.init()设置默认参数
         *  Configs会读取classpath下的zfbinfo.properties文件配置信息，如果找不到该文件则确认该文件是否在classpath目录
         */
        Configs.init("zfbinfo.properties");

        /** 使用Configs提供的默认参数
         *  AlipayTradeService可以使用单例或者为静态成员对象，不需要反复new
         */
        tradeService = new AlipayTradeServiceImpl.ClientBuilder().build();
    }

    private static final Logger logger = LoggerFactory.getLogger(InnocentServiceImpl.class);




    @Autowired
    private InnocentMapper innocentMapper;

    @Override
    public ServerResponse addBrand(Brand brand) {
        boolean result = innocentMapper.insertBrand(brand) > 0;
        if (result)
            return ServerResponse.createBySuccessMessage("添加品牌成功");
        else
            return ServerResponse.createByErrorMessage("添加品牌失败");
    }

    @Override
    public ServerResponse getBrandList() {
        List<Brand> list = innocentMapper.selectBrand();
        return ServerResponse.createBySuccess("查询成功", list);
    }

    @Override
    public ServerResponse getBrandPutOn() {
        List<Brand> list = innocentMapper.selectBrandByStatus(Const.BRAND_PUT_ON_STATUS);
        return ServerResponse.createBySuccess("查询成功", list);
    }

    @Override
    public ServerResponse changeBrandPutOnStatus(Brand brand) {
        boolean result = innocentMapper.updateBrandStatusById(brand) > 0;
        if (result)
            return ServerResponse.createBySuccessMessage("修改成功");
        else
            return ServerResponse.createByErrorMessage("修改失败");
    }

    @Override
    public ServerResponse addHotspot(Hotspot hotspot) {
        boolean isExist = innocentMapper.countHotspotByDescribe(hotspot.getDescribe()) > 0;
        if (isExist)
            return ServerResponse.createByErrorMessage("添加选购热点失败，该选购热点已存在");
        innocentMapper.insertHotspot(hotspot);
        Integer id = hotspot.getId();
        if (id != null) {
            hotspot = innocentMapper.selectHotspotById(id);
            return ServerResponse.createBySuccess("添加选购热点成功", hotspot);
        } else {
            return ServerResponse.createByErrorMessage("添加选购热点失败");
        }
    }

    @Override
    public ServerResponse getHotspotList() {
        List<Hotspot> list = innocentMapper.selectHotspot();
        return ServerResponse.createBySuccess("获取选购热点成功", list);
    }

    @Override
    public ServerResponse addFunction(Function function) {
        boolean isExist = innocentMapper.countFunctionByDescribe(function.getDescribe()) > 0;
        if (isExist)
            return ServerResponse.createByErrorMessage("添加功能失败，该功能已存在");
        innocentMapper.insertFunction(function);
        Integer id = function.getId();
        if (id != null) {
            function = innocentMapper.selectFunctionById(id);
            return ServerResponse.createBySuccess("添加功能成功", function);
        } else {
            return ServerResponse.createByErrorMessage("添加功能失败");
        }
    }

    @Override
    public ServerResponse getFunctionList() {
        List<Function> list = innocentMapper.selectFunction();
        return ServerResponse.createBySuccess("获取功能成功", list);
    }

    @Override
    public ServerResponse addOrigin(Origin origin) {
        boolean isExist = innocentMapper.countOriginByDescribe(origin.getDescribe()) > 0;
        if (isExist)
            return ServerResponse.createByErrorMessage("添加产地失败，该功能已存在");
        innocentMapper.inserOrigin(origin);
        Integer id = origin.getId();
        if (id != null) {
            origin = innocentMapper.selectOriginById(id);
            return ServerResponse.createBySuccess("添加产地成功", origin);
        } else {
            return ServerResponse.createByErrorMessage("添加产地失败");
        }
    }

    @Override
    public ServerResponse getOriginList() {
        List<Origin> list = innocentMapper.selectOrigin();
        return ServerResponse.createBySuccess("获取产地成功", list);
    }

    @Override
    public ServerResponse addMarketTime(MarketTime marketTime) {
        boolean isExist = innocentMapper.countMarketTimeByDescribe(marketTime.getDescribe()) > 0;
        if (isExist)
            return ServerResponse.createByErrorMessage("添加上市时间失败，该上市时间已存在");
        innocentMapper.insertMarketTime(marketTime);
        Integer id = marketTime.getId();
        if (id != null) {
            marketTime = innocentMapper.selectMarketTimeById(id);
            return ServerResponse.createBySuccess("添加上市时间成功", marketTime);
        } else {
            return ServerResponse.createByErrorMessage("添加上市时间失败");
        }
    }

    @Override
    public ServerResponse getMarketTimeList() {
        List<MarketTime> list = innocentMapper.selectMarketTime();
        return ServerResponse.createBySuccess("获取上市时间成功", list);
    }

    @Override
    public ServerResponse addSkin(Skin skin) {
        boolean isExist = innocentMapper.countSkinByDescribe(skin.getDescribe()) > 0;
        if (isExist)
            return ServerResponse.createByErrorMessage("添加适用肤质失败，该适用肤质已存在");
        innocentMapper.insertSkin(skin);
        Integer id = skin.getId();
        if (id != null) {
            skin = innocentMapper.selectSkinById(id);
            return ServerResponse.createBySuccess("添加适用肤质成功", skin);
        } else {
            return ServerResponse.createByErrorMessage("添加适用肤质失败");
        }
    }

    @Override
    public ServerResponse getSkinList() {
        List<Skin> list = innocentMapper.selectSkin();
        return ServerResponse.createBySuccess("获取适用肤质成功", list);
    }

    @Override
    @Transactional
    public ServerResponse addProduction(Production production, List<ProductionHotspot> productionHotspots, List<ProductionFunction> productionFunctions, List<ProductionSkin> productionSkins) {
        String brandName = innocentMapper.selectBrandById(production.getBrandId()).getName();
        String originDescribe = innocentMapper.selectOriginById(production.getOriginId()).getDescribe();
        String marketTimeDescribe = innocentMapper.selectMarketTimeById(production.getMarketTimeId()).getDescribe();
        String hotspotDescribe = "";
        String functionDescribe = "";
        String skinDescribe = "";
        for (int i = 0; i < productionHotspots.size(); i++) {
            hotspotDescribe += innocentMapper.selectHotspotById(productionHotspots.get(i).getHotspotId()).getDescribe() + " ";
        }
        for (int i = 0; i < productionFunctions.size(); i++) {
            functionDescribe += innocentMapper.selectFunctionById(productionFunctions.get(i).getFunctionId()).getDescribe() + " ";
        }
        for (int i = 0; i < productionSkins.size(); i++) {
            skinDescribe += innocentMapper.selectSkinById(productionSkins.get(i).getSkinId()).getDescribe() + " ";
        }
        String search = production.getName() + "" + brandName + " " + originDescribe + " " + marketTimeDescribe + " " + hotspotDescribe + functionDescribe + skinDescribe;
        production.setSearch(search);
        innocentMapper.insertProduction(production);
        Integer id = production.getId();
        if (id == null)
            return ServerResponse.createByErrorMessage("添加产品失败");
        for (int i = 0; i < productionHotspots.size(); i++) {
            productionHotspots.get(i).setProductionId(id);
        }
        for (int i = 0; i < productionFunctions.size(); i++) {
            productionFunctions.get(i).setProductionId(id);
        }
        for (int i = 0; i < productionSkins.size(); i++) {
            productionSkins.get(i).setProductionId(id);
        }
        innocentMapper.insertProductionHotspot(productionHotspots);
        innocentMapper.insertProductionFunction(productionFunctions);
        innocentMapper.insertProductionSkin(productionSkins);
        // 判断是否回滚，是则说明插入失败
        if (TransactionAspectSupport.currentTransactionStatus().isRollbackOnly())
            return ServerResponse.createByErrorMessage("添加产品失败,添加过程出错");
        return ServerResponse.createBySuccessMessage("添加产品成功");
    }

    @Override
    public ServerResponse addGoods(Goods goods) {
        innocentMapper.insertGoods(goods);
        Integer id = goods.getId();
        if (id == null)
            return ServerResponse.createByErrorMessage("添加商品失败");
        goods = innocentMapper.selectGoodsById(id);
        return ServerResponse.createBySuccess("添加商品成功", goods);
    }

    @Override
    public ServerResponse editBrand(Brand brand) {
        boolean result = innocentMapper.updateBrandByIdSelective(brand) > 0;
        if (result)
            return ServerResponse.createBySuccessMessage("编辑品牌成功");
        else
            return ServerResponse.createByErrorMessage("编辑品牌失败");
    }

    @Override
    @Transactional
    public ServerResponse getProductions(int page, int pageNum) {
        int totalNum = innocentMapper.countProduction();
        int totalPage = (int) (totalNum + pageNum - 1) / pageNum;
        if (totalPage < page)
            page = totalPage;
        int index = (page - 1) * pageNum;
        List<Production> productionList = innocentMapper.selectProduction(index, pageNum);
        List<ProductionDetails> productionDetailsList = Lists.newArrayList();
        int productionSize = productionList.size();
        for (int i = 0; i < productionSize; i++) {
            ProductionDetails productionDetails = new ProductionDetails();
            productionDetails.setId(productionList.get(i).getId());
            productionDetails.setName(productionList.get(i).getName());
            productionDetails.setDescription(productionList.get(i).getDescription());
            productionDetails.setDetail(productionList.get(i).getDetail());
            productionDetails.setImgUrl(productionList.get(i).getImgUrl());
            Brand brand = innocentMapper.selectBrandById(productionList.get(i).getBrandId());
            productionDetails.setBrand(brand);
            Origin origin = innocentMapper.selectOriginById(productionList.get(i).getOriginId());
            productionDetails.setOrigin(origin);
            MarketTime marketTime = innocentMapper.selectMarketTimeById(productionList.get(i).getMarketTimeId());
            productionDetails.setMarketTime(marketTime);
            List<Hotspot> hotspots = innocentMapper.selectHotspotByProduction(productionList.get(i).getId());
            productionDetails.setHotspots(hotspots);
            List<Function> functions = innocentMapper.selectFunctionByProduction(productionList.get(i).getId());
            productionDetails.setFunctions(functions);
            List<Skin> skins = innocentMapper.selectSkinByProduction(productionList.get(i).getId());
            productionDetails.setSkins(skins);
            List<Goods> goodses = innocentMapper.selectGoodsByProduction(productionList.get(i).getId());
            productionDetails.setGoodses(goodses);
            productionDetailsList.add(productionDetails);
        }
        Map resultMap = Maps.newHashMap();
        resultMap.put("total_page", totalPage);
        resultMap.put("page", page);
        resultMap.put("page_num", pageNum);
        resultMap.put("production_details_list", productionDetailsList);
        if (TransactionAspectSupport.currentTransactionStatus().isRollbackOnly())
            return ServerResponse.createByErrorMessage("查找过程出错");
        return ServerResponse.createBySuccess("查找成功", resultMap);
    }

    @Override
    @Transactional
    public ServerResponse getProductionsByBrand(Integer[] brandIds) {
        List<ProductionDetails> productionDetailsList = Lists.newArrayList();
        for (int brandId : brandIds) {
            List<Production> productionList = innocentMapper.selectProductionByBrand(brandId);
            Brand brand = innocentMapper.selectBrandById(brandId);
            int productionSize = productionList.size();
            for (int i = 0; i < productionSize; i++) {
                ProductionDetails productionDetails = new ProductionDetails();
                productionDetails.setId(productionList.get(i).getId());
                productionDetails.setName(productionList.get(i).getName());
                productionDetails.setDescription(productionList.get(i).getDescription());
                productionDetails.setDetail(productionList.get(i).getDetail());
                productionDetails.setImgUrl(productionList.get(i).getImgUrl());
                productionDetails.setBrand(brand);
                Origin origin = innocentMapper.selectOriginById(productionList.get(i).getOriginId());
                productionDetails.setOrigin(origin);
                MarketTime marketTime = innocentMapper.selectMarketTimeById(productionList.get(i).getMarketTimeId());
                productionDetails.setMarketTime(marketTime);
                List<Hotspot> hotspots = innocentMapper.selectHotspotByProduction(productionList.get(i).getId());
                productionDetails.setHotspots(hotspots);
                List<Function> functions = innocentMapper.selectFunctionByProduction(productionList.get(i).getId());
                productionDetails.setFunctions(functions);
                List<Skin> skins = innocentMapper.selectSkinByProduction(productionList.get(i).getId());
                productionDetails.setSkins(skins);
                List<Goods> goodses = innocentMapper.selectGoodsByProduction(productionList.get(i).getId());
                productionDetails.setGoodses(goodses);
                productionDetailsList.add(productionDetails);
            }
        }
        if (TransactionAspectSupport.currentTransactionStatus().isRollbackOnly())
            return ServerResponse.createByErrorMessage("查找过程出错");
        return ServerResponse.createBySuccess("查找成功", productionDetailsList);
    }

    @Override
    @Transactional
    public ServerResponse searchProductions(String search) {
        List<ProductionSame> productionSameList = innocentMapper.selectProductionSearch();
        int productionSameListSize = productionSameList.size();
        for (int i = 0; i < productionSameListSize; i++) {
            productionSameList.get(i).setSame(IKAnalyzerUtil.Compare(search, productionSameList.get(i).getSearch()));
        }
        Collections.sort(productionSameList);
        List<ProductionDetails> productionDetailsList = Lists.newArrayList();
        for (int i = 0; i < productionSameListSize; i++) {
            if(productionSameList.get(i).getSame()>0.600){
                Production production = innocentMapper.selectProductionById(productionSameList.get(i).getId());
                ProductionDetails productionDetails = new ProductionDetails();
                productionDetails.setId(production.getId());
                productionDetails.setName(production.getName());
                productionDetails.setDescription(production.getDescription());
                productionDetails.setDetail(production.getDetail());
                productionDetails.setImgUrl(production.getImgUrl());
                productionDetails.setBrand(innocentMapper.selectBrandById(production.getBrandId()));
                Origin origin = innocentMapper.selectOriginById(production.getOriginId());
                productionDetails.setOrigin(origin);
                MarketTime marketTime = innocentMapper.selectMarketTimeById(production.getMarketTimeId());
                productionDetails.setMarketTime(marketTime);
                List<Hotspot> hotspots = innocentMapper.selectHotspotByProduction(production.getId());
                productionDetails.setHotspots(hotspots);
                List<Function> functions = innocentMapper.selectFunctionByProduction(production.getId());
                productionDetails.setFunctions(functions);
                List<Skin> skins = innocentMapper.selectSkinByProduction(production.getId());
                productionDetails.setSkins(skins);
                List<Goods> goodses = innocentMapper.selectGoodsByProduction(production.getId());
                productionDetails.setGoodses(goodses);
                productionDetailsList.add(productionDetails);
            }
        }
        if (TransactionAspectSupport.currentTransactionStatus().isRollbackOnly())
            return ServerResponse.createByErrorMessage("查找过程出错");
        return ServerResponse.createBySuccess("查找成功", productionDetailsList);
    }


    @Override
    @Transactional
    public ServerResponse getProductionShow(int id) {
        Production production = innocentMapper.selectProductionById(id);
        ProductionDetails productionDetails = new ProductionDetails();
        productionDetails.setId(production.getId());
        productionDetails.setName(production.getName());
        productionDetails.setDescription(production.getDescription());
        productionDetails.setDetail(production.getDetail());
        productionDetails.setImgUrl(production.getImgUrl());
        productionDetails.setBrand(innocentMapper.selectBrandById(production.getBrandId()));
        Origin origin = innocentMapper.selectOriginById(production.getOriginId());
        productionDetails.setOrigin(origin);
        MarketTime marketTime = innocentMapper.selectMarketTimeById(production.getMarketTimeId());
        productionDetails.setMarketTime(marketTime);
        List<Hotspot> hotspots = innocentMapper.selectHotspotByProduction(production.getId());
        productionDetails.setHotspots(hotspots);
        List<Function> functions = innocentMapper.selectFunctionByProduction(production.getId());
        productionDetails.setFunctions(functions);
        List<Skin> skins = innocentMapper.selectSkinByProduction(production.getId());
        productionDetails.setSkins(skins);
        List<Goods> goodses = innocentMapper.selectGoodsByProduction(production.getId());
        productionDetails.setGoodses(goodses);
        if (TransactionAspectSupport.currentTransactionStatus().isRollbackOnly())
            return ServerResponse.createByErrorMessage("查找过程出错");
        return ServerResponse.createBySuccess("查找成功", productionDetails);
    }

    @Override
    @Transactional
    public ServerResponse getProductionAssess(int id) {
        List<Assess> assessList = innocentMapper.selectAssessByProduction(id);
        List<ProductionAssess> productionAssessList = Lists.newArrayList();
        int assessListSize = assessList.size();
        for (int i = 0; i < assessListSize; i++) {
            ProductionAssess productionAssess = new ProductionAssess();
            productionAssess.setId(assessList.get(i).getId());
            productionAssess.setProductionId(id);
            productionAssess.setContent(assessList.get(i).getContent());
            productionAssess.setUpdatetime(assessList.get(i).getUpdatetime());
            productionAssess.setUser(innocentMapper.selectUserNameAndImgById(assessList.get(i).getUserId()));
            productionAssess.setGoods(innocentMapper.selectGoodsById(assessList.get(i).getGoodsId()));
            productionAssessList.add(productionAssess);
        }
        if (TransactionAspectSupport.currentTransactionStatus().isRollbackOnly())
            return ServerResponse.createByErrorMessage("查找过程出错");
        return ServerResponse.createBySuccess("查找成功", productionAssessList);
    }

    @Override
    @Transactional
    public ServerResponse editProduction(Production production, List<ProductionHotspot> productionHotspots, List<ProductionFunction> productionFunctions, List<ProductionSkin> productionSkins) {
        String brandName = innocentMapper.selectBrandById(production.getBrandId()).getName();
        String originDescribe = innocentMapper.selectOriginById(production.getOriginId()).getDescribe();
        String marketTimeDescribe = innocentMapper.selectMarketTimeById(production.getMarketTimeId()).getDescribe();
        String hotspotDescribe = "";
        String functionDescribe = "";
        String skinDescribe = "";
        for (int i = 0; i < productionHotspots.size(); i++) {
            hotspotDescribe += innocentMapper.selectHotspotById(productionHotspots.get(i).getHotspotId()).getDescribe() + " ";
        }
        for (int i = 0; i < productionFunctions.size(); i++) {
            functionDescribe += innocentMapper.selectFunctionById(productionFunctions.get(i).getFunctionId()).getDescribe() + " ";
        }
        for (int i = 0; i < productionSkins.size(); i++) {
            skinDescribe += innocentMapper.selectSkinById(productionSkins.get(i).getSkinId()).getDescribe() + " ";
        }
        String search = production.getName() + "" + brandName + " " + originDescribe + " " + marketTimeDescribe + " " + hotspotDescribe + functionDescribe + skinDescribe;
        production.setSearch(search);
        innocentMapper.updateProductionById(production);
        innocentMapper.deleteProductionHotspot(production.getId());
        innocentMapper.deleteProductionFunction(production.getId());
        innocentMapper.deleteProductionSkin(production.getId());
        innocentMapper.insertProductionHotspot(productionHotspots);
        innocentMapper.insertProductionFunction(productionFunctions);
        innocentMapper.insertProductionSkin(productionSkins);
        // 判断是否回滚，是则说明编辑失败
        if (TransactionAspectSupport.currentTransactionStatus().isRollbackOnly())
            return ServerResponse.createByErrorMessage("编辑产品失败,过程出错");
        return ServerResponse.createBySuccessMessage("编辑产品成功");
    }

    @Override
    @Transactional
    public ServerResponse goodsPutOff(int id){
        innocentMapper.updateGoodsStatus(id,0);
        if (TransactionAspectSupport.currentTransactionStatus().isRollbackOnly())
            return ServerResponse.createByErrorMessage("下架失败,过程出错");
        return ServerResponse.createBySuccessMessage("下架成功");
    }

    @Override
    @Transactional
    public ServerResponse goodsPutOn(int id){
        innocentMapper.updateGoodsStatus(id,1);
        if (TransactionAspectSupport.currentTransactionStatus().isRollbackOnly())
            return ServerResponse.createByErrorMessage("上架失败,过程出错");
        return ServerResponse.createBySuccessMessage("上架成功");
    }

    @Override
    public ServerResponse editGoods(Goods goods){
        int result = innocentMapper.updateGoods(goods);
        if(result>0)
            return ServerResponse.createBySuccessMessage("修改成功");
        return ServerResponse.createByErrorMessage("修改失败");
    }

    @Override
    public ServerResponse pay(Integer orderId, Integer userId, String path){
        Map<String ,String> resultMap = Maps.newHashMap();
        Order order = innocentMapper.selectOrderByUserAndId(userId,orderId);
        System.out.println(order);
        if(order == null){
            return ServerResponse.createByErrorMessage("用户没有该订单");
        }
        resultMap.put("orderNo",String.valueOf(order.getId()));

        // (必填) 商户网站订单系统中唯一订单号，64个字符以内，只能包含字母、数字、下划线，
        // 需保证商户系统端不能重复，建议通过数据库sequence生成，
        String outTradeNo = order.getId().toString();


        // (必填) 订单标题，粗略描述用户的支付目的。如“xxx品牌xxx门店当面付扫码消费”
        String subject = "Kiss Olivek口红热卖商城扫码支付,订单号:"+outTradeNo;


        // (必填) 订单总金额，单位为元，不能超过1亿元
        // 如果同时传入了【打折金额】,【不可打折金额】,【订单总金额】三者,则必须满足如下条件:【订单总金额】=【打折金额】+【不可打折金额】
        String totalAmount = order.getPrice().toString();
        logger.info("============totalAmount"+totalAmount);


        // (可选) 订单不可打折金额，可以配合商家平台配置折扣活动，如果酒水不参与打折，则将对应金额填写至此字段
        // 如果该值未传入,但传入了【订单总金额】,【打折金额】,则该值默认为【订单总金额】-【打折金额】
        String undiscountableAmount = "0";



        // 卖家支付宝账号ID，用于支持一个签约账号下支持打款到不同的收款账号，(打款到sellerId对应的支付宝账号)
        // 如果该字段为空，则默认为与支付宝签约的商户的PID，也就是appid对应的PID
        String sellerId = "";

        // 订单描述，可以对交易或商品进行一个详细地描述，比如填写"购买商品2件共15.00元"
        String body = new StringBuilder().append("订单").append(outTradeNo).append("购买商品共").append(totalAmount).append("元").toString();


        // 商户操作员编号，添加此参数可以为商户操作员做销售统计
        String operatorId = "test_operator_id";

        // (必填) 商户门店编号，通过门店号和商家后台可以配置精准到门店的折扣信息，详询支付宝技术支持
        String storeId = "test_store_id";

        // 业务扩展参数，目前可添加由支付宝分配的系统商编号(通过setSysServiceProviderId方法)，详情请咨询支付宝技术支持
        ExtendParams extendParams = new ExtendParams();
        extendParams.setSysServiceProviderId("2088100200300400500");




        // 支付超时，定义为120分钟
        String timeoutExpress = "120m";

        // 商品明细列表，需填写购买商品详细信息，
        List<GoodsDetail> goodsDetailList = new ArrayList<GoodsDetail>();

        List<OrderItem> orderItemList = innocentMapper.selectOrderItemByOrderId(orderId);
        for(OrderItem orderItem : orderItemList){
            Goods kogoods = innocentMapper.selectGoodsById(orderItem.getGoodsId());
            Production production = innocentMapper.selectProductionById(kogoods.getProductionId());
            GoodsDetail goods = GoodsDetail.newInstance(orderItem.getGoodsId().toString(), production.getName()+":"+kogoods.getColorName(),
                    Long.valueOf(BigDecimalUtil.mul(orderItem.getPrice().doubleValue(),new Double(100).doubleValue()).setScale(0, BigDecimal.ROUND_HALF_UP).toString()),
                    orderItem.getNumber());
            goodsDetailList.add(goods);
        }

        // 创建扫码支付请求builder，设置请求参数
        AlipayTradePrecreateRequestBuilder builder = new AlipayTradePrecreateRequestBuilder()
                .setSubject(subject).setTotalAmount(totalAmount).setOutTradeNo(outTradeNo)
                .setUndiscountableAmount(undiscountableAmount).setSellerId(sellerId).setBody(body)
                .setOperatorId(operatorId).setStoreId(storeId).setExtendParams(extendParams)
                .setTimeoutExpress(timeoutExpress)
                // TODO: 18-10-15 支付宝回调地址 
                .setNotifyUrl(Const.ALIPAY_CALLBACK)//支付宝服务器主动通知商户服务器里指定的页面http路径,根据需要设置
                .setGoodsDetailList(goodsDetailList);


        AlipayF2FPrecreateResult result = tradeService.tradePrecreate(builder);
        switch (result.getTradeStatus()) {
            case SUCCESS:
                logger.info("支付宝预下单成功: )");

                AlipayTradePrecreateResponse response = result.getResponse();
                dumpResponse(response);

                File folder = new File(path);
                if(!folder.exists()){
                    folder.setWritable(true);
                    folder.mkdirs();
                }

                // 需要修改为运行机器上的路径
                //细节细节细节
                String qrPath = String.format(path+"/qr-%s.png",response.getOutTradeNo());
                String qrFileName = String.format("qr-%s.png",response.getOutTradeNo());
                ZxingUtils.getQRCodeImge(response.getQrCode(), 256, qrPath);

                File targetFile = new File(path,qrFileName);
                    // FTPUtil.uploadFile(Const.FILE_SAVE_PATH,Lists.newArrayList(targetFile));
                Map map = FTPSSMLoad.uploadQr(Const.FILE_SAVE_PATH,targetFile);
                if(!(boolean)map.get("result")){
                    logger.error("上传二维码异常");
                }
                String qrUrl = map.get("http_url").toString();
                logger.info("qrUrl:" + qrUrl);
                logger.info("qrPath:" + qrPath);
                resultMap.put("qrUrl",qrUrl);
                return ServerResponse.createBySuccess(resultMap);
            case FAILED:
                logger.error("支付宝预下单失败!!!");
                return ServerResponse.createByErrorMessage("支付宝预下单失败!!!");

            case UNKNOWN:
                logger.error("系统异常，预下单状态未知!!!");
                return ServerResponse.createByErrorMessage("系统异常，预下单状态未知!!!");

            default:
                logger.error("不支持的交易状态，交易返回异常!!!");
                return ServerResponse.createByErrorMessage("不支持的交易状态，交易返回异常!!!");
        }
    }

    // 简单打印应答
    private void dumpResponse(AlipayResponse response) {
        if (response != null) {
            logger.info(String.format("code:%s, msg:%s", response.getCode(), response.getMsg()));
            logger.info("");
            if (StringUtils.isNotEmpty(response.getSubCode())) {
                logger.info(String.format("subCode:%s, subMsg:%s", response.getSubCode(), response.getSubMsg()));
            }
            logger.info("body:" + response.getBody());
        }
    }

    @Override
    public ServerResponse aliCallback(Map<String,String> params){
        Integer orderId = Integer.parseInt(params.get("out_trade_no"));
        String tradeNo = params.get("trade_no");
        String tradeStatus = params.get("trade_status");
        Order order = innocentMapper.selectOrderById(orderId);
        if(order == null){
            return ServerResponse.createByErrorMessage("非KissOLive口红热卖商城的订单,回调忽略");
        }
        if(order.getStatus() >= Const.OrderStatusEnum.PAID.getCode()){
            return ServerResponse.createBySuccess("支付宝重复调用");
        }
        if(Const.AlipayCallback.TRADE_STATUS_TRADE_SUCCESS.equals(tradeStatus)){
            order.setTime(DateTimeUtil.strToDate(params.get("gmt_payment")));
            order.setStatus(Const.OrderStatusEnum.PAID.getCode());
            innocentMapper.updateOrderPayStatusById(order);
        }
/*
        PayInfo payInfo = new PayInfo();
        payInfo.setUserId(order.getUserId());
        payInfo.setOrderNo(order.getOrderNo());
        payInfo.setPayPlatform(Const.PayPlatformEnum.ALIPAY.getCode());
        payInfo.setPlatformNumber(tradeNo);
        payInfo.setPlatformStatus(tradeStatus);

        payInfoMapper.insert(payInfo);
*/
        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse queryOrderPayStatus(Integer userId,Integer orderId){
        Order order = innocentMapper.selectOrderByUserAndId(userId,orderId);
        if(order == null){
            logger.info("用户没有该订单");
            return ServerResponse.createByErrorMessage("用户没有该订单");
        }
        if(order.getStatus() >= Const.OrderStatusEnum.PAID.getCode()){
            logger.info("该订单已经支付");
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByError();
    }
}
