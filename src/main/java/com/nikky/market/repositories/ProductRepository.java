package com.nikky.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nikky.market.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
