package com.reknology.audioProject;

public class SoundPlayer extends Thread{
	
	MediaPlayer mediaPlayer;
	int delay;
	String filename;
	int loops;
	
	public SoundPlayer(MediaPlayer mediaPlayer, String filename, int delay, int loops){
		this.mediaPlayer = mediaPlayer;
		this.filename = filename;
		this.delay = delay;
		this.loops = loops;
	}
	
	public void run() {
		mediaPlayer.setFilename(filename);
		int count = 0;
		while(count < loops){
			mediaPlayer.play();
			count ++;
		}
		
	}

}
