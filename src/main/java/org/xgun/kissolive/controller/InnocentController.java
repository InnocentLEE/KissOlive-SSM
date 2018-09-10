package org.xgun.kissolive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.xgun.kissolive.common.Const;
import org.xgun.kissolive.common.ServerResponse;
import org.xgun.kissolive.pojo.*;
import org.xgun.kissolive.service.IInnocentService;
import org.xgun.kissolive.utils.FTPSSMLoad;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lee on 2018/9/7.
 */
@Controller
public class InnocentController {

    @Autowired
    private IInnocentService iInnocentService;

    /**
     * 添加品牌信息
     *
     * @param session
     * @param request
     * @param brandLogo
     * @param brandName
     * @return
     */
    @RequestMapping(value = "/production/add_brand.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addBrand(HttpSession session, HttpServletRequest request,
                                   @RequestParam("logo_img") MultipartFile brandLogo,
                                   @RequestParam("brand_name") String brandName) {
        // TODO: 2018/9/8 校验身份
        Map map = FTPSSMLoad.upload(brandLogo, request, Const.FILE_SAVE_PATH);
        Brand brand = new Brand(Const.ID_INIT, brandName, map.get("http_url").toString(), Const.BRAND_PUT_ON_STATUS);
        return iInnocentService.addBrand(brand);
    }

    /**
     * 查看全部品牌信息
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/production/get_brand_list.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getBrandList(HttpSession session) {
        // TODO: 2018/9/8 校验管理员身份 
        return iInnocentService.getBrandList();
    }

    /**
     * 获取上架的品牌信息
     *
     * @return
     */
    @RequestMapping(value = "/production/get_brand_put_on.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getBrandPutOn() {
        return iInnocentService.getBrandPutOn();
    }

    /**
     * 修改品牌的上架状态
     *
     * @param session
     * @param id
     * @param status
     * @return
     */
    @RequestMapping(value = "/production/change_brand_put_on_status.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse changeBrandPutOnStatus(HttpSession session, @RequestParam("brand_id") int id,
                                                 @RequestParam("brand_status") int status) {
        // TODO: 2018/9/8 校验管理员身份
        if (!(status == 0 || status == 1))
            return ServerResponse.createByErrorMessage("修改失败，status=[ 1 | 0 ]");
        Brand brand = new Brand(id, null, null, status);
        return iInnocentService.changeBrandPutOnStatus(brand);
    }

    /**
     * 添加选购热点信息
     *
     * @param session
     * @param describe
     * @return
     */
    @RequestMapping(value = "/production/add_hotspot.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addHotspot(HttpSession session, @RequestParam("hotspot_describe") String describe) {
        // TODO: 2018/9/9 校验管理员身份
        Hotspot hotspot = new Hotspot(Const.ID_INIT, describe);
        return iInnocentService.addHotspot(hotspot);
    }

    /**
     * 获取全部选购热点
     *
     * @return
     */
    @RequestMapping(value = "/production/get_hotspot_list.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getHotspotList() {
        return iInnocentService.getHotspotList();
    }

    /**
     * 添加功能
     *
     * @param session
     * @param describe
     * @return
     */
    @RequestMapping(value = "/production/add_function.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addFunction(HttpSession session, @RequestParam("function_describe") String describe) {
        // TODO: 2018/9/9  校验管理员身份
        Function function = new Function(Const.ID_INIT, describe);
        return iInnocentService.addFunction(function);
    }

    /**
     * 获取全部功能
     *
     * @return
     */
    @RequestMapping(value = "/production/get_function_list.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getFunctionList() {
        return iInnocentService.getFunctionList();
    }

    /**
     * 添加产地
     *
     * @param session
     * @param describe
     * @return
     */
    @RequestMapping(value = "/production/add_origin.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addOrigin(HttpSession session, @RequestParam("origin_describe") String describe) {
        // TODO: 2018/9/10  校验管理员身份
        Origin origin = new Origin(Const.ID_INIT, describe);
        return iInnocentService.addOrigin(origin);
    }

    /**
     * 获取全部产地
     *
     * @return
     */
    @RequestMapping(value = "/production/get_origin_list.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getOriginList() {
        return iInnocentService.getOriginList();
    }

    /**
     * 添加上市时间
     *
     * @param session
     * @param describe
     * @return
     */
    @RequestMapping(value = "/production/add_market_time.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addMarketTime(HttpSession session, @RequestParam("market_time_describe") String describe) {
        // TODO: 2018/9/10  校验管理员身份
        MarketTime marketTime = new MarketTime(Const.ID_INIT, describe);
        return iInnocentService.addMarketTime(marketTime);
    }

    /**
     * 获取全部上市时间
     *
     * @return
     */
    @RequestMapping(value = "/production/get_market_time_list.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getMarketTimeList() {
        return iInnocentService.getMarketTimeList();
    }

    /**
     * 添加适用肤质
     *
     * @param session
     * @param describe
     * @return
     */
    @RequestMapping(value = "/production/add_skin.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addSkin(HttpSession session, @RequestParam("skin_describe") String describe) {
        // TODO: 2018/9/10  校验管理员身份
        Skin skin = new Skin(Const.ID_INIT, describe);
        return iInnocentService.addSkin(skin);
    }

    /**
     * 获取全部适用肤质
     *
     * @return
     */
    @RequestMapping(value = "/production/get_skin_list.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getSkinList() {
        return iInnocentService.getSkinList();
    }

    /**
     * 富文本上传图片接口
     *
     * @param request
     * @param file
     * @return
     */
    @RequestMapping(value = "/production/upload_image.do", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> uploadImage(HttpServletRequest request, @RequestParam("img") MultipartFile file) {
        Map map = FTPSSMLoad.upload(file, request, Const.FILE_SAVE_PATH);
        Map<String, String> result = new HashMap<>();
        result.put("url", map.get("http_url").toString());
        return result;
    }

    @RequestMapping(value = "/production/add_production.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addProduction(HttpSession session, HttpServletRequest request,
                                        @RequestParam("brand_id") int brandId,
                                        @RequestParam("origin_id") int originId,
                                        @RequestParam("market_time_id") int marketTimeId,
                                        @RequestParam("hotspots")int hotspot[],
                                        @RequestParam("functions")int function[],
                                        @RequestParam("skins")int skin[],
                                        @RequestParam("production_name")String name,
                                        @RequestParam("description")String description,
                                        @RequestParam("detail")String detail,
                                        @RequestParam("img")MultipartFile file) {
        // TODO: 2018/9/10 校验管理员身份
        Map map = FTPSSMLoad.upload(file, request, Const.FILE_SAVE_PATH);
        Production production = new Production(Const.ID_INIT,brandId,originId,marketTimeId,name,description,map.get("http_url").toString(),detail,null,null);
        List<ProductionHotspot> productionHotspots = new ArrayList<>();
        for (int i = 0; i < hotspot.length; i++) {
            productionHotspots.add(new ProductionHotspot(hotspot[i],Const.ID_INIT));
        }
        List<ProductionFunction> productionFunctions = new ArrayList<>();
        for (int i = 0; i < function.length; i++) {
            productionFunctions.add(new ProductionFunction(function[i],Const.ID_INIT));
        }
        List<ProductionSkin> productionSkins = new ArrayList<>();
        for (int i = 0; i < skin.length; i++) {
            productionSkins.add(new ProductionSkin(skin[i],Const.ID_INIT));
        }
        return iInnocentService.addProduction(production,productionHotspots,productionFunctions,productionSkins);
    }
}
