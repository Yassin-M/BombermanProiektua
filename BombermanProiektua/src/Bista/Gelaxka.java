package Bista;

import java.util.*;
import javax.swing.*;
@SuppressWarnings("deprecation")

public class Gelaxka extends JLabel implements Observer{
	private static final long serialVersionUID = -6493016058572553189L;
	int lerroa;
	int zutabea;
	
	public Gelaxka(int pLerroa, int pZutabea) {
		this.lerroa = pLerroa;
		this.zutabea = pZutabea;
		this.setEnabled(true);
	}
	
	
	@Override
	public void update(Observable arg0, Object arg1) {
		
	}
}
