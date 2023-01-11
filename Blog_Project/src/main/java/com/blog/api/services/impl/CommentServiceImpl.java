package com.blog.api.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.api.entities.Comments;
import com.blog.api.entities.Post;
import com.blog.api.exceptions.ResourceNotFoundException;
import com.blog.api.payloads.CommentDto;
import com.blog.api.repositories.CommentsRepo;
import com.blog.api.repositories.PostRepo;
import com.blog.api.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private PostRepo postRepo;
	@Autowired
	private CommentsRepo commentsRepo;
	@Autowired
	private ModelMapper mapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {

		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));

		Comments comment = this.mapper.map(commentDto, Comments.class);
		comment.setPost(post);

		Comments savedComment = this.commentsRepo.save(comment);
		return this.mapper.map(savedComment, CommentDto.class);

	}

	@Override
	public void deleteComment(Integer commentId) {

		Comments comments = this.commentsRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comments", "CommentId", commentId));

		this.commentsRepo.delete(comments);

	}

}
