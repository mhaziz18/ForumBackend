package com.example.services.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.SujetRequest;
import com.example.entities.Comment;
import com.example.entities.Rating;
import com.example.entities.Sujet;
import com.example.entities.User;
import com.example.repositories.SujetRepository;
import com.example.repositories.UserRepository;
import com.example.services.SujetService;
import com.example.services.UserService;

@Service
public class SujetServiceImp implements SujetService {

	@Autowired
	SujetRepository sujetRepository;

	@Autowired
	UserService userService;

	@Override
	public Sujet createSujet(SujetRequest sujetRequest) {
		// TODO Auto-generated method stub

		User user = userService.findUser(sujetRequest.getUserId());
		Sujet sujet = new Sujet();
		//sujet.setId(1L);
		sujet.setText(sujetRequest.getText());
		sujet.setTitle(sujetRequest.getTitle());
		sujet.setUser(user);
		sujet.setComments(new ArrayList<Comment>());
		sujet.setRatings(new ArrayList<Rating>());
		sujet.setTotalRating(0);
		return sujetRepository.save(sujet);

	}

	@Override
	public List<Sujet> listSujet() {
		// TODO Auto-generated method stub
		return sujetRepository.findAll();
	}

	@Override
	public Sujet updateSujet(Sujet sujet) {
		// TODO Auto-generated method stub
		Optional<Sujet> existingUser=sujetRepository.findById(sujet.getId());
		if(existingUser.isPresent()){

			return sujetRepository.save(sujet);
		}else
		{return null;
		}
	}

	@Override
	public void deleteSujet(Long id) {

		// TODO Auto-generated method stub
		sujetRepository.deleteById(id);

	}

	@Override
	public Sujet findSujet(Long id) {
		// TODO Auto-generated method stub
		Sujet sujet = sujetRepository.findById(id).get();
		return sujet;
	}


}
