
package Eredua;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Etsaia implements GelaxkaElementua {
	private int[] aukerak; // 0 = gora, 1 = behera, 2 = ezkerrera, 3 = eskuma
	private int i;
	private int j;
	private boolean norabidea; // true = ezkerrra, false = eskuma
	private Timer timer = null;
	private boolean aktibo;
	
	protected Etsaia(int pI, int pJ, boolean pNorabidea) {
		this.i = pI;
		this.j = pJ;
		this.aukerak = new int[]{0,1,2,3};
		this.aktibo = true;
		this.norabidea = pNorabidea;
	}
	/*
	private int[] getEgoera() {
		return this.aukerak;
	}
	*/
	private void setEgoera(int[] egoera) {
		this.aukerak = null;
		this.aukerak = egoera;
	}
	
	protected abstract int getMota();
	
	private void kenduAukera(int aukera) {
		int luzera = aukerak.length-1;
		int[] lag = new int[luzera];
		int j = 0;
		for (int i: aukerak) {
			if (i != aukera) {
				lag[j]=i;
				j++;
			}
		}
		aukerak = lag;
	}
	
	
	public void hasiMugitzen() {	
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				if (timer != null) {
					timer.cancel();
				}
				boolean zuzena= false;
				do {
					zuzena = mugitu();
				} while (!zuzena && aktibo);	
				if (aktibo) hasiMugitzen();
			}
		};
		timer = new Timer();
		timer.scheduleAtFixedRate(timerTask, 1000, 1000);		
	}
		
	
	private boolean mugitu() {
		if (aukerak.length == 0) {
			setEgoera(new int []{0,1,2,3});
			return true;
		}
		Random rand = new Random();
		int aukera = rand.nextInt(aukerak.length);
		boolean zuzena = true;
		int mota = getMota();
		switch(aukerak[aukera]) {
			case 0: 
				if (i>0) {
					zuzena=BombermanKudeatzailea.getNireKudeatzaile().getLaberintoa().etsaiaMugitu(i,j,i-1,j,norabidea,mota);
				} else {
					zuzena=false;
				}
				if (!zuzena) {
					kenduAukera(0);
				} else aktibo = false;
				break;
			case 1:
				if (i<10) {
					zuzena = BombermanKudeatzailea.getNireKudeatzaile().getLaberintoa().etsaiaMugitu(i,j,i+1,j,norabidea,mota);
				} else {
					zuzena=false;
				}
				if (!zuzena) {
					kenduAukera(1);
				} else aktibo = false;
				break;
			case 2:
				if (j>0) {
					zuzena = BombermanKudeatzailea.getNireKudeatzaile().getLaberintoa().etsaiaMugitu(i,j,i,j-1, true,mota);
				} else {
					zuzena=false;
				}
				if (!zuzena) {
					kenduAukera(2);
				} else aktibo = false;
				break;
			case 3:
				if (j<16) {
					zuzena = BombermanKudeatzailea.getNireKudeatzaile().getLaberintoa().etsaiaMugitu(i,j,i,j+1, false,mota);
				} else {
					zuzena=false;
				}
				if (!zuzena) {
					kenduAukera(3);
				} else aktibo = false;
				break;
			default:
				mugitu();
				break;
		}
		
		return zuzena;
	}
	
	public void kenduTimer() {
		if (this.timer!=null) {
	        timer.cancel();
	        timer = null;
	        aktibo = false;
		}
    }
	
	public void ezabatu() {
		kenduTimer();
		aktibo = false;
	}
	
	

}
