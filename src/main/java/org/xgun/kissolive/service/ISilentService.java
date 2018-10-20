package org.xgun.kissolive.service;

import org.xgun.kissolive.common.ServerResponse;
import org.xgun.kissolive.pojo.Activity;
import org.xgun.kissolive.pojo.Card;
import org.xgun.kissolive.pojo.ChatMessage;

import java.math.BigDecimal;

/**
 * Created by GvG on 2018/9/10.
 */
public interface ISilentService {

    ServerResponse addACard(Card card);

    ServerResponse getMyCard(Integer userId);

    ServerResponse deleteCardByBatch(Integer userId, int[] cardIds);

    ServerResponse deleteCardGoodsByBatch(Integer userId, int[] goodsIds);

    ServerResponse getActivityMenu(int page, int num);

    ServerResponse insertActivity(Activity activity, int[] vipLevel, int[] goods, BigDecimal[] price);

    ServerResponse deleteActivityByBatch(int[] activityIds);

    ServerResponse getActivityInfo(Integer activityId);

    ServerResponse getActivityGoodsInfo(Integer activityId);

    ServerResponse updateActivityInfo(Activity activity);

    ServerResponse updateActivityLevel(Integer activityId, int[] vipIds);

    ServerResponse updateActivityGoods(Integer activityId, int[] goods, BigDecimal[] price);

    ServerResponse getAllSalesByMonthOneYear(Integer year);

    ServerResponse getBrandShopInfo(String year, String month);

    ServerResponse getProductShopTrend(Integer productionId, Integer year);

    ServerResponse getProductionShopRank(Integer num);

    ServerResponse getProductionCardRank(Integer num);

    ServerResponse getProductionBrowseRank(Integer num);

    ServerResponse getNewMessageNumByUser(Integer userId);

    ServerResponse getChatAllMessage(Integer userId,  Integer type);

    ServerResponse sendingNewMessage(ChatMessage chatMessage);

    ServerResponse getMessageUserList();

    ServerResponse setMessageStatus(Integer userId, Integer source);
}
