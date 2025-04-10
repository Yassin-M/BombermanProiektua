package Eredua;

public class BombermanKudeatzaile {
	private static BombermanKudeatzaile nireBombermanKudeatzaile;
	private Laberintoa lab;
	
	private BombermanKudeatzaile() {
		lab = new Laberintoa();
	}
	
	public static BombermanKudeatzaile getNireKudeatzaile() {
		if (nireBombermanKudeatzaile == null) {
			nireBombermanKudeatzaile = new BombermanKudeatzaile();
		}
		return nireBombermanKudeatzaile;
	}
	
	public void laberintoaErreseteatu() {
		nireBombermanKudeatzaile = null;
	}
	
	public void laberintoaSortu(int mota, String jokalariMota) {
		lab = LaberintoFactory.sortuLaberintoa(mota, jokalariMota);
	}
	
	public void etsaiakHasieratu() {
		lab.etsaiakHasieratu();
	}
}