package com.rx.service;

import java.util.List;

import com.rx.model.Curso;

import io.reactivex.rxjava3.core.Observable;

public class GeneradorService {
	private final List<Curso> cursos = List.of(
	           new Curso("Java Básico", 20),
	           new Curso("Spring Boot", 40),
	           new Curso("Microservicios", 50),
	           new Curso("RxJava", 15),
	           new Curso("Docker", 30),
	           new Curso("Kubernetes", 45)
	  );
	
	public Observable<Curso> cursos(){
		return Observable.fromIterable(cursos);
	}
}
