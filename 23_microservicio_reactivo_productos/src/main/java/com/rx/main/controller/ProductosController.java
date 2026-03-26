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
		super();
		this.productosService = productosService;
	}
	
	@GetMapping(value="productos",produces=MediaType.APPLICATION_NDJSON_VALUE)
	public ResponseEntity<Flux<Producto>> catalogoProductos(){
		return ResponseEntity.ok(productosService.catalogo());
	}
	
	@GetMapping(value="productos/por-categoria",produces=MediaType.APPLICATION_NDJSON_VALUE)
	public ResponseEntity<Flux<Producto>> porCategoria(@RequestParam String categoria){
		return ResponseEntity.ok(productosService.productosPorCategoria(categoria));
	}
	@GetMapping(value="productos/{id}",produces=MediaType.APPLICATION_NDJSON_VALUE)
	public Mono<ResponseEntity<Producto>> porCategoria(@PathVariable int id){
		return productosService.buscarProducto(id)//Mono<Producto>
				.map(p->new ResponseEntity<>(p,HttpStatus.OK));
	}
	@PostMapping(value="productos",produces=MediaType.APPLICATION_NDJSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public Mono<ResponseEntity<Producto>> alta(@RequestBody Producto producto){
		return productosService.altaProducto(producto)//Mono<Producto>
				.map(p->new ResponseEntity<>(p,HttpStatus.OK))//Mono<ResponeEntity<Producto>>
				.switchIfEmpty(Mono.just(new ResponseEntity<>(HttpStatus.CONFLICT)));
				
	}
	@DeleteMapping(value="productos/{codigoProducto}",produces=MediaType.APPLICATION_NDJSON_VALUE)
	public Mono<ResponseEntity<Producto>> eliminar(@PathVariable int codigoProducto){
		return productosService.eliminarProducto(codigoProducto)//Mono<Producto>
				.map(p->new ResponseEntity<>(p,HttpStatus.OK));//Mono<ResponeEntity<Producto>>			
	}
	
	

}
