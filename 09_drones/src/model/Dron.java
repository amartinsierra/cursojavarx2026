package model;

import java.time.LocalDateTime;

public class Dron {
	private final String nombre;
    private final double x;
    private final double y;
    private final LocalDateTime hora;

    public Dron(String nombre, double x, double y, LocalDateTime hora) {
        this.nombre = nombre;
        this.x = x;
        this.y = y;
        this.hora = hora;
    }

    public String getNombre() { return nombre; }
    public double getX() { return x; }
    public double getY() { return y; }
    public LocalDateTime getHora() { return hora; }

    @Override
    public String toString() {
        return nombre + " (" + x + "," + y + ") @ " + hora;
    }
}
