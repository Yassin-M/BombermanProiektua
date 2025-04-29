package Eredua;


import java.util.Arrays;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Predicate;

import javax.sound.sampled.LineUnavailableException;

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
	    Arrays.stream(laberintoa).flatMap(Arrays::stream).filter(gelaxka -> gelaxka.zerDago() == BlokeMota.ETSAIA).forEach(Gelaxka::etsaiaHasieratu);
	}
	
	public BlokeMota zerDago(int i, int j) {
		//ZerDago baina kontrolatuta
		try {
			return laberintoa[i][j].zerDago();
		}
		catch(IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	public Gelaxka getGelaxka(int i, int j) {
		return laberintoa[i][j];
	}

	public Etsaia getEtsaia(int i, int j) {
		return (Etsaia) laberintoa[i][j].getElementua();
	}

	private void setJokalaria(int i, int j, Jokalaria pJok) {
		laberintoa[i][j].setJokalaria(pJok);
	}
	
	public void bombaJarri(int i, int j, int pBombaMota) {
		try {
			Audio.getNireAudio();
			Audio.playSoinua("../BombermanProiektua/BombermanProiektua/src/Audioa/WhatsApp Audio 2025-04-29 at 17.44.56.wav");
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		laberintoa[i][j].setBomba(i,j,pBombaMota);
	}
	

	public boolean suaJarri(int i, int j, int pMota){
		boolean zuzena = false;
		if ((laberintoa[i][j].zerDago() != BlokeMota.BLOKEGOGORRA) || (pMota == 4)) {
			zuzena = true;
			laberintoa[i][j].setSua(i,j,pMota);
		}
		return zuzena;
	}
	
	public void posoiaJarri(int i,int j/*, boolean pZuzena*/) {
		//if(pZuzena) {
		laberintoa[i][j].setPosoia(i,j,2);
		//}
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
			if (zerDago == null || zerDago == BlokeMota.SUA || zerDago == BlokeMota.ETSAIA || zerDago == BlokeMota.POSOIA) {
				laberintoa[i][j].kenduJokalaria();
				mugimendua.accept(jok);
				setJokalaria(i+pI, j+pJ, jok);
	            if (zerDago == BlokeMota.SUA || zerDago == BlokeMota.ETSAIA || zerDago == BlokeMota.POSOIA) {
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
			try {
				Audio.getNireAudio().soinuaGelditu();
				Audio.getNireAudio();
				Audio.playSoinua("../BombermanProiektua/BombermanProiektua/src/Audioa/Canción de victoria de Brawl Stars (2020).wav");
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
        } else {
			jokalaria.jokalariaHil();
        	try {
				Audio.getNireAudio().soinuaGelditu();
				Audio.getNireAudio();
				Audio.playSoinua("../BombermanProiektua/BombermanProiektua/src/Audioa/el-primo-me-muero-101soundboards.wav");
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
		}
		BombermanKudeatzailea.getNireKudeatzaile().itxaron(irabazi);
	}
	/*public void kenduBlokeBiguna() {
		this.blokeBigunKop--;
		if (this.blokeBigunKop == 0 && this.etsaiKop == 0) {
		    partidaAmaitu(true);
		}
    }*/
	
	public void kenduEtsaia() {
        this.etsaiKop--;
        System.out.println("Etsaia kendu da. Etsai kop: " + this.etsaiKop);
        if (this.etsaiKop == 0) {
            partidaAmaitu(true);
        }
        
	}
	
	public void timerrakAmatatu(boolean pIrabazi) {
		Arrays.stream(laberintoa).flatMap(Arrays::stream).forEach(Gelaxka::amatatuTimer);
	}
	
	public boolean etsaiaMugitu(int i, int j, int iBerria, int jBerria, boolean pNorabidea,int mota) {
		boolean zuzena = true;
		Random rand = new Random();
		if (laberintoa[iBerria][jBerria].zerDago() == null) {
			zuzena = laberintoa[iBerria][jBerria].setEtsaia(pNorabidea,mota);	
			if (zuzena) {
				laberintoa[i][j].kenduAurrekoa();
				if (mota==1) posoiaJarri(i,j);
				laberintoa[iBerria][jBerria].etsaiaHasieratu();	
			}
		} else if (laberintoa[iBerria][jBerria].zerDago() == BlokeMota.SUA && rand.nextInt(100) >= 80){
			laberintoa[i][j].etsaiaHil();
			if (mota==1) posoiaJarri(i,j);
			kenduEtsaia();
		} else if (laberintoa[iBerria][jBerria].zerDago() == BlokeMota.POSOIA) {
			  if (mota==1) {
				  zuzena = laberintoa[iBerria][jBerria].setEtsaia(pNorabidea,mota);	
				  if (zuzena) {
						laberintoa[i][j].kenduAurrekoa();
						if (mota==1) posoiaJarri(i,j);
						laberintoa[iBerria][jBerria].etsaiaHasieratu();	
				  }
			  } else if (rand.nextInt(100) >= 80) {
					laberintoa[i][j].etsaiaHil();
					kenduEtsaia();
			  } else zuzena = false;
		} else if (laberintoa[iBerria][jBerria].zerDago() == BlokeMota.JOKALARIA) {
			laberintoa[iBerria][jBerria].setEtsaia(pNorabidea,mota);
			laberintoa[i][j].kenduAurrekoa();
			if (mota==1) posoiaJarri(i,j);
			partidaAmaitu(false);
		} else zuzena = false;
		
		return zuzena;
	}
 }
