package com.rx.client;

import com.rx.service.InfoProductosService;

import io.reactivex.rxjava3.schedulers.Schedulers;

public class ProcesarDatos {
	 public static void main(String[] args) throws InterruptedException {
		var infoService=new InfoProductosService();
		infoService.getData()
		.observeOn(Schedulers.computation())
		.subscribe(d->{
			System.out.println("codigo: "+d.getCodigo()+" precio "+d.getPrecio()+" facturación: "+d.getFacturacion());
			Thread.sleep(50);
		});
		
		Thread.sleep(15000);
	 }
}
