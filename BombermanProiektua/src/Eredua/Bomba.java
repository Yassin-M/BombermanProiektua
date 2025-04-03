package Eredua;

import java.util.Timer;
import java.util.TimerTask;

public abstract class Bomba {
	private static final int PERIODO = 3;
	protected int kont;
	protected Timer timer = null;
	protected int i;
	protected int j;
	
	public Bomba(int i, int j) {
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
		
	public abstract void updateKont();
}
