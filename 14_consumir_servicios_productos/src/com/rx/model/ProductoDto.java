package com.rx.model;

public class ProductoDto {
	private int codigo;
	private int stock;
	private double precio;
	public ProductoDto(int codigo, int stock, double precio) {
		this.codigo = codigo;
		this.stock = stock;
		this.precio = precio;
	}
	public ProductoDto() {
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
}
