package org.xgun.kissolive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xgun.kissolive.common.ServerResponse;
import org.xgun.kissolive.pojo.Activity;
import org.xgun.kissolive.pojo.Card;
import org.xgun.kissolive.service.ISilentService;
import org.xgun.kissolive.utils.FTPSSMLoad;
import org.xgun.kissolive.vo.CardInfo;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by GvG on 2018/9/10.
 */
@Controller
public class SilentController {

    @Autowired
    private ISilentService iSilentService;

    /*购物车模块*/

    /**
     * 将商品添加入购物车 或者 对已存在于购物车的商品改变数量
     * @param goodsId
     * @param num
     * @return
     */
    @RequestMapping(value = "/shoppingCart/add_card.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addCard(@RequestParam("goodsId")Integer goodsId,
                                  @RequestParam("num")Integer num){
        //获取当前用户userId
        Integer userId = 1;//测试用
        Card card = new Card(null,userId,goodsId,num,null);
        System.out.println(card.toString());
        return iSilentService.addACard(card);
    }

    /**
     * 获取当前用户的我的购物车页面信息
     * @return
     */
    @RequestMapping(value = "/shoppingCart/get_myCard.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getMyShoppingCart(){
        return iSilentService.getMyCard();
    }

    /**
     * 根据购物车id号来删除对应商品，批量删除
     * @param cardIds
     * @return
     */
    @RequestMapping(value = "/shoppingCart/delete_card_ByBatch.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse deleteCard(@RequestParam("cardIds")int[] cardIds){
        if( cardIds==null || cardIds.length==0 ) {
            return ServerResponse.createByErrorMessage("删除失败");
        }
        return iSilentService.deleteCardByBatch(cardIds);
    }


    /*活动模块*/

    /**
     * 新添加活动，上传海报图，vip等级限制，商品优惠
     * @param request
     * @param title:活动标题
     * @param img:海报图
     * @param detail:详情富文本
     * @param beginTime
     * @param endTime
     * @param vipLevel:可参与活动会员等级
     * @param goods:优惠商品ID
     * @param price:价格
     * @return
     */
    @RequestMapping(value = "/activity/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse insertActivity(HttpServletRequest request, @RequestParam(value="title") String title,
                                         @RequestParam(value="img",required = false) MultipartFile img,
                                         @RequestParam(value="detail")String detail, @RequestParam(value="beginTime")String beginTime,
                                         @RequestParam(value="endTime")String endTime, @RequestParam(value="vipLevel")int[] vipLevel,
                                         @RequestParam(value="goods",required = false)int[] goods,
                                         @RequestParam(value="price",required = false) BigDecimal[] price){
        //判断上传的文件内容是否为图片
        try {
            if(!img.isEmpty()) {
                InputStream inputStream = img.getInputStream();
                BufferedImage bi = ImageIO.read(inputStream);
                if (bi == null) {
                    return ServerResponse.createByErrorMessage("请上传格式正确的图片！");
                }
            }else {
                return ServerResponse.createByErrorMessage("请上传图片！");
            }
        }catch (IOException e) {
            return ServerResponse.createByErrorMessage("IOException error");
        }catch (Exception e) {
            return ServerResponse.createByErrorMessage("Exception error");
        }

        if(StringUtils.isEmpty(title)||StringUtils.isEmpty(detail)||StringUtils.isEmpty(beginTime)||
                StringUtils.isEmpty(endTime)||vipLevel.length==0||goods.length!=price.length){
            return ServerResponse.createByErrorMessage("参数出错");
        }

        Activity activity = new Activity();
        //活动海报图上传
        Map fileMap = FTPSSMLoad.upload(img, request, "/activity/coverImg/");
        activity.setImgUrl(fileMap.get("http_url").toString());

        activity.setTitle(title);
        activity.setDetail(detail);
        //日期字符串转日期类型
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            activity.setBegintime(formatter.parse(beginTime));
            activity.setEndtime(formatter.parse(endTime));
        }catch (ParseException e) {
            return ServerResponse.createByErrorMessage("日期格式不对");
        }

