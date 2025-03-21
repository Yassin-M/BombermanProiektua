package Eredua;

import java.util.*;

public abstract class Jokalaria {
	protected int bombaKont;
	protected boolean bizirik;
	protected int Xposizioa;
	protected int Yposizioa;
	private Norabidea norabidea;
	
	protected Timer timer = null;
	protected int kont;
	protected static final int PERIODO = 3;
	
	protected Jokalaria (int pBombaKont, boolean pBizirik, int pXposizioa, int pYposizioa) {
		bombaKont = pBombaKont;
		bizirik = pBizirik;
		Xposizioa = pXposizioa;
		Yposizioa = pYposizioa;
		norabidea = Norabidea.HASIERA;
	}

	public void mugituEskuinera() {
		Xposizioa++;
		this.norabidea = Norabidea.ESKUINERA;
	}

	public void mugituEzkerretara() {
		Xposizioa--;
		this.norabidea = Norabidea.EZKERRERA;
	}

	public void mugituGora() {
		Yposizioa--;
		this.norabidea = Norabidea.GORA;
	}

	public void mugituBehera() {
		Yposizioa++;
		this.norabidea = Norabidea.BEHERA;
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
	
	public Norabidea getNorabidea() {
		return this.norabidea;
	}
}
