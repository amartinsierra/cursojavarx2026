package com.rx.model;

public class DatosDto {
	private int codigo;
	private double precio;
	private double facturacion;
	public DatosDto(int codigo, double precio, double facturacion) {
		this.codigo = codigo;
		this.precio = precio;
		this.facturacion = facturacion;
	}
	public DatosDto() {
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public double getFacturacion() {
		return facturacion;
	}
	public void setFacturacion(double facturacion) {
		this.facturacion = facturacion;
	}
	
}
