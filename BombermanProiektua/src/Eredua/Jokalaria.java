package Eredua;

import java.util.*;

public abstract class Jokalaria extends Observable {
	protected int bombaKont;
	protected boolean bizirik;
	protected int Xposizioa;
	protected int Yposizioa;
	
	public Jokalaria (int pBombaKont, boolean pBizirik, int pXposizioa, int pYposizioa) {
		bombaKont = pBombaKont;
		bizirik = pBizirik;
		Xposizioa = pXposizioa;
		Yposizioa = pYposizioa;
	}

	public void mugituEskuinera() {
		if(Laberintoa.getNireLaberintoa().zerDago(Yposizioa, Xposizioa+1)==null) {
			Xposizioa++;
		}
	}

	public void mugituEzkerretara() {
		if(Laberintoa.getNireLaberintoa().zerDago(Yposizioa, Xposizioa-1)==null) {
			Xposizioa--;
		}
	}

	public void mugituGora() {
		if(Laberintoa.getNireLaberintoa().zerDago(Yposizioa-1, Xposizioa)==null) {
			Yposizioa--;
		}
	}

	public void mugituBehera() {
		if(Laberintoa.getNireLaberintoa().zerDago(Yposizioa+1, Xposizioa)==null) {
			Yposizioa++;
		}
	}
	
	public int getXposizioa() {
		return this.Xposizioa;
	}
	
	public int getYposizioa() {
        return this.Yposizioa;
    }
	
	public abstract void bombaJarri();
	
	public void jokalariaHil () {
		bizirik = false;
	}
}
