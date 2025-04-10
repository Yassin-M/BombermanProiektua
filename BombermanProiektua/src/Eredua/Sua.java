package Eredua;

import java.util.Timer;
import java.util.TimerTask;

public class Sua implements GelaxkaElementua {
	
	private static final int PERIODO = 2;
	private int kont;
	private Timer timer = null;
	private int i;
	private int j;
	
	public Sua(int i, int j) {
		this.i = i;
		this.j = j;
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
	
	public void updateKont() {
		kont--;
		if(kont==0) {
			BombermanKudeatzailea.getNireKudeatzaile().getLaberintoa().suaKendu(i,j);
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
		return new Integer[]{1,0,0,0,0,0,0,0};
	}
}
