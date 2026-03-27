package com.rx.main.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="productos")
public class Producto {
	@Id	
	private String id;
	private String nombre;
	private String categoria;	
	private double precioUnitario;
	private int stock;
	public Producto(String id, String nombre, String categoria, double precioUnitario, int stock) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.categoria = categoria;
		this.precioUnitario = precioUnitario;
		this.stock = stock;
	}
	public Producto() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public double getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
}
