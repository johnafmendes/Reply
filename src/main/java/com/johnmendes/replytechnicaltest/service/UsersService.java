package com.johnmendes.replytechnicaltest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnmendes.replytechnicaltest.entity.User;
import com.johnmendes.replytechnicaltest.exceptions.UserExistException;
import com.johnmendes.replytechnicaltest.exceptions.UserNotFoundException;
import com.johnmendes.replytechnicaltest.repository.UserRepository;

@Service
public class UsersService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> list(){
		return userRepository.findAll();
	}
	
	public User save(User user){
		if(user.getUserName() != null){
			User result = userRepository.findByUserName(user.getUserName());
			
			if(result != null){
				throw new UserExistException("UserName already exist.");
			}
		}
		return userRepository.save(user);
	}
	
	public User findByUserName(String userName){
		User user = userRepository.findByUserName(userName);
		
		if(user == null){
			throw new UserNotFoundException("User not found");
		}
		return user;
	}
	
	public User findByCreditCard(String creditCard){
		User user = userRepository.findByCreditCard(creditCard);
		
		if(user == null){
			throw new UserNotFoundException("Credit Card not found");
		}
		return user;
	}
}
