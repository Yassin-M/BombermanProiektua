package Eredua;

public class BombaNormala extends Bomba {
	public BombaNormala(int i, int j) {
		super(i, j, new PortaeraBombaNormala());
	}

	@Override
	public Integer[] lortuEgoera() {
		return new Integer[]{0,1,0,0,0,0,0,0};
	}
}
