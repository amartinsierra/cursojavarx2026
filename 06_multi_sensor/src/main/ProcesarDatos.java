package main;

import java.util.ArrayList;
import java.util.List;

import sensores.SensorMixto;

public class ProcesarDatos {

	public static void main(String[] args) throws InterruptedException {
		
		var sensores=new SensorMixto();
		sensores
		.getData()
		.subscribe(d->System.out.println("Temperatura: "+d.getTemperatura()+" Humedad: "+d.getHumedad()));
		Thread.sleep(10000);

	}

}
