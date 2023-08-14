package com.dannamodas.sistema.entities.dto;

import java.io.Serializable;
import java.util.Date;

import com.dannamodas.sistema.entities.Category;
import com.dannamodas.sistema.entities.User;

public class UserDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private Date dateOfBirth;
	private Category cat;
	
	public UserDTO(User obj) {
		this.id = obj.getId();
		this.name = obj.getName();
		this.dateOfBirth = obj.getDateOfBirth();
		this.cat = obj.getCategory();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public Category getCategory() {
		return cat;
	}

	public void setCategory(Category cat) {
		this.cat = cat;
	}
}
