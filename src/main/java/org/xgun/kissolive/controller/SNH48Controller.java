package org.xgun.kissolive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.xgun.kissolive.common.ServerResponse;
import org.xgun.kissolive.pojo.Stock;
import org.xgun.kissolive.pojo.Supplier;
import org.xgun.kissolive.service.ISNH48Service;
import org.xgun.kissolive.vo.ListStock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class SNH48Controller {

    @Autowired
    private ISNH48Service service;

    /**
     * 获取供应商列表
     * @return
     */
    @GetMapping("/supplier")
    public ServerResponse<List<Supplier>> listSupplier() {

        return service.listSupplier();
    }

    /**
     * 创建一个供应商
     * @param name 供应商名称
     * @return
     */
    @PostMapping("/supplier")
    public ServerResponse addSupplier(@RequestParam("name") String name) {

        return service.addSupplier(name);
    }

    /**
     * 修改供应商名称
     * @param id 供应商id
     * @param name 修改后的供应商名称
     * @return
     */
    @PutMapping("/supplier/{id}")
    public ServerResponse updateSupplier(@PathVariable("id") Integer id, @RequestParam("name") String name) {

        return service.updateSupplier(id, name);
    }

    /**
     * 删除供应商
     * @param id 供应商id
     * @return
     */
    @DeleteMapping("/supplier/{id}")
    public ServerResponse removeSupplier(@PathVariable("id") Integer id) {

        return service.removeSupplier(id);
    }

    //接收前端传的Date类型值
    @InitBinder
    public void initDate(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }

    /**
     * 入库
     * @param stock 入库信息
     * @return
     */
    @PostMapping("/stock")
    public ServerResponse addStock(Stock stock) {

        return service.addStock(stock);
    }

    /**
     * 获取库存信息列表
     * @return
     */
    @GetMapping("/stock")
    public ServerResponse<List<ListStock>> listStock() {

        return service.listStock();
    }
}
