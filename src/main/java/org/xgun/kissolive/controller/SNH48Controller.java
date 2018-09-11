package org.xgun.kissolive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.xgun.kissolive.common.ServerResponse;
import org.xgun.kissolive.pojo.Supplier;
import org.xgun.kissolive.service.ISNH48Service;

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
}
