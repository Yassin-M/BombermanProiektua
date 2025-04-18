package Eredua;

public class PortaeraBombaSakratua implements BombaPortaera {

	@Override
	public void eztanda(int i, int j) {
		BombermanKudeatzailea.getNireKudeatzaile().getLaberintoa().suaJarri(i, j);
		if (j > 0) {
			BombermanKudeatzailea.getNireKudeatzaile().getLaberintoa().suaJarri(i, j-1);
		}
		if (j < 16) {
			BombermanKudeatzailea.getNireKudeatzaile().getLaberintoa().suaJarri(i, j+1);
		} 
		
		if (i > 0) {
			BombermanKudeatzailea.getNireKudeatzaile().getLaberintoa().suaJarri(i-1, j);
			if (j > 0) {
				BombermanKudeatzailea.getNireKudeatzaile().getLaberintoa().suaJarri(i - 1, j - 1);
			}
			if (j < 16) {
				BombermanKudeatzailea.getNireKudeatzaile().getLaberintoa().suaJarri(i - 1, j + 1);
			}
		}
		
		if (i < 10) {
			BombermanKudeatzailea.getNireKudeatzaile().getLaberintoa().suaJarri(i+1, j);
			if (j > 0) {
				BombermanKudeatzailea.getNireKudeatzaile().getLaberintoa().suaJarri(i + 1, j - 1);
			}
			if (j < 16) {
				BombermanKudeatzailea.getNireKudeatzaile().getLaberintoa().suaJarri(i + 1, j + 1);
			}
		}

	}

}
