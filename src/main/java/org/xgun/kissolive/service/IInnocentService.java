package org.xgun.kissolive.service;

import org.xgun.kissolive.common.ServerResponse;
import org.xgun.kissolive.pojo.Brand;
import org.xgun.kissolive.pojo.Function;
import org.xgun.kissolive.pojo.Hotspot;
import org.xgun.kissolive.pojo.Origin;


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

}
