package service;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

import io.reactivex.rxjava3.core.Observable;
import model.Dron;

public class HiloDron {
	private final String nombre;

    public HiloDron(String nombre) {
        this.nombre = nombre;
    }

    public Observable<Dron> flujoDatos() {
        return Observable.<Dron>create(emitter -> {
            new Thread(() -> {
                try {
                    while (!emitter.isDisposed()) {
                        double x = ThreadLocalRandom.current().nextDouble(0, 500);
                        double y = ThreadLocalRandom.current().nextDouble(0, 500);
                        Dron dato = new Dron(nombre, x, y, LocalDateTime.now());
                        //enviar el dato a través del flujo Observable
                        emitter.onNext(dato);
                        Thread.sleep(2000); // cada segundo emite nueva posición
                    }
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }).start();
        });
    }
}
