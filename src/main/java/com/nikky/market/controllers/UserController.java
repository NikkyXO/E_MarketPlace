package com.nikky.market.controllers;

import java.util.List;

import com.nikky.market.errorHandling.errorClasses.ResourceNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.nikky.market.entities.User;
import com.nikky.market.repositories.UserRepository;
import com.nikky.market.services.impl.UserServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Validated
public class UserController {

	private final UserServiceImpl userService;
	private final UserRepository userRepository;


	@GetMapping("/all")
	public ResponseEntity<List<User>> listAllUsers() {
		if (userService.listUsers().isEmpty()) {
			throw new ResourceNotFoundException("No user Found!");
		}

		return ResponseEntity.ok(userService.listUsers());
	}

	@PutMapping(value = "/user/{id}")
	ResponseEntity<User> update(@PathVariable("id") @Min(1) Long id, @Valid @RequestBody User user) {

		User getUser = userRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("User with ID :"+id+" Not Found!"));

		user.setId(getUser.getId());
		userRepository.save(user);
		return ResponseEntity.ok().body(user);

	}

	@GetMapping(value="/user")
	ResponseEntity<User> getByUsername(@RequestParam(required=true) String email) {

		User usr = userRepository.findByEmail(email) //findByUsername(username)
				.orElseThrow(()->new ResourceNotFoundException(email +" NOT Found!"));

		return ResponseEntity.ok().body(usr);
	}

	@DeleteMapping(value="/user/{id}")
	ResponseEntity<String> deleteById(@PathVariable("id") @Min(1) Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("User with ID :"+id+" Not Found!"));

		userRepository.deleteById(user.getId());
		return ResponseEntity.ok().body("Employee deleted with success!");

	}

	@DeleteMapping(value="/deleteAll")
	ResponseEntity<String> deleteAll() {

		userRepository.deleteAll();
		return ResponseEntity.ok().body("User Table has been cleared with success!");

	}
}
