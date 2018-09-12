package org.xgun.kissolive.service;

import org.xgun.kissolive.common.ServerResponse;
import org.xgun.kissolive.pojo.Supplier;

import java.util.List;

public interface ISNH48Service {

    ServerResponse<List<Supplier>> listSupplier();

    ServerResponse addSupplier(String name);

    ServerResponse removeSupplier(Integer id);

    ServerResponse updateSupplier(Integer id, String name);
}