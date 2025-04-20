package Eredua;

public class Sua extends Blast{

	public Sua(int i, int j) {
		super(i, j);
	}

	public int getPeriodo() {
		return 2;
	}
	@Override
	public Integer[] lortuEgoera() {
		return new Integer[]{1,0,0,0,0,0,0,0};
	}
}
