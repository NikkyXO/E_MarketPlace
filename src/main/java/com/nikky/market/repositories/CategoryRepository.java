package com.nikky.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nikky.market.entities.market.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
}
