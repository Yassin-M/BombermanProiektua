package Eredua;

public class Posoia extends Blast {
	
	public Posoia(int i, int j) {
		super(i, j);
	}
	public int getPeriodo() {
		return 3;
	}
	@Override
	public Integer[] lortuEgoera() {
		return new Integer[]{2,0,0,0,0,0,0,0,0};
	}
}
