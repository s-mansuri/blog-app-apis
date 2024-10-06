package com.weblanch.spring_blog_rest_api.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
@Schema(description = "PostDto Model Information")
public class PostDto {
    private long postId;

    @Schema(description = "Blog post title")
    // title should not be null or empty and min length is 2
    @NotEmpty
    @NotNull
    @Size(min = 2, message = "Post title should at least have 2 chars")
    private String title;

    @NotEmpty
    @Size(min = 10, message = "Post description should at least have 10 chars")
    private String description;

    @NotEmpty
    @Size(min = 10, message = "Post content should at least have 10 chars")
    private String content;

    private Set<CommentDto> comments;
    private Long categoryId;
}
