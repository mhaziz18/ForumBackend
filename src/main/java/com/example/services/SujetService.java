package com.example.services;



import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dto.SujetRequest;
import com.example.entities.Rating;
import com.example.entities.Sujet;
import com.example.repositories.SujetRepository;

@Service
public interface SujetService {

	public Sujet createSujet(SujetRequest sujetRequest);
	
	public List<Sujet> listSujet();
	
	public Sujet updateSujet(Sujet sujet);
	
	public void deleteSujet(Long id);
	
	public Sujet findSujet(Long id);
	
	
}
