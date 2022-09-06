package com.example.services.implementation;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.RatingRequest;
import com.example.entities.Rating;
import com.example.entities.Sujet;
import com.example.entities.User;
import com.example.repositories.RatingRepository;
import com.example.repositories.SujetRepository;
import com.example.services.RatingService;
import com.example.services.SujetService;
import com.example.services.UserService;


@Service
@Transactional
public class RatingServiceImpl implements RatingService {

	@Autowired
	RatingRepository ratingRepository;
	
	@Autowired
	SujetService sujetService;

	@Autowired
	UserService userService;
	
	public Rating addRating(RatingRequest ratingRequest) {
		// TODO Auto-generated method stub
		
		Rating rating = new Rating();
		
		Sujet sujet = sujetService.findSujet(ratingRequest.getSujetId());
		User user = userService.findUser(ratingRequest.getUserId());
		
		rating.setUser(user);
		rating.setSujet(sujet);
		rating.setValue(ratingRequest.getValue());
		Rating newRating = ratingRepository.save(rating);
		int totalRating =0;
		List<Rating> ratings = ratingRepository.findAll();
		for (Rating el:ratings) {
			totalRating += el.getValue();
		}
		int avrRating = Math.round(totalRating/ratings.size());
		
		//int totalRating = ratingRepository.getRatingsValue(ratingRequest.getSujetId());
		System.out.println("========== my variables ===========");
		System.out.println(avrRating);
		sujet.setTotalRating(avrRating);
		sujetService.updateSujet(sujet);
		
		return newRating;
		
	}

	@Override
	public Rating editRating(RatingRequest ratingRequest,Long ratingId) {
		// TODO Auto-generated method stub
		Rating rating = ratingRepository.findById(ratingId).get();		
		Sujet sujet = sujetService.findSujet(ratingRequest.getSujetId());
		User user = userService.findUser(ratingRequest.getUserId());
		
		rating.setUser(user);
		rating.setSujet(sujet);
		rating.setValue(ratingRequest.getValue());
		Rating newRating = ratingRepository.save(rating);
		int totalRating =0;
		List<Rating> ratings = ratingRepository.findAll();
		for (Rating el:ratings) {
			totalRating += el.getValue();
		}
		int avrRating = Math.round(totalRating/ratings.size());
		
		//int totalRating = ratingRepository.getRatingsValue(ratingRequest.getSujetId());
		System.out.println("========== my variables ===========");
		System.out.println(avrRating);
		sujet.setTotalRating(avrRating);
		sujetService.updateSujet(sujet);
		
		return newRating;
		
	}
	
	
	public Rating getRatingByUserAndBySujet(Long sujetId, Long userId) {
		
		return ratingRepository.findRatingBySujetAndUser(sujetId, userId);
	}
	
	@Override
	public List<Rating> getRatingBySujet(Long id){
		return ratingRepository.findRatingBySujet(id);
	}

}
