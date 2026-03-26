package com.rx.main.service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rx.main.model.Producto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class ProductosServiceImpl implements ProductosService {
	private static List<Producto> productos=new ArrayList<>(List.of(new Producto(100,"Azucar","Alimentación",1.10,20),
			new Producto(101,"Leche","Alimentación",1.20,15),
			new Producto(102,"Jabón","Limpieza",0.89,30),
			new Producto(103,"Mesa","Hogar",125,4),
			new Producto(104,"Televisión","Hogar",650,10),
			new Producto(105,"Huevos","Alimentación",2.20,30),
			new Producto(106,"Fregona","Limpieza",3.40,6),
			new Producto(107,"Detergente","Limpieza",8.7,12)));
	@Override
	public Flux<Producto> catalogo() {
		return Flux.fromIterable(productos)
				.delayElements(Duration.ofMillis(500));
	}

	@Override
	public Flux<Producto> productosPorCategoria(String categoria) {
		return catalogo()
				.filter(p->p.getCategoria().equals(categoria));
	}

	@Override
	public Mono<Producto> buscarProducto(int codigo) {
		return catalogo()
				.filter(p->p.getCodProducto()==codigo)
				.next();
	}

	@Override
	public Mono<Producto> altaProducto(Producto producto) {
		return buscarProducto(producto.getCodProducto()) // Mono<Producto>
				.flatMap(p -> Mono.<Producto>empty()) // si existe → Mono vacío
	            .switchIfEmpty(
	                Mono.fromSupplier(() -> {
	                    productos.add(producto);
	                    return producto;
	                })
	            );	
	}

	@Override
	public Mono<Producto> eliminarProducto(int codigo) {
		return buscarProducto(codigo)//Mono<Producto>
				.map(p->{
					productos.removeIf(e->e.getCodProducto()==codigo);
					return p;
				});
	}

}
