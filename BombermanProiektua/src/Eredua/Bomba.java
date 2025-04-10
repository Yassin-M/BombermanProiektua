package Eredua;

import java.util.Timer;
import java.util.TimerTask;

public abstract class Bomba implements GelaxkaElementua {
	private static final int PERIODO = 3;
	private int kont;
	private Timer timer = null;
	private int i;
	private int j;
	private BombaPortaera bombaPortaera;
	
	public Bomba(int i, int j, BombaPortaera pPortaera) {
			this.i = i;
			this.j = j;
			this.bombaPortaera = pPortaera; 
			kont = PERIODO;
			TimerTask timerTask = new TimerTask() {
				@Override
				public void run() {
					updateKont();
				}
			};
			timer = new Timer();
			timer.scheduleAtFixedRate(timerTask, 0, 1000);
	}
	
	protected BombaPortaera getBombaPortaera() {
		return this.bombaPortaera;
	}
	
	public void updateKont() {
		kont--;
		if(kont==0) {
			getBombaPortaera().eztanda(i,j);
			
			if(timer != null) {
				timer.cancel();
			}
			timer = null;
		}
	}
	
	public void kenduTimer() {
		if (this.timer!=null) {
	        timer.cancel();
	        timer = null;
		}
    }
	
	public Integer[] lortuEgoera() {
		return new Integer[]{0,1,0,0,0,0,0,0};
	}
}
