package com.jesslabs.usermanager.repository;

import com.jesslabs.usermanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