        //insert
        try {
            return iSilentService.insertActivity(activity, vipLevel, goods, price);
        }catch (RuntimeException e){
            return ServerResponse.createByErrorMessage("添加活动失败");
        }
    }

    /**
     * 删除活动，包括相关会员限制，优惠信息
     * @param activityIds
     * @return
     */
    @RequestMapping(value = "/activity/delete_ByBatch.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse deleteActivity(@RequestParam("activityIds")int[] activityIds){
        if(activityIds==null||activityIds.length==0){
            return ServerResponse.createByErrorMessage("删除失败");
        }
        return iSilentService.deleteActivityByBatch(activityIds);
    }

    /**
     * 获取活动列表信息
     * @param page 第几页
     * @param num 每页显示记录数
     * @return
     */
    @RequestMapping(value = "/activity/get_Menu/{page}/{num}", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getActivityMenu(@PathVariable(value = "page")Integer page, @PathVariable(value = "num")Integer num){
        try {
            return iSilentService.getActivityMenu(page, num);
        }catch (Exception e){
            return ServerResponse.createByErrorMessage("获取失败");
        }
    }

    /**
     * 获取指定活动ID的详细信息
     * @param activityId
     * @return
     */
    @RequestMapping(value = "/activity/get_Info/{activityId}", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getActivityInfo(@PathVariable(value = "activityId")Integer activityId){
        if(activityId == null){
            return ServerResponse.createByErrorMessage("查询失败");
        }
        return iSilentService.getActivityInfo(activityId);
    }

    /**
     * 获取指定活动ID的所有商品优惠信息
     * @param activityId
     * @return
     */
    @RequestMapping(value = "/activity/get_GoodsInfo/{activityId}", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getActivityGoodsInfo(@PathVariable(value = "activityId")Integer activityId){
        if(activityId == null){
            return ServerResponse.createByErrorMessage("查询失败");
        }
        return iSilentService.getActivityGoodsInfo(activityId);
    }

    /**
     * 修改指定活动ID活动信息
     * @param request
     * @param title
     * @param img
     * @param detail
     * @param beginTime
     * @param endTime
     * @param activityId
     * @return
     */
    @RequestMapping(value = "/activity/update_activityInfo.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse updateActivityInfo(HttpServletRequest request, @RequestParam(value="title", required = false) String title,
                                             @RequestParam(value="img", required = false) MultipartFile img,
                                             @RequestParam(value="detail", required = false)String detail, @RequestParam(value="beginTime", required = false)String beginTime,
                                             @RequestParam(value="endTime", required = false)String endTime,
                                             @RequestParam(value="activityId")Integer activityId){
        Activity activity = new Activity();
        activity.setImgUrl(null);
        //判断是否有新海报图片上传
        if(!img.isEmpty()){
            try {
                InputStream inputStream = img.getInputStream();
                BufferedImage bi = ImageIO.read(inputStream);
                if (bi == null) {
                    return ServerResponse.createByErrorMessage("请上传格式正确的图片！");
                }
            }catch (IOException e) {
                return ServerResponse.createByErrorMessage("IOException error");
            }catch (Exception e) {
                return ServerResponse.createByErrorMessage("Exception error");
            }
            //活动海报图上传
            Map fileMap = FTPSSMLoad.upload(img, request, "/activity/coverImg/");
            activity.setImgUrl(fileMap.get("http_url").toString());
        }
        activity.setId(activityId);
        activity.setTitle(title);
        activity.setDetail(detail);

        //日期字符串转日期类型
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(beginTime!=null){
            try {
                activity.setBegintime(formatter.parse(beginTime));
            }catch (ParseException e) {
                return ServerResponse.createByErrorMessage("ParseException error");
            }
        }
        if(endTime!=null){
            try {
                activity.setEndtime(formatter.parse(endTime));
            }catch (ParseException e) {
                return ServerResponse.createByErrorMessage("ParseException error");
            }
        }
        return iSilentService.updateActivityInfo(activity);
    }

    /**
     * 修改活动对应会员等级限制
     * @param activityId
     * @param vipIds
     * @return
     */
    @RequestMapping(value = "/activity/update_level.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse updateActivityLevel(@RequestParam(value = "activityId")Integer activityId,
                                              @RequestParam(value = "vipIds")int[] vipIds){
        if(activityId==null||vipIds.length==0){
            return ServerResponse.createByErrorMessage("参数错误");
        }
        try {
            return iSilentService.updateActivityLevel(activityId, vipIds);
        }catch (DataAccessException e){
            return ServerResponse.createByErrorMessage("修改失败");
        }
    }

    /**
     * 修改商品优惠信息
     * @param activityId
     * @param goods
     * @param price
     * @return
     */
    @RequestMapping(value = "/activity/update_goods.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse updateActivityGoods(@RequestParam(value = "activityId")Integer activityId,
                                              @RequestParam(value = "goods")int[] goods,
                                              @RequestParam(value = "price")BigDecimal[] price){
        if(activityId==null||goods.length!=price.length){
            return ServerResponse.createByErrorMessage("参数错误");
        }
        try {
            return iSilentService.updateActivityGoods(activityId, goods, price);
        }catch (DataAccessException e){
            return ServerResponse.createByErrorMessage("修改失败");
        }
    }


    /*数据统计模块*/

    /**
     * 获取某年份每个月的销售总额
     * @param year
     * @return
     */
    @RequestMapping(value = "/statistics/get_sales_all/{year}", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getSalesByMonthOneYear(@PathVariable(value = "year")Integer year){
        if(year == null){
            return ServerResponse.createByErrorMessage("参数错误");
        }
        return iSilentService.getAllSalesByMonthOneYear(year);
    }

    /**
     * 获取某月份所有产品各个商品的销售量
     * @param year_month
     * @return
     */
    @RequestMapping(value = "/statistics/get_brandShopInfo/{year_month}", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getSalesByMonthOneYear(@PathVariable(value = "year_month")String year_month){
        if(StringUtils.isEmpty(year_month)){
            return ServerResponse.createByErrorMessage("参数错误");
        }
        String year = year_month.substring(0,year_month.indexOf("-"));
        String month = year_month.substring(year_month.indexOf("-")+1);
        try {
            new SimpleDateFormat("yyyy").parse(year);
            new SimpleDateFormat("MM").parse(month);
        }catch (ParseException e){
            return ServerResponse.createByErrorMessage("参数错误");
        }
        return iSilentService.getBrandShopInfo(year,month);
    }

    /**
     * 获取某个产品的销售走势，一年内
     * @param productionId
     * @param year
     * @return
     */
    @RequestMapping(value = "/statistics/get_ShopTrend/{productionId}/{year}", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getProductShopTrend(@PathVariable(value = "productionId")Integer productionId,
                                              @PathVariable(value = "year")Integer year){
        if(productionId == null||year == null){
            return ServerResponse.createByErrorMessage("参数错误");
        }
        return iSilentService.getProductShopTrend(productionId,year);
    }

    /**
     * 获取产品销售前几
     * @param num
     * @return
     */
    @RequestMapping(value = "/statistics/get_ShopRank/{num}", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getProductionShopRank(@PathVariable(value = "num")Integer num){
        if(num == null || num <= 0){
            return ServerResponse.createByErrorMessage("参数错误");
        }

        return iSilentService.getProductionShopRank(num);
    }

    /**
     * 获取产品购物车添加数量排名前几
     * @param num
     * @return
     */
    @RequestMapping(value = "/statistics/get_CardRank/{num}", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getProductionCardRank(@PathVariable(value = "num")Integer num){
        if(num == null || num <= 0){
            return ServerResponse.createByErrorMessage("参数错误");
        }

        return iSilentService.getProductionCardRank(num);
    }

    /**
     * 获取产品浏览量排名前几
     * @param num
     * @return
     */
    @RequestMapping(value = "/statistics/get_BrowseRank/{num}", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getProductionBrowseRank(@PathVariable(value = "num")Integer num){
        if(num == null || num <= 0){
            return ServerResponse.createByErrorMessage("参数错误");
        }

        return iSilentService.getProductionBrowseRank(num);
    }
}
