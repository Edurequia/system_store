package com.dannamodas.sistema.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dannamodas.sistema.entities.Category;
import com.dannamodas.sistema.repositories.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	CategoryRepository repository;
	
	public Category findById(Long id) {
		Optional<Category> obj = repository.findById(id);
		return obj.get();
	}
	
	public List<Category> findAll(){
		List<Category> list = repository.findAll();
		return list;
	}
	
}
