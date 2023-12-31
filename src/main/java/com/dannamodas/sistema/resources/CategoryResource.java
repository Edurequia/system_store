package com.dannamodas.sistema.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dannamodas.sistema.entities.Category;
import com.dannamodas.sistema.exceptions.ResourceNotFoundException;
import com.dannamodas.sistema.services.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {
	
	@Autowired
	CategoryService service;
	
	@GetMapping(value= "/{id}")
	public ResponseEntity<Category> findById(@PathVariable Long id){
		try {
		Category obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
		} catch(ResourceNotFoundException e) {
			throw new ResourceNotFoundException("Category not found with ID: " + id);
		}
	}
	
	@GetMapping
	public ResponseEntity<List<Category>> findAll(){
		List<Category> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	
}
