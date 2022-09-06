package com.example.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.RatingRequest;
import com.example.entities.Rating;
import com.example.services.RatingService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class RatingController {
	
	@Autowired
	RatingService ratingService;
	
	@CrossOrigin(origins ="*", allowedHeaders = "*")
	@GetMapping("/rating")
	public Rating getRating(@RequestParam Long sujetId, @RequestParam Long userId) {
		return ratingService.getRatingByUserAndBySujet(sujetId, userId);
	}

	@CrossOrigin(origins ="*", allowedHeaders = "*")
	@PostMapping("/rating")
	public Rating addRating(@RequestBody RatingRequest ratingRequest) {
		return ratingService.addRating(ratingRequest);
	}
	
	@CrossOrigin(origins ="*", allowedHeaders = "*", methods = RequestMethod.PUT)
	@PutMapping("/rating/{id}")
	public Rating editRating(@RequestBody RatingRequest ratingRequest, @PathVariable Long id) {
		log.info(ratingRequest.toString());
		return ratingService.editRating(ratingRequest,id);
	}
	
	@CrossOrigin(origins ="*", allowedHeaders = "*")
	@GetMapping("/rating/sujet/{id}")
	public List<Rating> getRating(@PathVariable Long id) {
		return ratingService.getRatingBySujet(id);
	}
	
}
