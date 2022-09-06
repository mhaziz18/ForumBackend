package com.example.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.dto.EmailDetails;
import com.example.entities.Role;
import com.example.entities.User;
import com.example.repositories.UserRepository;
import com.example.services.EmailService;
import com.example.services.UserService;

@RestController
@Slf4j
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserRepository userRepo;
	@Autowired
	UserService userService;
	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired 
	private EmailService emailService;
	
	@CrossOrigin(origins ="*", allowedHeaders = "*")
	@PostMapping("/auth/register")
	public ResponseEntity<User> register(@RequestBody User user) throws Exception {
		
		//verification email is avaible
		User userFound = userRepo.findByEmail(user.getEmail());

		if(userFound!=null) {
			return ResponseEntity.badRequest().body(null);
		}

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole(Role.USER);
		User newUser = userRepo.save(user);
		//log.info("ID = "+newUser.getId());
		
		// sending a mail
		Long id = newUser.getId();
		String body = String.format("To activate your account click on the link: http://localhost:4200/activate?id=%s",id);
		EmailDetails details = new EmailDetails(newUser.getEmail(),body,"Account Activation","");
		emailService.sendSimpleMail(details);
		
		return ResponseEntity.ok().body(user);
		
	}
	@CrossOrigin(origins ="*", allowedHeaders = "*")
	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUser(@PathVariable Long id){
		return ResponseEntity.ok().body(userService.findUser(id));
	}
	
	
	
	
	
	
	@CrossOrigin(origins ="*", allowedHeaders = "*")
	@GetMapping("/auth/activate/{id}")
	public ResponseEntity<User> activate(@PathVariable Long id) throws Exception {
			
		User user = userService.findUser(id);
		if(user!=null && user.getIsActivated()!=true) {
			user.setIsActivated(true);
			userService.updateUser(user);
			
		}
		return ResponseEntity.ok().body(user);	
		
	}

	@CrossOrigin(origins ="*", allowedHeaders = "*")
	@GetMapping(path = "/user")
	public  List<User> listUser(){
		return userService.listUser();
	}
	

	/*@DeleteMapping(path = "/user/delete/{userId}")
public  String deleteUser(){
    userService.deleteUser(userId);
    return "user deleted";
}*/

	@CrossOrigin(origins ="*", allowedHeaders = "*", methods = RequestMethod.DELETE)
	@DeleteMapping(path = "/user/{userID}")
	public ResponseEntity<HashMap<String, String>> deleteUser(@PathVariable(name = "userID")Long userID){
		userService.deleteUser(userID);
		HashMap<String, String> responce = new HashMap<>();
		responce.put("message", "Deleted Successfully");
		return ResponseEntity.ok().body(responce);
	}
	
	
	
	@PutMapping("/user/{id}")
	@CrossOrigin(origins ="*", allowedHeaders = "*", methods = RequestMethod.PUT)
	public User editUser(@PathVariable Long id,@RequestParam("image") MultipartFile image,@RequestParam("fullName") String fullName) throws IOException {
		User user = userService.findUser(id);
		if(!image.isEmpty()) {
			File file = new File(System.getProperty("user.dir")).getCanonicalFile();
	        System.out.println("Parent directory : " + file.getParent());
	        File file2 = new File(file.getParent()+ "/frontend/src/assets/images/users/"+image.getOriginalFilename());
	        file2.createNewFile();
	        FileOutputStream fout = new FileOutputStream(file2);
	        fout.write(image.getBytes());
	        user.setImage("/assets/images/users/"+image.getOriginalFilename());
		}
		if(!fullName.isEmpty()) {
			user.setFullName(fullName);	
		}
		return userService.updateUser(user);
	}
	
	

}
