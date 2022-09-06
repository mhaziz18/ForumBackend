package com.example.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entities.User;



@Service
public interface UserService {
    public User creatUser(User user);
    public User updateUser(User user);
    public void deleteUser(Long userId);
    public User findUser(Long id);
    public List<User> listUser();
}
