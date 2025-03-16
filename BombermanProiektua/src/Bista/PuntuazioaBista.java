package Bista;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

@SuppressWarnings({ "deprecation", "serial" })
public class PuntuazioaBista extends JLabel implements Observer {

	public PuntuazioaBista() {
		super();
		this.setText("Score: 0");
	}

	@Override
	public void update(Observable o, Object arg) {
		this.setText("Puntuazioa: " + arg);
	}
}
