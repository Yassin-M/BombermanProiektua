package Eredua;


public class EtsaiArrunta extends Etsaia {

	protected EtsaiArrunta(int pI, int pJ, boolean pNorabidea) {
		super(pI, pJ, pNorabidea);
	
	}
	
	@Override
	protected int getMota() {
		return 0;
	}
	public Integer[] lortuEgoera() {
		return new Integer[]{0,0,0,0,1,0,0,0,0};
	}
	
}	