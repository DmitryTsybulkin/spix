package com.swallow.spix.dao;

import com.swallow.spix.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRep extends JpaRepository<Role, String> {
    Optional<Role> findRoleByName(String name);
}
