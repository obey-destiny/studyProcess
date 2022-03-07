package com.example.ems_vue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class EmsVueApplicationTests {

    @Autowired
    private StringRedisTemplate stringredisTemplate;

    @Test
    public void test() {
        stringredisTemplate.opsForValue().set("name", "张三");
    }


}
