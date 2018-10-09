package org.xgun.kissolive.service.impl;

import ch.qos.logback.core.joran.event.SaxEventRecorder;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.xgun.kissolive.common.Const;
import org.xgun.kissolive.common.ServerResponse;
import org.xgun.kissolive.dao.InnocentMapper;
import org.xgun.kissolive.pojo.*;
import org.xgun.kissolive.service.IInnocentService;
import org.xgun.kissolive.utils.IKAnalyzerUtil;
import org.xgun.kissolive.vo.ProductionDetails;
import org.xgun.kissolive.vo.ProductionSame;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by Lee on 2018/9/7.
 */
@Service("iInnocentService")
public class InnocentServiceImpl implements IInnocentService {

    @Autowired
    private InnocentMapper innocentMapper;

    @Override
    public ServerResponse addBrand(Brand brand) {
        boolean result = innocentMapper.insertBrand(brand) > 0;
        if (result)
            return ServerResponse.createBySuccessMessage("添加品牌成功");
        else
            return ServerResponse.createByErrorMessage("添加品牌失败");
    }

    @Override
    public ServerResponse getBrandList() {
        List<Brand> list = innocentMapper.selectBrand();
        return ServerResponse.createBySuccess("查询成功", list);
    }

    @Override
    public ServerResponse getBrandPutOn() {
        List<Brand> list = innocentMapper.selectBrandByStatus(Const.BRAND_PUT_ON_STATUS);
        return ServerResponse.createBySuccess("查询成功", list);
    }

    @Override
    public ServerResponse changeBrandPutOnStatus(Brand brand) {
        boolean result = innocentMapper.updateBrandStatusById(brand) > 0;
        if (result)
            return ServerResponse.createBySuccessMessage("修改成功");
        else
            return ServerResponse.createByErrorMessage("修改失败");
    }

    @Override
    public ServerResponse addHotspot(Hotspot hotspot) {
        boolean isExist = innocentMapper.countHotspotByDescribe(hotspot.getDescribe()) > 0;
        if (isExist)
            return ServerResponse.createByErrorMessage("添加选购热点失败，该选购热点已存在");
        innocentMapper.insertHotspot(hotspot);
        Integer id = hotspot.getId();
        if (id != null) {
            hotspot = innocentMapper.selectHotspotById(id);
            return ServerResponse.createBySuccess("添加选购热点成功", hotspot);
        } else {
            return ServerResponse.createByErrorMessage("添加选购热点失败");
        }
    }

    @Override
    public ServerResponse getHotspotList() {
        List<Hotspot> list = innocentMapper.selectHotspot();
        return ServerResponse.createBySuccess("获取选购热点成功", list);
    }

    @Override
    public ServerResponse addFunction(Function function) {
        boolean isExist = innocentMapper.countFunctionByDescribe(function.getDescribe()) > 0;
        if (isExist)
            return ServerResponse.createByErrorMessage("添加功能失败，该功能已存在");
        innocentMapper.insertFunction(function);
        Integer id = function.getId();
        if (id != null) {
            function = innocentMapper.selectFunctionById(id);
            return ServerResponse.createBySuccess("添加功能成功", function);
        } else {
            return ServerResponse.createByErrorMessage("添加功能失败");
        }
    }

    @Override
    public ServerResponse getFunctionList() {
        List<Function> list = innocentMapper.selectFunction();
        return ServerResponse.createBySuccess("获取功能成功", list);
    }

    @Override
    public ServerResponse addOrigin(Origin origin) {
        boolean isExist = innocentMapper.countOriginByDescribe(origin.getDescribe()) > 0;
        if (isExist)
            return ServerResponse.createByErrorMessage("添加产地失败，该功能已存在");
        innocentMapper.inserOrigin(origin);
        Integer id = origin.getId();
        if (id != null) {
            origin = innocentMapper.selectOriginById(id);
            return ServerResponse.createBySuccess("添加产地成功", origin);
        } else {
            return ServerResponse.createByErrorMessage("添加产地失败");
        }
    }

