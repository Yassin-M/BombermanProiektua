package Eredua;

import java.util.Observable;

@SuppressWarnings("deprecation")

public class Gelaxka extends Observable {
	//atributuak
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
	private Integer[] lortuEgoera() {
		Integer[] emaitza = new Integer[]{0,0,0,0,0,0,0};
		if (this.sua!=null) emaitza[0] = 1;
		if (this.bomba != null) emaitza[1] = 1;
		if (this.blokeBiguna != null) emaitza[2] = 1;
		if (this.blokeGogorra != null) emaitza[3] = 1;
		if (this.etsaia!=null) emaitza[4] = 1;
		if (this.jokalari!=null) emaitza[5] =1;
		if (emaitza[5]==1) {
			Norabidea norabidea = this.jokalari.getNorabidea();
			switch (norabidea) {
			case GORA:
				emaitza[6] = 1;
				break;
			case BEHERA:
				emaitza[6] = 2;
				break;
			case EZKERRERA:
				emaitza[6] = 3;
				break;
			case ESKUINERA:
				emaitza[6] = 4;
				break;
			default:
				break;
			}
		}
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
	
	public boolean setSua(int i, int j) {
		//jokalaria hil den ala ez bueltatzen du
		boolean jokalariHil = false;
		if (this.blokeMota != BlokeMota.BLOKEGOGORRA) {
			if(blokeMota == BlokeMota.JOKALARIA || this.jokalari != null) {
				jokalariHil = true;
				this.jokalari = null;
			}
			if(blokeMota!=null) {
				if (blokeMota == BlokeMota.BLOKEBIGUNA) {
					kenduAurrekoa();
					Laberintoa.getNireLaberintoa().addScore(100);
					Laberintoa.getNireLaberintoa().kenduBlokeBiguna();
				}
				else if (blokeMota == BlokeMota.ETSAIA) {
					kenduAurrekoa();
					Laberintoa.getNireLaberintoa().addScore(500);
				} else if(blokeMota == BlokeMota.SUA) {
					this.sua.kendu();
					kenduAurrekoa();	
				}
				else {
					kenduAurrekoa();
				}
			}
			this.sua = new Sua(i,j);
			this.blokeMota = BlokeMota.SUA;
			setChanged();
			notifyObservers(lortuEgoera());
		}
		return jokalariHil;
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
        else if (this.sua!=null) {
        	this.blokeMota=BlokeMota.SUA;
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
