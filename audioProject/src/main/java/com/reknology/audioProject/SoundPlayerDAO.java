package com.reknology.audioProject;

public class SoundPlayerDAO {
	
	MediaPlayer mediaPlayer;
	public SoundPlayerDAO(MediaPlayer mediaPlayer){
		this.mediaPlayer = mediaPlayer;
	}
	
	public void play(String filename, int delay, int loops){
		SoundPlayer soundPlayer = new SoundPlayer(mediaPlayer, filename, delay,loops);
		soundPlayer.start();
	}
}
