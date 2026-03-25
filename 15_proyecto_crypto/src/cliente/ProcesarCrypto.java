package cliente;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.schedulers.Schedulers;
import model.CryptoData;
import service.GeneradorCrypto;

public class ProcesarCrypto {
	public static void main(String[] args) throws InterruptedException {
		GeneradorCrypto service = new GeneradorCrypto();

        var flujo = service.flujoCotizacion();
        ExecutorService executor=Executors.newCachedThreadPool();
        executor.submit(()->{
	        // Consumidor 1: alertas de cambios en BTC
	        flujo.filter(p -> p.getNombre().equals("bitcoin"))
	             .buffer(2, 1)
	             .filter(list -> list.size() == 2)
	             .filter(list -> {
	                 double p1 = list.get(0).getValor();
	                 double p2 = list.get(1).getValor();
	                 double cambio = Math.abs((p2 - p1) / p1) * 100;
	                 return cambio >= 0.01;
	             })
	             .observeOn(Schedulers.computation())
	             .subscribe(list -> {
	                 CryptoData ultimo = list.get(1);
	                 System.out.printf("⚡ ALERTA BTC: %.2f USD (%s)%n",
	                         ultimo.getValor(), ultimo.getFecha());
	             },ex -> System.err.println("Error en consumidor BTC: " + ex));});
      
        executor.submit(()->{
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
	        },ex -> System.err.println("Error en consumidor BTC: " + ex));}
	     );

        Thread.sleep(600000); // mantener vivo

	}
}
