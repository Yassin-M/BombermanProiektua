package Eredua;

public class MegaBomba extends Bomba {

	public MegaBomba(int i, int j) {
		super(i, j, new PortaeraMegaBomba());
	}
	
	@Override
	public Integer[] lortuEgoera() {
		return new Integer[]{0,4,0,0,0,0,0,0};
	}

}
