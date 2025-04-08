package Eredua;

public class JokBeltza extends Jokalaria {

	public JokBeltza(int pBombaKont, int pI, int pJ) {
		super(pBombaKont, pI, pJ);
	}

	@Override
	public void bombaJarri() {
		if (super.bombaKont>0) {
			Laberintoa.getNireLaberintoa().bombaJarri(i, j, 2);
			super.bombaKont--;
			if(super.bombaKont==0) {
				super.timerBomba();
			}
		}
	}
	
	public Integer[] lortuEgoera() {
		Integer [] emaitza = new Integer[]{0,0,0,0,0,1,0,1};
		switch (norabidea) {
		case GORA:
			emaitza[6] = 1;
			break;
		case BEHERA:
			emaitza[6] = 2;
			break;
		case EZKERRERA:
			emaitza[6] = 3;
			break;
		case ESKUINERA:
			emaitza[6] = 4;
			break;
		default:
			break;
		}
		return emaitza;
	}
}
