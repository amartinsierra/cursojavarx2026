package com.rx.client;

import com.rx.service.GenerarDatosService;

import io.reactivex.rxjava3.schedulers.Schedulers;

public class ProcesarDatos {

	public static void main(String[] args) throws InterruptedException {
		var generarDatos=new GenerarDatosService();
		generarDatos.generarCodigos()
		.observeOn(Schedulers.io())
		.subscribe(d->{
			System.out.println("Consumiendo "+d+" hilo "+Thread.currentThread().getName());
			Thread.sleep(100);
		});
		
		
		Thread.sleep(15000);

	}

}
