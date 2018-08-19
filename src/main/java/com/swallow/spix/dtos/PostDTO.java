package com.swallow.spix.dtos;

import org.springframework.web.multipart.MultipartFile;

public class PostDTO {

    private Long id;
    private String fileName;
    private String url;
    private int likes;
    private int dislikes;

    public PostDTO(Long id, String fileName, String url, int likes, int dislikes) {
        this.id = id;
        this.fileName = fileName;
        this.url = url;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    public PostDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
