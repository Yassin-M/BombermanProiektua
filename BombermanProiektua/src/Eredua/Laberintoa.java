package Eredua;

public class Laberintoa {
	private static Laberintoa nireLaberintoa;
	private Gelaxka[][] laberintoa;
	
	private Laberintoa() {
		this.laberintoa = new Gelaxka[11][17];
	}
	
	public static Laberintoa getNireLaberintoa() {
		if (nireLaberintoa == null) {
			nireLaberintoa = new Laberintoa();
		}
		return nireLaberintoa;
	}
}
