package com.tienda.init.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tienda.init.model.Producto;
import com.tienda.init.service.ProductosService;

import reactor.core.publisher.Flux;

@RestController
public class ProductosController {
	ProductosService productosService;

	public ProductosController(ProductosService productosService) {
		this.productosService = productosService;
	}
	@GetMapping(value="tienda",produces=MediaType.APPLICATION_NDJSON_VALUE)
	public Flux<Producto> productosStock(@RequestParam int stock){
		return productosService.catalogoPorStock(stock);
	}
}
