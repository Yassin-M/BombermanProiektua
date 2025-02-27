package Eredua;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

public class Sua extends Observable{
	
	private static final int PERIODO = 2;
	private int kont;
	private Timer timer = null;
	
	public Sua() {
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
			//sua amaitu
		}
		setChanged();
		notifyObservers();
	}
	
	public void gelaxkakApurtu() {
		//TODO
		
		setChanged();
		notifyObservers();
	}
}
