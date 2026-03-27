package cliente;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import io.reactivex.rxjava3.schedulers.Schedulers;
import model.CryptoData;
import model.Data;
import service.GeneradorCrypto;

public class ProcesarCrypto {
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
		GeneradorCrypto service = new GeneradorCrypto();

        var flujo = service.flujoCotizacion();

        // Consumidor 1: alertas de cambios en BTC
        flujo.filter(p -> p.getNombre().equals("bitcoin"))
             .buffer(2, 1)
             .filter(list -> list.size() == 2)
             .observeOn(Schedulers.computation())
             .subscribe(list -> {
            	 CryptoData primero = list.get(0);
                 CryptoData ultimo = list.get(1);
                 Data data;
                 if(primero.getValor()>ultimo.getValor()) {
                	 data=new Data(primero.getValor()-ultimo.getValor(),"bajada");
                 }else {
                	 data=new Data(ultimo.getValor()-primero.getValor(),"subida");
                 }
                 producer.send(new ProducerRecord<String,String>("cryptoTopic",data.toString()));
             });

        // Consumidor 2: comparación BTC vs ETH
        flujo
        .buffer(2, TimeUnit.SECONDS) // cada 2 segundos recojo lo último de ambos
        .filter(list -> list.size() == 2)
        .subscribe(lista -> {
            double btc = 0;
            double eth = 0;

            for (CryptoData p : lista) {
                if (p.getNombre().equals("bitcoin")) {
                    btc = p.getValor();
                } else if (p.getNombre().equals("ethereum")) {
                    eth = p.getValor();
                }
            }

            System.out.printf("📊 Comparación: BTC %.2f vs ETH %.2f -> %s más alto%n",
                    btc, eth, btc > eth ? "BTC" : "ETH");
        });

        Thread.sleep(60000); // mantener vivo

	}
}
