package com.rx.main.service;

import com.rx.main.model.Producto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductosService {
	Flux<Producto> catalogo();
	Flux<Producto> productosCategoria(String categoria);
	Mono<Producto> productoCodigo(String cod);
	Mono<Void> altaProducto(Producto producto);
	Mono<Producto> eliminarProducto(String cod);
	Mono<Producto> actualizarPrecio(String cod, double precio);
}
