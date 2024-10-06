package com.weblanch.spring_blog_rest_api.controller;

import com.weblanch.spring_blog_rest_api.entity.Post;
import com.weblanch.spring_blog_rest_api.payload.PostDto;
import com.weblanch.spring_blog_rest_api.payload.PostResponse;
import com.weblanch.spring_blog_rest_api.service.PostService;
import com.weblanch.spring_blog_rest_api.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@Tag(name = "CRUD REST APIs for Post Resource")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @Operation(summary = "Create Post API", description = "This will create Post")
    @ApiResponse(responseCode = "201", description = "This will create a new resource")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        return new ResponseEntity<PostDto>(postService.createPost(postDto),
                HttpStatus.CREATED);
    }

    @GetMapping
    public PostResponse getAllPosts(@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                                    @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                                    @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                                    @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir){
        return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "postId") long postId){
        return ResponseEntity.ok(postService.getPostById(postId));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> updatePostById(@Valid @PathVariable(name = "postId") long postId, @RequestBody PostDto postDto){
        return ResponseEntity.ok(postService.updatePostById(postDto, postId));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePostById(@PathVariable(name = "postId") long postId){
        postService.deletePostById(postId);
        return new ResponseEntity<>("Post with id: " + postId + " deleted successfully!", HttpStatus.OK);
    }

    @GetMapping("categories/{categoryId}")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable(name = "categoryId") Long categoryId){
        List<PostDto> postDtos = postService.getPostsByCategory(categoryId);
        return ResponseEntity.ok(postDtos);
    }
}
