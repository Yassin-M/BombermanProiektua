package Eredua;

public class Audio {
	private static Audio nireAudio = null;
	
	private Audio() {
		
	}
	
	public static Audio getNireAudio() {
		if(nireAudio == null) {
			nireAudio = new Audio();
		}
		return nireAudio;
	}
	
	/*
	 public static void playSound(String filepath) {
        new Thread(() -> {
            try {
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(filepath));
                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);
                clip.start();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        }).start();
    }
    
	*/
}
