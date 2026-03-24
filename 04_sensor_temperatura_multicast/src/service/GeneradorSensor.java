package service;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;

public class GeneradorSensor {
	private Observable<Long> observable=Observable.interval(100, TimeUnit.MILLISECONDS)
			.map(n->(long)(Math.random()*36+5)).take(20);
	public Observable<Long> datosTemperaturaFrio(){
		return observable;
	}
	
	public Observable<Long> datosTemperaturaCaliente(){
		return observable.share();
	}
}