    @Override
    public ServerResponse getOriginList() {
        List<Origin> list = innocentMapper.selectOrigin();
        return ServerResponse.createBySuccess("获取产地成功", list);
    }

    @Override
    public ServerResponse addMarketTime(MarketTime marketTime) {
        boolean isExist = innocentMapper.countMarketTimeByDescribe(marketTime.getDescribe()) > 0;
        if (isExist)
            return ServerResponse.createByErrorMessage("添加上市时间失败，该上市时间已存在");
        innocentMapper.insertMarketTime(marketTime);
        Integer id = marketTime.getId();
        if (id != null) {
            marketTime = innocentMapper.selectMarketTimeById(id);
            return ServerResponse.createBySuccess("添加上市时间成功", marketTime);
        } else {
            return ServerResponse.createByErrorMessage("添加上市时间失败");
        }
    }

    @Override
    public ServerResponse getMarketTimeList() {
        List<MarketTime> list = innocentMapper.selectMarketTime();
        return ServerResponse.createBySuccess("获取上市时间成功", list);
    }

    @Override
    public ServerResponse addSkin(Skin skin) {
        boolean isExist = innocentMapper.countSkinByDescribe(skin.getDescribe()) > 0;
        if (isExist)
            return ServerResponse.createByErrorMessage("添加适用肤质失败，该适用肤质已存在");
        innocentMapper.insertSkin(skin);
        Integer id = skin.getId();
        if (id != null) {
            skin = innocentMapper.selectSkinById(id);
            return ServerResponse.createBySuccess("添加适用肤质成功", skin);
        } else {
            return ServerResponse.createByErrorMessage("添加适用肤质失败");
        }
    }

    @Override
    public ServerResponse getSkinList() {
        List<Skin> list = innocentMapper.selectSkin();
        return ServerResponse.createBySuccess("获取适用肤质成功", list);
    }

    @Override
    @Transactional
    public ServerResponse addProduction(Production production, List<ProductionHotspot> productionHotspots, List<ProductionFunction> productionFunctions, List<ProductionSkin> productionSkins) {
        String brandName = innocentMapper.selectBrandById(production.getBrandId()).getName();
        String originDescribe = innocentMapper.selectOriginById(production.getOriginId()).getDescribe();
        String marketTimeDescribe = innocentMapper.selectMarketTimeById(production.getMarketTimeId()).getDescribe();
        String hotspotDescribe = "";
        String functionDescribe = "";
        String skinDescribe = "";
        for (int i = 0; i < productionHotspots.size(); i++) {
            hotspotDescribe += innocentMapper.selectHotspotById(productionHotspots.get(i).getHotspotId()).getDescribe() + " ";
        }
        for (int i = 0; i < productionFunctions.size(); i++) {
            functionDescribe += innocentMapper.selectFunctionById(productionFunctions.get(i).getFunctionId()).getDescribe() + " ";
        }
        for (int i = 0; i < productionSkins.size(); i++) {
            skinDescribe += innocentMapper.selectSkinById(productionSkins.get(i).getSkinId()).getDescribe() + " ";
        }
        String search = production.getName() + "" + brandName + " " + originDescribe + " " + marketTimeDescribe + " " + hotspotDescribe + functionDescribe + skinDescribe;
        production.setSearch(search);
        innocentMapper.insertProduction(production);
        Integer id = production.getId();
        if (id == null)
            return ServerResponse.createByErrorMessage("添加产品失败");
        for (int i = 0; i < productionHotspots.size(); i++) {
            productionHotspots.get(i).setProductionId(id);
        }
        for (int i = 0; i < productionFunctions.size(); i++) {
            productionFunctions.get(i).setProductionId(id);
        }
        for (int i = 0; i < productionSkins.size(); i++) {
            productionSkins.get(i).setProductionId(id);
        }
        innocentMapper.inserProductionHotspot(productionHotspots);
        innocentMapper.inserProductionFunction(productionFunctions);
        innocentMapper.inserProductionSkin(productionSkins);
        // 判断是否回滚，是则说明插入失败
        if (TransactionAspectSupport.currentTransactionStatus().isRollbackOnly())
            return ServerResponse.createByErrorMessage("添加产品失败,添加过程出错");
        return ServerResponse.createBySuccessMessage("添加产品成功");
    }

