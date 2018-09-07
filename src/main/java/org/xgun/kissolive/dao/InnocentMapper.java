package org.xgun.kissolive.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.xgun.kissolive.pojo.Address;
import org.xgun.kissolive.pojo.User;

/**
 * Created by Lee on 2018/9/7.
 */
public interface InnocentMapper {
    int countByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    int insertUser(User user);

    int selectIdByUser(@Param("phoneNumber") String phoneNumber);

    int insertAddress(Address address);
}
