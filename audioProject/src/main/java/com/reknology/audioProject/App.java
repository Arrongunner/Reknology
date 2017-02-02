package com.reknology.audioProject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.io.ByteStreams.readFully;
import static com.google.common.io.ByteStreams.skipFully;
import static java.lang.String.format;
import static java.util.concurrent.TimeUnit.SECONDS;
import static javax.sound.sampled.AudioFileFormat.Type.WAVE;
import static javax.sound.sampled.AudioFormat.Encoding.PCM_SIGNED;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
/**
 * Hello world!
 *
 */
public class App 
{
    @SuppressWarnings("unchecked")
	public static void main( String[] args )
    {
    	
  	SoundDAO soundDAO = new SoundDAO();
//  	try {
//		soundDAO.playSound("./Sounds/ding.wav", 100, 4);
//	} catch (LineUnavailableException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} catch (EncodingNotSupportedException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} catch (UnsupportedAudioFileException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//    	
//
//		Encoder encoder = new Encoder();
//       	File file = new File("./Sounds/ding.wav");
//		Clip clip = null;
//		try {
//			clip = encoder.Encode(file);
//		} catch (LineUnavailableException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (EncodingNotSupportedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (UnsupportedAudioFileException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		final Clip clip2 = clip;
//       	HashMap<Integer, TimerTask> map = new HashMap<Integer, TimerTask>();
//       	TimerTask timerTask1 = new TimerTask(){
//			 @Override
//	            public void run() { 
//				 		clip2.start();
//			 }
//		};
//		TimerTask timerTask2 = new TimerTask(){
//			 @Override
//	            public void run() { 
//				 		clip2.start();
//			 }
//		};
//		TimerTask timerTask3 = new TimerTask(){
//			 @Override
//	            public void run() { 
//				 		clip2.start();
//			 }
//		};
//		TimerTask timerTask4 = new TimerTask(){
//			 @Override
//	            public void run() { 
//				 		clip2.start();
//			 }
//		};
//		TimerTask timerTask5 = new TimerTask(){
//			 @Override
//	            public void run() { 
//				 		clip2.start();
//			 }
//		};
//       	map.put(1, timerTask1);
//       	map.put(2, timerTask2);
//       	map.put(3, timerTask3);
//       	map.put(4, timerTask4);
//       	map.put(5, timerTask5);
//       	try {
//
//           	System.out.println(map.get(1));
//           	System.out.println("here");
//			soundDAO.playSound2(map, 4, 2000);
//		} catch (LineUnavailableException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (EncodingNotSupportedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (UnsupportedAudioFileException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
           
  	HashMap map = new HashMap();
  	map.put(1, true);
  	map.put(2, true);
  	map.put(3, false);
  	map.put(4, true);
  	map.put(5, true);
  	map.put(6, false);
  	HashMap map2 = new HashMap();
  	map2.put(1, false);
  	map2.put(2, false);
  	map2.put(3, true);
  	map2.put(4, false);
  	map2.put(5, false);
  	map2.put(6, true);
	Encoder encoder = new Encoder();
	File file = new File("./Sounds/hit.wav");
	File file2 = new File("./Sounds/drum.wav");

	PlayThread playThread2 = new PlayThread(file2, map, 6, 400);
	playThread2.start();
	
	PlayThread playThread = new PlayThread(file, map2, 6, 400);
	playThread.start();
	
	AudioInputStream audioInputStream = null;
	try {
		 audioInputStream = AudioSystem.getAudioInputStream(file);
	} catch (UnsupportedAudioFileException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	AudioInputStream audioInputStream2 = null;
	try {
		 audioInputStream2 = AudioSystem.getAudioInputStream(file2);
	} catch (UnsupportedAudioFileException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    ArrayList<AudioInputStream> streams = new ArrayList<AudioInputStream>();
    AudioInputStream ais1 = null;
    AudioInputStream ais2 = null;
    try {
		 ais1 = encoder.EncodeAIS(file);
	     ais2 = encoder.EncodeAIS(file2);
	} catch (LineUnavailableException | IOException | EncodingNotSupportedException
			| UnsupportedAudioFileException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}

    streams.add(ais1);
    streams.add(ais1);
    streams.add(ais1);
    streams.add(ais2);

    
    ExportDAO exportDAO = new ExportDAO();
    
    
    try {
		exportDAO.export(streams,1,100,"test2.wav");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    

}
}
