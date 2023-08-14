package com.nikky.market.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nikky.market.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
//	@Query(value = """
//			select u from User u
//            where u.email = :email
//
//            """)
	Optional<User> findByEmail(String email);
	
//	Optional<User> findByUsername(String username);
	
	


}
