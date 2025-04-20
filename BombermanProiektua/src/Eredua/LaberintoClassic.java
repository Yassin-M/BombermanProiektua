package Eredua;

import java.util.Random;

public class LaberintoClassic extends Laberintoa {
	public LaberintoClassic() {
		super();
	}

	@Override
	public void laberintoaSortu(String pJok) {
		super.laberintoa = new Gelaxka[11][17];
		for (int i=0; i<11; i++) {
			for (int j=0; j<17; j++) {
				laberintoa[i][j] = new Gelaxka(i,j);
				if (i==0 && j==0) {
					this.jokalaria = JokalariFactory.getNireJokalariFactory().sortuJokalaria(pJok);
					laberintoa[i][j].setJokalaria(this.jokalaria);
				}
				else if ((i==0 && j==1)||(i==1 && j==0)) {
					continue;
				}
				else if (i%2 != 0 && j%2 != 0) {
					laberintoa[i][j].setBlokeGogorra();
				}
				else {
					Random r = new Random();
					if (r.nextInt(100) >= 40) {
						laberintoa[i][j].setBlokeBiguna();
						blokeBigunKop++;					
					}
					else if (r.nextInt(100) >= 90 && etsaiKop < 6) {
						etsaiKop++;
						laberintoa[i][j].setEtsaia(true,0);
					}
					else if (r.nextInt(100) >= 80 && etsaiKop < 6) {
						etsaiKop++;
						laberintoa[i][j].setEtsaia(true,1);
					}
				}
			}
		}
		etsaiakHasieratu();
	}
}