    @Override
    public ServerResponse addGoods(Goods goods) {
        innocentMapper.insertGoods(goods);
        Integer id = goods.getId();
        if (id == null)
            return ServerResponse.createByErrorMessage("添加商品失败");
        goods = innocentMapper.selectGoodsById(id);
        return ServerResponse.createBySuccess("添加商品成功", goods);
    }

    @Override
    public ServerResponse editBrand(Brand brand) {
        boolean result = innocentMapper.updateBrandByIdSelective(brand) > 0;
        if (result)
            return ServerResponse.createBySuccessMessage("编辑品牌成功");
        else
            return ServerResponse.createByErrorMessage("编辑品牌失败");
    }

    @Override
    @Transactional
    public ServerResponse getProductions(int page, int pageNum) {
        int totalNum = innocentMapper.countProduction();
        int totalPage = (int) (totalNum + pageNum - 1) / pageNum;
        if (totalPage < page)
            page = totalPage;
        int index = (page - 1) * pageNum;
        List<Production> productionList = innocentMapper.selectProduction(index, pageNum);
        List<ProductionDetails> productionDetailsList = Lists.newArrayList();
        int productionSize = productionList.size();
        for (int i = 0; i < productionSize; i++) {
            ProductionDetails productionDetails = new ProductionDetails();
            productionDetails.setId(productionList.get(i).getId());
            productionDetails.setName(productionList.get(i).getName());
            productionDetails.setDescription(productionList.get(i).getDescription());
            productionDetails.setDetail(productionList.get(i).getDetail());
            productionDetails.setImgUrl(productionList.get(i).getImgUrl());
            Brand brand = innocentMapper.selectBrandById(productionList.get(i).getBrandId());
            productionDetails.setBrand(brand);
            Origin origin = innocentMapper.selectOriginById(productionList.get(i).getOriginId());
            productionDetails.setOrigin(origin);
            MarketTime marketTime = innocentMapper.selectMarketTimeById(productionList.get(i).getMarketTimeId());
            productionDetails.setMarketTime(marketTime);
            List<Hotspot> hotspots = innocentMapper.selectHotspotByProduction(productionList.get(i).getId());
            productionDetails.setHotspots(hotspots);
            List<Function> functions = innocentMapper.selectFunctionByProduction(productionList.get(i).getId());
            productionDetails.setFunctions(functions);
            List<Skin> skins = innocentMapper.selectSkinByProduction(productionList.get(i).getId());
            productionDetails.setSkins(skins);
            List<Goods> goodses = innocentMapper.selectGoodsByProduction(productionList.get(i).getId());
            productionDetails.setGoodses(goodses);
            productionDetailsList.add(productionDetails);
        }
        Map resultMap = Maps.newHashMap();
        resultMap.put("total_page",totalPage);
        resultMap.put("page",page);
        resultMap.put("page_num",pageNum);
        resultMap.put("production_details_list",productionDetailsList);
        if (TransactionAspectSupport.currentTransactionStatus().isRollbackOnly())
            return ServerResponse.createByErrorMessage("查找过程出错");
        return ServerResponse.createBySuccess("查找成功",resultMap);
    }

