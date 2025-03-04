package Eredua;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

@SuppressWarnings("deprecation")
public class Bomba extends Observable {
	private static final int PERIODO = 3;
	private int kont;
	private Timer timer = null;
	private int i;
	private int j;
	
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
		
	public void updateKont() {
			kont--;
			if(kont==0) {
				Laberintoa.getNireLaberintoa().suaJarri(i, j, false);
				timer.cancel();
				timer = null;
				setChanged();
				notifyObservers();
			}
		}
}
