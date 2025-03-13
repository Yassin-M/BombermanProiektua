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
	private int x; 
	private int y;
	
	
	public Gelaxka(int pX, int pY) {
		this.sua = null;
        this.bomba = null;
        this.blokeBiguna = null;
        this.blokeGogorra = null;
        this.etsaia = null;
        this.jokalari = null;
        this.blokeMota = null;
        this.x = pX;
        this.y = pY;
	}
	private boolean[] lortuEgoera() {
		boolean[] emaitza = new boolean[6];
		emaitza[0] = this.sua != null;
		emaitza[1] = this.bomba != null;
		emaitza[2] = this.blokeBiguna != null;
		emaitza[3] = this.blokeGogorra != null;
		emaitza[4] = this.etsaia != null;
		emaitza[5] = this.jokalari != null;
		return emaitza;
	}
	
	public int getX() {
        return x;
    }
	
	public int getY() {
        return y;
    }
	
	public void setJokalaria(Jokalaria pJok) {
		this.jokalari = pJok;
		this.blokeMota = BlokeMota.JOKALARIA;
		setChanged();
		notifyObservers(lortuEgoera());
	}
	
	public void setSua(int i, int j) {
		if (this.blokeMota != BlokeMota.BLOKEGOGORRA) {
			if(blokeMota == BlokeMota.JOKALARIA) {
				Laberintoa.getNireLaberintoa().partidaAmaitu(jokalari);
			}
			if(blokeMota!=null) {
				if (blokeMota == BlokeMota.BLOKEBIGUNA) {
					Laberintoa.getNireLaberintoa().addScore(100);
					Laberintoa.getNireLaberintoa().kenduBlokeBiguna();
					
				}
				if (blokeMota == BlokeMota.ETSAIA) {
					Laberintoa.getNireLaberintoa().addScore(500);
				}
				kenduAurrekoa();	
			}
			this.sua = new Sua(i,j);
			this.blokeMota = BlokeMota.SUA;
			setChanged();
			notifyObservers(lortuEgoera());			
		}
	}
	public void setBlokeBiguna() {
		this.blokeBiguna = new BlokeBiguna();
		if(blokeMota!=null) {
			kenduAurrekoa();
		}
		this.blokeMota = BlokeMota.BLOKEBIGUNA;
		setChanged();
		notifyObservers(lortuEgoera());
	}
	public void setBlokeGogorra() {
		this.blokeGogorra = new BlokeGogorra();
		if(blokeMota!=null) {
			kenduAurrekoa();
		}
		this.blokeMota = BlokeMota.BLOKEGOGORRA;
		setChanged();
		notifyObservers(lortuEgoera());
	}
	
	public void setBomba(int i,int j,boolean pUltrabomba)  {
		if (this.jokalari != null) {
			if (pUltrabomba) {
				this.bomba = new UltraBomba(i,j);
				this.blokeMota = BlokeMota.ULTRABOMBA;
			} else {
				this.bomba = new Bomba(i,j);
				this.blokeMota = BlokeMota.BOMBA;
			}
			setChanged();
			notifyObservers(lortuEgoera());
		}
	}
	
	public void kenduAurrekoa() {
		if (this.blokeMota!=null) {
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
				default:
					break;
			}
		}
		setChanged();
		notifyObservers(lortuEgoera());
	}
	public void kenduJokalaria() {
		this.jokalari = null;
        if(this.bomba!=null) {
        	this.blokeMota=BlokeMota.BOMBA;
        }
        else {
        	this.blokeMota=null;
        }
        setChanged();
        notifyObservers(lortuEgoera());
	}

	public Jokalaria getJokalaria() {
		return this.jokalari;
	}
	
	public BlokeMota zerDago() {
		return blokeMota;
	}
}
