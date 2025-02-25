package Eredua;

import java.util.Observable;
import java.util.Random;
@SuppressWarnings("deprecation")

public class Laberintoa extends Observable{
	private static Laberintoa nireLaberintoa;
	private Gelaxka[][] laberintoa;
	
	private Laberintoa() {
		laberintoaSortu();
	}
	
	public static Laberintoa getNireLaberintoa() {
		if (nireLaberintoa == null) {
			nireLaberintoa = new Laberintoa();
		}
		return nireLaberintoa;
	}
	
	private void laberintoaSortu() {
		this.laberintoa = new Gelaxka[11][17];
		for (int i=0; i<11; i++) {
			for (int j=0; j<17; j++) {
				laberintoa[i][j] = new Gelaxka();
				if (i==0 && j==0) {
					//laberintoa[i][j].setJokalaria();
				}
				else if ((i==0 && j==1)||(i==1 && j==0)) {
					continue;
				}
				else if (i%2 != 0 && i%2 != 0) {
					laberintoa[i][j].setBlokeGogorra();
				}
				else {
					Random r = new Random();
					if (r.nextInt(100) >= 40) {
						laberintoa[i][j].setBlokeBiguna();
					}
					else if (r.nextInt(100) >= 90) {
						//laberintoa[i][j].setEtsaia();
					}
					else {
						laberintoa[i][j] = new Gelaxka();
					}
				}
			}
		}
		setChanged();
		notifyObservers();
	}
}
