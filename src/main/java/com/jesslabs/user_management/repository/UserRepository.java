package com.jesslabs.user_management.repository;

import com.jesslabs.user_management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByUsername(String username);

    Optional<User> findUserByUsername(String username);


}
