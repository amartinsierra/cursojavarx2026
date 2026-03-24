package cliente;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import service.GeneradorSensor;

public class ProcesarDatos {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService executor =Executors.newCachedThreadPool();
		var flujo=new GeneradorSensor().datosTemperaturaCaliente();
		executor.submit(()->flujo
				.filter(t->t>=38||t<10)
				.subscribe(t->System.out.println("peligro: "+t)));
		
		executor.submit(()->flujo
				.collect(Collectors.averagingLong(n->n))
				.subscribe(r->System.out.println("Media actual: "+r)));
		executor.awaitTermination(10000, TimeUnit.MILLISECONDS);
		executor.shutdown();

	}

}
