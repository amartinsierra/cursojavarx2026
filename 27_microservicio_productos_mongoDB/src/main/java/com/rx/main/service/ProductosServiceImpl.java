package com.rx.main.service;

import java.time.Duration;

import org.springframework.stereotype.Service;

import com.rx.main.model.Producto;
import com.rx.main.repository.ProductosRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class ProductosServiceImpl implements ProductosService {
	
	ProductosRepository productosRepository;
	
	

	public ProductosServiceImpl(ProductosRepository productosRepository) {
		super();
		this.productosRepository = productosRepository;
	}

	@Override
	public Flux<Producto> catalogo() {
		return productosRepository.findAll()//Flux<Producto>
				.delayElements(Duration.ofMillis(500));//Flux<Producto>
	}

	@Override
	public Flux<Producto> productosCategoria(String categoria) {
		return productosRepository.findByCategoria(categoria);
	}

	@Override
	public Mono<Producto> productoCodigo(String cod) {
		return productosRepository.findById(cod);
				
	}
	@Override
	public Mono<Void> altaProducto(Producto producto) {
		return productoCodigo(producto.getId())//Mono<Producto>
				.switchIfEmpty(Mono.just(producto).flatMap(p->productosRepository.save(p)))//Mono<Producto>
				.then();//Mono<Void>
	}

	@Override
	public Mono<Producto> eliminarProducto(String cod) {
		return productoCodigo(cod) //Mono<Producto>
				.flatMap(p->productosRepository.deleteById(cod) //Mono<Void>
						.then(Mono.just(p))//Mono<Producto>
				);//Mono<Producto>
				
	}

	@Override
	public Mono<Producto> actualizarPrecio(String cod, double precio) {
		return productoCodigo(cod) //Mono<Producto>
				.flatMap(p->{
					p.setPrecioUnitario(precio);
					return productosRepository.save(p);//Mono<Producto>
				});//Mono<Producto>
	}

}
