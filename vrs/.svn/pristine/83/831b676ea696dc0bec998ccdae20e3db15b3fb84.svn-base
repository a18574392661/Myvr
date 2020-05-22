package com.bootdo.vrs.interceptors;

import com.bootdo.vrs.service.IpgetCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/*
怎么简单怎么来了
 */
//@Component
//@Order(2)
public class ProShowCount implements ApplicationRunner {

    @Autowired
    private IpgetCountService ipgetCountService;




    @Override
    public void run(ApplicationArguments args) throws Exception {

        ipgetCountService.list(null);

    }
}
