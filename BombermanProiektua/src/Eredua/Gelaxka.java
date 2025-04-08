package Eredua;

import java.util.ArrayList;
import java.util.Observable;

@SuppressWarnings("deprecation")

public class Gelaxka extends Observable {
	//elementuak identifikatzeko
	private static final int sua = 0, bomba = 1, blokeBiguna = 2, blokeGogorra = 3, etsaia = 4, jokalaria = 5;
	
	private int x; 
	private int y;
	
	private GelaxkaElementua elementua;
	
	
	public Gelaxka(int pX, int pY) {
        this.x = pX;
        this.y = pY;
	}
	private Integer[] lortuEgoera() {
		if (this.elementua == null) {
			return new Integer[]{0,0,0,0,0,0,0,0};
		}
		else {
			return this.elementua.lortuEgoera();
		}
	}
	
	public int getX() {
        return x;
    }
	
	public int getY() {
        return y;
    }
	
	public void setJokalaria(Jokalaria pJok) {
		if (lortuEgoera()[etsaia] == 1) {
			gehituElementua(pJok);
			Laberintoa.getNireLaberintoa().addScore(100);
		} 
		else if (lortuEgoera()[sua] == 1){
			gehituElementua(pJok);
		}
		else {
			this.elementua = pJok;
		}
		setChanged();
		notifyObservers(lortuEgoera());
	}
	public void setEtsaia(boolean pNorabidea) {
		if (lortuEgoera()[jokalaria] == 1) {
			gehituElementua(new Etsaia(x,y,pNorabidea));
			Laberintoa.getNireLaberintoa().addScore(100);
		} 
		else {
			this.elementua = new Etsaia(x,y,pNorabidea);
		}
		setChanged();
        notifyObservers(lortuEgoera());
    }
	
	public void setSua(int i, int j) {
		if (lortuEgoera()[blokeGogorra] == 0) {
			if(elementua!=null) {
				if (lortuEgoera()[jokalaria] == 1) {
					gehituElementua(new Sua(i,j));
					Laberintoa.getNireLaberintoa().partidaAmaitu(false);
				}
				else if (lortuEgoera()[blokeBiguna] == 1) {
					this.elementua = new Sua(i,j);
					Laberintoa.getNireLaberintoa().addScore(100);
					Laberintoa.getNireLaberintoa().kenduBlokeBiguna();
				}
				else if (lortuEgoera()[etsaia] == 1) {
					gehituElementua(new Sua(i,j));
					Laberintoa.getNireLaberintoa().addScore(500);
					Laberintoa.getNireLaberintoa().kenduEtsaia();
					((ElementuTalde)this.elementua).getEtsaia().ezabatu();;
				} 
				else if(lortuEgoera()[sua] == 1) {
					if (this.elementua instanceof Sua) {
						((Sua) this.elementua).kenduTimer();
					}
					else {
						((ElementuTalde) this.elementua).kenduTimer();
					}
					this.elementua = new Sua(i,j);
				}
				else {
					this.elementua = new Sua(i,j);
				}
			}
			else {
				this.elementua = new Sua(i,j);
			}
			setChanged();
			notifyObservers(lortuEgoera());
		}
	}
	public void setBlokeBiguna() {
		this.elementua = BlokeFactory.getNireBlokeFactory().sortuBloke(2);
		setChanged();
		notifyObservers(lortuEgoera());
	}
	public void setBlokeGogorra() {
		this.elementua = BlokeFactory.getNireBlokeFactory().sortuBloke(1);

		setChanged();
		notifyObservers(lortuEgoera());
	}
	
	public void setBomba(int i,int j,int pBombaMota)  {
		if (lortuEgoera()[jokalaria] == 1) {
			gehituElementua(BombaFactory.getNireBombaFactory().sortuBomba(i, j, pBombaMota));
			setChanged();
			notifyObservers(lortuEgoera());
		}
	}
	
	
	public void kenduAurrekoa() {
		this.elementua = null;
		setChanged();
		notifyObservers(lortuEgoera());
	}
	
	public void kenduJokalaria() {
        if(lortuEgoera()[bomba]==1) {
        	this.elementua=((ElementuTalde)this.elementua).getBomba();
        }
        else if (lortuEgoera()[sua]==1) {
        	this.elementua=((ElementuTalde)this.elementua).getSua();
        }
        else {
        	this.elementua=null;
        }
        setChanged();
        notifyObservers(lortuEgoera());
	}
	
	public void etsaiaHasieratu() {
		if (lortuEgoera()[etsaia]==1) {
			((Etsaia)this.elementua).hasiMugitzen();
			setChanged();
			notifyObservers(lortuEgoera());
		}
	}
	
	public void etsaiaHil() {
		if (lortuEgoera()[etsaia]==1) {
			this.elementua = null;
			if (this.elementua instanceof Etsaia) {
				((Etsaia)this.elementua).ezabatu();
			}
			else if (this.elementua instanceof ElementuTalde) {
				((ElementuTalde)this.elementua).kenduTimer();
			}
			setChanged();
			notifyObservers(lortuEgoera());
		}
	}
	
	public BlokeMota zerDago() {
		Integer[] egoera = lortuEgoera();
		if (egoera[blokeBiguna] == 1) {
			return BlokeMota.BLOKEBIGUNA;
		} else if (egoera[blokeGogorra] == 1) {
			return BlokeMota.BLOKEGOGORRA;
		} else if (egoera[sua] == 1) {
			return BlokeMota.SUA;
		} else if (egoera[bomba] == 1) {
			return BlokeMota.BOMBA;
		} else if (egoera[etsaia] == 1) {
			return BlokeMota.ETSAIA;
		} else if (egoera[jokalaria] == 1) {
			return BlokeMota.JOKALARIA;
		}
		else {
			return null;
		}
	}
	
	public void amatatuTimer() {
		if (elementua instanceof ElementuTalde) {
			((ElementuTalde)this.elementua).kenduTimer();
		}
		else if (lortuEgoera()[sua] == 1) { 
			((Sua)this.elementua).kenduTimer();
		}
		else if (lortuEgoera()[bomba] == 1) {
			((Bomba)this.elementua).kenduTimer();
		}
		else if (lortuEgoera()[etsaia] == 1) {
			((Etsaia)this.elementua).kenduTimer();
		}
	}
	
	private void gehituElementua(GelaxkaElementua pElementua) {
		if (this.elementua == null) {
			this.elementua = pElementua;
		} else {
			ArrayList<GelaxkaElementua> elementuak = new ArrayList<GelaxkaElementua>();
			elementuak.add(this.elementua);
			elementuak.add(pElementua);
			this.elementua = new ElementuTalde(elementuak);
		}
	}
}
