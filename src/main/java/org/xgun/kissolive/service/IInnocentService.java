package org.xgun.kissolive.service;

import org.xgun.kissolive.common.ServerResponse;
import org.xgun.kissolive.pojo.Brand;
import org.xgun.kissolive.pojo.Hotspot;


/**
 * Created by Lee on 2018/9/7.
 */
public interface IInnocentService {

    ServerResponse addBrand(Brand brand);

    ServerResponse getBrandList();

    ServerResponse getBrandPutOn();

    ServerResponse changeBrandPutOnStatus(Brand brand);

    ServerResponse addHotspot(Hotspot hotspot);


}
