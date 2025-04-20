package Eredua;

public class ZuloBeltza extends Blast {

	public ZuloBeltza(int i, int j) {
		super(i, j);
	}
	public int getPeriodo() {
		return 2;
	}
	@Override
	public Integer[] lortuEgoera() {
		return new Integer[]{3,0,0,0,0,0,0,0};
	}

}
