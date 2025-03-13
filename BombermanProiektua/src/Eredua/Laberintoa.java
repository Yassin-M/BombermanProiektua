package Eredua;

import java.util.Observable;
import java.util.Random;

@SuppressWarnings("deprecation")

public class Laberintoa extends Observable{
	private static Laberintoa nireLaberintoa;
	private Gelaxka[][] laberintoa;
	private Jokalaria jokalaria;
	private int blokeBigunKop = 0;
	private int score = 0;
	
	private Laberintoa() {
		//laberintoaSortu();
	}
	
	public static Laberintoa getNireLaberintoa() {
		if (nireLaberintoa == null) {
			nireLaberintoa = new Laberintoa();
		}
		return nireLaberintoa;
	}
	
	public void laberintoaSortu() {
		this.laberintoa = new Gelaxka[11][17];
		for (int i=0; i<11; i++) {
			for (int j=0; j<17; j++) {
				laberintoa[i][j] = new Gelaxka(i,j);
				if (i==0 && j==0) {
					this.jokalaria = new JokZuria(10,true,0,0);
					laberintoa[i][j].setJokalaria(this.jokalaria);
				}
				else if ((i==0 && j==1)||(i==1 && j==0)) {
					continue;
				}
				else if (i%2 != 0 && j%2 != 0) {
					laberintoa[i][j].setBlokeGogorra();
				}
				else {
					Random r = new Random();
					if (r.nextInt(100) >= 40) {
						laberintoa[i][j].setBlokeBiguna();
						blokeBigunKop++;
					
					
					}
					else if (r.nextInt(100) >= 90) {
						//laberintoa[i][j].setEtsaia();
					}
				}
			}
		}
		setChanged();
		notifyObservers(1);
	}
	
	public BlokeMota zerDago(int i, int j) {
		return laberintoa[i][j].zerDago();
	}
	
	public Gelaxka getGelaxka(int i, int j) {
		return laberintoa[i][j];
	}

	public void setJokalaria(int i, int j, Jokalaria pJok) {
		laberintoa[i][j].setJokalaria(pJok);
	}
	
	public void bombaJarri(int i, int j, boolean pUltrabomba) {
		if(jokalaria.bombaKont>0) {
			laberintoa[i][j].setBomba(i,j,pUltrabomba);
			jokalaria.bombaKont--;
			if(jokalaria.bombaKont==0) {
				jokalaria.timerBomba();
			}
		}
	}
	
	public void suaJarri(int i, int j, boolean pUltrabomba) {
		Jokalaria jokalaria = getJokalaria();
		if (!pUltrabomba) {
			if (i==jokalaria.getYposizioa() && j==jokalaria.getXposizioa()) {
				partidaAmaitu(getJokalaria(),false);
			}
			laberintoa[i][j].setSua(i,j);
			if (i > 0) {
				
				laberintoa[i-1][j].setSua(i-1, j);
			}
			if (i < 10) {
				
				laberintoa[i+1][j].setSua(i+1, j);
			}
			if (j > 0) {
				
				laberintoa[i][j-1].setSua(i, j-1);
			}
			if (j < 16) {
				
				laberintoa[i][j+1].setSua(i, j+1);
			}
		}
		else {
			//TODO
		}
	}
	
	public void suaKendu(int i, int j) {
		if(i>=0 && i<=10 && j>=0 && j<=16) {
			laberintoa[i][j].kenduAurrekoa();
		}
	}
	
	public void jokalariaMugituEskuinera() {
		Jokalaria j = getJokalaria();
		if(j.bizirik && j.getXposizioa() < 16) {
			if (laberintoa[j.getYposizioa()][j.getXposizioa() + 1].zerDago() == null) {
				laberintoa[j.getYposizioa()][j.getXposizioa()].kenduJokalaria();
				j.mugituEskuinera();
	            setJokalaria(j.getYposizioa(), j.getXposizioa(), j);
			} else if (laberintoa[j.getYposizioa()][j.getXposizioa() + 1].zerDago() == BlokeMota.SUA) {
				partidaAmaitu(j,false);
			}
		}
	}
	
	public void jokalariaMugituEzkerretara() {
		Jokalaria j = getJokalaria();
		if(j.bizirik && j.getXposizioa() > 0) {
			if (laberintoa[j.getYposizioa()][j.getXposizioa()-1].zerDago() == null) {
				laberintoa[j.getYposizioa()][j.getXposizioa()].kenduJokalaria();
				j.mugituEzkerretara();
	            setJokalaria(j.getYposizioa(), j.getXposizioa(), j);
			} else if (laberintoa[j.getYposizioa()][j.getXposizioa() - 1].zerDago() == BlokeMota.SUA) {
				partidaAmaitu(j, false);
			}
		}
	}
	
	public void jokalariaMugituGora() {
		Jokalaria j = getJokalaria();
		if(j.bizirik && j.getYposizioa() > 0) {
			if (laberintoa[j.getYposizioa()-1][j.getXposizioa()].zerDago() == null) {
				laberintoa[j.getYposizioa()][j.getXposizioa()].kenduJokalaria();
				j.mugituGora();
	            setJokalaria(j.getYposizioa(), j.getXposizioa(), j);
			} else if (laberintoa[j.getYposizioa()-1][j.getXposizioa()].zerDago() == BlokeMota.SUA) {
				partidaAmaitu(j, false);
			}
		}
	}
	
	public void jokalariaMugituBehera() {
        Jokalaria j = getJokalaria();
        if(j.bizirik && j.getYposizioa() < 10) {
	        if (laberintoa[j.getYposizioa()+1][j.getXposizioa()].zerDago() == null) {
	            laberintoa[j.getYposizioa()][j.getXposizioa()].kenduJokalaria();
	            j.mugituBehera();
	            setJokalaria(j.getYposizioa(), j.getXposizioa(), j);
	        } else if (laberintoa[j.getYposizioa()+1][j.getXposizioa() + 1].zerDago() == BlokeMota.SUA) {
				partidaAmaitu(j, false);
			}
        }        
	}
	
	public Jokalaria getJokalaria() {
        return this.jokalaria;
    }
	
	public int getScore() {
		return this.score;
	}
	
	public void addScore(int pScore) {
		this.score += pScore;
	}
	
	public void partidaAmaitu(Jokalaria j, boolean irabazi) {
		if (irabazi) {
			setChanged();
			notifyObservers(true);
		} else {
			laberintoa[j.getYposizioa()][j.getXposizioa()].kenduJokalaria();
			j.jokalariaHil();
			setChanged();
			notifyObservers("Amaituta");
		}
	}
	public void kenduBlokeBiguna() {
		this.blokeBigunKop--;
		if (this.blokeBigunKop == 0) {
			partidaAmaitu(getJokalaria(),true);
		}
    }
	
	

	
 }
