package Eredua;

import java.util.*;

@SuppressWarnings("deprecation")
public abstract class Jokalaria extends Observable {
	protected int bombaKont;
	protected boolean bizirik;
	protected int Xposizioa;
	protected int Yposizioa;
	protected Timer timer = null;
	protected int kont;
	protected static final int PERIODO = 3;
	
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
	
	public void timerBomba() {
		kont = PERIODO;
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				updateTimer();
			}
		};
		timer = new Timer();
		timer.scheduleAtFixedRate(timerTask, 0, 1000);
	}
	
	private void updateTimer() {
		kont--;
		if(kont==0) {
			this.bombaKont++;
			timer.cancel();
			timer = null;
		}
	}
	
	public abstract void bombaJarri();
	
	public void jokalariaHil () {
		bizirik = false;
	}
}
