package Eredua;

public class BlastFactory {
	
	private static BlastFactory nireBF = null;
	
	private BlastFactory() {}
	
	public static BlastFactory getNireBF() {
		if (nireBF == null) {
			nireBF = new BlastFactory();
		}
		return nireBF;
	}
	
	public Blast sortuBlast(int i, int j, int pMota) {
		Blast b = null;
		switch (pMota) {
		case 1:
			b = new Sua(i, j);
			break;
		case 2:
			//b = new Posoia(i, j);
			break;
		case 3: 
			b = new ZuloBeltza(i, j);
		default:
			break;
		}
		return b;
	}
}
