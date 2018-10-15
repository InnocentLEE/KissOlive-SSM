package org.xgun.kissolive.service;

import org.xgun.kissolive.common.ServerResponse;
import org.xgun.kissolive.pojo.*;

import java.util.List;
import java.util.Map;


/**
 * Created by Lee on 2018/9/7.
 */
public interface IInnocentService {

    ServerResponse addBrand(Brand brand);

    ServerResponse getBrandList();

    ServerResponse getBrandPutOn();

    ServerResponse changeBrandPutOnStatus(Brand brand);

    ServerResponse addHotspot(Hotspot hotspot);

    ServerResponse getHotspotList();

    ServerResponse addFunction(Function function);

    ServerResponse getFunctionList();

    ServerResponse addOrigin(Origin origin);

    ServerResponse getOriginList();

    ServerResponse addMarketTime(MarketTime marketTime);

    ServerResponse getMarketTimeList();

    ServerResponse addSkin(Skin skin);

    ServerResponse getSkinList();

    ServerResponse addProduction(Production production, List<ProductionHotspot> productionHotspots, List<ProductionFunction> productionFunctions, List<ProductionSkin> productionSkins);

    ServerResponse addGoods(Goods goods);

    ServerResponse editBrand(Brand brand);

    ServerResponse getProductions(int page, int pageNum);

    ServerResponse getProductionsByBrand(Integer[] brandId);

    ServerResponse searchProductions(String search);

    ServerResponse getProductionShow(int id);

    ServerResponse getProductionAssess(int id);

    ServerResponse editProduction(Production production, List<ProductionHotspot> productionHotspots, List<ProductionFunction> productionFunctions, List<ProductionSkin> productionSkins);

    ServerResponse goodsPutOff(int id);

    ServerResponse goodsPutOn(int id);

    ServerResponse editGoods(Goods goods);

    ServerResponse pay(Integer orderId, Integer userId, String path);

    ServerResponse aliCallback(Map<String,String> params);

    ServerResponse queryOrderPayStatus(Integer userId,Integer orderId);
}
