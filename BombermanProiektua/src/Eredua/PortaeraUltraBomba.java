package Eredua;

public class PortaeraUltraBomba implements BombaPortaera {

	@Override
	public void eztanda(int i, int j) {
		Laberintoa l = BombermanKudeatzailea.getNireKudeatzaile().getLaberintoa();
		l.suaJarri(i, j);
		if (l.zerDago(i+1,j) != BlokeMota.BLOKEGOGORRA && l.zerDago(i-1,j) != BlokeMota.BLOKEGOGORRA) {
			for (int y = 0; y < 11; y++) {
				if (y != i) {
					l.suaJarri(y, j);
				}
			}
		}
		if (l.zerDago(i,j+1) != BlokeMota.BLOKEGOGORRA && l.zerDago(i,j-1) != BlokeMota.BLOKEGOGORRA) {
			for (int x = 0; x < 17; x++) {
				if (x != j) {
					l.suaJarri(i, x);
				}
			}
		}
	}
}