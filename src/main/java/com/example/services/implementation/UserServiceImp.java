package com.example.services.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.User;
import com.example.repositories.UserRepository;
import com.example.services.UserService;

@Service
public class UserServiceImp implements UserService {

	@Autowired 
    private UserRepository userRepository;

    @Override
    public User creatUser(User user) {
        
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
       Optional<User> existingUser=userRepository.findById(user.getId());
       if(existingUser.isPresent()){

        return userRepository.save(user);
    }else
    {return null;
}
       }
    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<User> listUser() {
    
        return userRepository.findAll();
    }
    
	@Override
	public User findUser(Long id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id).get();
	}
}
