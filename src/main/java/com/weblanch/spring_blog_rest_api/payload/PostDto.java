package com.weblanch.spring_blog_rest_api.payload;

import lombok.Data;

import java.util.Set;

@Data
public class PostDto {
    private long postId;
    private String title;
    private String description;
    private String content;
    private Set<CommentDto> comments;
}
