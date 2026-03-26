package com.rx.main.controller;

import java.time.Duration;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@RestController
public class SensorController {
	@GetMapping(value="sensor")
	public Flux<String> sensor(){
		return Flux.interval(Duration.ofSeconds(1))
				.map(n->(Math.random()*36+5)+"")
				.subscribeOn(Schedulers.boundedElastic());
	}
}
