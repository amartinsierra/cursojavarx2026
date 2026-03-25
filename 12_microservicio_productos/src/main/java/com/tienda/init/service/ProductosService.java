package com.tienda.init.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tienda.init.model.Producto;

@Service
public class ProductosService {

    private final List<Producto> productos = new ArrayList<>();

    public ProductosService() {
        // create 10 sample products
        for (int i = 1; i <= 10; i++) {
            double precio = Math.round((5.0 + i * 2.75) * 100.0) / 100.0;
            int stock = 5 + i * 3;
            productos.add(new Producto(i, precio, stock));
        }
    }

    public List<Producto> catalogo() {
        return productos;
    }
}
