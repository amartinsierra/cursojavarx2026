package com.rx.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rx.model.DatosDto;
import com.rx.model.ProductoDto;
import com.rx.model.VentaDto;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class InfoProductosService {
	private String urlProductos="http://localhost:8000/productos";
	private String urlVentas="http://localhost:9000/ventas";
	private final ObjectMapper mapper = new ObjectMapper();
	private Flowable<ProductoDto> getFlujoProducto(){
		HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(urlProductos))
                .timeout(Duration.ofSeconds(5))
                .GET()
                .build();
		HttpClient httpClient=HttpClient.newBuilder()
				.build();
        CompletableFuture<HttpResponse<String>> respFuture = httpClient.sendAsync(req, BodyHandlers.ofString());

        CompletableFuture<List<ProductoDto>> listFuture = respFuture.thenApply(resp -> {
            if (resp.statusCode() >= 200 && resp.statusCode() < 300) {
                try {
                    return mapper.readValue(resp.body(), new TypeReference<List<ProductoDto>>() {});
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            throw new RuntimeException("Non-2xx response: " + resp.statusCode());
        });

        return Flowable.fromFuture(listFuture) //Flowable<List<ProductoDto>>
        		.flatMap(l->Flowable.fromIterable(l));
	}
	
	private Flowable<VentaDto> getFlujoVenta(){
		HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(urlVentas))
                .timeout(Duration.ofSeconds(5))
                .GET()
                .build();
		HttpClient httpClient=HttpClient.newBuilder()
				.build();
        CompletableFuture<HttpResponse<String>> respFuture = httpClient.sendAsync(req, BodyHandlers.ofString());

        CompletableFuture<List<VentaDto>> listFuture = respFuture.thenApply(resp -> {
            if (resp.statusCode() >= 200 && resp.statusCode() < 300) {
                try {
                    return mapper.readValue(resp.body(), new TypeReference<List<VentaDto>>() {});
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            throw new RuntimeException("Non-2xx response: " + resp.statusCode());
        });

        return Flowable.fromFuture(listFuture) //Flowable<List<ProductoDto>>
        		.flatMap(l->Flowable.fromIterable(l));
	}
	
	public Flowable<DatosDto> getData(){
		return Flowable.zip(getFlujoProducto(),getFlujoVenta(),(p,v)->new DatosDto(p.getCodigo(),p.getPrecio(),v.getFacturacion()))
		.concatMap(d->Flowable.just(d).delay(20,TimeUnit.MILLISECONDS))
		.subscribeOn(Schedulers.io())
		.onBackpressureLatest();
	}
			
}
