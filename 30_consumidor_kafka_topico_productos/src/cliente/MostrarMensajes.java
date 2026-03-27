package cliente;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class MostrarMensajes {

	private static KafkaConsumer<String,String> consumer;
	static String topico="productosTopic";
	static {
		Properties props = new Properties();
		props.put("group.id", "GroupA");
		props.put("bootstrap.servers", "localhost:9092");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
		consumer=new KafkaConsumer<>(props);
	}
	public static void main(String[] args) {
		consumer.subscribe(List.of(topico));
		while(true) {
			ConsumerRecords<String, String> records=consumer.poll(Duration.ofMillis(100));
			records.forEach(r->System.out.println(r.value()));
		}

	}

}
