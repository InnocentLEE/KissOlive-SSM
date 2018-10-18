package org.xgun.kissolive.controller;

import com.aliyuncs.exceptions.ClientException;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xgun.kissolive.common.Const;
import org.xgun.kissolive.common.ResponseCode;
import org.xgun.kissolive.common.ServerResponse;
import org.xgun.kissolive.pojo.Address;
import org.xgun.kissolive.pojo.User;
import org.xgun.kissolive.service.IUserService;
import org.xgun.kissolive.utils.MNS;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lee on 2018/9/7.
 */
@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private IUserService iUserService;

    /**
     * 发送验证码
     * @param session
     * @param phoneNumber
     * @return
     * @throws ClientException
     */
    @RequestMapping(value="sent_verify_code.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse sentVerifyCode(HttpSession session,@RequestParam(value = "phone_number")String phoneNumber) throws ClientException {
        Map<String,String> vcodeMap = Maps.newHashMap();
        String vcode = MNS.getVerifyCode();
        // begin 去掉短信验证码

        vcodeMap.put(Const.VERIFY_CODE,vcode);
        vcodeMap.put(Const.PHONE_NUMBER,phoneNumber);
        session.removeAttribute(Const.VERIFY_CODE);
        session.setAttribute(Const.VERIFY_CODE,vcodeMap);
        Map<String,String> map = (Map<String, String>) session.getAttribute(Const.VERIFY_CODE);
        System.out.println(map.get(Const.VERIFY_CODE));
        return ServerResponse.createBySuccess("验证码发送成功！skr~",vcodeMap);
        // end 去掉短信验证码
        /*String responseCode = MNS.sendSms(phoneNumber,vcode).getCode();
        if(responseCode.equals("OK")){
            vcodeMap.put(Const.VERIFY_CODE,vcode);
            vcodeMap.put(Const.PHONE_NUMBER,phoneNumber);
            session.removeAttribute(Const.VERIFY_CODE);
            session.setAttribute(Const.VERIFY_CODE,vcodeMap);
            return ServerResponse.createBySuccess("验证码发送成功！skr~",vcodeMap);
        }else {
            return ServerResponse.createByErrorMessage("验证码发送失败，请重试！");
        }*/
    }


    /**
     * 检查手机号码是否被注册
     * @param phoneNumber
     * @return
     */
    @RequestMapping(value="check_phone_number.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse checkPhoneNumber(@RequestParam(value = "phone_number")String phoneNumber){
        return iUserService.checkPhoneNumber(phoneNumber);
    }


    /**
     * 注册
     * @param session
     * @param phoneNumber
     * @param password
     * @param province
     * @param city
     * @param district
     * @param detail
     * @param consignee
     * @param telphone
     * @param vcode
     * @return
     */
    @RequestMapping(value="register.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse register(HttpSession session, @RequestParam(value = "phone_number")String phoneNumber,
                                   @RequestParam(value = "password")String password, @RequestParam(value = "address_province")String province,
                                   @RequestParam(value = "address_city")String city, @RequestParam(value = "address_district")String district,
                                   @RequestParam(value = "address_detail")String detail, @RequestParam(value = "address_consignee")String consignee,
                                   @RequestParam(value = "address_telphone")String telphone, @RequestParam(value = "verify_code")String vcode){
        Map<String,String> vcodeMap = (Map<String, String>) session.getAttribute(Const.VERIFY_CODE);
        boolean b1 = vcodeMap.get(Const.VERIFY_CODE).equals(vcode);
        boolean b2 = vcodeMap.get(Const.PHONE_NUMBER).equals(phoneNumber);
        if(!( b1 && b2 )){
            return ServerResponse.createByErrorMessage("验证码错误");
        }
        User user = new User(0,"Olive"+phoneNumber,password,phoneNumber,Const.USER_IMG_URL_DEFAULT,0,Const.Role.ROLE_CUSTOMER);
        Address address = new Address(0,0,province,city,district,detail,null,consignee,telphone);
        return iUserService.register(user,address);
    }

    /**
     * 登录接口
     * @param session
     * @param phoneNumber
     * @param password
     * @return
     */
    @RequestMapping(value="login.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse login(HttpSession session, @RequestParam(value = "phone_number")String phoneNumber,
                                @RequestParam(value = "password")String password){

        ServerResponse response = iUserService.login(phoneNumber,password);
        if (response.isSuccess()) {
            session.removeAttribute(Const.CURRENT_USER);
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }

    /**
     * 退出登录
     * @param session
     * @return
     */
    @RequestMapping(value = "logout.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse logout(HttpSession session) {
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess();
    }

    /**
     * 获取用户信息
     * @param session
     * @return
     */
    @RequestMapping(value = "get_info.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getInfo(HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user==null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"还没登录，请先登录");
        return iUserService.getInfo(user.getId());
    }

    /**
     * 获取用户地址
     * @param session
     * @return
     */
    @RequestMapping(value = "get_address_list.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getAddressList(HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user==null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"还没登录，请先登录");
        return iUserService.getAddressList(user.getId());
    }

    /**
     * 修改用户名
     * @param session
     * @param username
     * @return
     */
    @RequestMapping(value = "update_username.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse updateUsername(HttpSession session,@RequestParam("username") String username){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user==null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"还没登录，请先登录");
        User updateUser = new User();
        updateUser.setId(user.getId());
        updateUser.setUsername(username);
        ServerResponse response = iUserService.updateUsername(updateUser);
        if(response.isSuccess()){
            user.setUsername(username);
            session.removeAttribute(Const.CURRENT_USER);
            session.setAttribute(Const.CURRENT_USER, user);
        }
        return response;
    }
}
