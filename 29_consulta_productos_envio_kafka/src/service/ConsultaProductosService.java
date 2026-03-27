package service;




import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.reactivex.rxjava3.core.Observable;
import model.Item;

public class ConsultaProductosService {
	private final String url="http://localhost:10000/tienda";
	public Observable<Item> productosStock(int stock){
		//completar
		ObjectMapper mapper = new ObjectMapper();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
			.uri(URI.create(url+"?stock="+stock))
			.GET()
			.build();	
		
		//stream de líneas de texto de respuesta
        CompletableFuture<HttpResponse<Stream<String>>> future =
                client.sendAsync(request, HttpResponse.BodyHandlers.ofLines());

        return Observable.fromFuture(future)
                .flatMap(resp -> Observable.fromStream(resp.body()))
                .map(linea -> mapper.readValue(linea, Item.class));
	}
}
