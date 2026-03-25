package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


public class InfoDto {
	private double usd;

	public InfoDto(double usd) {
		this.usd = usd;
	}
	public InfoDto() {

	}
	public double getUsd() {
		return usd;
	}
	public void setUsd(double usd) {
		this.usd = usd;
	}
	
	
}
