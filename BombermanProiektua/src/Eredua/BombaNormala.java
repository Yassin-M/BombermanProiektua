package Eredua;

public class BombaNormala extends Bomba {
	
	public BombaNormala(int i, int j) {
		super(i, j);
	}

	@Override
	public void updateKont() {
		super.kont--;
		if(super.kont==0) {
			Laberintoa.getNireLaberintoa().suaJarri(super.i, super.j, false);
			super.timer.cancel();
			super.timer = null;
		}
	}

}
