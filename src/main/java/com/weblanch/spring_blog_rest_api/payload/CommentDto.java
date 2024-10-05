package com.weblanch.spring_blog_rest_api.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class CommentDto {
    private long commentId;
    private String name;
    private String email;
    private String body;
}
