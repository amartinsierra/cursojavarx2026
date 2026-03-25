package com.tienda.init.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienda.init.model.Producto;
import com.tienda.init.service.ProductosService;

@RestController
public class ProductosController {

    private final ProductosService productosService;

    public ProductosController(ProductosService productosService) {
        this.productosService = productosService;
    }

    @GetMapping(path = "/productos", produces = "application/json")
    public List<Producto> catalogoProductos() {
        return productosService.catalogo();
    }
}
