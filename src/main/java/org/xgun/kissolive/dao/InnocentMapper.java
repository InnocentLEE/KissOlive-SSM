package org.xgun.kissolive.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.xgun.kissolive.pojo.*;
import org.xgun.kissolive.vo.ProductionSame;

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

    // 根据id获取品牌
    Brand selectBrandById(Integer id);

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

    // 查找适用肤质是否存在
    int countSkinByDescribe(String describe);

    // 插入一条适用肤质记录
    void insertSkin(@Param("skin")Skin skin);

    // 根据id获取适用肤质
    Skin selectSkinById(Integer id);

    // 获取全部适用肤质
    List<Skin> selectSkin();

    // 插入一条产品记录
    void insertProduction(@Param("production")Production production);

    // 插入产品选购热点
    void insertProductionHotspot(@Param("list")List<ProductionHotspot> list);

    // 插入产品功能
    void insertProductionFunction(@Param("list")List<ProductionFunction> list);

    // 插入产品适用肤质
    void insertProductionSkin(@Param("list")List<ProductionSkin> list);

    // 插入一条商品记录
    void insertGoods(@Param("goods")Goods goods);

    // 根据id查找商品记录
    Goods selectGoodsById(Integer id);

    // 更新品牌信息
    int updateBrandByIdSelective(Brand brand);

    // 统计产品总数量
    int countProduction();

    // 分页查找产品列表
    List<Production> selectProduction(@Param("index")int index, @Param("num")int num);

    // 根据产品查找选购热点
    List<Hotspot> selectHotspotByProduction(@Param("production_id")int productionId);

    // 根据产品查找功能
    List<Function> selectFunctionByProduction(@Param("production_id")int productionId);

    // 根据产品查找适用肤质
    List<Skin> selectSkinByProduction(@Param("production_id")int productionId);

    // 根据产品查找商品
    List<Goods> selectGoodsByProduction(@Param("production_id")int productionId);

    // 根据品牌查找产品列表
    List<Production> selectProductionByBrand(@Param("brand_id")int brandId);

    // 获取产品搜索描述
    List<ProductionSame> selectProductionSearch();

    // 根据产品id查找产品
    Production selectProductionById(@Param("id")int id);

    // 查找产品的评价
    List<Assess> selectAssessByProduction(@Param("production_id")int productionId);

    // 根据id查找用户信息
    User selectUserNameAndImgById(@Param("id")int id);

    // 更新产品信息
    int updateProductionById(Production production);

    // 删除产品选购热点
    int deleteProductionHotspot(@Param("production_id")int id);

    // 删除产品功能
    int deleteProductionFunction(@Param("production_id")int id);

    // 删除产品适用肤质
    int deleteProductionSkin(@Param("production_id")int id);

    // 修改商品上架状态
    int updateGoodsStatus(@Param("id")int id,@Param("status")int status);

    // 修改商品信息
    int updateGoods(Goods goods);
}
