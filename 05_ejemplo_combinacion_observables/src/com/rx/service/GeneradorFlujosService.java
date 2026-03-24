package com.rx.service;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;

public class GeneradorFlujosService {
	Observable<Integer> numeros=Observable.just(1, 2, 3, 4)
			.concatMap(n->Observable.just(n).delay(100, TimeUnit.MILLISECONDS));
	Observable<String> cadenas=Observable.just("a", "b", "c", "d", "e", "f")
			.concatMap(n->Observable.just(n).delay(50, TimeUnit.MILLISECONDS));
	
	public Observable<String> mixto(){
		return Observable.zip(numeros, cadenas,(a,b)->a+b);
	}
	
	public Observable<String> mixtoMerge(){
		return Observable.merge(numeros.map(n->n.toString()),cadenas);
	}
	public Observable<String> mixtoLatest(){
		return Observable.combineLatest(numeros, cadenas,(a,b)->a+b);
	}
	
}
