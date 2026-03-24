package model;

import java.time.LocalDateTime;

public class Posicion {
	private final double latitud;
    private final double longitud;
    private final String timestamp;

    public Posicion(double latitud, double longitud, String timestamp) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.timestamp = timestamp;
    }

    public double getLatitud() { return latitud; }
    public double getLongitud() { return longitud; }
    public String getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return "Posicion{" +
                "lat=" + latitud +
                ", lon=" + longitud +
                ", t=" + timestamp +
                '}';
    }
}
