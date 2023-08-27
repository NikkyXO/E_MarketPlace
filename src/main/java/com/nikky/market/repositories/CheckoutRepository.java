package com.nikky.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nikky.market.entities.market.Checkout;

public interface CheckoutRepository extends JpaRepository<Checkout, Long> {

}
