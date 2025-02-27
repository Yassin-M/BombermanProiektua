package Eredua;

public class JokZuria extends Jokalaria{

	public JokZuria(int pBombaKont, boolean pBizirik, int pXposizioa, int pYposizioa) {
		super(pBombaKont, pBizirik, pXposizioa, pYposizioa);
	}

	@Override
	public void bombaJarri() {
		Laberintoa.getNireLaberintoa().bombaJarri(Xposizioa, Yposizioa, false);
	}
}