    @Override
    @Transactional
    public ServerResponse getProductionsByBrand(Integer brandId){
        List<Production> productionList = innocentMapper.selectProductionByBrand(brandId);
        Brand brand = innocentMapper.selectBrandById(brandId);
        List<ProductionDetails> productionDetailsList = Lists.newArrayList();
        int productionSize = productionList.size();
        for (int i = 0; i < productionSize; i++) {
            ProductionDetails productionDetails = new ProductionDetails();
            productionDetails.setId(productionList.get(i).getId());
            productionDetails.setName(productionList.get(i).getName());
            productionDetails.setDescription(productionList.get(i).getDescription());
            productionDetails.setDetail(productionList.get(i).getDetail());
            productionDetails.setImgUrl(productionList.get(i).getImgUrl());
            productionDetails.setBrand(brand);
            Origin origin = innocentMapper.selectOriginById(productionList.get(i).getOriginId());
            productionDetails.setOrigin(origin);
            MarketTime marketTime = innocentMapper.selectMarketTimeById(productionList.get(i).getMarketTimeId());
            productionDetails.setMarketTime(marketTime);
            List<Hotspot> hotspots = innocentMapper.selectHotspotByProduction(productionList.get(i).getId());
            productionDetails.setHotspots(hotspots);
            List<Function> functions = innocentMapper.selectFunctionByProduction(productionList.get(i).getId());
            productionDetails.setFunctions(functions);
            List<Skin> skins = innocentMapper.selectSkinByProduction(productionList.get(i).getId());
            productionDetails.setSkins(skins);
            List<Goods> goodses = innocentMapper.selectGoodsByProduction(productionList.get(i).getId());
            productionDetails.setGoodses(goodses);
            productionDetailsList.add(productionDetails);
        }
        if (TransactionAspectSupport.currentTransactionStatus().isRollbackOnly())
            return ServerResponse.createByErrorMessage("查找过程出错");
        return ServerResponse.createBySuccess("查找成功",productionDetailsList);
    }

    @Override
    @Transactional
    public ServerResponse searchProductions(String search){
        List<ProductionSame> productionSameList = innocentMapper.selectProductionSearch();
        int productionSameListSize = productionSameList.size();
        for (int i = 0; i < productionSameListSize; i++) {
            productionSameList.get(i).setSame(IKAnalyzerUtil.Compare(search,productionSameList.get(i).getSearch()));
        }
        Collections.sort(productionSameList);
        List<ProductionDetails> productionDetailsList = Lists.newArrayList();
        for (int i = 0; i < productionSameListSize; i++) {
            Production production = innocentMapper.selectProductionById(productionSameList.get(i).getId());
            ProductionDetails productionDetails = new ProductionDetails();
            productionDetails.setId(production.getId());
            productionDetails.setName(production.getName());
            productionDetails.setDescription(production.getDescription());
            productionDetails.setDetail(production.getDetail());
            productionDetails.setImgUrl(production.getImgUrl());
            productionDetails.setBrand(innocentMapper.selectBrandById(production.getBrandId()));
            Origin origin = innocentMapper.selectOriginById(production.getOriginId());
            productionDetails.setOrigin(origin);
            MarketTime marketTime = innocentMapper.selectMarketTimeById(production.getMarketTimeId());
            productionDetails.setMarketTime(marketTime);
            List<Hotspot> hotspots = innocentMapper.selectHotspotByProduction(production.getId());
            productionDetails.setHotspots(hotspots);
            List<Function> functions = innocentMapper.selectFunctionByProduction(production.getId());
            productionDetails.setFunctions(functions);
            List<Skin> skins = innocentMapper.selectSkinByProduction(production.getId());
            productionDetails.setSkins(skins);
            List<Goods> goodses = innocentMapper.selectGoodsByProduction(production.getId());
            productionDetails.setGoodses(goodses);
            productionDetailsList.add(productionDetails);
        }
        if (TransactionAspectSupport.currentTransactionStatus().isRollbackOnly())
            return ServerResponse.createByErrorMessage("查找过程出错");
        return ServerResponse.createBySuccess("查找成功",productionDetailsList);
    }
}
