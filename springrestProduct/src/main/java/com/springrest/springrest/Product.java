package com.springrest.springrest;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Product implements Serializable {
	
	@Id
	private int id;
	private String ProductName;
	private String description;

	public Product(int id, String productName, String description) {
		super();
		this.id = id;
		ProductName = productName;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", ProductName=" + ProductName + ", description=" + description + "]";
	}

}
