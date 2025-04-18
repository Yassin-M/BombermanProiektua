package Eredua;

public class UltraBomba extends Bomba{

	public UltraBomba(int i, int j) {
		super(i, j, new PortaeraUltraBomba());
	}

	@Override
	public Integer[] lortuEgoera() {
		return new Integer[]{0,2,0,0,0,0,0,0};
	}
}
