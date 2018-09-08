package org.xgun.kissolive.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.xgun.kissolive.common.Const;
import org.xgun.kissolive.common.ServerResponse;
import org.xgun.kissolive.pojo.Brand;
import org.xgun.kissolive.pojo.Hotspot;
import org.xgun.kissolive.service.IInnocentService;
import org.xgun.kissolive.utils.FTPSSMLoad;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
     * @param session
     * @param request
     * @param brandLogo
     * @param brandName
     * @return
     */
    @RequestMapping(value="/production/add_brand.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addBrand(HttpSession session, HttpServletRequest request,
                                   @RequestParam("logo_img") MultipartFile brandLogo,
                                   @RequestParam("brand_name") String brandName){
        // TODO: 2018/9/8 校验身份
        Map map = FTPSSMLoad.upload(brandLogo,request,"/kissolive/");
        Brand brand = new Brand(Const.ID_INIT,brandName,map.get("http_url").toString(), Const.BRAND_PUT_ON_STATUS);
        return iInnocentService.addBrand(brand);
    }

    /**
     * 查看全部品牌信息
     * @param session
     * @return
     */
    @RequestMapping(value="/production/get_brand_list.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getBrandList(HttpSession session){
        // TODO: 2018/9/8 校验管理员身份 
        return iInnocentService.getBrandList();
    }

    /**
     * 获取上架的品牌信息
     * @return
     */
    @RequestMapping(value="/production/get_brand_put_on.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getBrandPutOn(){
        return iInnocentService.getBrandPutOn();
    }

    /**
     * 修改品牌的上架状态
     * @param session
     * @param id
     * @param status
     * @return
     */
    @RequestMapping(value="/production/change_brand_put_on_status.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse changeBrandPutOnStatus(HttpSession session, @RequestParam("brand_id") int id,
                                                 @RequestParam("brand_status") int status){
        // TODO: 2018/9/8 校验管理员身份
        if(!(status==0||status==1))
            return ServerResponse.createByErrorMessage("修改失败，status=[ 1 | 0 ]");
        Brand brand = new Brand(id,null,null,status);
        return iInnocentService.changeBrandPutOnStatus(brand);
    }

    /**
     * 添加选购热点信息
     * @param session
     * @param describe
     * @return
     */
    @RequestMapping(value="/production/add_hotspot.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addHotspot(HttpSession session,@RequestParam("hotspot_describe") String describe){
        // TODO: 2018/9/9 校验管理员身份
        Hotspot hotspot = new Hotspot(Const.ID_INIT,describe);
        return iInnocentService.addHotspot(hotspot);
    }
}
