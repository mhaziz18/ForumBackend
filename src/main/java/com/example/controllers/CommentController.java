package com.example.controllers;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.CommentRequest;
import com.example.entities.Comment;
import com.example.services.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	@CrossOrigin(origins ="*", allowedHeaders = "*")
	@PostMapping("/comment")
	public ResponseEntity<Comment> createComment(@RequestBody CommentRequest commentRequest) throws Exception {
		
		//verification email is avaible
		Comment newComment = commentService.createComment(commentRequest);
		return ResponseEntity.ok().body(newComment);
		
	}
	
	@CrossOrigin(origins ="*", allowedHeaders = "*")
	@GetMapping("/comment")
	public ResponseEntity<List<Comment>> listComment() throws Exception {
		
		//verification email is avaible
		List<Comment> liste = commentService.listComment();
		return ResponseEntity.ok().body(liste);
		
	}
	
	@CrossOrigin(origins ="*", allowedHeaders = "*", methods = RequestMethod.PUT)
	@PutMapping("/comment")
	public ResponseEntity<Comment> updateComment(@RequestBody Comment comment) throws Exception {
		
		//verification email is avaible
		Comment newComment = commentService.updateComment(comment);
		return ResponseEntity.ok().body(newComment);
		
	}
	
	@CrossOrigin(origins ="*", allowedHeaders = "*", methods = RequestMethod.DELETE)
	@DeleteMapping("/comment/{id}")
	public ResponseEntity<HashMap < String , String > > daleteComment(@PathVariable Long id) throws Exception {
		
		//verification email is avaible
		commentService.deleteComment(id);
		
		HashMap responce = new HashMap < String , String > ();
		responce.put("Message", "Sujet Deleted Succesfully");
		
		return ResponseEntity.ok().body(responce);
		
	}
	
	@CrossOrigin(origins ="*", allowedHeaders = "*")
	@GetMapping("/comment/{id}")
	public ResponseEntity<Comment> getComment(@PathVariable Long id) throws Exception {
		//verification email is avaible
		Comment newComment = commentService.findComment(id);
		return ResponseEntity.ok().body(newComment);
		
	}
	

}
