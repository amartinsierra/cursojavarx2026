package service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import io.reactivex.rxjava3.core.Observable;

public class GeneradorSensor {
	String url="http://localhost:8002/sensor";
	public Observable<Double> datosTemperatura(){
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
			.uri(URI.create(url))
			.GET()
			.header("accept", "text/event-stream")
			.build();
		CompletableFuture<HttpResponse<Stream<String>>> future =
				client.sendAsync(request, HttpResponse.BodyHandlers.ofLines());
		return Observable.fromFuture(future)
			.flatMap(resp -> Observable.fromStream(resp.body()))
			.doOnNext(System.out::println)
			.filter(s->!s.isEmpty())
			.map(s->Double.parseDouble(s));
	}
}
