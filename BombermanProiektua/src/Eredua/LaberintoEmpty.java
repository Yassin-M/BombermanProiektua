package Eredua;

import java.util.Random;

public class LaberintoEmpty extends Laberintoa {
	public LaberintoEmpty() {
		super();
	}

	
	@Override
	public void laberintoaSortu(String pJok) {
		this.laberintoa = new Gelaxka[11][17];
		for (int i=0; i<11; i++) {
			for (int j=0; j<17; j++) {
				laberintoa[i][j] = new Gelaxka(i,j);
				if (i==0 && j==0) {
					this.jokalaria = JokalariFactory.getNireJokalariFactory().sortuJokalaria(pJok);
					laberintoa[i][j].setJokalaria(this.jokalaria);
				}
				else if ((i==0 && j==1)||(i==1 && j==0)) {
					continue;
				} else {
					Random r = new Random();
					if (r.nextInt(100) >= 90 && etsaiKop < 10) {
						laberintoa[i][j].setEtsaia(true,0);
						etsaiKop++;
					} else if (r.nextInt(100) >= 80 && etsaiKop < 10) {
						laberintoa[i][j].setEtsaia(true,1);
						etsaiKop++;
					}
				}
			}
		}
		etsaiakHasieratu();		
	}
	
}
