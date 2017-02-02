package com.reknology.audioProject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundDAO {

	public void playSound(String path, int loops, int delay) throws LineUnavailableException, IOException, EncodingNotSupportedException, UnsupportedAudioFileException{
		Encoder encoder = new Encoder();
		File file = new File(path);
		final Clip clip = encoder.Encode(file);
		Timer timer = new Timer();
		TimerTask timerTask = new TimerTask(){
			 @Override
	            public void run() { 
				 		clip.start();
			 }
		};
		timer.scheduleAtFixedRate(new TimerTask(){
			 @Override
	            public void run() { 
				 		clip.start();
			 }
		}, 2000, 1000);
		
		
	}
	
	public void playSound2(HashMap<Integer, TimerTask> map, int loops, int delay) throws LineUnavailableException, IOException, EncodingNotSupportedException, UnsupportedAudioFileException{
		System.out.println(map.get(1));
		System.out.println(map.get(1));
		int count = 1;
		while(count <= loops){
			
			count++;
		}
		
	}
	
	
}
