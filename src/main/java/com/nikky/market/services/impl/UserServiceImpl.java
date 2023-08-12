package com.nikky.market.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nikky.market.entities.User;
import com.nikky.market.repositories.UserRepository;
import com.nikky.market.services.UserService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User findByUsername(String email) {
		// userRepository.findOne(email);
		return null;
	}

	@Override
	public User findByUserId(Long Id) {
		return userRepository.findById(Id).orElse(null);
	}

	@Override
	public User saveUser(User user) {

		return userRepository.save(user);
	}

	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(Long Id) {
		User user = userRepository.findById(Id).orElseThrow(
				() -> new EntityNotFoundException("User with ID " + Id + " not found"));
		userRepository.delete(user);
		
		
		
		
	}



}
