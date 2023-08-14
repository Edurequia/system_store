package com.dannamodas.sistema.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.dannamodas.sistema.entities.Category;
import com.dannamodas.sistema.entities.Order;
import com.dannamodas.sistema.entities.OrderItem;
import com.dannamodas.sistema.entities.Product;
import com.dannamodas.sistema.entities.User;
import com.dannamodas.sistema.enums.OrderStatus;
import com.dannamodas.sistema.repositories.CategoryRepository;
import com.dannamodas.sistema.repositories.OrderItemRepository;
import com.dannamodas.sistema.repositories.OrderRepository;
import com.dannamodas.sistema.repositories.ProductRepository;
import com.dannamodas.sistema.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private UserRepository userRepository;
	
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
		
		User u1 = new User(null, "Eduardo Requia", sdf.parse("22/11/1998"), "09201902930", "senhaaaaa");
		User u2 = new User(null, "Vanessa Guasque", sdf.parse("07/01/2000"), "93283829829", "senhaa");
		User u3 = new User(null, "Gustavo Requia", sdf.parse("30/09/2000"), "28382288282", "senhaaa");
		
		u1.setCategory(a);
		u2.setCategory(g);
		u3.setCategory(f);
		
		Order o1 = new Order(null, sdf.parse("10/07/2023"), OrderStatus.PAID, u3);
		Order o2 = new Order(null, sdf.parse("10/06/2023"), OrderStatus.CANCELED, u2);
		Order o3 = new Order(null, sdf.parse("10/05/2023"), OrderStatus.WAITING_PAYMENT, u1); 
		
		userRepository.saveAll(Arrays.asList(u1, u2, u3));
		orderRepository.saveAll(Arrays.asList(o1, o2, o3)); 
		
		OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
		OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
		OrderItem oi3 = new OrderItem(o2, p5, 2, p5.getPrice());
		OrderItem oi4 = new OrderItem(o3, p4, 3, p4.getPrice());
		
		orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));
		
	}
	
	
	
}
