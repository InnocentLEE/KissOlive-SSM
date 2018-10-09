package org.xgun.kissolive.service.impl;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.xgun.kissolive.common.ServerResponse;
import org.xgun.kissolive.dao.SilentMapper;
import org.xgun.kissolive.pojo.Activity;
import org.xgun.kissolive.pojo.ActivityGoods;
import org.xgun.kissolive.pojo.Card;
import org.xgun.kissolive.service.ISilentService;
import org.xgun.kissolive.vo.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by GvG on 2018/9/10.
 */
@Service("iSilentService")
public class SilentServiceImpl implements ISilentService {

    @Autowired
    private SilentMapper silentMapper;

    @Override
    @Transactional
    public ServerResponse addACard(Card card) {
        //判断该商品是否已在购物车中
        Integer checkNum = silentMapper.checkCard(card);
        boolean result = false;
        if(checkNum != null) {
            //若购物车中存在商品，添加或减少数量
            //判断数量是否合法
            if(checkNum+card.getNum()>0) {
                result = silentMapper.updateCard(card) > 0;
            }
        }else {
            //若没有，则新添商品及数量
            //数量需要大于零
            if(card.getNum()>0) {
                result = silentMapper.insertNewCard(card) > 0;
            }
        }
        if(result) {
            return ServerResponse.createBySuccessMessage("添加购物车成功");
        }else {
            return ServerResponse.createByErrorMessage("添加购物车失败");
        }
    }

    @Override
    public ServerResponse getMyCard() {
        //获取当前用户userId
        Integer userId = 1;//测试用
        List<CardInfo> cardList = silentMapper.selectMyCard(userId);
        if(CollectionUtils.isEmpty(cardList)) {
            return ServerResponse.createBySuccess("购物车空空如也~",null);
        }else {
            return ServerResponse.createBySuccess("获取购物车成功",cardList);
        }
    }

    @Override
    public ServerResponse deleteCardByBatch(int[] cardIds) {
        //获取当前用户userId
        Integer userId = 1;//测试用
        boolean result = silentMapper.deleteCardByBatch(cardIds,userId)>0;
        if(result) {
            return ServerResponse.createBySuccessMessage("购物车删除成功");
        }else {
            return ServerResponse.createBySuccessMessage("购物车删除失败");
        }
    }

    @Transactional
    @Override
    public ServerResponse getActivityMenu(int page, int num) {
        if (page <= 0) {
            page = 1;
        }
        if (num <= 0) {
            num = 5;
        }
        //总记录数
        int totalNum = silentMapper.selectActivityCount();
        //总页数
        int totalPage = totalNum/num;
        if(totalNum%num != 0){
            totalPage++;
        }
        //获取活动列表信息
        List<ActivityMenuInfo> list = silentMapper.selectActivityMenu((page-1)*num,num);
        Map MenuMap = Maps.newHashMap();
        MenuMap.put("nowPage", page);
        MenuMap.put("showNum", num);
        MenuMap.put("totalNum", totalNum);
        MenuMap.put("totalPage", totalPage);
        MenuMap.put("ListInfo",list);

        return ServerResponse.createBySuccess(MenuMap);
    }

    @Transactional
    @Override
    public ServerResponse insertActivity(Activity activity, int[] vipLevel, int[] goods, BigDecimal[] price) {
        //活动表insert
        boolean result = false;
        result = silentMapper.insertActivity(activity)>0;
        if(!result) {
            return ServerResponse.createByErrorMessage("创建活动失败");
        }else {
            result = false;
        }

        int activityId = activity.getId();
        //活动对应会员等级表insert
        result = silentMapper.insertActivityLevel(activityId,vipLevel)>0;
        if(result || goods.length != 0) {
            List<ActivityGoods> list = new ArrayList<ActivityGoods>();
            for( int i = 0 ; i < goods.length && i < price.length ; i++ ){
                ActivityGoods activityGoods = new ActivityGoods();
                activityGoods.setGoodsId(goods[i]);
                activityGoods.setPrice(price[i]);
                list.add(activityGoods);
            }
            result = silentMapper.insertActivityGoods(activityId,list)>0;
        }
        if(result){
            return ServerResponse.createBySuccessMessage("新添加活动成功");
        }else{
            throw new RuntimeException("添加活动失败");
        }
    }

