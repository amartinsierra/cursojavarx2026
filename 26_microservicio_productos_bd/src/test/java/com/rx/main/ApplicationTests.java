package com.rx.main;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rx.main.model.Producto;
import com.rx.main.repository.ProductosRepository;
import com.rx.main.service.ProductosServiceImpl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class ApplicationTests {
	
	@InjectMocks
	ProductosServiceImpl productosService;
	@Mock
    private ProductosRepository productosRepository;
	

	@Test
	void testCatalogoCategorias() {
		
		Flux<Producto> testResult=Flux.just(new Producto(1,"p1","Alimentación",1,10),
				new Producto(2,"p2","Alimentación",1,10)
				);
		when(productosRepository.findByCategoria("Alimentación")).thenReturn(testResult);
		String categoria="Alimentación";
		StepVerifier.create(productosService.productosPorCategoria(categoria))
		.expectNextMatches(p->p.getNombre().equals("p1"))
		.expectNextMatches(p->p.getCodProducto()==2)
		.expectNextCount(0)
		.verifyComplete();
	}
	@Test
	void testAltaProducto() {
		/*Producto p1=new Producto(4, "test1", "Limpieza", 10, 20);
		Producto p2=new Producto(2, "testNo", "Limpieza", 10, 20);
		when(productosRepository.findById(4)).thenReturn(Mono.empty());
		when(productosRepository.findById(2)).thenReturn(Mono.just(p2));
		
		
		StepVerifier.create(productosService.altaProducto(p1))
		.expectNextMatches(p->p.getNombre().equals("test1"))
		.verifyComplete();
		StepVerifier.create(productosService.altaProducto(p2))
		.expectNextCount(0)
		.verifyComplete();*/
	}

}
