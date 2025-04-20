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
			BombermanKudeatzailea.getNireKudeatzaile().getLaberintoa().addScore(100);
		} 
		else if (lortuEgoera()[sua] != 0){
			gehituElementua(pJok);
		}
		else {
			this.elementua = pJok;
		}
		setChanged();
		notifyObservers(lortuEgoera());
	}
	public boolean setEtsaia(boolean pNorabidea, int mota) {
		Etsaia etsai = EtsaiaFactory.getNireEtsaiaFactory().sortuEtsaia(x, y, pNorabidea, mota);
		boolean zuzena = false;
		if (lortuEgoera()[etsaia] == 0) {
			zuzena = true;

			if (lortuEgoera()[jokalaria] != 0) {
				gehituElementua(etsai);
				BombermanKudeatzailea.getNireKudeatzaile().getLaberintoa().addScore(100);
			} 
			else {
				this.elementua = etsai;
			}
			setChanged();
	        notifyObservers(lortuEgoera());
		}
		return zuzena;
    }
	
	public void setSua(int i, int j, int pMota) {
		BlastFactory BF = BlastFactory.getNireBF();
		if (lortuEgoera()[blokeGogorra] == 0 || pMota == 4) {
			if(elementua!=null) {
				if (lortuEgoera()[jokalaria] != 0) {
					gehituElementua(BF.sortuBlast(i,j,pMota));
					BombermanKudeatzailea.getNireKudeatzaile().getLaberintoa().partidaAmaitu(false);
				}
				else if (lortuEgoera()[blokeBiguna] == 1) {
					this.elementua = BF.sortuBlast(i,j,pMota);
					BombermanKudeatzailea.getNireKudeatzaile().getLaberintoa().addScore(100);
					//BombermanKudeatzailea.getNireKudeatzaile().getLaberintoa().kenduBlokeBiguna();
				}
				else if (lortuEgoera()[etsaia] == 1) {
					gehituElementua(BF.sortuBlast(i,j,pMota));
					BombermanKudeatzailea.getNireKudeatzaile().getLaberintoa().addScore(500);
					BombermanKudeatzailea.getNireKudeatzaile().getLaberintoa().kenduEtsaia();
					((ElementuTalde)this.elementua).getEtsaia().ezabatu();;
				} 
				else if(lortuEgoera()[sua] != 0) {
					if (this.elementua instanceof Blast) {
						((Blast) this.elementua).kenduTimer();
					}
					else {
						((ElementuTalde) this.elementua).kenduTimer();
					}
					this.elementua = BF.sortuBlast(i,j,pMota);
				}
				else {
					this.elementua = BF.sortuBlast(i,j,pMota);
				}
			}
			else {
				this.elementua = BF.sortuBlast(i,j,pMota);
			}
			setChanged();
			notifyObservers(lortuEgoera());
		}
	}
	
	public void setPosoia(int i, int j, int pMota) {
		BlastFactory BF = BlastFactory.getNireBF();
		if(elementua!=null) {
			if (lortuEgoera()[jokalaria] != 0) {
				gehituElementua(BF.sortuBlast(i,j,pMota));
				BombermanKudeatzailea.getNireKudeatzaile().getLaberintoa().partidaAmaitu(false);
			} else if(lortuEgoera()[sua] != 0) {
				if (this.elementua instanceof Blast) {
					((Blast) this.elementua).kenduTimer();
				}
				else {
					((ElementuTalde) this.elementua).kenduTimer();
				}
				this.elementua = BF.sortuBlast(i,j,pMota);
			}
			else {
				this.elementua = BF.sortuBlast(i,j,pMota);
			}
		}
		else {
			this.elementua = BF.sortuBlast(i,j,pMota);
		}
		setChanged();
		notifyObservers(lortuEgoera());
	
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
		if (lortuEgoera()[jokalaria] != 0) {
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
        if(lortuEgoera()[bomba]!=0) {
        	this.elementua=((ElementuTalde)this.elementua).getBomba();
        }
        else if (lortuEgoera()[sua]!=0) {
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
		} else if (egoera[sua] != 0) {
			return BlokeMota.SUA;
		} else if (egoera[bomba] != 0) {
			return BlokeMota.BOMBA;
		} else if (egoera[etsaia] == 1) {
			return BlokeMota.ETSAIA;
		} else if (egoera[jokalaria] != 0) {
			return BlokeMota.JOKALARIA;
		} else if(egoera[sua] == 2) {
			return BlokeMota.POSOIA;
		}
		else {
			return null;
		}
	}
	
	public void amatatuTimer() {
		if (elementua instanceof ElementuTalde) {
			((ElementuTalde)this.elementua).kenduTimer();
			if (((ElementuTalde) this.elementua).getSua() != null && ((ElementuTalde) this.elementua).getEtsaia()!=null) {
				this.elementua = null;
			}
		}
		else if (lortuEgoera()[sua] != 0) { 
			((Blast)this.elementua).kenduTimer();
			this.elementua = null;
			
		}
		else if (lortuEgoera()[bomba] != 0) {
			((Bomba)this.elementua).kenduTimer();
			this.elementua = null;

		}
		else if (lortuEgoera()[etsaia] == 1) {
			((Etsaia)this.elementua).kenduTimer();
			this.elementua = null;

		}
 		setChanged();
 		notifyObservers(lortuEgoera());
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
	public GelaxkaElementua getElementua() {
		return elementua;
	}
}
