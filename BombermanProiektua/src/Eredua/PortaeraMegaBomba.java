package Eredua;

public class PortaeraMegaBomba implements BombaPortaera {

	@Override
	public void eztanda(int i, int j) {
		BombermanKudeatzailea.getNireKudeatzaile().getLaberintoa().suaJarri(i, j, 4);
		if (i > 0) {
			BombermanKudeatzailea.getNireKudeatzaile().getLaberintoa().suaJarri(i-1, j, 4);
		}
		if (i < 10) {
			BombermanKudeatzailea.getNireKudeatzaile().getLaberintoa().suaJarri(i+1, j, 4);
		}
		if (j > 0) {
			BombermanKudeatzailea.getNireKudeatzaile().getLaberintoa().suaJarri(i, j-1, 4);
		}
		if (j < 16) {
			BombermanKudeatzailea.getNireKudeatzaile().getLaberintoa().suaJarri(i, j+1, 4);
		}

	}

}
