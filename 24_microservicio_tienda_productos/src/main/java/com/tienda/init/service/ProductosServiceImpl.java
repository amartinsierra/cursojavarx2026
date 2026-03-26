package com.tienda.init.service;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.tienda.init.model.Producto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductosServiceImpl implements ProductosService {
	private final String urlBase="http://localhost:8003/";
	WebClient client=WebClient.create(urlBase);
	@Override
	public Flux<Producto> catalogoPorStock(int stock) {		
		return client
				.get()
				.uri("productos")
				.accept(MediaType.APPLICATION_NDJSON)
				.retrieve()
				.bodyToFlux(Producto.class) //Flux<Producto>
				.filter(p->p.getStock()>=stock);
	}
	@Override
	public Mono<Producto> alta(Producto producto) {	
			return client
					.post()
					.uri("productos")
					.contentType(MediaType.APPLICATION_JSON)
					.bodyValue(producto)
					.retrieve()
					.bodyToMono(Producto.class);
		
	}
	
}
