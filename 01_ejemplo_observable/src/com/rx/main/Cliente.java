package com.rx.main;

import com.rx.service.GenerarDatos;

public class Cliente {

	public static void main(String[] args) throws InterruptedException {
		var generarDatos=new GenerarDatos();
		generarDatos.numeros() //Observable
		.subscribe(System.out::println);
		for(int i=1;i<=100;i++) {
			Thread.sleep(50);
			System.out.println("Otras tareas");
		}

		Thread.sleep(10000);
	}

}
