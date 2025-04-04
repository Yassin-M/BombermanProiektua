package Eredua;

public class JokalariFactory {
	public static JokalariFactory nireJokalariFactory;

	private JokalariFactory() {}

	public static JokalariFactory getNireJokalariFactory() {
		if (nireJokalariFactory == null) {
			nireJokalariFactory = new JokalariFactory();
		}
		return nireJokalariFactory;
	}

	public Jokalaria sortuJokalaria(String pMota) {
		Jokalaria jokalaria = null;
		switch (pMota) {
		case "zuria":
			jokalaria = new JokZuria(10, 0, 0);
			break;
		case "beltza":
			jokalaria = new JokBeltza(1, 0, 0);
			break;
		default:
			break;
		}
		return jokalaria;
	}
}
