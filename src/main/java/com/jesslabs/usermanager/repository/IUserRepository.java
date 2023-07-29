package com.jesslabs.usermanager.repository;

import com.jesslabs.usermanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
}
