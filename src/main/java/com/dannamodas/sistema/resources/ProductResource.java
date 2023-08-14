package com.dannamodas.sistema.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dannamodas.sistema.entities.Product;
import com.dannamodas.sistema.exceptions.ResourceNotFoundException;
import com.dannamodas.sistema.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {
	
	@Autowired
	ProductService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Product> findById(@PathVariable Long id){
		try {
		Product obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
		} catch(ResourceNotFoundException e) {
			throw new ResourceNotFoundException("Product not found with ID: " + id);
		}
	}
	
	@GetMapping
	public ResponseEntity<List<Product>> findAll(){
		List<Product> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id){
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Product> update(@RequestBody Product product, @PathVariable Long id){
		Product productExists = service.findById(id);
		
		if (productExists != null) {
			productExists.setName(product.getName());
			productExists.setPrice(product.getPrice());
			productExists.setDescription(product.getDescription());
			
			Product obj = service.update(product);
			return ResponseEntity.ok().body(obj);
		}
		else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
}
