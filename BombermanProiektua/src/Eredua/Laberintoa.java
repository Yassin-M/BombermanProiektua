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
	
	private Laberintoa() {}
	
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
					if(pJok.equals("zuria")) {
						this.jokalaria = new JokZuria();
					}else {
						this.jokalaria = new JokBeltza();
					}
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
						laberintoa[i][j].setEtsaia();
					}
				}
			}
		}
		setChanged();
		notifyObservers(1);
	}
	
	public void laberintoaSortuSoft(String pJok) {
		this.laberintoa = new Gelaxka[11][17];
		for (int i=0; i<11; i++) {
			for (int j=0; j<17; j++) {
				laberintoa[i][j] = new Gelaxka(i,j);
				if (i==0 && j==0) {
					if(pJok.equals("zuria")) {
						this.jokalaria = new JokZuria();
					}else {
						this.jokalaria = new JokBeltza();
					}
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
						laberintoa[i][j].setEtsaia();
						etsaiKop++;
					}
				}
			}
		}
		setChanged();
		notifyObservers(2);
	}
	
	public void laberintoaSortuEmpty(String pJok) {
		this.laberintoa = new Gelaxka[11][17];
		for (int i=0; i<11; i++) {
			for (int j=0; j<17; j++) {
				laberintoa[i][j] = new Gelaxka(i,j);
				if (i==0 && j==0) {
					if(pJok.equals("zuria")) {
						this.jokalaria = new JokZuria();
					}else {
						this.jokalaria = new JokBeltza();
					}
					laberintoa[i][j].setJokalaria(this.jokalaria);
				}
				else if ((i==0 && j==1)||(i==1 && j==0)) {
					continue;
				} else {
					Random r = new Random();
					if (r.nextInt(100) >= 90 && etsaiKop < 10) {
						laberintoa[i][j].setEtsaia();
						etsaiKop++;
					}
				}
			}
		}
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
	
	public void bombaJarri(int i, int j, boolean pUltrabomba) {
		laberintoa[i][j].setBomba(i,j,pUltrabomba);
	}
	

	public void suaJarri(int i, int j, boolean pUltrabomba) {
		laberintoa[i][j].setSua(i,j);
		if (!pUltrabomba) {
			if (i > 0) {
				laberintoa[i-1][j].setSua(i-1, j);
			}
			if (i < 10) {
				laberintoa[i+1][j].setSua(i+1, j);
			}
			if (j > 0) {
				laberintoa[i][j-1].setSua(i, j-1);
			}
			if (j < 16) {
				laberintoa[i][j+1].setSua(i, j+1);
			}
		}
		else {
			if (this.zerDago(i+1,j) != BlokeMota.BLOKEGOGORRA && this.zerDago(i-1,j) != BlokeMota.BLOKEGOGORRA) {
				for (int y = 0; y < 11; y++) {
					laberintoa[y][j].setSua(y, j);
				}
			}
			if (this.zerDago(i,j+1) != BlokeMota.BLOKEGOGORRA && this.zerDago(i,j-1) != BlokeMota.BLOKEGOGORRA) {
				for (int x = 0; x < 17; x++) {
					laberintoa[i][x].setSua(i, x);
				}
			}
		}
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
				
				partidaAmaitu(jok,false);
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
				partidaAmaitu(jok, false);
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
				partidaAmaitu(jok, false);
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
				partidaAmaitu(jok, false);
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
	
	public void partidaAmaitu(Jokalaria j, boolean irabazi) {
		if (irabazi) {
			itxaron(irabazi);
        } else {
			laberintoa[j.getIposizioa()][j.getJposizioa()].kenduJokalaria();
			j.jokalariaHil();
			itxaron(irabazi);
		}
	}
	public void kenduBlokeBiguna() {
		this.blokeBigunKop--;
		if (this.blokeBigunKop == 0 && this.etsaiKop == 0) {
		    partidaAmaitu(getJokalaria(),true);
		}
    }
	
	public void kenduEtsaia() {
        this.etsaiKop--;
        if (this.etsaiKop == 0) {
            partidaAmaitu(getJokalaria(), true);
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
 }
