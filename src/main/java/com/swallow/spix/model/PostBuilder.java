package com.swallow.spix.model;

import java.math.BigInteger;

public final class PostBuilder {
    private Long id;
    private User user;
    private String fileName;
    private BigInteger likes;
    private BigInteger dislikes;

    private PostBuilder() {
    }

    public static PostBuilder aPost() {
        return new PostBuilder();
    }

    public PostBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public PostBuilder withUser(User user) {
        this.user = user;
        return this;
    }

    public PostBuilder withFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public PostBuilder withLikes(BigInteger likes) {
        this.likes = likes;
        return this;
    }

    public PostBuilder withDislikes(BigInteger dislikes) {
        this.dislikes = dislikes;
        return this;
    }

    public Post build() {
        Post post = new Post(user, fileName, likes, dislikes);
        post.setId(id);
        return post;
    }
}
