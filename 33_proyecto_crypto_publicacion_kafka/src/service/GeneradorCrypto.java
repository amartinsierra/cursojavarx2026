package service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.reactivex.rxjava3.core.Flowable;
import model.CryptoData;

public class GeneradorCrypto {
	private Flowable<CryptoData> flujo;
	private final String url="https://api.coingecko.com/api/v3/simple/price?ids=bitcoin,ethereum&vs_currencies=usd";
	public GeneradorCrypto() {
		HttpClient client = HttpClient.newHttpClient();
	    ObjectMapper mapper = new ObjectMapper();
		//creación del Flowable con los datos de cada moneda, obtenidos
		//con una frecuencia de 10 segundos
		flujo=Flowable.interval(0, 10, TimeUnit.SECONDS)
    	        .flatMap((Long t) -> {
    	            HttpRequest req = HttpRequest.newBuilder()
    	                    .uri(URI.create(url))
    	                    .GET()
    	                    .build();

    	            CompletableFuture<CryptoData[]> future =
    	                    client.sendAsync(req, HttpResponse.BodyHandlers.ofString())
    	                          .thenApply(HttpResponse::body)
    	                          .thenApply(body -> {
    	                              try {
    	                                  JsonNode root = mapper.readTree(body);
    	                                  CryptoData btc = new CryptoData("bitcoin",
    	                                          root.get("bitcoin").get("usd").asDouble(),
    	                                          LocalDateTime.now());
    	                                  CryptoData eth = new CryptoData("ethereum",
    	                                          root.get("ethereum").get("usd").asDouble(),
    	                                          LocalDateTime.now());
    	                                  return new CryptoData[] {btc,eth};
    	                              } catch (Exception e) {
    	                                  throw new RuntimeException(e);
    	                              }
    	                          });

    	            return  Flowable.fromFuture(future);
    	        }) //Flowable<CrytoData[]>
    	        .flatMap(arr -> Flowable.fromArray(arr))
    	        .share();
    	        
	}
	//genera un flujo que va emitiendo cotización cada 10 segundos
	public Flowable<CryptoData> flujoCotizacion(){
		return flujo;
	}
}
