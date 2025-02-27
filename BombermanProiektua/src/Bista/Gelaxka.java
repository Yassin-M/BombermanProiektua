package Bista;

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class Gelaxka extends JLabel implements Observer{
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
