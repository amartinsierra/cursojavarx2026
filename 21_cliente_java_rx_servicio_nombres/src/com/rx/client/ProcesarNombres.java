package com.rx.client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import io.reactivex.rxjava3.core.Observable;

public class ProcesarNombres {

	public static void main(String[] args) throws InterruptedException {
		String url="http://localhost:8001/nombres";
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
			.uri(URI.create(url))
			.GET()
			.header("accept", "text/event-stream")
			.build();
		
		
		CompletableFuture<HttpResponse<Stream<String>>> future =
		client.sendAsync(request, HttpResponse.BodyHandlers.ofLines());
		// Creamos Observable a partir del future
		Observable.fromFuture(future)  //Observable<Stream<String>>
		// Convertimos la respuesta a un Observable<String>
		.flatMap(resp -> Observable.fromStream(resp.body())) //Observable<String>
		.filter(s->!s.isEmpty())
			.subscribe(
				msg -> System.out.println("Recibido: " + msg),
				err -> System.err.println("Error: " + err),
				() -> System.out.println("Stream completado")
		);


		Thread.sleep(10000);
	}

}
