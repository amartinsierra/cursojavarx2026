package com.rx.main.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rx.main.model.Producto;
import com.rx.main.service.ProductosService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ProductosController {

	ProductosService productosService;
	public ProductosController(ProductosService productosService) {
		this.productosService = productosService;
	}
	@GetMapping(value="productos",produces=MediaType.APPLICATION_NDJSON_VALUE)
	public ResponseEntity<Flux<Producto>> productos(){
		return new ResponseEntity<>(productosService.catalogo(),HttpStatus.OK);
	}
	@GetMapping(value="productos/por-categoria",produces=MediaType.APPLICATION_NDJSON_VALUE)
	public ResponseEntity<Flux<Producto>> productosCategoria(@RequestParam String categoria){
		return new ResponseEntity<>(productosService.productosCategoria(categoria),HttpStatus.OK);
	}
	
	@GetMapping(value="productos/{cod}",produces=MediaType.APPLICATION_NDJSON_VALUE)
	public ResponseEntity<Mono<Producto>> productoCodigo(@PathVariable String cod) {
		return new ResponseEntity<>(productosService.productoCodigo(cod),HttpStatus.OK);
	}
	@PostMapping(value="productos",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Mono<Void>> altaProducto(@RequestBody Producto producto) {
		
		return new ResponseEntity<>(productosService.altaProducto(producto),HttpStatus.OK);
	}
	@DeleteMapping(value="productos",produces=MediaType.APPLICATION_NDJSON_VALUE)
	public Mono<ResponseEntity<Producto>> eliminarProducto(@RequestParam("cod") String cod) {
		return productosService.eliminarProducto(cod)//Mono<Producto>
				.map(p->new ResponseEntity<>(p,HttpStatus.OK))//Mono<ResponseEntity<Producto>>
				.switchIfEmpty(Mono.just(new ResponseEntity<>(HttpStatus.NOT_FOUND)));//Mono<ResponseEntity<Producto>>
	}
	
}
