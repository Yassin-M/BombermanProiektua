package Eredua;

import java.util.Timer;
import java.util.TimerTask;

public abstract class Blast implements GelaxkaElementua {
	
	private int kont;
	private Timer timer = null;
	private int i;
	private int j;
	
	
	
	protected Blast(int i, int j) {
		this.i = i;
		this.j = j;
		this.kont = getPeriodo();
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				updateKont();
			}
		};
		timer = new Timer();
		timer.scheduleAtFixedRate(timerTask, 0, 1000);
	}
	
	public abstract int getPeriodo();
	
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

	public abstract Integer[] lortuEgoera();
}
