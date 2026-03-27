package client;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.schedulers.Schedulers;
import service.ConsultaProductosService;

public class MostrarProductos {
	private static final String CATEGORIA="Alimentación";
	public static void main(String[] args) throws InterruptedException {
		
		ConsultaProductosService consultaService=new ConsultaProductosService();
		ExecutorService executor=Executors.newCachedThreadPool();
		executor.submit(()->{
			consultaService.productosCategoria(CATEGORIA)
			.observeOn(Schedulers.io())
			.subscribe(p->System.out.println("Producto: "+p.getNombre()+" Stock: "+p.getStock()+" Categoria: "+p.getCategoria()));
		});
		executor.submit(()->{
			consultaService.productosCategoria(CATEGORIA)
			.observeOn(Schedulers.computation())
			.collect(Collectors.averagingInt(p->p.getStock()))
			.subscribe(s->System.out.println("Stock promedio: "+s));
		});
		
		Thread.sleep(10000);

	}

}
