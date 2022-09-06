package com.example.services.implementation;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.CommentRequest;
import com.example.entities.Comment;
import com.example.entities.Sujet;
import com.example.entities.User;
import com.example.repositories.CommentRepository;
import com.example.services.CommentService;
import com.example.services.SujetService;
import com.example.services.UserService;

@Service
@Transactional
public class CommentServiceImp implements CommentService{
	
	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	SujetService sujetService;
	
	@Override
	public Comment createComment(CommentRequest commentRequerst) {
		// TODO Auto-generated method stub

		User user = userService.findUser(commentRequerst.getUserId());
		Sujet sujet = sujetService.findSujet(commentRequerst.getSujetId());
		Comment comment = new Comment();
		comment.setUser(user);
		sujet.addComment(comment);
		comment.setText(commentRequerst.getText());
		comment.setSujetId(sujet.getId());
		return commentRepository.save(comment);
		
	}

	@Override
	public List<Comment> listComment() {
		// TODO Auto-generated method stub
		return commentRepository.findAll();
	}

	@Override
	public Comment updateComment(Comment comment) {
		// TODO Auto-generated method stub
		Optional<Comment> existingUser=commentRepository.findById(comment.getId());
		if(existingUser.isPresent()){
			return commentRepository.save(comment);
				
		} 
		else {
			
			return null;	
		}
	}

	@Override
	public void deleteComment(Long id) {
		
		Comment comment = findComment(id);
		
		Sujet sujet = sujetService.findSujet(comment.getSujetId());
		sujet.removeComment(comment);
		
		commentRepository.deleteById(id);
		
	}

	@Override
	public Comment findComment(Long id) {
		// TODO Auto-generated method stub
		return commentRepository.findById(id).get();
	}

}
