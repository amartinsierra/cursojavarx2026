package com.rx.service;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class GenerarDatos {

	public Observable<String> dias(){
		return Observable.just("lunes", "martes", "miércoles", "jueves");
	}
	
	public Observable<Long> numeros(){
		return Observable.interval(100, TimeUnit.MILLISECONDS)
				.subscribeOn(Schedulers.io())
				.map(n->{
					System.out.println("Produciendo en hilo "+Thread.currentThread().getName());
					return n;
				});
	}
}
