package Eredua;

public class BombaFactory {
	public static BombaFactory nireBombaFactory;
	
	private BombaFactory() {}

	public static BombaFactory getNireBombaFactory() {
		if (nireBombaFactory == null) {
			nireBombaFactory = new BombaFactory();
		}
		return nireBombaFactory;
	}
	
	public Bomba sortuBomba(int i, int j, int pMota) {
		Bomba bomba = null;
		switch (pMota) {
		case 1:
			bomba = new BombaNormala(i, j);
			break;
		case 2:
			bomba = new UltraBomba(i, j);
			break;
		case 3: 
			bomba = new BombaSakratua(i,j);
		case 4: 
			bomba = new MegaBomba(i,j);
		default:
			break;
		}
		return bomba;
	}
}
