package com.example.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entities.Rating;


public interface RatingRepository extends JpaRepository<Rating, Long> {

	@Query("SELECT rt from Rating rt where rt.sujet.id=?1")
    public List<Rating> findRatingBySujet(Long id);
	
	@Query("SELECT AVG(rt.value) from Rating rt where rt.sujet.id=?1")
    public int getRatingsValue(Long id);
	
	@Query("SELECT rt from Rating rt where rt.sujet.id=?1 and rt.user.id=?2")
    public Rating findRatingBySujetAndUser(Long sujetId, Long userId);
	
	
	
}
