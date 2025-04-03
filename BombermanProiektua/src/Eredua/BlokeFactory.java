package Eredua;

public class BlokeFactory {
	public static BlokeFactory nireBlokeFactory;
	
	private BlokeFactory() {}

	public static BlokeFactory getNireBlokeFactory() {
		if (nireBlokeFactory == null) {
			nireBlokeFactory = new BlokeFactory();
		}
		return nireBlokeFactory;
	}
	
	public Bloke sortuBloke(int pMota) {
		Bloke bloke = null;
		switch (pMota) {
			case 1:
				bloke = new BlokeGogorra();
				break;
			case 2:
				bloke = new BlokeBiguna();
				break;
			default:
				break;
		}
		return bloke;
	}
}
