package com.bootdo.testDemo;

import com.bootdo.school.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

@RestController()
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDemo {
    @Autowired
    RedisTemplate redisTemplate;


    @Autowired
    RedisUtil redisUtil;


    @Test
    public void test() {
        redisTemplate.opsForValue().set("a", "b");
        System.out.println(redisTemplate.opsForValue().get("a"));
    };



    @Test
    public void test2() {
        Jedis jedis=redisUtil.getJedis();
        System.out.println(jedis.exists("LBTALL"));

        jedis.close();;
    };



}
