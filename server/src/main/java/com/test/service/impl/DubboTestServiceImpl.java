package com.test.service.impl;

import org.apache.dubbo.config.annotation.DubboService;
import org.dromara.soul.client.dubbo.common.annotation.SoulDubboClient;
import com.test.entity.DubboTest;
import com.test.entity.ListResp;
import com.test.service.DubboTestService;

import java.util.Arrays;
import java.util.Random;

/**
 * The type Dubbo service.
 */
@DubboService
public class DubboTestServiceImpl implements DubboTestService {

    @Override
    @SoulDubboClient(path = "/findById2", desc = "Query by Id")
    public DubboTest findById2(final String id, final String name) {
        DubboTest dubboTest = new DubboTest();
        dubboTest.setId(id);
        dubboTest.setName("hello world shenyu Apache, findById");
        return dubboTest;
    }

    @Override
    @SoulDubboClient(path = "/findById", desc = "Query by Id")
    public DubboTest findById(final String id) {
        DubboTest dubboTest = new DubboTest();
        dubboTest.setId(id);
        dubboTest.setName("hello world shenyu Apache, findById");
        return dubboTest;
    }

    @Override
    @SoulDubboClient(path = "/findAll", desc = "Get all data")
    public DubboTest findAll() {
        DubboTest dubboTest = new DubboTest();
        dubboTest.setName("hello world shenyu Apache, findAll");
        dubboTest.setId(String.valueOf(new Random().nextInt()));
        return dubboTest;
    }

    @Override
    @SoulDubboClient(path = "/insert", desc = "Insert a row of data")
    public DubboTest insert(final DubboTest dubboTest) {
        dubboTest.setName("hello world shenyu Apache Dubbo: " + dubboTest.getName());
        return dubboTest;
    }

    @Override
    @SoulDubboClient(path = "/findList", desc = "Find list")
    public ListResp findList() {
        ListResp listResp = new ListResp();
        listResp.setTotal(1);
        listResp.setUsers(Arrays.asList(new DubboTest("1", "test")));
        return listResp;
    }
}
