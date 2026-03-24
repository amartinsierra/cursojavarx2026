package sensores;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import model.Sensor;

public class SensorMixto {
	private Observable<Double> getTemperaturas(){
		return Observable.interval(150, TimeUnit.MILLISECONDS)
				.map(n->Math.random()*40);
	}
	private Observable<Integer> getHumedad(){
		return Observable.interval(300, TimeUnit.MILLISECONDS)
				.map(n->(int)(Math.random()*90+10));
	}
	public Observable<Sensor> getData(){
		return Observable.combineLatest(
				getTemperaturas(),
				getHumedad(),
				(t,h)->new Sensor(t,h)
				);
	}
}
