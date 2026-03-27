package model;

import java.time.LocalDateTime;

public class CryptoData {
	private String nombre;
	private double valor;
	private LocalDateTime fecha;
	public CryptoData(String nombre, double valor, LocalDateTime fecha) {
		super();
		this.nombre = nombre;
		this.valor = valor;
		this.fecha = fecha;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	
}
