package com.swallow.spix.dao;

import com.swallow.spix.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRep extends JpaRepository<Post, Long> {
}
