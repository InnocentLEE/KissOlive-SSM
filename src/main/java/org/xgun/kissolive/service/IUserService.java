package org.xgun.kissolive.service;

import org.xgun.kissolive.common.ServerResponse;
import org.xgun.kissolive.pojo.Address;
import org.xgun.kissolive.pojo.User;


/**
 * Created by Lee on 2018/9/7.
 */
public interface IUserService {
    ServerResponse checkPhoneNumber(String phoneNumber);
    ServerResponse register(User user, Address address);
    ServerResponse login(String telphone, String password);
    ServerResponse getInfo(int userid);
    ServerResponse getAddressList(int userid);
    ServerResponse updateUsername(User user);
    ServerResponse addAddress(Address address);
}
