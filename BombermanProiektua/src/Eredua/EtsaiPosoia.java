
package Eredua;

public class EtsaiPosoia extends Etsaia {

	protected EtsaiPosoia(int pI, int pJ, boolean pNorabidea) {
		super(pI, pJ, pNorabidea);
		
	}
	
	@Override
	public int getMota() {
		return 1;
	}
	public Integer[] lortuEgoera() {
		return new Integer[]{0,0,0,0,1,0,0,0,1};
	}
}