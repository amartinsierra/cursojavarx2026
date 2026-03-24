package com.rx.main;

import com.rx.service.GenerarDatos;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Cliente {

	public static void main(String[] args) throws InterruptedException {
		var generarDatos=new GenerarDatos();
		generarDatos.numeros() //Observable
		.observeOn(Schedulers.io())
		.map(n->n)
		.observeOn(Schedulers.computation())
		.subscribe(n->System.out.println(n+" consumiendo en el hilo "+Thread.currentThread().getName()));
		for(int i=1;i<=100;i++) {
			Thread.sleep(50);
			System.out.println("Otras tareas en el hilo "+Thread.currentThread().getName());
		}

		Thread.sleep(10000);
	}

}
