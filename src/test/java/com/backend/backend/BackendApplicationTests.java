package com.backend.backend;

import com.backend.backend.model.entity.User;
import com.backend.backend.redis.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.backend.backend.enums.TokenEnum.REFRESH_TOKEN_EXPIRE_TIME;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BackendApplicationTests {

    @Test
    public void contextLoads() {
    }


}
