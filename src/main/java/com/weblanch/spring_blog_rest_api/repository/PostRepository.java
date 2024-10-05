package com.weblanch.spring_blog_rest_api.repository;

import com.weblanch.spring_blog_rest_api.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

// We don't need to add any annotation as JpaRepository already has the required annotations
public interface PostRepository extends JpaRepository<Post, Long> {
    // JpaRepository is having all crud methods already in it so no code is needed here
}
