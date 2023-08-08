package com.dannamodas.sistema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dannamodas.sistema.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
