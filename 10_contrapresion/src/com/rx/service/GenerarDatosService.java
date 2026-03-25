package com.rx.service;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.BackpressureOverflowStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

public class GenerarDatosService {

	public Observable<Long> generarCodigosSinBackpressure(){
		return Observable.interval(10, TimeUnit.MILLISECONDS)
				.map(n->{
					System.out.println("Produciendo "+n+" hilo "+Thread.currentThread().getName());
					return n;
				});
	}
	
	public Flowable<Long> generarCodigos(){
		return Flowable.interval(10, TimeUnit.MILLISECONDS)
				.map(n->{
					System.out.println("Produciendo "+n+" hilo "+Thread.currentThread().getName());
					return n;
				})
				//.onBackpressureBuffer(300,() -> System.out.println("**************Buffer lleno, eliminando datos!!!!!"),BackpressureOverflowStrategy.DROP_OLDEST);
				//.onBackpressureDrop(e->System.out.println("-----Eliminado el: "+e));
				.onBackpressureLatest(e->System.out.println("-----Eliminado el: "+e));
	}
}
