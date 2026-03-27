package com.rx.main.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Table("productos")
public class Producto implements Persistable<Integer>{
	@Id
	@Column(value="codProducto")
	private int codProducto;
	private String nombre;
	private String categoria;
	@Column(value="precioUnitario")
	private double precioUnitario;
	private int stock;
	@Transient
	private boolean nuevo;
	@JsonIgnore
	@Override
	public Integer getId() {
		return codProducto;
	}
	@JsonIgnore
	@Override
	public boolean isNew() {
		return nuevo;
	}
	
	
	public Producto() {
		super();
	}
	public Producto(int codProducto, String nombre, String categoria, double precioUnitario, int stock) {
		super();
		this.codProducto = codProducto;
		this.nombre = nombre;
		this.categoria = categoria;
		this.precioUnitario = precioUnitario;
		this.stock = stock;
	}
	public int getCodProducto() {
		return codProducto;
	}
	public void setCodProducto(int codProducto) {
		this.codProducto = codProducto;
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
	public boolean isNuevo() {
		return nuevo;
	}
	public void setNuevo(boolean nuevo) {
		this.nuevo = nuevo;
	}
	
}