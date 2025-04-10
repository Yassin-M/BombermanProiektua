package Eredua;

public class LaberintoFactory {
	private static LaberintoFactory nireFactory;
	
	private LaberintoFactory() {}
	
	public static LaberintoFactory getNireLaberintoFactory() {
		if (nireFactory == null) {
			nireFactory = new LaberintoFactory();
		}
		return nireFactory;
	}
	
	public Laberintoa sortuLaberintoa(int mota, String jokalariMota) {
        Laberintoa laberintoa = null;
        switch (mota) {
        case 1:
            laberintoa = new LaberintoaClassic(jokalariMota);
            break;
        case 2:
            laberintoa = new LaberintoaSoft(jokalariMota);
            break;
        case 3:
            laberintoa = new LaberintoaEmpty(jokalariMota);
            break;
        default:
            break;
        }
        return laberintoa;
    }
		
}