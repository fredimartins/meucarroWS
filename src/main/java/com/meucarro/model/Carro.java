package com.meucarro.model;

import java.util.Date;

public class Carro {

	private Integer id;
	private String placa;
	private String desc;
	private int hodometro;
	private int ultima_troca_oleo;
	private double lat;
	private double lng;
	private boolean alarme;
	private Date ultimoStatus;

	public Carro(Integer id, String placa, String desc, int hodometro, boolean alarme, double lat, double lng) {
		this.id = id;
		this.placa = placa;
		this.desc = desc;
		this.hodometro = hodometro;
		this.ultima_troca_oleo = hodometro - (id * 1000);
		this.alarme = alarme;
		this.lat = lat;
		this.lng = lng;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getHodometro() {
		return hodometro;
	}

	public void setHodometro(int hodometro) {
		this.hodometro = hodometro;
	}

	public int getUltima_troca_oleo() {
		return ultima_troca_oleo;
	}

	public void setUltima_troca_oleo(int ultima_troca_oleo) {
		this.ultima_troca_oleo = ultima_troca_oleo;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public boolean isAlarme() {
		return alarme;
	}

	public void setAlarme(boolean alarme) {
		this.alarme = alarme;
	}
	
	public Date getUltimoStatus() {
		return ultimoStatus;
	}

	public void setUltimoStatus() {
		this.ultimoStatus = new Date();
	}
}
