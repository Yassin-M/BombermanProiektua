package Bista;

import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

import Eredua.BombermanKudeatzailea;
import Eredua.Gelaxka;

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
		BombermanKudeatzailea.getNireKudeatzaile().getLaberintoa().getGelaxka(pLerroa, pZutabea).addObserver(this);
	}
	
	@Override
    public void update(Observable o, Object arg) {
		//Gelaxka gelaxka = (Gelaxka) o;
		Integer[] egoera = (Integer[]) arg;
		String jokmota = "white";
		if(egoera[7]==1) {
    		jokmota = "black";
    	} else if (egoera[7]==2) {
    		jokmota = "blue";
    	}
        // Actualiza la gelaxka
		if (egoera[0]!=0 && egoera[5]==0) {
        	irudia = "/Bista/irudiak/miniBlast1.gif";
        	if (egoera[0] == 3) irudia = "/Bista/irudiak/blueblast.gif";
        	ImageIcon icon = new ImageIcon(Gelaxka.class.getResource(irudia));
        	Image img = icon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT);
            setIcon(new ImageIcon(img));
        } else if (egoera[5]!=0) {
        	if (egoera[0]!=0) {
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
                default: 
                	irudia = "/Bista/irudiak/onFire2.png";
                	break;
				}
				blinkTimer = new Timer(150, e -> {
					if (sutanDago) {
						switch (egoera[7]){
						case 0:
							irudia = "/Bista/irudiak/onFire2.png";
							break;
						case 1:
							irudia = "/Bista/irudiak/onFire4.png";
		                    break;
		                default: 
							irudia = "/Bista/irudiak/onFire2.png";
							break;
						}						
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
			}
        	else if (egoera[4]==1) {
        		irudia = "/Bista/irudiak/rip.png";
        		ImageIcon icon = new ImageIcon(Gelaxka.class.getResource(irudia));
                setIcon(new ImageIcon(icon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH)));
        		
        		//SI QUEREMOS QUE PARPADEE
        		/*if (blinkTimer != null && blinkTimer.isRunning()) {
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
						switch (egoera[7]){
						case 0:
							irudia = "/Bista/irudiak/onFire2.png";
							break;
						case 1:
							irudia = "/Bista/irudiak/onFire4.png";
		                    break;
						}
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
				*/			
				}
        	else if(egoera[1]!=0) {
        		irudia = "/Bista/irudiak/"+jokmota+"withbomb1.png";
        	}
        	else {
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
        } else if (egoera[1]!=0) {
        	irudia = "/Bista/irudiak/bomb1.png";
			if (egoera[1]==2) irudia = "/Bista/irudiak/bluebomb1.png";
			ImageIcon icon = new ImageIcon(Gelaxka.class.getResource(irudia));
			setIcon(new ImageIcon(icon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH)));
        } 
        else if(egoera[4]==1) {
	        irudia = "/Bista/irudiak/pass1.png";
			ImageIcon icon = new ImageIcon(Gelaxka.class.getResource(irudia));
			setIcon(new ImageIcon(icon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH)));
        }
        else {
        	setIcon(null);
        	irudia=null;
        }

    } 
}