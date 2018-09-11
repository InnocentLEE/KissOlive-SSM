package org.xgun.kissolive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xgun.kissolive.common.ServerResponse;
import org.xgun.kissolive.pojo.Card;
import org.xgun.kissolive.service.ISilentService;
import org.xgun.kissolive.vo.CardInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GvG on 2018/9/10.
 */
@Controller
public class SilentController {

    @Autowired
    private ISilentService iSilentService;

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
    @RequestMapping(value = "/shoppingCart/delete_card_ByBatch.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse deleteCard(@RequestParam("cardIds")int[] cardIds){
        if( cardIds==null || cardIds.length==0 ) {
            return ServerResponse.createByErrorMessage("删除失败");
        }
        return iSilentService.deleteCardByBatch(cardIds);
    }
}
