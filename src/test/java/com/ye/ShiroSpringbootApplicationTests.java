package com.ye;

import com.ye.Mapper.UserMapper;
import com.ye.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShiroSpringbootApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Test
    void contextLoads() {
        User user = userMapper.selectUserByName("oppo");
        System.out.println(user);
        System.out.println(user.getId());
        System.out.println(user.getName());
        System.out.println(user.getPwd());
    }

}
