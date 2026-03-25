package com.tienda.init.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienda.init.model.Venta;
import com.tienda.init.service.VentasService;

@RestController
public class VentasController {

    private final VentasService ventasService;

    public VentasController(VentasService ventasService) {
        this.ventasService = ventasService;
    }

    @GetMapping(path = "/ventas", produces = "application/json")
    public List<Venta> todasVentas() {
        return ventasService.todas();
    }
}
