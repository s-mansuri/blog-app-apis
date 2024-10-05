package com.weblanch.spring_blog_rest_api.controller;

import com.weblanch.spring_blog_rest_api.payload.CommentDto;
import com.weblanch.spring_blog_rest_api.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts/{postId}/comments")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long postId,
                                                    @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.OK);
    }

    @GetMapping()
    public List<CommentDto> getCommentsByPostId(@PathVariable(value = "postId") Long postId){
        return commentService.getCommentsByPostId(postId);
    }

    @GetMapping("{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "postId") Long postId,
                                                     @PathVariable(value = "commentId") Long commentId){
      CommentDto commentDto =  commentService.getCommentById(postId, commentId);
      return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }

    @PutMapping("{commentId}")
    public ResponseEntity<CommentDto> updateCommentById(@PathVariable(value = "postId") Long postId,
                                                        @PathVariable(value = "commentId") long commentId,
                                                        @RequestBody CommentDto commentDto){
        CommentDto updatedComment = commentService.updateCommentById(postId, commentId, commentDto);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);

    }

    @DeleteMapping("{commentId}")
    public ResponseEntity<String> deleteCommentById(@PathVariable(value = "postId") Long postId,
                                                    @PathVariable(value = "commentId") long commentId){
        commentService.deleteCommentById(postId, commentId);
        return new ResponseEntity<String>("Comment with id: " + commentId + " delete successfully", HttpStatus.OK);
    }
}
