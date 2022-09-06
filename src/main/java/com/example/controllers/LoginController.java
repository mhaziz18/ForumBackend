package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.JwtRequest;
import com.example.dto.JwtResponse;
import com.example.entities.User;
import com.example.repositories.UserRepository;
import com.example.security.JWTUtility;
import com.example.services.implementation.MyUserDetailsService;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/api")
@Slf4j
public class LoginController {

	@Autowired
	UserRepository userRepo;

	@Autowired
	private JWTUtility jwtUtility;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authmanager;
	
	@CrossOrigin(origins ="*", allowedHeaders = "*")
	@PostMapping("/auth/login")
		public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception {
		try {
			//log.info(jwtRequest.toString());
			authmanager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getEmail(), jwtRequest.getPassword()));
		}catch(BadCredentialsException e) {
			log.error(e.getMessage());
			throw new Exception("Invalid Credentials",e);
		}
		
		User user = userRepo.findByEmail(jwtRequest.getEmail());
		if(user!=null && user.getIsActivated()==true) {
			final UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getEmail());
			final String token = jwtUtility.generateToken(userDetails);
			return new JwtResponse(token,user);	
		}else {
			log.error("error occured");
			throw new BadCredentialsException("Invalid Credentials");

		}
		
	}
}
