package Eredua;

public class BombaSakratua extends Bomba {

	public BombaSakratua(int i, int j) {
		super(i, j, new PortaeraBombaSakratua());
	}

	@Override
	public Integer[] lortuEgoera() {
		return new Integer[]{0,3,0,0,0,0,0,0};
	}
}
