package com.rx.client;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.scheduler.Schedulers;

public class ProcesarDatos {
	private static String URL="http://localhost:8002/";
	public static void main(String[] args) throws InterruptedException {
		WebClient webClient=WebClient.create(URL);
		webClient
		.get()
		.uri("sensor")
		.accept(MediaType.TEXT_EVENT_STREAM)
		.retrieve()
		.bodyToFlux(String.class) //Flux<String>
		.map(s->Double.parseDouble(s))
		.filter(n->n>37||n<10)
		.publishOn(Schedulers.parallel())
		.subscribe(s->System.out.println("Alerta de temperatura!!!: "+s));
		
		Thread.sleep(10000);
	}

}
