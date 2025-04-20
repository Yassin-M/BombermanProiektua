package Eredua;

public class EtsaiaFactory {

	public static EtsaiaFactory nireEtsaiaFactory;
	
	private EtsaiaFactory() {}
	
	public static EtsaiaFactory getNireEtsaiaFactory() {
		if (nireEtsaiaFactory == null) {
			nireEtsaiaFactory = new EtsaiaFactory();
		}
		return nireEtsaiaFactory;
	}
	
	public Etsaia sortuEtsaia(int x, int y, boolean pNorabidea, int pMota) {
		Etsaia etsaia = null;
		switch (pMota) {
			case 0:
				etsaia = new EtsaiArrunta(x,y,pNorabidea);
				break;
			case 1:
				etsaia = new EtsaiPosoia(x,y, pNorabidea);
				break;
		}
		return etsaia;
	}
	
}
