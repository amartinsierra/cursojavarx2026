package model;

import java.io.Serializable;

public class Data implements Serializable{
	private double diferencia;
	private String tipo;
	public Data(double diferencia, String tipo) {
		this.diferencia = diferencia;
		this.tipo = tipo;
	}
	public double getDiferencia() {
		return diferencia;
	}
	public void setDiferencia(double diferencia) {
		this.diferencia = diferencia;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String toString() {
		return "{\"diferencia\":"+diferencia+",\"tipo\":"+tipo+"}";			
	}
	
}