    @Override
    public ServerResponse deleteActivityByBatch(int[] activityIds) {
        //删除活动信息
        boolean result = silentMapper.deleteActivity(activityIds)>0;
        if(result) {
            return ServerResponse.createBySuccessMessage("活动删除成功");
        }else {
            return ServerResponse.createBySuccessMessage("活动删除失败");
        }
    }

    @Override
    public ServerResponse getActivityInfo(Integer activityId) {
        Activity activity = silentMapper.selectActivity(activityId);
        if(activity != null){
            return ServerResponse.createBySuccess("查询活动成功",activity);
        }
        return ServerResponse.createByErrorMessage("查询失败");
    }

    @Override
    public ServerResponse getActivityGoodsInfo(Integer activityId) {
        List<ActivityGoodsInfo> list = silentMapper.selectActivityGoodsInfo(activityId);
        if(list == null){
            return ServerResponse.createByErrorMessage("获取失败");
        }else if(list.size()==0){
            return ServerResponse.createBySuccess("该活动无商品优惠信息！",list);
        }
        return ServerResponse.createBySuccess("获取成功", list);
    }

    @Override
    public ServerResponse updateActivityInfo(Activity activity) {
        boolean result = silentMapper.updateActivity(activity)>0;
        if(result) {
            return ServerResponse.createBySuccessMessage("修改活动信息成功");
        }else{
            return ServerResponse.createByErrorMessage("修改失败");
        }
    }

    @Transactional
    @Override
    public ServerResponse updateActivityLevel(Integer activityId, int[] vipIds) {
        //先删除之前信息
        int[] activityIds = {activityId};
        silentMapper.deleteActivityLevel(activityIds);
        boolean result = silentMapper.insertActivityLevel(activityId,vipIds)>0;
        if(result) {
            return ServerResponse.createBySuccessMessage("修改活动对应会员等级信息成功");
        }else{
            return ServerResponse.createByErrorMessage("修改失败");
        }
    }

    @Transactional
    @Override
    public ServerResponse updateActivityGoods(Integer activityId, int[] goods, BigDecimal[] price) {
        //删除之前信息
        int[]activityIds = {activityId};
        silentMapper.deleteActivityGoods(activityIds);

        List<ActivityGoods> list = new ArrayList<ActivityGoods>();
        for(int i = 0 ; i < goods.length ; i++) {
            ActivityGoods activityGoods = new ActivityGoods(activityId,goods[i],price[i],null);
            list.add(activityGoods);
        }
        boolean result = silentMapper.insertActivityGoods(activityId,list)>0;
        if(result) {
            return ServerResponse.createBySuccessMessage("修改活动商品优惠信息成功");
        }else{
            return ServerResponse.createByErrorMessage("修改失败");
        }
    }

    @Override
    public ServerResponse getAllSalesByMonthOneYear(Integer year) {
        //获取数据列表
        List<SalesCountByMonth> list = silentMapper.selectSalesByMonth(year);
        //存储完整数据列表
        List<SalesCountByMonth> newlist = new ArrayList<SalesCountByMonth>();
        if(list == null){
            return ServerResponse.createByErrorMessage("获取失败");
        }else if(list.size() < 12){
            int i = 1;
            //遍历存储，若没有相应月份数据，则在新列表中添加该月份数据
            for(SalesCountByMonth sales : list) {
                String month = "";
                if(i<10)
                    month = "0"+i;
                else if(i>=10&&i<=12)
                    month = ""+i;
                else
                    break;
                while(!sales.getMonth().equals(month)){
                    newlist.add(new SalesCountByMonth(month,new BigDecimal(0)));
                    i++;
                    if(i<10)
                        month = "0"+i;
                    else if(i>=10&&i<=12)
                        month = ""+i;
                    else
                        break;
                }
                newlist.add(sales);

                i++;
                if(i>12){
                    break;
                }
            }
            i = newlist.size()+1;
            while(i<=12){
                String month = "";
                if(i<10)
                    month = "0"+i;
                else if(i>=10&&i<=12)
                    month = ""+i;
                else
                    break;
                newlist.add(new SalesCountByMonth(month,new BigDecimal(0)));
                i++;
            }
        }

        return ServerResponse.createBySuccess("获取数据成功",newlist);
    }

