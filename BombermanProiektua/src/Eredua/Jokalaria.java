package Eredua;

import java.util.*;

public abstract class Jokalaria implements GelaxkaElementua {
	protected int bombaKont;
	protected boolean bizirik;
	protected int i;
	protected int j;
	protected Norabidea norabidea;
	
	protected Timer timer = null;
	protected int kont;
	protected static final int PERIODO = 3;
	
	protected Jokalaria (int pBombaKont, int pI, int pJ) {
		bombaKont = pBombaKont;
		bizirik = true;
		i = pI;
		j = pJ;
		norabidea = Norabidea.HASIERA;
	}

	public void mugituEskuinera() {
		j++;
		this.norabidea = Norabidea.ESKUINERA;
	}

	public void mugituEzkerretara() {
		j--;
		this.norabidea = Norabidea.EZKERRERA;
	}

	public void mugituGora() {
		i--;
		this.norabidea = Norabidea.GORA;
	}

	public void mugituBehera() {
		i++;
		this.norabidea = Norabidea.BEHERA;
	}
	
	public int getJposizioa() {
		return this.j;
	}
	
	public int getIposizioa() {
        return this.i;
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
