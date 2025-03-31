package Eredua;

public class JokZuria extends Jokalaria{

	public JokZuria() {
		super(10,0,0);
	}

	@Override
	public void bombaJarri() {
		if (super.bombaKont>0) {
			Laberintoa.getNireLaberintoa().bombaJarri(i, j, false);
			super.bombaKont--;
			if(super.bombaKont==0) {
				super.timerBomba();
			}
		}	
	}
}
