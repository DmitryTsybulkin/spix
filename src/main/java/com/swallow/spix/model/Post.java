package com.swallow.spix.model;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @Column(name = "fileName")
    private String fileName;

    @Column(name = "likes")
    private BigInteger likes;

    @Column(name = "dislike")
    private BigInteger dislikes;

    public Post(User user, String fileName, BigInteger likes, BigInteger dislikes) {
        this.user = user;
        this.fileName = fileName;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    public Post() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public BigInteger getLikes() {
        return likes;
    }

    public void setLikes(BigInteger likes) {
        this.likes = likes;
    }

    public BigInteger getDislikes() {
        return dislikes;
    }

    public void setDislike(BigInteger dislikes) {
        this.dislikes = dislikes;
    }

}
