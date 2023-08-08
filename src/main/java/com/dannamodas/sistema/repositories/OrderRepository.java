package com.dannamodas.sistema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dannamodas.sistema.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
	
}
