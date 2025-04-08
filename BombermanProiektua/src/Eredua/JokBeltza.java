package Eredua;

public class JokBeltza extends Jokalaria {
	private int UltraBombaKont;

	public JokBeltza(int pBombaKont, int pI, int pJ) {
		super(pBombaKont, pI, pJ);
		this.UltraBombaKont = 1;
	}

	@Override
	public void bombaJarri() {
		if (this.UltraBombaKont > 0) {
			Laberintoa.getNireLaberintoa().bombaJarri(i, j, 2);
			this.UltraBombaKont--;
			if (this.UltraBombaKont == 0) {
				super.timerBomba();
			}
		}
		else if (super.bombaKont>0) {
			Laberintoa.getNireLaberintoa().bombaJarri(i, j, 1);
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
