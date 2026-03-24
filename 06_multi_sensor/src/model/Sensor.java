package model;

public class Sensor {
	private double temperatura;
	private double humedad;
	public Sensor(double temperatura, double humedad) {
		super();
		this.temperatura = temperatura;
		this.humedad = humedad;
	}
	public double getTemperatura() {
		return temperatura;
	}
	public double getHumedad() {
		return humedad;
	}
	
}
