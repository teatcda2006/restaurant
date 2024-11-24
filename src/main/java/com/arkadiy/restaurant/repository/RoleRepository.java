package com.arkadiy.restaurant.repository;

import com.arkadiy.restaurant.entity.Role;
import com.arkadiy.restaurant.entity.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
