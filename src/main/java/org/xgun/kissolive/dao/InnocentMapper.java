package org.xgun.kissolive.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.xgun.kissolive.pojo.*;

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

    // 查找功能是否存在
    int countFunctionByDescribe(String describe);

    // 插入一条功能记录
    void insertFunction(@Param("function")Function function);

    // 根据id获取功能
    Function selectFunctionById(Integer id);

    // 获取全部功能
    List<Function> selectFunction();

    // 查找产地是否存在
    int countOriginByDescribe(String describe);

    // 插入一条产地记录
    void inserOrigin(@Param("origin")Origin origin);

    // 根据id获取产地
    Origin selectOriginById(Integer id);

    // 获取全部产地
    List<Origin> selectOrigin();

    // 查找上市时间是否存在
    int countMarketTimeByDescribe(String describe);

    // 插入一条上市时间记录
    void insertMarketTime(@Param("marketTime")MarketTime marketTime);

    // 根据id获取上市时间
    MarketTime selectMarketTimeById(Integer id);

    // 获取全部上市时间
    List<MarketTime> selectMarketTime();
}
