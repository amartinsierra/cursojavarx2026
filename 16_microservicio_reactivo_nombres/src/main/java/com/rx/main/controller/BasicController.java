package com.rx.main.controller;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@CrossOrigin("*")
@RestController
public class BasicController {
	@GetMapping(value="nombres")
	public Flux<String> nombres(){
		return Flux.just("lunes","martes","miercoles","jueves")
				.delayElements(Duration.of(200,ChronoUnit.MILLIS));
	}
	@GetMapping(value="calculo")
	public Mono<String> calculo(){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Mono.just("resultado");
	}
}
