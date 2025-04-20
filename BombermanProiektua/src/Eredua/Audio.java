package Eredua;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Audio {
	private static Audio nireAudio = null;
	private static Clip clip;
	private static FloatControl bol;
	
	private Audio() throws LineUnavailableException {
		clip = AudioSystem.getClip();
	}
	
	public static Audio getNireAudio() throws LineUnavailableException {
		if(nireAudio == null) {
			nireAudio = new Audio();
		}
		return nireAudio;
	}
	
	 public static void playSoinua(String filepath) {
        new Thread(() -> {
            try {
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(filepath));
                clip = AudioSystem.getClip();
                clip.open(audioIn);
                clip.start();
                bol = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        }).start();
    }
	 
	 public void soinuaGelditu() {
		if (clip.isRunning()) {
			clip.stop();
		}
	 }
	 public void setBolumena(float bolumena) {
	        if (bol != null) {
	            float min = bol.getMinimum();
	            float max = bol.getMaximum();
	            float dB = (max - min) * bolumena + min;
	            bol.setValue(dB);
	        }
	    }
}
