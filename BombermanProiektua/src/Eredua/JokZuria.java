package Eredua;

public class JokZuria extends Jokalaria{

	public JokZuria(int pBombaKont, int pI, int pJ) {
		super(pBombaKont, pI, pJ);
	}

	@Override
	public void bombaJarri() {
		if (super.bombaKont>0) {
			Laberintoa.getNireLaberintoa().bombaJarri(i, j, 1);
			super.bombaKont--;
			if(super.bombaKont==0) {
				super.timerBomba();
			}
		}	
	}
}
