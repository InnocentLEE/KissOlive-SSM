package org.xgun.kissolive.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.xgun.kissolive.pojo.Address;
import org.xgun.kissolive.pojo.Brand;
import org.xgun.kissolive.pojo.User;

/**
 * Created by Lee on 2018/9/7.
 */
public interface InnocentMapper {

    // 查找手机号是否被注册
    int countByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    // 插入一个用户记录
    int insertUser(User user);

    // 根据注册手机号查找用户id
    int selectIdByUser(@Param("phoneNumber") String phoneNumber);

    // 插入一条收货地址记录
    int insertAddress(Address address);

    // 插入一条品牌信息记录
    int insertBrand(Brand brand);
}
