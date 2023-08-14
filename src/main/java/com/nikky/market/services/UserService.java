package com.nikky.market.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nikky.market.entities.User;
import com.nikky.market.repositories.UserRepository;




public interface UserService {
	
	User findByUsername(String email);
	
	User findByUserId(Long Id);
	
	User saveUser(User user);
	
	User updateUser(User user, Long id);

	void deleteUser(Long Id);
	
	List<User> listUsers();

}
