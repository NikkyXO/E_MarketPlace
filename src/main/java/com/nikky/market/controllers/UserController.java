package com.nikky.market.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nikky.market.entities.User;
import com.nikky.market.repositories.UserRepository;
import com.nikky.market.services.impl.UserServiceImpl;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserServiceImpl userservice;

	
	@PostMapping("/create")
	public User addUser(@RequestBody User user) {
		return userservice.saveUser(user);
	}
	
}
