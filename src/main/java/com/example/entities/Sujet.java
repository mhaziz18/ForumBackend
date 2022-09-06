package com.example.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Sujet {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String title;
	private String text;
	
	@ManyToOne
	private User user;
	
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval=true)
	private List<Comment> comments;
	
	@OneToMany(mappedBy="sujet",cascade = CascadeType.ALL,orphanRemoval=true)
	private List<Rating> ratings;
	
	private int totalRating;
	
	public List<Comment>  addComment (Comment comment){
		this.comments.add(comment);
		return this.comments;
	}
	
	public void removeComment(Comment comment) {
		this.comments.remove(comment);
	}
	
}
