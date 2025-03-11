package Bista;

import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Eredua.Gelaxka;
import Eredua.Laberintoa;

@SuppressWarnings("deprecation")
public class GelaxkaBista extends JLabel implements Observer{
	private static final long serialVersionUID = 4860883700750938819L;
	int lerroa;
	int zutabea;
	
	public GelaxkaBista(int pLerroa, int pZutabea) {
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
		Laberintoa.getNireLaberintoa().getGelaxka(pLerroa, pZutabea).addObserver(this);
	}
	
	@Override
    public void update(Observable o, Object arg) {
		Gelaxka gelaxka = (Gelaxka) o;
		boolean[] egoera = (boolean[]) arg;
		String irudia;
        // Actualiza la gelaxka
        if (egoera[5]) {
        	if(egoera[1]) {
        		irudia = "/Bista/irudiak/whitewithbomb1.png";
			} else {
				switch(Laberintoa.getNireLaberintoa().getJokalaria().getNorabidea()) {
				case GORA:
					irudia = "/Bista/irudiak/whiteup1.png";
					break;
				case BEHERA:
					irudia = "/Bista/irudiak/whitedown1.png";
					break;
				case EZKERRERA:
					irudia = "/Bista/irudiak/whiteleft1.png";
					break;
				case ESKUINERA:
					irudia = "/Bista/irudiak/whiteright1.png";
                    break;
				default:
					irudia = "/Bista/irudiak/whitedown1.png";
					break;
				}
			}
            ImageIcon icon = new ImageIcon(Gelaxka.class.getResource(irudia));
	        setIcon(new ImageIcon(icon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH)));
        } else if (egoera[1]) {
			irudia = "/Bista/irudiak/bomb1.png";
			ImageIcon icon = new ImageIcon(Gelaxka.class.getResource(irudia));
			setIcon(new ImageIcon(icon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH)));
        } else if (egoera[0]) {
        	irudia = "/Bista/irudiak/miniBlast1.gif";
        	ImageIcon icon = new ImageIcon(Gelaxka.class.getResource(irudia));
        	Image img = icon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT);
            setIcon(new ImageIcon(img));
        }
        else if (gelaxka.zerDago()==null) {
        	setIcon(null);
        }

    } 
}