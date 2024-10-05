package com.weblanch.spring_blog_rest_api.service;

import com.weblanch.spring_blog_rest_api.payload.PostDto;
import com.weblanch.spring_blog_rest_api.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
    PostDto getPostById(long postId);
    PostDto updatePostById(PostDto postDto, long postId);
    String deletePostById(long postId);
}
