package Eredua;


import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Predicate;

public abstract class Laberintoa {
	protected Gelaxka[][] laberintoa;
	protected Jokalaria jokalaria;
	protected int blokeBigunKop = 0;
	protected int etsaiKop = 0;
	private int score = 0;
	
	protected Laberintoa() {
		this.laberintoa = null;
		this.jokalaria = null;
		this.blokeBigunKop = 0;
		this.etsaiKop = 0;
		this.score = 0;
	}
	
	public abstract void laberintoaSortu(String pJok);
	
	protected void etsaiakHasieratu() {
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 17; j++) {
				if (laberintoa[i][j].zerDago() == BlokeMota.ETSAIA) {
					laberintoa[i][j].etsaiaHasieratu();
				}
			}
		}
	}
	
	public BlokeMota zerDago(int i, int j) {
		//ZerDago baina kontrolatuta
		try {
			return laberintoa[i][j].zerDago();
		}
		catch(IndexOutOfBoundsException e) {
			//System.out.println(i+", "+j+" posizioa matrizetik kanpo dago");
			return null;
		}
	}
	
	public Gelaxka getGelaxka(int i, int j) {
		return laberintoa[i][j];
	}

	private void setJokalaria(int i, int j, Jokalaria pJok) {
		laberintoa[i][j].setJokalaria(pJok);
	}
	
	public void bombaJarri(int i, int j, int pBombaMota) {
		laberintoa[i][j].setBomba(i,j,pBombaMota);
	}
	

	public boolean suaJarri(int i, int j, int pMota){
		boolean zuzena = false;
		if (laberintoa[i][j].zerDago() != BlokeMota.BLOKEGOGORRA) {
			zuzena = true;
			laberintoa[i][j].setSua(i,j,pMota);
		}
		return zuzena;
	}
	
	public void suaKendu(int i, int j) {
		if(i>=0 && i<=10 && j>=0 && j<=16) {
				laberintoa[i][j].kenduAurrekoa();
		}
	}

	public void jokalariaMugitu(Consumer<Jokalaria> mugimendua, Predicate<Jokalaria> baldintza, int pI, int pJ) {
		Jokalaria jok = getJokalaria();
		int i = jok.getIposizioa();
		int j = jok.getJposizioa();
		if(jok.bizirik && baldintza.test(jok)) {
			BlokeMota zerDago = laberintoa[i+pI][j+pJ].zerDago();
			if (zerDago == null || zerDago == BlokeMota.SUA || zerDago == BlokeMota.ETSAIA) {
				laberintoa[i][j].kenduJokalaria();
				mugimendua.accept(jok);
				setJokalaria(i+pI, j+pJ, jok);
	            if (zerDago == BlokeMota.SUA || zerDago == BlokeMota.ETSAIA) {
					partidaAmaitu(false);
					if (zerDago == BlokeMota.ETSAIA) {
						laberintoa[i+pI][j+pJ].amatatuTimer();
					}
				}
			}
		}
	}
	
	public Jokalaria getJokalaria() {
        return this.jokalaria;
    }
	
	public int getScore() {
		return this.score;
	}
	
	public void addScore(int pScore) {
		this.score += pScore;
	}
	
	public void partidaAmaitu(boolean irabazi) {
		if (irabazi) {
			BombermanKudeatzailea.getNireKudeatzaile().itxaron(irabazi);
        } else {
			jokalaria.jokalariaHil();
			BombermanKudeatzailea.getNireKudeatzaile().itxaron(irabazi);
		}
	}
	public void kenduBlokeBiguna() {
		this.blokeBigunKop--;
		if (this.blokeBigunKop == 0 && this.etsaiKop == 0) {
		    partidaAmaitu(true);
		}
    }
	
	public void kenduEtsaia() {
        this.etsaiKop--;
        if (this.etsaiKop == 0) {
            partidaAmaitu(true);
        }
	}
	
	public void timerrakAmatatu(boolean pIrabazi) {
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 17; j++) {
				laberintoa[i][j].amatatuTimer();
			}
		}
	}
	
	public boolean etsaiaMugitu(int i, int j, int iBerria, int jBerria, boolean pNorabidea) {
		boolean zuzena = true;
		Random rand = new Random();
		if (laberintoa[iBerria][jBerria].zerDago() == null) {
			zuzena = laberintoa[iBerria][jBerria].setEtsaia(pNorabidea);	
			if (zuzena) {
				laberintoa[i][j].kenduAurrekoa();
				laberintoa[iBerria][jBerria].etsaiaHasieratu();	
			}
		} else if (laberintoa[iBerria][jBerria].zerDago() == BlokeMota.SUA && rand.nextInt(100) >= 80) {
			laberintoa[i][j].etsaiaHil();
			kenduEtsaia();
		} else if (laberintoa[iBerria][jBerria].zerDago() == BlokeMota.JOKALARIA) {
			laberintoa[iBerria][jBerria].setEtsaia(pNorabidea);
			laberintoa[i][j].kenduAurrekoa();
			partidaAmaitu(false);
		} else zuzena = false;
		return zuzena;
	}
 }
