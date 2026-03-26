package com.rx.client;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

public class ProcesarDatos {
	private static String URL="http://localhost:8001/";
	public static void main(String[] args) throws InterruptedException {
		WebClient webClient=WebClient.create(URL);
		webClient
		.get()
		.uri("nombres")
		.accept(MediaType.TEXT_EVENT_STREAM)
		.retrieve()
		.bodyToFlux(String.class) //Flux<String>
		.subscribe(s->System.out.println(s));
		for(int i=1;i<=100;i++) {
			Thread.sleep(50);
			System.out.println("Otras tareas");
		}
		Thread.sleep(5000);
	}

}
