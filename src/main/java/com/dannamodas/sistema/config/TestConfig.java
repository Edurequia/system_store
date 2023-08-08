package com.dannamodas.sistema.config;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.dannamodas.sistema.entities.Order;
import com.dannamodas.sistema.entities.Product;
import com.dannamodas.sistema.enums.OrderStatus;
import com.dannamodas.sistema.repositories.OrderRepository;
import com.dannamodas.sistema.repositories.ProductRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{

	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		orderRepository.deleteAll();
		productRepository.deleteAll();
		
		Product p1 = new Product(null, "Notebook", 3000.0, "Aparelho eletronico");
		Product p2 = new Product(null, "Mouse", 500.0, "Aparelho gamer");
		Product p3 = new Product(null, "TV", 2500.0, "50 polegadas");
		Product p4 = new Product(null, "CPU", 1000.0, "Gabinete acer");
		Product p5 = new Product(null, "Monitor", 550.0, "Tela led");
		
		productRepository.saveAll(Arrays.asList(p1, p2));
		List<Product> products1 = Arrays.asList(p1, p3);
		List<Product> products2 = Arrays.asList(p5, p1);
		List<Product> products3 = Arrays.asList(p4, p3);
		
		
		Order o1 = new Order(null, new Date(), OrderStatus.PAID, products1);
		Order o2 = new Order(null, new Date(), OrderStatus.CANCELED, products2);
		Order o3 = new Order(null, new Date(), OrderStatus.WAITING_PAYMENT, products3);
		
		
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
		
	}
	
	
	
}
