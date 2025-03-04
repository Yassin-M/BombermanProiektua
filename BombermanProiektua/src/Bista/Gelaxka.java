package Bista;

import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
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
		addComponentListener(new ComponentAdapter() {
		    @Override
		    public void componentResized(ComponentEvent e) {
		        	setSize(getWidth(), getHeight());
		        	ImageIcon icon = (ImageIcon) getIcon();
					if (icon != null) {
						Image img = icon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
						setIcon(new ImageIcon(img));
					}
		    }
		});
	}
	
	
	@Override
	public void update(Observable arg0, Object arg1) {
		
	}
}
