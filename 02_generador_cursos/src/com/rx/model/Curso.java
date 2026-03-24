package com.rx.model;

public class Curso {
	private String nombre;
	private int duracion;
	public Curso(String nombre, int duracion) {
		super();
		this.nombre = nombre;
		this.duracion = duracion;
	}
	public Curso() {
		super();
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	
}
