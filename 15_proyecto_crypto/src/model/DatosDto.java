package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


public class DatosDto {
	private InfoDto bitcoin;
	private InfoDto ethereum;
	public DatosDto(InfoDto bitcoin, InfoDto ethereum) {
		super();
		this.bitcoin = bitcoin;
		this.ethereum = ethereum;
	}
	public DatosDto() {
		
	}
	public InfoDto getBitcoin() {
		return bitcoin;
	}
	public void setBitcoin(InfoDto bitcoin) {
		this.bitcoin = bitcoin;
	}
	public InfoDto getEthereum() {
		return ethereum;
	}
	public void setEthereum(InfoDto ethereum) {
		this.ethereum = ethereum;
	}
}
