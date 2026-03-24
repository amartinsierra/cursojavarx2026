package com.rx.cliente;

import com.rx.service.GeneradorService;

public class Consumidor {

	public static void main(String[] args) {
		GeneradorService service = new GeneradorService(); 
		service.cursos()
		.filter(curso -> curso.getDuracion() < 40) 
		.subscribe(curso -> System.out.println("Curso: " + curso.getNombre() + ", Duración: " + curso.getDuracion() + " horas")); 
		
		
		

	}

}
