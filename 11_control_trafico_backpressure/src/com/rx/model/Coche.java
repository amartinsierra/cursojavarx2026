package com.rx.model;

public class Coche {
	private String matricula;
	private int velocidad;
	public Coche(String matricula, int velocidad) {
		this.matricula = matricula;
		this.velocidad = velocidad;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public int getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}
	
}
