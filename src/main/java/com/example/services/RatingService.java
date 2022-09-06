package com.example.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dto.RatingRequest;
import com.example.entities.Rating;

@Service
public interface RatingService {

	public Rating addRating(RatingRequest ratingRequest);
	
	public Rating editRating(RatingRequest rating,Long ratingId);
	
	public Rating getRatingByUserAndBySujet(Long postId, Long userId);
	
	public List<Rating> getRatingBySujet(Long id);
}
