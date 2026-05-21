package com.interview.Interview_Service.auth.repository;

import com.interview.Interview_Service.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String username);
}
