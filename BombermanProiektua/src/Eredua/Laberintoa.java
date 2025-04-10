package Eredua;

import java.util.Observable;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

@SuppressWarnings("deprecation")

public class Laberintoa extends Observable{
	private static Laberintoa nireLaberintoa;
	private Gelaxka[][] laberintoa;
	private Jokalaria jokalaria;
	private int blokeBigunKop = 0;
	private int etsaiKop = 0;
	private int score = 0;
    private int kont = 2;
	
	private Laberintoa() {
		this.laberintoa = null;
		this.jokalaria = null;
		this.blokeBigunKop = 0;
		this.etsaiKop = 0;
		this.score = 0;
		this.kont = 2;
	}
	
	public static Laberintoa getNireLaberintoa() {
		if (nireLaberintoa == null) {
			nireLaberintoa = new Laberintoa();
		}
		return nireLaberintoa;
	}
	
	public static synchronized void resetInstance() {
        nireLaberintoa = null;
    }
	
	public void jokoaAbiarazi() {
		
	}
	
	public void laberintoaSortuClassic(String pJok) {
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
						laberintoa[i][j].setEtsaia(true);
					}
				}
			}
		}
		etsaiakHasieratu();
		setChanged();
		notifyObservers(1);
	}
	
	private void etsaiakHasieratu() {
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 17; j++) {
				if (laberintoa[i][j].zerDago() == BlokeMota.ETSAIA) {
					laberintoa[i][j].etsaiaHasieratu();
				}
			}
		}
	}
	
	public void laberintoaSortuSoft(String pJok) {
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
					if (r.nextInt(100) >= 40) {
						laberintoa[i][j].setBlokeBiguna();
						blokeBigunKop++;
					} else if (r.nextInt(100) >= 90 && etsaiKop < 8) {
						laberintoa[i][j].setEtsaia(true);
						etsaiKop++;
					}
				}
			}
		}
		etsaiakHasieratu();
		setChanged();
		notifyObservers(2);
	}
	
	public void laberintoaSortuEmpty(String pJok) {
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
						laberintoa[i][j].setEtsaia(true);
						etsaiKop++;
					}
				}
			}
		}
		etsaiakHasieratu();
		setChanged();
		notifyObservers(3);
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
	

	public boolean suaJarri(int i, int j){
		boolean zuzena = false;
		if (laberintoa[i][j].zerDago() != BlokeMota.BLOKEGOGORRA) {
			zuzena = true;
			laberintoa[i][j].setSua(i,j);
		}
		return zuzena;
	}
	
	public void suaKendu(int i, int j) {
		if(i>=0 && i<=10 && j>=0 && j<=16) {
				laberintoa[i][j].kenduAurrekoa();
		}
	}
	
	public void jokalariaMugituEskuinera() {
		Jokalaria jok = getJokalaria();
		if(jok.bizirik && jok.getJposizioa() < 16) {
			if (laberintoa[jok.getIposizioa()][jok.getJposizioa() + 1].zerDago() == null) {
				laberintoa[jok.getIposizioa()][jok.getJposizioa()].kenduJokalaria();
				jok.mugituEskuinera();
	            setJokalaria(jok.getIposizioa(), jok.getJposizioa(), jok);
			} else if (laberintoa[jok.getIposizioa()][jok.getJposizioa() + 1].zerDago() == BlokeMota.SUA || laberintoa[jok.getIposizioa()][jok.getJposizioa() + 1].zerDago() == BlokeMota.ETSAIA) {
				laberintoa[jok.getIposizioa()][jok.getJposizioa()].kenduJokalaria();
				jok.mugituEskuinera();
	            setJokalaria(jok.getIposizioa(), jok.getJposizioa(), jok);
				partidaAmaitu(false);
			}
		}
	}
	
	public void jokalariaMugituEzkerretara() {
		Jokalaria jok = getJokalaria();
		if(jok.bizirik && jok.getJposizioa() > 0) {
			if (laberintoa[jok.getIposizioa()][jok.getJposizioa()-1].zerDago() == null) {
				laberintoa[jok.getIposizioa()][jok.getJposizioa()].kenduJokalaria();
				jok.mugituEzkerretara();
	            setJokalaria(jok.getIposizioa(), jok.getJposizioa(), jok);
			} else if (laberintoa[jok.getIposizioa()][jok.getJposizioa() - 1].zerDago() == BlokeMota.SUA || laberintoa[jok.getIposizioa()][jok.getJposizioa() - 1].zerDago() == BlokeMota.ETSAIA) {
				laberintoa[jok.getIposizioa()][jok.getJposizioa()].kenduJokalaria();
				jok.mugituEzkerretara();
	            setJokalaria(jok.getIposizioa(), jok.getJposizioa(), jok);
				partidaAmaitu(false);
			}
		}
	}
	
	public void jokalariaMugituGora() {
		Jokalaria jok = getJokalaria();
		if(jok.bizirik && jok.getIposizioa() > 0) {
			if (laberintoa[jok.getIposizioa()-1][jok.getJposizioa()].zerDago() == null) {
				laberintoa[jok.getIposizioa()][jok.getJposizioa()].kenduJokalaria();
				jok.mugituGora();
	            setJokalaria(jok.getIposizioa(), jok.getJposizioa(), jok);
			} else if (laberintoa[jok.getIposizioa()-1][jok.getJposizioa()].zerDago() == BlokeMota.SUA 	|| laberintoa[jok.getIposizioa()-1][jok.getJposizioa()].zerDago() == BlokeMota.ETSAIA) {
				laberintoa[jok.getIposizioa()][jok.getJposizioa()].kenduJokalaria();
				jok.mugituGora();
	            setJokalaria(jok.getIposizioa(), jok.getJposizioa(), jok);
				partidaAmaitu(false);
			}
		}
	}
	
	public void jokalariaMugituBehera() {
        Jokalaria jok = getJokalaria();
        if(jok.bizirik && jok.getIposizioa() < 10) {
	        if (laberintoa[jok.getIposizioa()+1][jok.getJposizioa()].zerDago() == null) {
	            laberintoa[jok.getIposizioa()][jok.getJposizioa()].kenduJokalaria();
	            jok.mugituBehera();
	            setJokalaria(jok.getIposizioa(), jok.getJposizioa(), jok);
	        } else if (laberintoa[jok.getIposizioa()+1][jok.getJposizioa() + 1].zerDago() == BlokeMota.SUA || laberintoa[jok.getIposizioa()+1][jok.getJposizioa()].zerDago() == BlokeMota.ETSAIA) {
	        	laberintoa[jok.getIposizioa()][jok.getJposizioa()].kenduJokalaria();
	            jok.mugituBehera();
	            setJokalaria(jok.getIposizioa(), jok.getJposizioa(), jok);
	        	partidaAmaitu(false);
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
		timerrakAmatatu();
		if (irabazi) {
			itxaron(irabazi);
        } else {
			jokalaria.jokalariaHil();
			itxaron(irabazi);
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
	private void itxaron(boolean pIrabazi) {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                kont--;
                if (kont == 0) {
                    timer.cancel();
                    setChanged();
        			notifyObservers(pIrabazi);
                }
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }
	
	private void timerrakAmatatu() {
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 17; j++) {
				if (laberintoa[i][j].zerDago() == BlokeMota.SUA || laberintoa[i][j].zerDago() == BlokeMota.BOMBA || laberintoa[i][j].zerDago() == BlokeMota.ETSAIA) {
					laberintoa[i][j].amatatuTimer();
				}
			}
		}
	}

	public boolean etsaiaMugituGora(int i, int j, boolean pNorabidea) {
		boolean zuzena = true;
		Random rand = new Random();
		if(i>0) {
			if (laberintoa[i-1][j].zerDago() == null) {
				zuzena = laberintoa[i-1][j].setEtsaia(pNorabidea);
				if (zuzena) {
					laberintoa[i][j].kenduAurrekoa();
					laberintoa[i-1][j].etsaiaHasieratu();
				}
			} else if (laberintoa[i-1][j].zerDago() == BlokeMota.SUA && rand.nextInt(100) >= 80) {
				laberintoa[i][j].etsaiaHil();
				//kenduEtsaia();
			} else if (laberintoa[i-1][j].zerDago() == BlokeMota.JOKALARIA) {
				laberintoa[i-1][j].setEtsaia(pNorabidea);
				laberintoa[i][j].kenduAurrekoa();
				partidaAmaitu(false);
			} else zuzena = false;
		} else zuzena = false;
		return zuzena;
	}

	public boolean etsaiaMugituBehera(int i, int j, boolean pNorabidea) {
		boolean zuzena = true;
		Random rand = new Random();
		if(i<10) {
			if (laberintoa[i+1][j].zerDago() == null) {
				zuzena = laberintoa[i+1][j].setEtsaia(pNorabidea);
				if (zuzena) {
					laberintoa[i][j].kenduAurrekoa();
					laberintoa[i+1][j].etsaiaHasieratu();
				}
			} else if (laberintoa[i+1][j].zerDago() == BlokeMota.SUA && rand.nextInt(100) >= 80) {
				laberintoa[i][j].etsaiaHil();
				//kenduEtsaia();
			} else if (laberintoa[i+1][j].zerDago() == BlokeMota.JOKALARIA) {
				laberintoa[i+1][j].setEtsaia(pNorabidea);
				laberintoa[i][j].kenduAurrekoa();
				partidaAmaitu(false);
			} else zuzena = false;
		} else zuzena = false;
		return zuzena;
	}

	public boolean etsaiaMugituEzerretara(int i, int j, boolean pNorabidea) {
		boolean zuzena = true;
		Random rand = new Random();
		if(j>0) {
			if (laberintoa[i][j-1].zerDago() == null) {
				zuzena = laberintoa[i][j-1].setEtsaia(pNorabidea);
				if (zuzena) {
					laberintoa[i][j].kenduAurrekoa();
					laberintoa[i][j-1].etsaiaHasieratu();	
				}
			} else if (laberintoa[i][j-1].zerDago() == BlokeMota.SUA && rand.nextInt(100) >= 80) {
				laberintoa[i][j].etsaiaHil();
				//kenduEtsaia();
			} else if (laberintoa[i][j-1].zerDago() == BlokeMota.JOKALARIA) {
				laberintoa[i][j-1].setEtsaia(pNorabidea);
				laberintoa[i][j].kenduAurrekoa();
				partidaAmaitu(false);
			} else zuzena = false;
		} else zuzena = false;
		return zuzena;
	}

	public boolean etsaiaMugituEskuinera(int i, int j, boolean pNorabidea) {
		boolean zuzena = true;
		Random rand = new Random();
		if(j<16) {
			if (laberintoa[i][j+1].zerDago() == null) {
				zuzena = laberintoa[i][j+1].setEtsaia(pNorabidea);	
				if (zuzena) {
					laberintoa[i][j].kenduAurrekoa();
					laberintoa[i][j+1].etsaiaHasieratu();	
				}
			} else if (laberintoa[i][j+1].zerDago() == BlokeMota.SUA && rand.nextInt(100) >= 80) {
				laberintoa[i][j].etsaiaHil();
				//kenduEtsaia();
			} else if (laberintoa[i][j+1].zerDago() == BlokeMota.JOKALARIA) {
				laberintoa[i][j+1].setEtsaia(pNorabidea);
				laberintoa[i][j].kenduAurrekoa();
				partidaAmaitu(false);
			} else zuzena = false;
		} else zuzena = false;
		return zuzena;
	}
 }
