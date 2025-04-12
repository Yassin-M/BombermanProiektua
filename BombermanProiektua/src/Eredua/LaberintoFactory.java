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
            laberintoa = new LaberintoClassic();
            break;
        case 2:
            laberintoa = new LaberintoSoft();
            break;
        case 3:
            laberintoa = new LaberintoEmpty();
            break;
        default:
            break;
        }
        laberintoa.laberintoaSortu(jokalariMota);
        return laberintoa;
    }
		
}