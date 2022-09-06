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

import com.example.dto.SujetRequest;
import com.example.entities.Sujet;
import com.example.services.SujetService;

@RestController
@RequestMapping("/api")
public class SujetController {

	@Autowired
	SujetService sujetService;
	
	@CrossOrigin(origins ="*", allowedHeaders = "*")
	@PostMapping("/sujet")
	public ResponseEntity<Sujet> createSujet(@RequestBody SujetRequest sujet) throws Exception {
		//verification email is avaible
		Sujet newSujet = sujetService.createSujet(sujet);
		return ResponseEntity.ok().body(newSujet);
	}
	
	@CrossOrigin(origins ="*", allowedHeaders = "*", methods = RequestMethod.PUT)
	@PutMapping("/sujet")
	public ResponseEntity<Sujet> updateSujet(@RequestBody Sujet sujet) throws Exception {
		
		//verification email is avaible
		Sujet newSujet = sujetService.updateSujet(sujet);
		return ResponseEntity.ok().body(newSujet);
		
	}
	
	@CrossOrigin(origins ="*", allowedHeaders = "*", methods = RequestMethod.DELETE)
	@DeleteMapping("/sujet/{id}")
	public ResponseEntity<HashMap<String, String>> deleteSujet(@PathVariable Long id) throws Exception {
		
		sujetService.deleteSujet(id);
		HashMap responce = new HashMap < String , String > ();
		responce.put("Message", "Sujet Deleted Succesfully");
		return ResponseEntity.ok().body(responce);
		
	}
	
	
	@CrossOrigin(origins ="*", allowedHeaders = "*")
	@GetMapping("/sujet")
	public ResponseEntity<List<Sujet>> listSujet() throws Exception {
		
		List<Sujet> list = sujetService.listSujet();
		return ResponseEntity.ok().body(list);
		
	}
	
	
	@CrossOrigin(origins ="*", allowedHeaders = "*")
	@GetMapping("/sujet/{id}")
	public ResponseEntity<Sujet> getSujet(@PathVariable Long id) throws Exception {
		
		Sujet user = sujetService.findSujet(id);
		return ResponseEntity.ok().body(user);
		
	}
	
}
