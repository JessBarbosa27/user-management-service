package com.jesslabs.user_management;

import com.jesslabs.user_management.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserManagementApplicationTest {
    UserService userService;

    @DisplayName("Test User manager service")
    @Test
    void testUserService() {
    }

}
