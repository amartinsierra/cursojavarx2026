package com.rx.service;

import java.util.concurrent.TimeUnit;

import com.rx.model.Coche;

import io.reactivex.rxjava3.core.Flowable;

public class EnvioInformacionService {
	Flowable<Coche> flujo;	
	public EnvioInformacionService() {
		this.flujo = Flowable.interval(10, TimeUnit.MILLISECONDS)
				.map(i->new Coche("MAT "+i,(int)(Math.random()*100+40)))
				.onBackpressureLatest(c->System.out.println("Se piede la matrícula "+c.getMatricula()));
	}

	public Flowable<Coche> enviarInfo(){
		return flujo;
						
	}
	
	
}
