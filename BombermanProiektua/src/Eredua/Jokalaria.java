package Eredua;

import java.util.*;

public abstract class Jokalaria implements GelaxkaElementua {
	protected int bombaKont;
	protected boolean bizirik;
	protected int i;
	protected int j;
	protected Norabidea norabidea;
	protected int irudia;
	
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
		if(norabidea!=Norabidea.ESKUINERA) {
			irudia = 1;
			this.norabidea = Norabidea.ESKUINERA;
		}else {
			if(irudia==5) {
				irudia = 2;
			}else {
				irudia++;
			}
		}
	}

	public void mugituEzkerretara() {
		j--;
		if(norabidea!=Norabidea.EZKERRERA) {
			irudia = 1;
			this.norabidea = Norabidea.EZKERRERA;
		}else {
			if(irudia==5) {
				irudia = 2;
			}else {
				irudia++;
			}
		}
	}

	public void mugituGora() {
		i--;
		if(norabidea!=Norabidea.GORA) {
			irudia = 1;
			this.norabidea = Norabidea.GORA;
		}else {
			if(irudia==5) {
				irudia = 2;
			}else {
				irudia++;
			}
		}
	}

	public void mugituBehera() {
		i++;
		if(norabidea!=Norabidea.BEHERA) {
			irudia = 1;
			this.norabidea = Norabidea.BEHERA;
		}else {
			if(irudia==4) {
				irudia = 1;
			}else {
				irudia++;
			}
		}
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
	
	public abstract Integer[] lortuEgoera();
}
