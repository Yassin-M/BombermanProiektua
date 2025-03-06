package Eredua;

import java.util.Observable;
import java.util.Random;

@SuppressWarnings("deprecation")

public class Laberintoa extends Observable{
	private static Laberintoa nireLaberintoa;
	private Gelaxka[][] laberintoa;
	private Jokalaria jokalaria;
	
	private Laberintoa() {
		laberintoaSortu();
	}
	
	public static Laberintoa getNireLaberintoa() {
		if (nireLaberintoa == null) {
			nireLaberintoa = new Laberintoa();
		}
		return nireLaberintoa;
	}
	
	private void laberintoaSortu() {
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
					}
					else if (r.nextInt(100) >= 90) {
						//laberintoa[i][j].setEtsaia();
					}
				}
			}
		}
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
		laberintoa[i][j].setBomba(i,j,pUltrabomba);
	}
	
	public void suaJarri(int i, int j, boolean pUltrabomba) {
		if (!pUltrabomba) {
			laberintoa[i][j].setSua(i,j);
			if (i > 0) {
				laberintoa[i - 1][j].setSua(i - 1, j);
			}
			if (i < 10) {
				laberintoa[i + 1][j].setSua(i + 1, j);
			}
			if (j > 0) {
				laberintoa[i][j - 1].setSua(i, j - 1);
			}
			if (j < 16) {
				laberintoa[i][j + 1].setSua(i, j + 1);
			}
		}
		else {
			//TODO
		}
	}
	
	public void suaKendu(int i, int j) {
		if(i>=0 && j>=0) {
			laberintoa[i][j].kenduAurrekoa();
		}
	}
	
	public void jokalariaMugituEskuinera() {
		Jokalaria j = getJokalaria();
		if(j.getXposizioa() < 16) {
			if (laberintoa[j.getYposizioa()][j.getXposizioa() + 1].zerDago() == null) {
				laberintoa[j.getYposizioa()][j.getXposizioa()].kenduJokalaria();
				j.mugituEskuinera();
	            setJokalaria(j.getYposizioa(), j.getXposizioa(), j);
			} else if (laberintoa[j.getYposizioa()][j.getXposizioa() + 1].zerDago() == BlokeMota.SUA) {
				partidaAmaitu();
			}
		}
	}
	
	public void jokalariaMugituEzkerretara() {
		Jokalaria j = getJokalaria();
		if(j.getXposizioa() > 0) {
			if (laberintoa[j.getYposizioa()][j.getXposizioa()-1].zerDago() == null) {
				laberintoa[j.getYposizioa()][j.getXposizioa()].kenduJokalaria();
				j.mugituEzkerretara();
	            setJokalaria(j.getYposizioa(), j.getXposizioa(), j);
			} else if (laberintoa[j.getYposizioa()][j.getXposizioa() + 1].zerDago() == BlokeMota.SUA) {
				partidaAmaitu();
			}
		}
	}
	
	public void jokalariaMugituGora() {
		Jokalaria j = getJokalaria();
		if(j.getYposizioa() > 0) {
			if (laberintoa[j.getYposizioa()-1][j.getXposizioa()].zerDago() == null) {
				laberintoa[j.getYposizioa()][j.getXposizioa()].kenduJokalaria();
				j.mugituGora();
	            setJokalaria(j.getYposizioa(), j.getXposizioa(), j);
			} else if (laberintoa[j.getYposizioa()][j.getXposizioa() + 1].zerDago() == BlokeMota.SUA) {
				partidaAmaitu();
			}
		}
	}
	
	public void jokalariaMugituBehera() {
        Jokalaria j = getJokalaria();
        if(j.getYposizioa() < 10) {
	        if (laberintoa[j.getYposizioa()+1][j.getXposizioa()].zerDago() == null) {
	            laberintoa[j.getYposizioa()][j.getXposizioa()].kenduJokalaria();
	            j.mugituBehera();
	            setJokalaria(j.getYposizioa(), j.getXposizioa(), j);
	        } else if (laberintoa[j.getYposizioa()][j.getXposizioa() + 1].zerDago() == BlokeMota.SUA) {
				partidaAmaitu();
			}
        }
        
	}
	
	public Jokalaria getJokalaria() {
        return this.jokalaria;
    }
	
	public void partidaAmaitu() {
		// TODO
	}
 }
