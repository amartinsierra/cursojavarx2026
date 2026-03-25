package com.tienda.init.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tienda.init.model.Venta;

@Service
public class VentasService {

    private final List<Venta> ventas = new ArrayList<>();

    public VentasService() {
        // generate 10 ventas with codes 1..10
        for (int i = 1; i <= 10; i++) {
            double facturacion = Math.round((100.0 + i * 37.5) * 100.0) / 100.0;
            ventas.add(new Venta(i, facturacion));
        }
    }

    public List<Venta> todas() {
        return ventas;
    }
}
