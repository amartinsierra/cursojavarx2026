package service;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;

public class GeneradorSensor {
	public Observable<Long> datosTemperatura(){
		return Observable.interval(500, TimeUnit.MILLISECONDS)
				.map(n->(long)(Math.random()*36+5));
	}
}
