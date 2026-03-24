package cliente;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import service.GeneradorSensor;

public class ComprobarDatos {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService executor=Executors.newCachedThreadPool();
		var generadorSensor=new GeneradorSensor().datosTemperaturaFrio();
		executor.submit(()->
			generadorSensor
			//.filter(t->t>=38||t<10)
			.collect(Collectors.summingLong(n->n))
			.subscribe(t->System.out.println("Proceso hilo 1 "+t)));
		
		executor.submit(()->
			generadorSensor
			.collect(Collectors.summingLong(n->n))
			//.filter(t->t>=38||t<10)
			.subscribe(t->System.out.println("Proceso hilo 2: "+t)));
		
		Thread.sleep(10000);
		executor.shutdown();

	}

}
