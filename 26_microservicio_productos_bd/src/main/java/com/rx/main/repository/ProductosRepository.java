package com.rx.main.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.rx.main.model.Producto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductosRepository extends ReactiveCrudRepository<Producto, Integer> {

	Flux<Producto> findByCategoria(String categoria);
	@Transactional
	Mono<Void> deleteByCategoria(String categoria);
}
