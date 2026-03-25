package service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import model.Dron;

public class CompositorDron {
	public Observable<Dron> flujoGlobal() {
        //***rellenar aquí
		//emitir un Observable con los datos de 10 drones,
		//utilizando hilos de entrada/salida
		List<Observable<Dron>> drones = IntStream.rangeClosed(1, 10)
                .mapToObj(i -> new HiloDron("Dron-" + i).flujoDatos())
                .collect(Collectors.toList());
		return Observable.merge(drones).subscribeOn(Schedulers.io());
    }
}
