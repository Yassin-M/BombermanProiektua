package Eredua;

import java.util.Observable;

@SuppressWarnings("deprecation")

public class Gelaxka extends Observable {
	//atributuak
	private Sua sua;
	private Bomba bomba;
	private Bloke blokea;
	private Etsaia etsaia;
	private Jokalaria jokalari;
	private BlokeMota blokeMota;
	private int x; 
	private int y;
	
	
	public Gelaxka(int pX, int pY) {
		this.sua = null;
        this.bomba = null;
        this.blokea = null;
        this.etsaia = null;
        this.jokalari = null;
        this.blokeMota = null;
        this.x = pX;
        this.y = pY;
	}
	private Integer[] lortuEgoera() {
		Integer[] emaitza = new Integer[]{0,0,0,0,0,0,0,0};
		if (this.sua!=null) emaitza[0] = 1;
		if (this.bomba != null) emaitza[1] = 1;
		if (this.blokea != null) {
			if (blokea instanceof BlokeBiguna) emaitza[2] = 1;
			else if (blokea instanceof BlokeGogorra) emaitza[3] = 1;
		}
		if (this.etsaia!=null) emaitza[4] = 1;
		if (this.jokalari!=null) emaitza[5] =1;
		if (this.jokalari instanceof JokBeltza) emaitza[7] =1;
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
	public void setEtsaia(boolean pNorabidea) {
        this.etsaia = new Etsaia(x,y,pNorabidea);
        if(blokeMota!=null) {
            kenduAurrekoa();
        }
        this.blokeMota = BlokeMota.ETSAIA;
        setChanged();
        notifyObservers(lortuEgoera());
    }
	
	public void setSua(int i, int j) {
		if (this.blokeMota != BlokeMota.BLOKEGOGORRA) {
			if(blokeMota == BlokeMota.JOKALARIA || this.jokalari != null) {
				Laberintoa.getNireLaberintoa().partidaAmaitu(this.jokalari,false);
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
					Laberintoa.getNireLaberintoa().kenduEtsaia();
					
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
	}
	public void setBlokeBiguna() {
		this.blokea = BlokeFactory.getNireBlokeFactory().sortuBloke(2);
		if(blokeMota!=null) {
			kenduAurrekoa();
		}
		this.blokeMota = BlokeMota.BLOKEBIGUNA;
		setChanged();
		notifyObservers(lortuEgoera());
	}
	public void setBlokeGogorra() {
		this.blokea = BlokeFactory.getNireBlokeFactory().sortuBloke(1);
		if(blokeMota!=null) {
			kenduAurrekoa();
		}
		this.blokeMota = BlokeMota.BLOKEGOGORRA;
		setChanged();
		notifyObservers(lortuEgoera());
	}
	
	public void setBomba(int i,int j,int pBombaMota)  {
		if (this.jokalari != null) {
			this.bomba = BombaFactory.getNireBombaFactory().sortuBomba(i, j, pBombaMota);
			this.blokeMota = BlokeMota.BOMBA;
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
	            case BLOKEBIGUNA:
	            	this.blokea = null;
	                this.blokeMota = null;
	                break;
				case BLOKEGOGORRA:
					this.blokea = null;
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
	
	public void etsaiaHasieratu() {
		if (this.etsaia != null && this.blokeMota == BlokeMota.ETSAIA) {
			this.etsaia.hasiMugitzen();
			setChanged();
			notifyObservers(lortuEgoera());
		}
	}
	
	public void etsaiaHil() {
		if (etsaia!=null) {
			etsaia.ezabatu();
			this.etsaia=null;
		}
	}
	
	public BlokeMota zerDago() {
		return blokeMota;
	}
}
