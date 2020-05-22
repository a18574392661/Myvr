package com.bootdo.vrs.tack;

import com.bootdo.vrs.service.UserAllotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class UserVipTack {

    @Autowired
    private UserAllotService userAllotService;



   //每天晚上十二点
    @Scheduled(cron = "0 1 12 * * ?")
    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
    private void configureTasks1() {
        userAllotService.editUserVip();
    }


    //3.每天晚上0点
    @Scheduled(cron = "0 1 0 * * ?")
    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
    private void configureTasks3() {
        userAllotService.editUserVip();
    }

    @Scheduled(cron = "0 1 1 * * ?")
    //每晚一点
    //@Scheduled(fixedRate=5000)
    private void configureTasks2() {
        userAllotService.editUserVip();
    }
}
