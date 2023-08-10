package com.dannamodas.sistema.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.dannamodas.sistema.entities.Category;
import com.dannamodas.sistema.entities.Order;
import com.dannamodas.sistema.entities.Product;
import com.dannamodas.sistema.enums.OrderStatus;
import com.dannamodas.sistema.repositories.CategoryRepository;
import com.dannamodas.sistema.repositories.OrderRepository;
import com.dannamodas.sistema.repositories.ProductRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Category a = new Category(null, "Acessórios");
		Category b = new Category(null, "Roupas");
		Category c = new Category(null, "Calçados");
		Category d = new Category(null, "Acessórios + Roupas + Calçados");
		Category e = new Category(null, "Acessórios + Roupas");
		Category f = new Category(null, "Acessórios + Calçados");
		Category g = new Category(null, "Roupas + Calçados");
		
		categoryRepository.saveAll(Arrays.asList(a, b, c, d, e, f, g));
		
		Product p1 = new Product(null, "Blusa", 3000.0, "Cropped");
		Product p2 = new Product(null, "Anel", 500.0, "Brilhoso");
		Product p3 = new Product(null, "Nike jordan", 2500.0, "Exclusivo nike");
		Product p4 = new Product(null, "Saia", 1000.0, "Mini jeans");
		Product p5 = new Product(null, "Colar", 550.0, "Ponto de luz");
		
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		
		p1.setCategories(b);
		p2.setCategories(a);
		p3.setCategories(c);
		p4.setCategories(b);
		p5.setCategories(a);
		
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		
		Order o1 = new Order(null, sdf.parse("10/07/2023"), OrderStatus.PAID);
		Order o2 = new Order(null, sdf.parse("10/06/2023"), OrderStatus.CANCELED);
		Order o3 = new Order(null, sdf.parse("10/05/2023"), OrderStatus.WAITING_PAYMENT); 
		
		orderRepository.saveAll(Arrays.asList(o1, o2, o3)); 
		
		o1.getProduct().add(p1);
		o2.getProduct().add(p2);
		o3.getProduct().add(p3);
		
		orderRepository.saveAll(Arrays.asList(o1, o2, o3)); 
		
	}
	
	
	
}
