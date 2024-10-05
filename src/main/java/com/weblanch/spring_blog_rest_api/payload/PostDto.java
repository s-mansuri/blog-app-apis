package com.weblanch.spring_blog_rest_api.payload;

import lombok.Data;

@Data
public class PostDto {
    private long postId;
    private String title;
    private String description;
    private String content;
}
