package com.tx.service;

import com.tx.dubbo.demo.DemoService;
import org.springframework.stereotype.Service;

/**
 * Created by tianxuan on 16/2/20.
 */
@Service("demoService")
public class DubboDemoServiceImpl implements DemoService {

    @Override
    public String sayHello(String s) {
        return "你好,"+s+"我是服务提供商1";
    }
}