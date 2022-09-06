package com.example.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dto.CommentRequest;
import com.example.entities.Comment;
@Service
public interface CommentService  {

	public Comment createComment(CommentRequest commentRequest);
	
	public List<Comment> listComment();
	
	public Comment updateComment(Comment comment);
	
	public void deleteComment(Long id);
	
	public Comment findComment(Long id);
}
