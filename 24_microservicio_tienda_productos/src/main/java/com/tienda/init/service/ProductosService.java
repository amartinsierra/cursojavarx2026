package com.tienda.init.service;

import com.tienda.init.model.Producto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductosService {
	Flux<Producto> catalogoPorStock(int stock);
	Mono<Producto> alta(Producto producto);
}
