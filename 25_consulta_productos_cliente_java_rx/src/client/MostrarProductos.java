package client;

import java.util.Scanner;

import io.reactivex.rxjava3.schedulers.Schedulers;
import service.ConsultaProductosService;

public class MostrarProductos {

	public static void main(String[] args) throws InterruptedException {
		Scanner scan=new Scanner(System.in);
		ConsultaProductosService consultaService=new ConsultaProductosService();
		System.out.println("Introduce stock mínimo:");
		int stockMin=scan.nextInt();
		consultaService.productosStock(stockMin)
		.observeOn(Schedulers.computation())
		.subscribe(it->System.out.println("Producto: "+it.getNombre()+" Stock: "+it.getStock()+" Categoria: "+it.getCategoria()));
		
		Thread.sleep(10000);

	}

}
