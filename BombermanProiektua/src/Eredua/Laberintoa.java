package Eredua;

import java.util.Observable;
import java.util.Random;
@SuppressWarnings("deprecation")

public class Laberintoa extends Observable{
	private static Laberintoa nireLaberintoa;
	private Gelaxka[][] laberintoa;
	
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
				laberintoa[i][j] = new Gelaxka();
				if (i==0 && j==0) {
					laberintoa[i][j].setJokalaria(new JokZuria(10,true,0,0));
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
					else {
						laberintoa[i][j] = new Gelaxka();
					}
				}
			}
		}
	}
	
	public BlokeMota zerDago(int i, int j) {
		return laberintoa[i][j].zerDago();
	}
	
	public void bombaJarri(int i, int j, boolean pUltrabomba) {
		laberintoa[i][j].setBomba(i,j,pUltrabomba);
	}
	
	public void suaJarri(int i, int j, boolean pUltrabomba) {
		if (!pUltrabomba) {
			laberintoa[i][j].setSua(i,j);
			laberintoa[i][j+1].setSua(i,j);
			laberintoa[i][j-1].setSua(i,j);
			laberintoa[i+1][j].setSua(i,j);
			laberintoa[i-1][j].setSua(i,j);
		}
		else {
			//TODO
		}
	}
	
	public void suaKendu(int i, int j) {
		laberintoa[i][j].kenduAurrekoa();
	}
}
