package Eredua;

public abstract class Jokalaria {
	private int bombaKont;
	private boolean bizirik;
	private int Xposizioa;
	private int Yposizioa;
	
	public Jokalaria (int pBombaKont, boolean pBizirik, int pXposizioa, int pYposizioa) {
		bombaKont = pBombaKont;
		bizirik = pBizirik;
		Xposizioa = pXposizioa;
		Yposizioa = pYposizioa;
	}

	public void mugituEskuinera() {
		Xposizioa++;
	}

	public void mugituEzkerretara() {
		Xposizioa--;
	}

	public void mugituGora() {
		Yposizioa--;
	}

	public void mugituBehera() {
		Yposizioa++;
	}
}
