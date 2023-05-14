package com.jesslabs.usermanager.repository;

import com.jesslabs.usermanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @author barbo on 23-03-2023
 */
public interface IUserRepository extends JpaRepository<User, Long> {
}
