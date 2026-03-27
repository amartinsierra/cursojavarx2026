package service;




import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.reactivex.rxjava3.core.Observable;
import model.Producto;

public class ConsultaProductosService {
	private final String url="http://localhost:9001/productos";
	ObjectMapper mapper = new ObjectMapper();
	public Observable<Producto> productosCategoria(String categoria){
		return datos(url+"?categoria="+categoria);
	}
	public Observable<Producto> productos(){
		return datos(url);
	}
	
	private Observable<Producto> datos(String url){
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
			.uri(URI.create(url))
			.GET()
			.build();			
		//stream de líneas de texto de respuesta
        CompletableFuture<HttpResponse<Stream<String>>> future =
                client.sendAsync(request, HttpResponse.BodyHandlers.ofLines());
        return Observable.fromFuture(future)
                .flatMap(resp -> Observable.fromStream(resp.body()))
                .map(linea -> mapper.readValue(linea, Producto.class));
		/*CompletableFuture<HttpResponse<String>> future =
                client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        return Observable.fromFuture(future)
        	    .map(resp -> mapper.readValue(
        	        resp.body(),
        	        new TypeReference<List<Producto>>() {}
        	    ))
        	    .flatMapIterable(lista -> lista);*/
	}	
}
