package Eredua;

public class UltraBomba extends Bomba{

	public UltraBomba(int i, int j) {
		super(i, j);
	}

	public void updateKont() {
		super.kont--;
		if(super.kont==0) {
			Laberintoa.getNireLaberintoa().suaJarri(super.i, super.j, true);
			super.timer.cancel();
			super.timer = null;
		}
	}
}
