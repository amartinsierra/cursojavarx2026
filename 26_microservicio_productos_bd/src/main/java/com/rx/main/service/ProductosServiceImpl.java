package com.rx.main.service;

import java.time.Duration;

import org.springframework.stereotype.Service;

import com.rx.main.model.Producto;
import com.rx.main.repository.ProductosRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductosServiceImpl implements ProductosService {
	
	ProductosRepository repository;
	

	public ProductosServiceImpl(ProductosRepository repository) {
		this.repository = repository;
	}

	@Override
	public Flux<Producto> catalogo() {
		return repository.findAll().delayElements(Duration.ofMillis(500));
	}

	@Override
	public Flux<Producto> productosPorCategoria(String categoria) {
		return repository.findByCategoria(categoria);
	}

	@Override
	public Mono<Producto> buscarProducto(int codigo) {
		return repository.findById(codigo);
	}

	@Override
	public Mono<Void> altaProducto(Producto producto) {
		return buscarProducto(producto.getCodProducto())//Mono<Producto>
				.switchIfEmpty(Mono.just(producto)
						.flatMap(p->repository.save(p)))//Mono<Producto>
				.then();//Mono<Void>
	}

	@Override
	public Mono<Producto> eliminarProducto(int codigo) {
		return buscarProducto(codigo) //Mono<Producto>
				.flatMap(p->repository.deleteById(codigo) //Mono<Void>
						.then(Mono.just(p))//Mono<Producto>
				);//Mono<Producto>
	}

}
