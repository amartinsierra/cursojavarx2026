package com.tienda.init.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.tienda.init.model.Producto;

import reactor.core.publisher.Flux;

@Service
public class ProductosServiceImpl implements ProductosService {
	private final String urlBase="http://localhost:9000/";
	@Value("${topico}")
	String topico;
	@Override
	public Flux<Producto> catalogoPorStock(int stock) {
		WebClient client=WebClient.create(urlBase+"productos");
		return client
				.get()
				.accept(MediaType.APPLICATION_NDJSON)
				.retrieve()
				.bodyToFlux(Producto.class) //Flux<Producto>
				.filter(p->p.getStock()>=stock);
	}
	@KafkaListener(topics="${topico}",groupId = "tiendaGroup")
	public void procesarProductos(Producto producto) {
		System.out.println("Producto en tópico: "+producto.getNombre());
	}
}