    @Override
    public ServerResponse getBrandShopInfo(String year, String month) {
        List<BrandProductionShopInfo> list = silentMapper.selectBrandShopInfo(year,month);
        if(list == null){
            return ServerResponse.createByErrorMessage("获取失败");
        }else if (list.size()==0){
            return ServerResponse.createBySuccessMessage("数据为空");
        }
        return ServerResponse.createBySuccess("获取数据成功",list);
    }

    @Override
    public ServerResponse getProductShopTrend(Integer productionId, Integer year) {
        //获取数据列表
        List<ShopCountByMonth> list = silentMapper.selectShopInfoByPOneYear(productionId,year);
        //存储完整数据列表
        List<ShopCountByMonth> newlist = new ArrayList<ShopCountByMonth>();
        if(list == null){
            return ServerResponse.createByErrorMessage("获取失败");
        }else if(list.size() < 12){
            int i = 1;
            //遍历存储，若没有相应月份数据，则在新列表中添加该月份数据
            for(ShopCountByMonth count : list) {
                String month = "";
                if(i<10)
                    month = "0"+i;
                else if(i>=10&&i<=12)
                    month = ""+i;
                else
                    break;
                while(!count.getMonth().equals(month)){
                    newlist.add(new ShopCountByMonth(month,0));
                    i++;
                    if(i<10)
                        month = "0"+i;
                    else if(i>=10&&i<=12)
                        month = ""+i;
                    else
                        break;
                }
                newlist.add(count);

                i++;
                if(i>12){
                    break;
                }
            }
            i = newlist.size()+1;
            while(i<=12){
                String month = "";
                if(i<10)
                    month = "0"+i;
                else if(i>=10&&i<=12)
                    month = ""+i;
                else
                    break;
                newlist.add(new ShopCountByMonth(month,0));
                i++;
            }
        }

        return ServerResponse.createBySuccess("获取数据成功",newlist);
    }

    @Override
    public ServerResponse getProductionShopRank(Integer num) {
        List<ProductionNum> list = silentMapper.selectPShopByRank(num);
        if(list == null){
            return  ServerResponse.createByErrorMessage("获取失败");
        }
        if(list.size() == 0){
            return  ServerResponse.createBySuccessMessage("数据为空");
        }
        return ServerResponse.createBySuccess("获取成功",list);
    }

    @Override
    public ServerResponse getProductionCardRank(Integer num) {
        List<ProductionNum> list = silentMapper.selectPCardByRank(num);
        if(list == null){
            return  ServerResponse.createByErrorMessage("获取失败");
        }
        if(list.size() == 0){
            return  ServerResponse.createBySuccessMessage("数据为空");
        }
        return ServerResponse.createBySuccess("获取成功",list);
    }

    @Override
    public ServerResponse getProductionBrowseRank(Integer num) {
        List<ProductionNum> list = silentMapper.selectPBrowseByRank(num);
        if(list == null){
            return  ServerResponse.createByErrorMessage("获取失败");
        }
        if(list.size() == 0){
            return  ServerResponse.createBySuccessMessage("数据为空");
        }
        return ServerResponse.createBySuccess("获取成功",list);
    }
}
