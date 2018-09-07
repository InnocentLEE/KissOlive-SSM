package org.xgun.kissolive.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.xgun.kissolive.common.ServerResponse;
import org.xgun.kissolive.dao.InnocentMapper;
import org.xgun.kissolive.pojo.Address;
import org.xgun.kissolive.pojo.User;
import org.xgun.kissolive.service.IUserService;
import org.xgun.kissolive.utils.MD5Util;

/**
 * Created by Lee on 2018/9/7.
 */
@Service("iUserService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private InnocentMapper innocentMapper;

    @Override
    public ServerResponse checkPhoneNumber(String phoneNumber){
        if(innocentMapper.countByPhoneNumber(phoneNumber)>0){
            return ServerResponse.createByErrorMessage("该手机号已经被注册！");
        }else {
            return ServerResponse.createBySuccessMessage("该手机号未被注册！skr~");
        }
    }

    @Override
    @Transactional
    public ServerResponse register(User user, Address address){
        if(!this.checkPhoneNumber(user.getTelphone()).isSuccess()){
            return ServerResponse.createByErrorMessage("该手机号已经被注册！");
        }
        String MD5password = MD5Util.MD5EncodeUtf8(user.getPassword());
        user.setPassword(MD5password);
        boolean b1 = innocentMapper.insertUser(user)>0;
        int userId = innocentMapper.selectIdByUser(user.getTelphone());
        address.setUserId(userId);
        boolean b2 = innocentMapper.insertAddress(address)>0;
        if(b1 && b2){
            return ServerResponse.createBySuccessMessage("注册成功！skr~");
        }else {
            // 手动回滚事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ServerResponse.createByErrorMessage("注册失败！");
        }
    }
}
