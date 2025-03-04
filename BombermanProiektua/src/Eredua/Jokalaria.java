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
		if(Laberintoa.getNireLaberintoa().zerDago(Xposizioa+1, Yposizioa)==null) {
			Xposizioa++;
			setChanged();
			notifyObservers();
		}
	}

	public void mugituEzkerretara() {
		if(Laberintoa.getNireLaberintoa().zerDago(Xposizioa-1, Yposizioa)==null) {
			Xposizioa--;
			setChanged();
			notifyObservers();
		}
	}

	public void mugituGora() {
		if(Laberintoa.getNireLaberintoa().zerDago(Xposizioa, Yposizioa-1)==null) {
			Yposizioa--;
			setChanged();
			notifyObservers();
		}
	}

	public void mugituBehera() {
		if(Laberintoa.getNireLaberintoa().zerDago(Xposizioa, Yposizioa+1)==null) {
			Yposizioa++;
			setChanged();
			notifyObservers();
		}
	}
	
	public int getXposizioa() {
		return this.Xposizioa;
	}
	
	public int getYposizioa() {
        return this.Yposizioa;
    }
	
	public abstract void bombaJarri();
}
