package com.sulimann.springsecurityexample.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sulimann.springsecurityexample.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

}
