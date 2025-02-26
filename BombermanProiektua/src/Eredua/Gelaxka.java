package Eredua;

import java.util.Observable;
@SuppressWarnings("deprecation")

public class Gelaxka extends Observable {
	private Sua sua;
	private Bomba bomba;
	private BlokeBiguna blokeBiguna;
	private BlokeGogorra blokeGogorra;
	private Etsaia etsaia;
	private Jokalaria jokalari;
	private BlokeMota blokeMota;
	
	
	public Gelaxka() {
		this.sua = null;
        this.bomba = null;
        this.blokeBiguna = null;
        this.blokeGogorra = null;
        this.etsaia = null;
        this.jokalari = null;
        this.blokeMota = null;
	}
	
	public void setSua() {
		this.sua = new Sua();
		kenduAurrekoa();
		this.blokeMota = BlokeMota.SUA;
		setChanged();
		notifyObservers();
	}
	public void setBlokeBiguna() {
		this.blokeBiguna = new BlokeBiguna();
		kenduAurrekoa();
		this.blokeMota = BlokeMota.BLOKEBIGUNA;
		setChanged();
		notifyObservers();
	}
	public void setBlokeGogorra() {
		this.blokeGogorra = new BlokeGogorra();
		kenduAurrekoa();
		this.blokeMota = BlokeMota.BLOKEGOGORRA;
		setChanged();
		notifyObservers();
	}
	
	private void kenduAurrekoa() {
		switch (this.blokeMota) {
			case SUA:
                this.sua = null;
                break;
            case BOMBA:
            	this.bomba = null;
                break;
            case BLOKEBIGUNA:
            	this.blokeBiguna = null;
				break;
			case BLOKEGOGORRA:
				this.blokeGogorra = null;
				break;
			case ETSAIA:
				this.etsaia = null;
				break;
			case JOKALARIA:
				this.jokalari = null;
				break;
		}
	}
	
	public BlokeMota zerDago() {
		return blokeMota;
	}
}
