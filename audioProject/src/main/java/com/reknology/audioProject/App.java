package com.reknology.audioProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class App 
{

	private static Scanner scanner = new Scanner(System.in);
	
    public static void main( String[] args )
    {
    //	consoleStart();
    	beat();
    }
    public static void consoleStart(){
		String file = scanner.next();
		MediaPlayer mediaPlayer = new MediaPlayer(file);
		try {
			mediaPlayer.playSound();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scanner.close();
    }
	public static void beat(){
		MediaPlayer mediaPlayer = new MediaPlayer("hit.wav");
		int time = 1;
		int gap = 200;
		while(time < 100){
			mediaPlayer.play();
			time++;
			try {
				Thread.sleep(gap);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

		
}
