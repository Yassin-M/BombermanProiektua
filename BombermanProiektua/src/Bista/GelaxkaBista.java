package Bista;

import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

import Eredua.Gelaxka;
import Eredua.Laberintoa;

@SuppressWarnings("deprecation")
public class GelaxkaBista extends JLabel implements Observer{
	private static final long serialVersionUID = 4860883700750938819L;
	//private int lerroa;
	//private int zutabea;
	private String irudia;
	private Timer blinkTimer;
	private boolean sutanDago;
	
	public GelaxkaBista(int pLerroa, int pZutabea, String pIrudia) {
		//this.lerroa = pLerroa;
		//this.zutabea = pZutabea;
		this.irudia = pIrudia;
		this.sutanDago = false;
		this.setEnabled(true);
		addComponentListener(new ComponentAdapter() {
		    @Override
		    public void componentResized(ComponentEvent e) {
		        	setSize(getWidth(), getHeight());
					if (irudia != null) {
						ImageIcon icon = new ImageIcon(GelaxkaBista.class.getResource(irudia));
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
		Integer[] egoera = (Integer[]) arg;
		String jokmota = "white";
        // Actualiza la gelaxka
		if (egoera[0]==1) {
			if (egoera[5]==1) {
				if (blinkTimer != null && blinkTimer.isRunning()) {
	                blinkTimer.stop();
	            }
				switch (egoera[7]){
				case 0:
					irudia = "/Bista/irudiak/onFire2.png";
					break;
				case 1:
					irudia = "/Bista/irudiak/onFire4.png";
                    break;
				}
				blinkTimer = new Timer(150, e -> {
					if (sutanDago) {
						irudia = "/Bista/irudiak/onFire2.png"; 
						ImageIcon icon = new ImageIcon(Gelaxka.class.getResource(irudia));
		                setIcon(new ImageIcon(icon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH)));
					} else {
						irudia = null;
						setIcon(null);
					}
	                sutanDago = !sutanDago;
	            });
	            blinkTimer.start();
				ImageIcon icon = new ImageIcon(Gelaxka.class.getResource(irudia));
				setIcon(new ImageIcon(icon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH)));					
			} else {
	        	irudia = "/Bista/irudiak/miniBlast1.gif";
	        	ImageIcon icon = new ImageIcon(Gelaxka.class.getResource(irudia));
	        	Image img = icon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT);
	            setIcon(new ImageIcon(img));
			}
        } else if (egoera[5]==1) {
        	if(egoera[7]==1) {
        		jokmota = "black";
        	}
        	if(egoera[1]==1) {
        		irudia = "/Bista/irudiak/"+jokmota+"withbomb1.png";
        	}
        	else if (egoera[4]==1) {
    				if (blinkTimer != null && blinkTimer.isRunning()) {
    	                blinkTimer.stop();
    	            }
    				switch (egoera[7]){
    				case 0:
    					irudia = "/Bista/irudiak/onFire2.png";
    					break;
    				case 1:
    					irudia = "/Bista/irudiak/onFire4.png";
                        break;
    				}
    				blinkTimer = new Timer(150, e -> {
    					if (sutanDago) {
    						irudia = "/Bista/irudiak/onFire2.png"; 
    						ImageIcon icon = new ImageIcon(Gelaxka.class.getResource(irudia));
    		                setIcon(new ImageIcon(icon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH)));
    					} else {
    						irudia = "/Bista/irudiak/pass1.png";
    						ImageIcon icon = new ImageIcon(Gelaxka.class.getResource(irudia));
    						setIcon(new ImageIcon(icon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH)));
    					}
    	                sutanDago = !sutanDago;
    	            });
    	            blinkTimer.start();
    				ImageIcon icon = new ImageIcon(Gelaxka.class.getResource(irudia));
    				setIcon(new ImageIcon(icon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH)));
        	} else {
				switch(egoera[6]) {
				case 1:
					irudia = "/Bista/irudiak/"+jokmota+"up1.png";
					break;
				case 2:
					irudia = "/Bista/irudiak/"+jokmota+"down1.png";
					break;
				case 3:
					irudia = "/Bista/irudiak/"+jokmota+"left1.png";
					break;
				case 4:
					irudia = "/Bista/irudiak/"+jokmota+"right1.png";
                    break;
				default:
					irudia = "/Bista/irudiak/"+jokmota+"down1.png";
					break;
				}
			}
        	
            ImageIcon icon = new ImageIcon(Gelaxka.class.getResource(irudia));
	        setIcon(new ImageIcon(icon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH)));
        } else if (egoera[1]==1) {
			irudia = "/Bista/irudiak/bomb1.png";
			ImageIcon icon = new ImageIcon(Gelaxka.class.getResource(irudia));
			setIcon(new ImageIcon(icon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH)));
        } 
        else if(egoera[4]==1) {
        	irudia = "/Bista/irudiak/pass1.png";
			ImageIcon icon = new ImageIcon(Gelaxka.class.getResource(irudia));
			setIcon(new ImageIcon(icon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH)));
        }
        else if (gelaxka.zerDago()==null) {
        	setIcon(null);
        	irudia=null;
        }

    } 
}