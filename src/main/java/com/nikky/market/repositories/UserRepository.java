package com.nikky.market.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nikky.market.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUsername(String username);
	
	Optional<User> findByEmail(String email);

	
	boolean existsByUsername(String username);
	
	boolean existsByEmail(String email);
}
