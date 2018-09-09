package org.xgun.kissolive.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.xgun.kissolive.pojo.Address;
import org.xgun.kissolive.pojo.Brand;
import org.xgun.kissolive.pojo.Hotspot;
import org.xgun.kissolive.pojo.User;

import java.util.List;

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

    // 获取全部品牌
    List<Brand> selectBrand();

    // 获取全部上架的品牌
    List<Brand> selectBrandByStatus(int status);

    // 根据id更新品牌的上架状态
    int updateBrandStatusById(Brand brand);

    // 查找选购热点是否存在
    int countHotspotByDescribe(String describe);

    // 插入一条选购热点记录
    void insertHotspot(@Param("hotspot") Hotspot hotspot);

    // 根据id获取选购热点
    Hotspot selectHotspotById(Integer id);

    // 获取全部选购热点
    List<Hotspot> selectHotspot();
}
