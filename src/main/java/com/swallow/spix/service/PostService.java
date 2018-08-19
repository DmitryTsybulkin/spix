package com.swallow.spix.service;

import com.swallow.spix.dao.PostRep;
import com.swallow.spix.dtos.PostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private PostRep postRep;

    @Autowired
    public PostService(PostRep postRep) {
        this.postRep = postRep;
    }

    public void createPost(final PostDTO dto) {
        //
    }

}
