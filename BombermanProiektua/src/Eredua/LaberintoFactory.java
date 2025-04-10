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
            laberintoa.laberintoaSortuClassic(jokalariMota);
            break;
        case 2:
            laberintoa = new LaberintoSoft();
            laberintoa.laberintoaSortuSoft(jokalariMota);
            break;
        case 3:
            laberintoa = new LaberintoEmpty();
            laberintoa.laberintoaSortuEmpty(jokalariMota);
            break;
        default:
            break;
        }
        return laberintoa;
    }
		
}