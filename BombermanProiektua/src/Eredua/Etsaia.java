package Eredua;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Etsaia {
	private int[] aukerak; // 0 = gora, 1 = behera, 2 = ezkerrera, 3 = eskuma
	private int i;
	private int j;
	private boolean norabidea; // true = ezkerrra, false = eskuma
	private Timer timer = null;
	private boolean aktibo;
	
	public Etsaia(int pI, int pJ, boolean pNorabidea) {
		this.i = pI;
		this.j = pJ;
		this.aukerak = new int[]{0,1,2,3};
		this.aktibo = true;
		this.norabidea = pNorabidea;
	}
	
	private int[] getEgoera() {
		return this.aukerak;
	}
	
	private void setEgoera(int[] egoera) {
		this.aukerak = null;
		this.aukerak = egoera;
	}
	
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
				timer.cancel();	
				boolean zuzena= false;
				do {
					zuzena = mugitu();
				} while (!zuzena);	
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
		switch(aukerak[aukera]) {
			case 0: 
				zuzena=Laberintoa.getNireLaberintoa().etsaiaMugituGora(i,j,norabidea);
				if (!zuzena) {
					kenduAukera(0);
				} else aktibo = false;
				break;
			case 1:
				zuzena = Laberintoa.getNireLaberintoa().etsaiaMugituBehera(i, j,norabidea);
				if (!zuzena) {
					kenduAukera(1);
				} else aktibo = false;
				break;
			case 2:
				zuzena = Laberintoa.getNireLaberintoa().etsaiaMugituEzerretara(i, j, true);
				if (!zuzena) {
					kenduAukera(2);
				} else aktibo = false;
				break;
			case 3:
				zuzena = Laberintoa.getNireLaberintoa().etsaiaMugituEskuinera(i, j, false);
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
	
	public void ezabatu() {
		timer = null;
	}

}
