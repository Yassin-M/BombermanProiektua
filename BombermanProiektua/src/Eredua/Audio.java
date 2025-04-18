package Eredua;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Audio {
	private static Audio nireAudio = null;
	private static Clip clip;
	
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
    
}
