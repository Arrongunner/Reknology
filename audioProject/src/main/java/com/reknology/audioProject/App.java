package com.reknology.audioProject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class App 
{

	private static Scanner scanner = new Scanner(System.in);
	
    public static void main( String[] args )
    {
    	consoleStart();        
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
}
