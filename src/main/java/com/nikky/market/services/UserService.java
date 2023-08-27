package com.nikky.market.services;

import com.nikky.market.entities.User;

import java.util.List;



public interface UserService {
	
	User findByUsername(String email);
	
	User findByUserId(Long Id);
	
	User saveUser(User user);
	
	User updateUser(User user, Long id);

	void deleteUser(Long Id);
	
	List<User> listUsers();

}
