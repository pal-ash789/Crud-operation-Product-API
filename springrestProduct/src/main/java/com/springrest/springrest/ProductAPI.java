package com.springrest.springrest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductAPI {

	int id;

	@Autowired
	public SessionFactory factory;

	List<Product> list;

	ProductAPI() {

		System.out.println("constructor is called");
	}

	@PostConstruct
	void init() {

		getLatestData();
	}

	void getLatestData() {

		Session session = factory.openSession();
		Query query = session.createQuery("from Product");
		list = query.list();
		System.out.println("Init method is called . Data from database ");
		System.out.println(list);
	}

	
	
	@GetMapping("/allProduct")
	List<Product> allProduct() {
		getLatestData();

		list = new ArrayList<Product>();
		list.add(new Product(100, "Mobile Phone ", "Used fo calling ang social media"));
		list.add(new Product(200, "Ipod", "Used for listining of music"));
		return list;
	}

	@GetMapping("/product/{id}")
	Product getProduct(@PathVariable int id) {

		Session session = factory.openSession();

		Product product = session.load(Product.class, id);

		return product;
	}

	@PostMapping("/product")
	List<Product> addProduct(@RequestBody Product[] product) {
		Session session = factory.openSession();

		Transaction tx = session.beginTransaction();

		for (Product products : product)
			session.save(product);

		tx.commit();

		getLatestData();
		return list;

	}

	@PutMapping("/product")
	List<Product> updateStudent(@RequestBody Product clientProduct) {

		Session session = factory.openSession();

		Transaction tx = session.beginTransaction();

		session.saveOrUpdate(clientProduct);

		tx.commit();

		getLatestData();

		return list;

	}

	@DeleteMapping("/product/{id}")
	List<Product> deleteProduct(@PathVariable int id) {
		Session session = factory.openSession();

		Product product = session.load(Product.class, id);

		Transaction tx = session.beginTransaction();

		session.delete(product);

		tx.commit();

		getLatestData();

		return list;
	}

}
