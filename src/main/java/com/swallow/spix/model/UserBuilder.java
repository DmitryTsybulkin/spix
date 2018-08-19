package com.swallow.spix.model;

import java.util.List;
import java.util.Set;

public final class UserBuilder {
    private String login;
    private String password;
    private Set<Role> roles;
    private List<Post> posts;

    public UserBuilder() {
    }

    public static UserBuilder anUser() {
        return new UserBuilder();
    }

    public UserBuilder withLogin(String login) {
        this.login = login;
        return this;
    }

    public UserBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder withRoles(Set<Role> roles) {
        this.roles = roles;
        return this;
    }

    public UserBuilder withPosts(List<Post> posts) {
        this.posts = posts;
        return this;
    }

    public User build() {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setRoles(roles);
        user.setPosts(posts);
        return user;
    }
}
