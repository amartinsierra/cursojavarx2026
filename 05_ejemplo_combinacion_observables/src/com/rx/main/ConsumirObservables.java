package com.rx.main;

import com.rx.service.GeneradorFlujosService;

public class ConsumirObservables {

	public static void main(String[] args) throws InterruptedException {
		var flujosService=new GeneradorFlujosService();
		
		//flujosService.mixtoMerge()
		//flujosService.mixto()
		flujosService.mixtoLatest()
		.subscribe(System.out::println);

		Thread.sleep(10000);
	}

}
