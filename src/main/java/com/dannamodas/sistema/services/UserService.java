package com.dannamodas.sistema.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dannamodas.sistema.entities.User;
import com.dannamodas.sistema.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository repository;
	
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.get();
	}
	
	public List<User> findAll(){
		List<User> list = repository.findAll();
		return list;
	}
	
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
	
	public User update(User user) {
		User obj = repository.save(user);
		return obj;
	}
	
}
