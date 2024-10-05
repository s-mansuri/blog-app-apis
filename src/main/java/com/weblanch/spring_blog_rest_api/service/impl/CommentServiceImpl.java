package com.weblanch.spring_blog_rest_api.service.impl;

import com.weblanch.spring_blog_rest_api.entity.Comment;
import com.weblanch.spring_blog_rest_api.entity.Post;
import com.weblanch.spring_blog_rest_api.exception.BlogAPIException;
import com.weblanch.spring_blog_rest_api.exception.ResourceNotFoundException;
import com.weblanch.spring_blog_rest_api.payload.CommentDto;
import com.weblanch.spring_blog_rest_api.payload.PostResponse;
import com.weblanch.spring_blog_rest_api.repository.CommentRepository;
import com.weblanch.spring_blog_rest_api.repository.PostRepository;
import com.weblanch.spring_blog_rest_api.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository){
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }


    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
        comment.setPost(post);

        return mapToDto(commentRepository.save(comment));
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        List<Comment> comments = commentRepository.findByPostPostId(postId);
        return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", commentId));
        if(!comment.getPost().getPostId().equals(post.getPostId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment doesn't belong to post specified");
        }

        return mapToDto(comment);
    }

    @Override
    public CommentDto updateCommentById(Long postId, long commentId, CommentDto commentDto) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", commentId));
        if(!comment.getPost().getPostId().equals(post.getPostId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment doesn't belong to post specified");
        }
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        return mapToDto(commentRepository.save(comment));
    }

    @Override
    public void deleteCommentById(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", commentId));
        if(!comment.getPost().getPostId().equals(post.getPostId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment doesn't belong to post specified");
        }
        commentRepository.delete(comment);
    }

    private CommentDto mapToDto(Comment comment){
        CommentDto commentDto = new CommentDto();
        commentDto.setCommentId(comment.getCommentId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());
        return commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto){
        Comment comment = new Comment();
        comment.setCommentId(commentDto.getCommentId());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        return comment;
    }
}
