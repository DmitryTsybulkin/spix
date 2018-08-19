package com.swallow.spix.dtos;

import java.util.List;

public class UserDTO {

    private Long id;
    private String login;
    private String password;
    private List<String> roles;

    public UserDTO(Long id, String login, List<String> roles) {
        this.id = id;
        this.login = login;
        this.roles = roles;
    }

    public UserDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
