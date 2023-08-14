package com.dannamodas.sistema.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dannamodas.sistema.entities.User;
import com.dannamodas.sistema.entities.dto.UserDTO;
import com.dannamodas.sistema.exceptions.ResourceNotFoundException;
import com.dannamodas.sistema.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	UserService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) {
		try {
			User obj = service.findById(id);
			return ResponseEntity.ok().body(obj);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> list = service.findAll();
		List<UserDTO> listDto = list.stream().map(obj -> new UserDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<User> update(@RequestBody User user, @PathVariable Long id) {
		User existUser = service.findById(id);
		if (existUser != null) {
			existUser.setName(user.getName());
			existUser.setDateOfBirth(user.getDateOfBirth());
			existUser.setCpf(user.getCpf());
			existUser.setPassword(user.getPassword());
			User obj = service.update(existUser);
			return ResponseEntity.ok().body(obj);
		} else {
			throw new ResourceNotFoundException("User not found with ID: " + id);
		}
	}
	
	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User user){
		User obj = service.insert(user);
		return ResponseEntity.ok().body(obj);
	}
}
