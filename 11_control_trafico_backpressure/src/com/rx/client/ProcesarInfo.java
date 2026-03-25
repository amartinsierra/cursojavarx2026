package com.rx.client;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.rx.service.EnvioInformacionService;

import io.reactivex.rxjava3.schedulers.Schedulers;

public class ProcesarInfo {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executor=Executors.newCachedThreadPool();
		var flujoDatos=new EnvioInformacionService().enviarInfo();
		//lanzar un hilo que simule un radar que multa a los vehículos
		//que superen los 120 km/h. El radar procesa a una velocidad de 30 milisegundos
		
		executor.submit(()->{
			flujoDatos.observeOn(Schedulers.io())
			.subscribe(c->{
				if(c.getVelocidad()>120) {
					System.out.println("Multado el coche "+c.getMatricula()+" con velocidad "+c.getVelocidad());
				}
				Thread.sleep(30);
			});
		});
		
		//lanzar otro hilo que muestre la velocidad media de los vehiculos
		//recibidos durante los últimos 500 milisegundos. Procesa los vehículos
		//A una velocidad de 20 milisegundos
		executor.submit(()->{
			flujoDatos.observeOn(Schedulers.io())
			.buffer(500, TimeUnit.MILLISECONDS) //tiene en cuenta solo los coches que han pasado en los ultimos 500ms
            .map(lista -> lista.stream()
                    .mapToInt(c -> c.getVelocidad())
                    .average()
                    .orElse(0))
            .subscribe(media ->System.out.println("velocidad media "+media));
		});
		executor.awaitTermination(15, TimeUnit.SECONDS);
		executor.shutdown();
	}
}
