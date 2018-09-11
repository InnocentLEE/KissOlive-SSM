package org.xgun.kissolive.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.xgun.kissolive.common.ServerResponse;
import org.xgun.kissolive.dao.SilentMapper;
import org.xgun.kissolive.pojo.Card;
import org.xgun.kissolive.service.ISilentService;
import org.xgun.kissolive.vo.CardInfo;

import java.util.List;

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
    public ServerResponse deleteCardByBatch(List<Integer> cardIdList) {
        //获取当前用户userId
        Integer userId = 1;//测试用
        boolean result = silentMapper.deleteCardByBatch(cardIdList,userId)>0;
        if(result) {
            return ServerResponse.createBySuccessMessage("购物车删除成功");
        }else {
            return ServerResponse.createBySuccessMessage("购物车删除失败");
        }
    }
}
