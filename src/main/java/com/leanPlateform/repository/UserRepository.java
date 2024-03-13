package com.leanPlateform.repository;

import com.leanPlateform.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);

    List<User> findByRole(String role);

    User findByUsername(String username);
    
    User findByIdAndRole(Long id, String role);
}
