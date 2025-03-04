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
	
	public void setJokalaria(Jokalaria pJok) {
		this.jokalari = pJok;
		if(blokeMota!=null) {
			kenduAurrekoa();
		}
		this.blokeMota = BlokeMota.JOKALARIA;
		setChanged();
		notifyObservers(new int[] {pJok.getXposizioa(), pJok.getYposizioa()});
	}
	
	
	public void setSua(int i, int j) {
		if (this.blokeMota != BlokeMota.BLOKEGOGORRA) {
			this.sua = new Sua(i,j);
			if(blokeMota!=null) {
				kenduAurrekoa();
			}
			this.blokeMota = BlokeMota.SUA;
			setChanged();
			notifyObservers();
		}
	}
	public void setBlokeBiguna() {
		this.blokeBiguna = new BlokeBiguna();
		if(blokeMota!=null) {
			kenduAurrekoa();
		}
		this.blokeMota = BlokeMota.BLOKEBIGUNA;
		setChanged();
		notifyObservers();
	}
	public void setBlokeGogorra() {
		this.blokeGogorra = new BlokeGogorra();
		if(blokeMota!=null) {
			kenduAurrekoa();
		}
		this.blokeMota = BlokeMota.BLOKEGOGORRA;
		setChanged();
		notifyObservers();
	}
	
	public void setBomba(int i,int j,boolean pUltrabomba)  {
		if (pUltrabomba) {
			this.bomba = new UltraBomba(i,j);
			this.blokeMota = BlokeMota.ULTRABOMBA;
		} else {
			this.bomba = new Bomba(i,j);
			this.blokeMota = BlokeMota.BOMBA;
		}
		if(blokeMota!=null) {
			kenduAurrekoa();
		}
		setChanged();
		notifyObservers();
	}
	
	public void kenduAurrekoa() {
		switch (this.blokeMota) {
			case SUA:
                this.sua = null;
                this.blokeMota = null;
                break;
            case BOMBA:
            	this.bomba = null;
                this.blokeMota = null;
                break;
			case ULTRABOMBA:
				this.bomba = null;
                this.blokeMota = null;
				break;
            case BLOKEBIGUNA:
            	this.blokeBiguna = null;
                this.blokeMota = null;
                break;
			case BLOKEGOGORRA:
				this.blokeGogorra = null;
                this.blokeMota = null;
				break;
			case ETSAIA:
				this.etsaia = null;
                this.blokeMota = null;
				break;
			case JOKALARIA:
				this.jokalari = null;
                this.blokeMota = null;
				break;
			default:
				break;
		}
		setChanged();
		notifyObservers();
	}

	public Jokalaria getJokalaria() {
		return this.jokalari;
	}
	
	public BlokeMota zerDago() {
		return blokeMota;
	}
}
