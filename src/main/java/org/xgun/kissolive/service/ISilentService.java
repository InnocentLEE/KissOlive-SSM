package org.xgun.kissolive.service;

import org.xgun.kissolive.common.ServerResponse;
import org.xgun.kissolive.pojo.Card;
import org.xgun.kissolive.vo.CardInfo;

import java.util.List;

/**
 * Created by GvG on 2018/9/10.
 */
public interface ISilentService {

    ServerResponse addACard(Card card);

    ServerResponse getMyCard();

    ServerResponse deleteCardByBatch(int[] cardIds);
}
