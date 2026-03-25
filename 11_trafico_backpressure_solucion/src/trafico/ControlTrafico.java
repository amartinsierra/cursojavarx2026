package trafico;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import model.Coche;

public class ControlTrafico {
	public static void main(String[] args) throws InterruptedException {
        Random random = new Random();

        // Flujo de coches a toda velocidad 🚗🚙🚕
        Flowable<Coche> coches = Flowable.interval(10, TimeUnit.MILLISECONDS)
                .map(i -> new Coche("CAR-" + i, 60 + random.nextInt(100)))
                .onBackpressureDrop(c->System.out.println("se pierde "+c.matricula)); // 

        // 🕵️ Radar lento (detector de excesos de velocidad)
        coches.observeOn(Schedulers.io())
                .subscribe(coche -> {
                    Thread.sleep(80); // radar lento
                    if (coche.velocidad > 120) {
                        System.out.println("🚨 Exceso detectado: " + coche);
                    }
                });

        // 📊 Estadísticas rápidas (media de velocidad cada 500ms)
        coches.observeOn(Schedulers.computation())
                .buffer(500, TimeUnit.MILLISECONDS) //tiene en cuenta solo los coches que han pasado en los ultimos 500ms
                .map(lista -> lista.stream()
                        .mapToInt(c -> c.velocidad)
                        .average()
                        .orElse(0))
                .subscribe(media ->
                        System.out.printf("📊 Velocidad media en los últimos 500ms: %.2f km/h%n", media)
                );

        Thread.sleep(20000); // fin simulacion
    }
}
