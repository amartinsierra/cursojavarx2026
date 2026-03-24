package cliente;

import service.GeneradorSensor;

public class ComprobarDatos {

	public static void main(String[] args) throws InterruptedException {
		var generadorSensor=new GeneradorSensor();
		generadorSensor.datosTemperatura()
		.filter(t->t>=38||t<10)
		.subscribe(t->System.out.println("Peligro!! "+t));
		
		Thread.sleep(10000);

	}

}
