package com.johnmendes.replytechnicaltest.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.SessionScope;

import com.johnmendes.replytechnicaltest.entity.User;

@SessionScope
@Repository
public class UserRepository {
	
	private List<User> userList;
	
	public UserRepository() {
		userList = new ArrayList<User>();
	}
	
	public User findByUserName(String userName) {
		for(User user : userList){
	        if(user.getUserName().contains(userName)) {
	        	return user;
	        }
	    }
		return null;
	}

	public User save(User user) {
		userList.add(user);
		return user;
	}
	
	public List<User> findAll(){
		return userList;
	}

	public User findByCreditCard(String creditCard) {
		for(User user : userList){
	        if(user.getCreditCard().getCreditCard().contains(creditCard)) {
	        	return user;
	        }
	    }
		return null;
	}
}
