package Eredua;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.LineUnavailableException;

@SuppressWarnings("deprecation")
public class BombermanKudeatzailea extends Observable{
	private static BombermanKudeatzailea nireBombermanKudeatzaile;
	private Laberintoa lab;
	private int kont = 2;
	
	private BombermanKudeatzailea() {
		try {
			Audio.getNireAudio();
			Audio.clipaAmaitu();
			Audio.playFondokoSoinua();
			Audio.getNireAudio().setBolumena(1.0f);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		};
		lab = null;
	}
	
	public static BombermanKudeatzailea getNireKudeatzaile() {
		if (nireBombermanKudeatzaile == null) {
			nireBombermanKudeatzaile = new BombermanKudeatzailea();
		}
		return nireBombermanKudeatzaile;
	}
	
	public Laberintoa getLaberintoa() {
		return lab;
	}
	
	public void laberintoaErreseteatu() {
		nireBombermanKudeatzaile = null;
	}
	
	public void laberintoaSortu(int mota, String jokalariMota) {
		try {
			Audio.getNireAudio().setBolumena(0.75f);
		} catch (LineUnavailableException e1) {
			e1.printStackTrace();
		}
		lab = LaberintoFactory.getNireLaberintoFactory().sortuLaberintoa(mota, jokalariMota);
		setChanged();
		if (lab instanceof LaberintoClassic) {
			notifyObservers(1);
		} else if (lab instanceof LaberintoSoft) {
			notifyObservers(2);
		} else if (lab instanceof LaberintoEmpty) {
			notifyObservers(3);
		}
	}
	
	public void itxaron(boolean pIrabazi) {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                kont--;
                if (kont == 0) {
                    timer.cancel();
                    lab.timerrakAmatatu(pIrabazi);
                    setChanged();
        			notifyObservers(pIrabazi);
                }
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
	}
}
