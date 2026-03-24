package com.rx.service;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;

public class GenerarDatos {

	public Observable<String> dias(){
		return Observable.just("lunes", "martes", "miércoles", "jueves");
	}
	
	public Observable<Long> numeros(){
		return Observable.interval(100, TimeUnit.MILLISECONDS);
	}
}
