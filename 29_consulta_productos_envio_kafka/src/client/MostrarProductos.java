package client;

import java.util.Properties;
import java.util.Scanner;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import io.reactivex.rxjava3.schedulers.Schedulers;
import service.ConsultaProductosService;

public class MostrarProductos {
	static KafkaProducer<String, String> producer;
	static {
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("acks", "all");
		props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		producer=new KafkaProducer<>(props);
	}

	public static void main(String[] args) throws InterruptedException {
		Scanner scan=new Scanner(System.in);
		ConsultaProductosService consultaService=new ConsultaProductosService();
		System.out.println("Introduce stock mínimo:");
		int stockMin=scan.nextInt();
		consultaService.productosStock(stockMin)
		.observeOn(Schedulers.computation())
		.subscribe(it->enviarInfo("Producto: "+it.getNombre()+" Stock: "+it.getStock()+" Categoria: "+it.getCategoria()));
		
		Thread.sleep(10000);

	}
	private static void enviarInfo(String cadena) {
		String topico="productosTopic";	
		ProducerRecord<String,String> record=new ProducerRecord<>(topico, cadena);
		//envio
		producer.send(record);
		
	}

}
