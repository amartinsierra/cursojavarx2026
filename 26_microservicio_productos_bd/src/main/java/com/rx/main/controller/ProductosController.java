package com.rx.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@CrossOrigin("*")
@RestController
public class ProductosController {
	
	ProductosService productosService;
	
	public ProductosController(ProductosService productosService) {

		this.productosService = productosService;
	}
	@GetMapping(value="productos")
	public ResponseEntity<Flux<Producto>> productos(){
		return new ResponseEntity<>(productosService.catalogo(),HttpStatus.OK);
	}
	@GetMapping(value="productos/por-categoria")
	public ResponseEntity<Flux<Producto>> productosCategoria(@RequestParam String categoria){
		return new ResponseEntity<>(productosService.productosPorCategoria(categoria),HttpStatus.OK);
	}
	
	@GetMapping(value="productos/{cod}")
	public ResponseEntity<Mono<Producto>> productoCodigo(@PathVariable int cod) {
		return new ResponseEntity<>(productosService.buscarProducto(cod),HttpStatus.OK);
	}
	@PostMapping(value="productos",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Mono<Void>> altaProducto(@RequestBody Producto producto) {
		producto.setNuevo(true);
		return new ResponseEntity<>(productosService.altaProducto(producto),HttpStatus.OK);
	}
	@DeleteMapping(value="productos")
	public Mono<ResponseEntity<Producto>> eliminarProducto(@RequestParam("cod") int cod) {
		return productosService.eliminarProducto(cod)//Mono<Producto>
				.map(p->new ResponseEntity<>(p,HttpStatus.OK))//Mono<ResponseEntity<Producto>>
				.switchIfEmpty(Mono.just(new ResponseEntity<>(HttpStatus.NOT_FOUND)));//Mono<ResponseEntity<Producto>>
	}
	
}

