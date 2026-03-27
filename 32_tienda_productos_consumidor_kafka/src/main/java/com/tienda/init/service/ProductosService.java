package com.tienda.init.service;

import com.tienda.init.model.Producto;

import reactor.core.publisher.Flux;

public interface ProductosService {
	Flux<Producto> catalogoPorStock(int stock);
	
}
