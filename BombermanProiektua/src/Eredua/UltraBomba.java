package Eredua;

public class UltraBomba extends Bomba{

	public UltraBomba(int i, int j) {
		super(i, j);
	}

	@Override
	public void updateKont() {
		super.kont--;
		if(super.kont==0) {
			BombermanKudeatzailea.getNireKudeatzaile().getLaberintoa().suaJarri(super.i, super.j, true);
			if(super.timer != null) {
				super.timer.cancel();
			}			
			super.timer = null;
		}
	}
}
