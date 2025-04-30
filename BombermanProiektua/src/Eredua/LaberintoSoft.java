package Eredua;

import java.util.Random;

public class LaberintoSoft extends Laberintoa {
	public LaberintoSoft() {
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
				} else if (pJok.equals("blue") && ((i==0 && j==2)||(i==2 && j==0))) {
					continue;
				} else if (pJok.equals("blue") && ((i==0 && j==3)||(i==3 && j==0))){
					laberintoa[i][j].setBlokeBiguna();
				} else { 
					Random r = new Random();
					if (r.nextInt(100) >= 40) {
						laberintoa[i][j].setBlokeBiguna();
					} else if (r.nextInt(100) >= 90 && etsaiKop < 8) {
						laberintoa[i][j].setEtsaia(true,0);
						etsaiKop++;
					} else if (r.nextInt(100) >= 90 && etsaiKop < 8) {
						laberintoa[i][j].setEtsaia(true,1);
						etsaiKop++;
					}
				}
			}
		}
		etsaiakHasieratu();		
	}
	
}
