package com.swallow.spix.dao;

import com.swallow.spix.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRep extends JpaRepository<User, Long> {
    Optional<User> findUserByLogin(String login);
    Boolean existsUserByLogin(String login);
}
