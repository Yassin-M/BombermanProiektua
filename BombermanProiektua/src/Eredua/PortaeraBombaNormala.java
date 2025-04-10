package Eredua;

public class PortaeraBombaNormala implements BombaPortaera{

	@Override
	public void eztanda(int i, int j) {
		Laberintoa.getNireLaberintoa().suaJarri(i, j);
		if (i > 0) {
			Laberintoa.getNireLaberintoa().suaJarri(i-1, j);
		}
		if (i < 10) {
			Laberintoa.getNireLaberintoa().suaJarri(i+1, j);
		}
		if (j > 0) {
			Laberintoa.getNireLaberintoa().suaJarri(i, j-1);
		}
		if (j < 16) {
			Laberintoa.getNireLaberintoa().suaJarri(i, j+1);
		}
	}
}
