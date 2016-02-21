package com.tx.service;

import com.tx.dubbo.demo.DemoService;

/**
 * Created by tianxuan on 16/2/20.
 */
public class DubboDemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String s) {
        return "你好,"+s+"我是服务提供商1";
    }
}
