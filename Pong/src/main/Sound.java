package main;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	Clip clip;
	URL soundURL [] = new URL [30] ;//30 sounds
	
	/* constructor */
	public Sound () {
		soundURL[0] = getClass().getResource("/sound/BlueBoyAdventure.wav");
		soundURL[1] = getClass().getResource("/sound/coin.wav");
		
	}
	
	/* setFile */
	public void setFile(int i) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]); 
			clip = AudioSystem.getClip() ;
			clip.open(ais);
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}
	/* playSound */
	public void playSound() {
		clip.start();
	}
	/* loop */
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	/* stopSound */
	public void stopSound() {
		clip.stop();
	}

}
