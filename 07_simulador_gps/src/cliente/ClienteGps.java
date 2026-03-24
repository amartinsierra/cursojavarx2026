package cliente;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.rxjava3.core.Observable;
import model.Posicion;
import service.GpsService;

public class ClienteGps {

	public static void main(String[] args) throws InterruptedException {
		GpsService gps = new GpsService();
		double longitudRef=41;
		double latitudRef=-2.5;
		double distanciaMin=10;
		ExecutorService executor=Executors.newCachedThreadPool();
		Observable<Posicion> observable=gps.generarPosicionesCompletas().share();
		executor.submit(()->
        	observable.subscribe(
                        pos -> System.out.println("📡 Recibida: " + pos),
                        err -> System.err.println("❌ Error: " + err),
                        () -> System.out.println("✅ Flujo completado")
                ));

		executor.submit(()->
			observable.subscribe(pos->{
				double distancia=Math.sqrt(Math.abs(pos.getLongitud()-longitudRef)/Math.abs(pos.getLatitud()-latitudRef));
				if(distancia<=distanciaMin) {
					System.out.println("Alerta, objeto próximo!!");
				}
			})
				
				);
        // dejamos correr la app unos segundos
        Thread.sleep(15000);

	}

}
