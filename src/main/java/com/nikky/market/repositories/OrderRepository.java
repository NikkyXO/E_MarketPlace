package com.nikky.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nikky.market.entities.market.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
