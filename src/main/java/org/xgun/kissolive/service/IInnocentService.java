package org.xgun.kissolive.service;

import org.xgun.kissolive.common.ServerResponse;
import org.xgun.kissolive.pojo.*;

import java.util.List;


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

    ServerResponse addProduction(Production production,List<ProductionHotspot> productionHotspots,List<ProductionFunction> productionFunctions,List<ProductionSkin> productionSkins);

}
