package com.reknology.audioProject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.TimerTask;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class PlayThread extends Thread{
	
	HashMap<Integer, Boolean> map;
	int loops;
	int delay;
	Clip clip;
	File file;
	
	PlayThread(File file, HashMap<Integer, Boolean> map, int loops, int delay){
		this.map = map;
		this.loops = loops;
		this.delay=delay;
		this.file = file;
	}
	
	public void run(){ 
		Encoder encoder = new Encoder();
		int count = 1;
		while(count <= loops){
			if(map.get(count)){
				Clip clip = null;
				try {
					clip = encoder.Encode(file);
				} catch (LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (EncodingNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnsupportedAudioFileException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				clip.start();
				try {
					currentThread().sleep(delay);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else{
				try {
					currentThread().sleep(delay);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
			count++;
		}
		
		
		}  
	

}
