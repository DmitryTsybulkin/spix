package com.swallow.spix.service;

import com.swallow.spix.dao.RoleRep;
import com.swallow.spix.dao.UserRep;
import com.swallow.spix.dtos.UserDTO;
import com.swallow.spix.exceptions.RoleNotFoundException;
import com.swallow.spix.exceptions.UserAlreadyExistsException;
import com.swallow.spix.exceptions.UserNotFoundException;
import com.swallow.spix.model.Role;
import com.swallow.spix.model.User;
import com.swallow.spix.model.UserBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRep userRep;
    private RoleRep roleRep;

    @Autowired
    public UserService(UserRep userRep, RoleRep roleRep) {
        this.userRep = userRep;
        this.roleRep = roleRep;
    }

    @Transactional
    public void createUser(final UserDTO dto) {
        if (userRep.existsUserByLogin(dto.getLogin())) {
            throw new UserAlreadyExistsException();
        }
        userRep.save(new UserBuilder().withLogin(dto.getLogin())
                .withPassword(dto.getPassword())
                .withRoles(dto.getRoles().stream().map(s -> roleRep.findRoleByName("USER_ROLE")
                        .orElseThrow(RoleNotFoundException::new))
                        .collect(Collectors.toSet()))
                .withPosts(null).build());
    }

    @Transactional(readOnly = true)
    public UserDTO getUserByLogin(final UserDTO dto) {
        return toDto(userRep.findUserByLogin(dto.getLogin()).orElseThrow(UserNotFoundException::new));
    }

    @Transactional(readOnly = true)
    public UserDTO getUserById(final Long id) {
        return toDto(userRep.findById(id).orElseThrow(UserNotFoundException::new));
    }

    @Transactional(readOnly = true)
    public List<UserDTO> getUsersList() {
        return userRep.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Transactional
    public void updateUser(final UserDTO dto) {
        User user = userRep.findById(dto.getId()).orElseThrow(UserNotFoundException::new);
        user.setLogin(dto.getLogin());
        user.setPassword(dto.getPassword());
        user.setRoles(dto.getRoles().stream().map(Role::new).collect(Collectors.toSet()));
    }

    @Transactional
    public void deleteUserById(final Long id) {
        User user = userRep.findById(id).orElseThrow(UserNotFoundException::new);
        userRep.delete(user);
    }

    @Transactional
    public void deleteUserByLogin(final UserDTO dto) {
        User user = userRep.findUserByLogin(dto.getLogin()).orElseThrow(UserNotFoundException::new);
        userRep.delete(user);
    }

    public UserDTO toDto(final User user) {
        return new UserDTO(user.getId(), user.getLogin(), user.getRoles()
                .stream().map(Role::getName).collect(Collectors.toList()));
    }

    public User fromDto(final UserDTO dto) {
        return new User(dto.getLogin(),
                dto.getPassword(),
                dto.getRoles().stream().map(s -> roleRep.findRoleByName(s).orElseThrow(RoleNotFoundException::new))
                        .collect(Collectors.toSet()),
                null);
    }

}
