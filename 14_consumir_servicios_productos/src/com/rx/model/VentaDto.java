package com.rx.model;

public class VentaDto {
	private int codigo;
	private double facturacion;
	public VentaDto(int codigo, double facturacion) {
		this.codigo = codigo;
		this.facturacion = facturacion;
	}
	public VentaDto() {
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public double getFacturacion() {
		return facturacion;
	}
	public void setFacturacion(double facturacion) {
		this.facturacion = facturacion;
	}
	
}
