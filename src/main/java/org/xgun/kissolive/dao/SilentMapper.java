package org.xgun.kissolive.dao;

import org.apache.ibatis.annotations.Param;
import org.xgun.kissolive.pojo.Activity;
import org.xgun.kissolive.pojo.ActivityGoods;
import org.xgun.kissolive.pojo.Card;
import org.xgun.kissolive.pojo.VipLevel;
import org.xgun.kissolive.vo.ActivityGoodsInfo;
import org.xgun.kissolive.vo.ActivityMenuInfo;
import org.xgun.kissolive.vo.CardInfo;

import java.util.List;

/**
 * Created by GvG on 2018/9/10.
 */
public interface SilentMapper {

    /*购物车模块*/

    //向用户购物车添加一样新的商品，购物车里还未添加的
    int insertNewCard(Card card);

    //向用户购物车里已有的商品增加或减少数量
    int updateCard(Card card);

    //查询我的购物车里是否已添加该商品，若有返回数量
    Integer checkCard(Card card);

    //查询用户我的购物车里所有信息
    List<CardInfo> selectMyCard(@Param("userId")Integer userId);

    //删除我的购物车里某件商品
    int deleteCardByBatch(@Param("cardIds")int[] cardIds, @Param("userId")Integer userId);

    /*活动模块*/

    //新增活动
    Integer insertActivity(Activity activity);

    //新增活动对应会员等级
    int insertActivityLevel(@Param("activityId")Integer activityId, @Param("vipIds")int[] vipIds);

    //新增活动对应商品优惠
    int insertActivityGoods(@Param("activityId")Integer activityId, @Param("goods")List<ActivityGoods> goods);

    //获取活动个数
    int selectActivityCount();

    //获取活动列表信息，从index+1条的开始num条记录
    List<ActivityMenuInfo> selectActivityMenu(@Param("index")Integer index, @Param("num")Integer num);

    //获取参加活动的对应会员等级列表
    List<VipLevel> selectActivityVipLevel(@Param("activityId")Integer activityId);

    //获取活动内容
    Activity selectActivity(@Param("activityId")Integer activityId);

    //获取活动对应优惠商品信息
    List<ActivityGoods> selectActivityGoods(@Param("activityId")Integer activityId);

    //获取活动对应优惠商品详细信息
    List<ActivityGoodsInfo> selectActivityGoodsInfo(@Param("activityId")Integer activityId);

    //更新活动内容
    int updateActivity(Activity activity);

    //删除活动
    int deleteActivity(@Param("activityIds")int[] activityIds);

    //删除活动对应会员等级信息
    int deleteActivityLevel(@Param("activityIds")int[] activityIds);

    //删除活动对应商品优惠信息
    int deleteActivityGoods(@Param("activityIds")int[] activityIds);


}
