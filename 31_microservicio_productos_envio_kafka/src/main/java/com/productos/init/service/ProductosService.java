package com.productos.init.service;

import com.productos.init.model.Producto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductosService {
	Flux<Producto> catalogo();
	Flux<Producto> productosPorCategoria(String categoria);
	Mono<Producto> buscarProducto(int codigo);
	Mono<Producto> altaProducto(Producto producto);
	Mono<Producto> eliminarProducto(int codigo);
}
