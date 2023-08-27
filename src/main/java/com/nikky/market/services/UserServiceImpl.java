package com.nikky.market.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nikky.market.authentication.AuthResponse;
import com.nikky.market.authentication.RegisterRequest;
import com.nikky.market.controllers.response.UserResponse;
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
	public User updateUser(User user, Long id) {
		var getUser = userRepository.findById(id).get();
		if (getUser != null) {
			if (Objects.nonNull(user.getLocation()) 
					&& !"".equalsIgnoreCase(user.getLocation())) {
				
				getUser.setLocation(user.getLocation());
			}
			
			if (Objects.nonNull(user.getRole()))  {	
				getUser.setRole(user.getRole());
			}
			
			return userRepository.save(getUser);
		}
		else {
			return null;
		}
		
	}
	

	@Override
	public void deleteUser(Long Id) {
		User user = userRepository.findById(Id).orElseThrow(
				() -> new EntityNotFoundException("User with ID " + Id + " not found"));
		userRepository.delete(user);
		
	}
	
	public UserResponse updateUser(RegisterRequest request) {
		
	    var user = User.builder()
	        .location(request.getLocation())
	        .build();
	    
	    userRepository.save(user);	 
	    return UserResponse.builder()
	    		.message("User is Successfully Updated")
	        .build();
	  }
	
	public List<User> listUsers() {
        return userRepository.findAll();
	}



}
