package Eredua;

public class JokBeltza extends Jokalaria {
	private int UltraBombaKont;

	public JokBeltza() {
		super(1, 0, 0);
		this.UltraBombaKont = 1;
	}

	@Override
	public void bombaJarri() {
		if (this.UltraBombaKont > 0) {
			Laberintoa.getNireLaberintoa().bombaJarri(i, j, true);
			this.UltraBombaKont--;
			if (this.UltraBombaKont == 0) {
				super.timerBomba();
			}
		}
		else if (super.bombaKont>0) {
			Laberintoa.getNireLaberintoa().bombaJarri(i, j, false);
			super.bombaKont--;
			if(super.bombaKont==0) {
				super.timerBomba();
			}
		}
	}
}
