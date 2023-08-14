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

import com.dannamodas.sistema.entities.Order;
import com.dannamodas.sistema.exceptions.ResourceNotFoundException;
import com.dannamodas.sistema.services.OrderService;

@RestController
@RequestMapping(value = "/orders")
public class OrderResource {
	
	@Autowired
	OrderService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Order> findById(@PathVariable Long id){
		try {
		Order obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException("Order not found with ID: " + id);
		}
	}
	
	@GetMapping
	public ResponseEntity<List<Order>> findAll(){
		List<Order> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Order> update(@RequestBody Order order, @PathVariable Long id){
		Order existOrder = service.findById(id);
		
		if(existOrder != null) {
			existOrder.setDate(order.getDate());
			existOrder.setOrderStatus(order.getOrderStatus());
			Order obj = service.update(existOrder);
			return ResponseEntity.ok().body(obj);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	} 
	
}
