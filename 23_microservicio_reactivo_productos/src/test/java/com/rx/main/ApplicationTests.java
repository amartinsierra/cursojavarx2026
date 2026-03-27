package com.rx.main;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rx.main.model.Producto;
import com.rx.main.service.ProductosServiceImpl;

import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class ApplicationTests {
	
	@InjectMocks
	ProductosServiceImpl productosService;

	@Test
	void testCatalogoCategorias() {
		String categoria="Alimentación";
		StepVerifier.create(productosService.productosPorCategoria(categoria))
		.expectNextMatches(p->p.getNombre().equals("Azucar"))
		.expectNextMatches(p->p.getCodProducto()==101)
		.expectNextCount(1)
		.verifyComplete();
	}
	
	@Test
	void testAltaProducto() {
		Producto p1=new Producto(400, "test1", "Limpieza", 10, 20);
		Producto p2=new Producto(101, "testNo", "Limpieza", 10, 20);
		StepVerifier.create(productosService.altaProducto(p1))
		.expectNextMatches(p->p.getNombre().equals("test1"))
		.verifyComplete();
		StepVerifier.create(productosService.altaProducto(p2))
		.expectNextCount(0)
		.verifyComplete();
	}

}
