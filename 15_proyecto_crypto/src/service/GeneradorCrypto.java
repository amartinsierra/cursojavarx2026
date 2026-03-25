package service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import model.CryptoData;
import model.DatosDto;

public class GeneradorCrypto {
	private final ObjectMapper mapper = new ObjectMapper();
	private Flowable<CryptoData> flujo;
	private final String url="https://api.coingecko.com/api/v3/simple/price?ids=bitcoin,ethereum&vs_currencies=usd";
	public GeneradorCrypto() {
		HttpClient client = HttpClient.newBuilder().build();
	    ObjectMapper mapper = new ObjectMapper();
	    HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
		//creación del Flowable con los datos de cada moneda, obtenidos
		//con una frecuencia de 65 segundos
		flujo=Flowable.interval(65, TimeUnit.SECONDS)
    	        .flatMap(t -> {   	            
    	            CompletableFuture<List<CryptoData>> future =
    	                    client.sendAsync(req, HttpResponse.BodyHandlers.ofString()) //CompletableFutere<HttpResponse<String>>
    	                          .thenApply(HttpResponse::body)
    	                          .thenApply(body -> {
    	                              try {
    	                            	 DatosDto datos= mapper.readValue(body, DatosDto.class);
    	                            	 CryptoData btc = new CryptoData("bitcoin",
		                                          datos.getBitcoin().getUsd(),
		                                          LocalDateTime.now());
		                                 CryptoData eth = new CryptoData("ethereum",
		                                          datos.getEthereum().getUsd(),
		                                          LocalDateTime.now());
		                                  return List.of(btc,eth);
    	                              } catch (Exception e) {
    	                                  throw new RuntimeException(e);
    	                              }
    	                          });

    	            return  Flowable.fromFuture(future);}) //Flowable<List<CryptoData>>
			    	        .flatMap(lst -> Flowable.fromIterable(lst))
			    	        .subscribeOn(Schedulers.io())
			    	        .onBackpressureLatest()
			    	        .share();
    	        
	}
	//genera un flujo que va emitiendo cotización cada 10 segundos
	public Flowable<CryptoData> flujoCotizacion(){
		return flujo;
	}
}
