package service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import model.Posicion;

public class GpsService {
	// Flujo de posiciones (latitud/longitud)
    private Observable<double[]> generarCoordenadas() {
        return Observable.interval(2, TimeUnit.SECONDS)
                .map(tick -> new double[]{
                        40.0 + Math.random(),   // latitud
                        -3.5 + Math.random()    // longitud
                });
    }

    // Flujo de tiempos
    private Observable<String> generarTiempos() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm:ss");
        return Observable.interval(1, TimeUnit.SECONDS)
                .map(tick -> LocalDateTime.now().format(fmt));
    }

    // Fusionamos ambos flujos en Posicion
    public Observable<Posicion> generarPosicionesCompletas() {
        return Observable.combineLatest(
                generarCoordenadas(),
                generarTiempos(),
                (coords, tiempo) -> new Posicion(coords[0], coords[1], tiempo)
        );
    }
}
