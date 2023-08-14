package com.dannamodas.sistema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dannamodas.sistema.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
}
