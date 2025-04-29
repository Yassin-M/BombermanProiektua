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
	private static Clip fondoa;
	private static Clip clip;
	private static FloatControl bol;
	private static String unekoPath;
	
	private Audio() throws LineUnavailableException {
		clip = AudioSystem.getClip();
	}
	
	public static Audio getNireAudio() throws LineUnavailableException {
		if(nireAudio == null) {
			nireAudio = new Audio();
		}
		return nireAudio;
	}
	
	public static void playFondokoSoinua() {
		new Thread(() -> {
       	 try {
               AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("../BombermanProiektua/BombermanProiektua/src/Audioa/Judas Priest - Painkiller (Official Lyric Video).wav"));
               fondoa = AudioSystem.getClip();
               fondoa.open(audioIn);
               fondoa.loop(Clip.LOOP_CONTINUOUSLY);
               fondoa.loop(0);
               fondoa.start();
               bol = (FloatControl) fondoa.getControl(FloatControl.Type.MASTER_GAIN);
           } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
               e.printStackTrace();
           }
       }).start();
	}
	
	 public static void playSoinua(String filepath) {
		 if (!filepath.equals("../BombermanProiektua/BombermanProiektua/src/Audioa/WhatsApp Audio 2025-04-29 at 17.44.56.wav")) {
			 soinuaGelditu();
		 }
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
	 
	 public static void soinuaGelditu() {
		if (fondoa.isRunning()) {
			fondoa.stop();
		}
	 }
	 
	 public static void clipaAmaitu() {
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