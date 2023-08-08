package com.dannamodas.sistema.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dannamodas.sistema.entities.Product;
import com.dannamodas.sistema.repositories.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository repository;
	
	public Product findById(Long id) {
		Optional<Product> obj = repository.findById(id);
		return obj.get();
	}
	
	public List<Product> findAll(){
		List<Product> list = repository.findAll();
		return list;
	}
	
	public void deleteById(Long id) {
		 repository.deleteById(id);
	}
}
