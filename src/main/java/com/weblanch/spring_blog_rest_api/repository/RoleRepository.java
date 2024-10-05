package com.weblanch.spring_blog_rest_api.repository;

import com.weblanch.spring_blog_rest_api.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(String roleName);
}
