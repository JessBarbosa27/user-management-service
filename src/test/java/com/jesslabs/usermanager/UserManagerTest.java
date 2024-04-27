package com.jesslabs.usermanager;

import com.jesslabs.usermanager.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserManagerTest {
    UserService userService;

    @DisplayName("Test User manager service")
    @Test
    void testUserService() {
    }

}
