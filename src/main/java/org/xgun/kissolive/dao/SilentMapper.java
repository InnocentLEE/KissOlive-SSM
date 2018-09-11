package org.xgun.kissolive.dao;

import org.apache.ibatis.annotations.Param;
import org.xgun.kissolive.pojo.Card;
import org.xgun.kissolive.vo.CardInfo;

import java.util.List;

/**
 * Created by GvG on 2018/9/10.
 */
public interface SilentMapper {

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
}
