package com.tienda.init.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tienda.init.model.Producto;
import com.tienda.init.service.ProductosService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ProductosController {
	ProductosService productosService;

	public ProductosController(ProductosService productosService) {
		this.productosService = productosService;
	}
	@GetMapping(value="tienda",produces=MediaType.APPLICATION_NDJSON_VALUE)
	public Mono<ResponseEntity<Flux<Producto>>> productosStock(@RequestParam int stock){
		return Mono.just(productosService.catalogoPorStock(stock))
				.map(f->ResponseEntity.ok(f));
	}
	@PostMapping(value="tienda",consumes=MediaType.APPLICATION_JSON_VALUE)
	public Mono<ResponseEntity<Mono<Void>>> alta(Producto producto){
		return productosService.alta(producto) 
	            .flatMap(p -> Mono.just(
	                    ResponseEntity.ok(Mono.<Void>empty())  // o Mono<Void> que represente la operación
	            ))
	            .switchIfEmpty(
	                    Mono.just(ResponseEntity.status(HttpStatus.CONFLICT).body(Mono.empty()))
	            );
	}
}
