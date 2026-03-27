package com.rx.main.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.rx.main.model.Producto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductosRepository extends ReactiveMongoRepository<Producto, String> {
	Flux<Producto> findByCategoria(String categoria);
	
	Mono<Void> deleteByNombre(String name);
	
	@Query(value="{precioUnitario:{$gt:?0}}", delete = true)
	Mono<Void> deletePrecio(double precioMax);
}
