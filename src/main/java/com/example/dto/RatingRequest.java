package com.example.dto;

import lombok.Data;

@Data
public class RatingRequest {

	private Long userId;
	
	private Long sujetId;
	
	private int value;
	
	
}
