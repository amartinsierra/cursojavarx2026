package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true) //para ignorar propiedades de la respuesta
public class Producto {
	private String nombre;
	private double precioUnitario;
	private String categoria;
	private int stock;
	public Producto() {
		
	}
	public Producto(String nombre, double precioUnitario, String categoria, int stock) {
		this.nombre = nombre;
		this.precioUnitario = precioUnitario;
		this.categoria = categoria;
		this.stock = stock;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
}
