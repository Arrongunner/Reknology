package com.reknology.audioProject;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.*;
import javax.sound.sampled.*;
import javax.sound.sampled.AudioFormat.Encoding;

public class LoopSounds {

    public static void main(String[] args) throws Exception {
    	

        File file = new File("./Sounds/hit.wav");
        Clip clip = AudioSystem.getClip();
        AudioInputStream ais = AudioSystem.getAudioInputStream( file );
        
        Encoding[] array = AudioSystem.getTargetEncodings(ais.getFormat());
        for(int i=0;i<array.length;i++){ System.out.println(i + "-->"+array[i]); } 
    	
        AudioFormat newFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, false) ;
        AudioInputStream lowResAIS = AudioSystem.getAudioInputStream(newFormat, ais);
        clip.open(lowResAIS);
        File file2 = new File("./Sounds/drum.wav");
        Clip clip2 = AudioSystem.getClip();
        AudioInputStream ais2 = AudioSystem.getAudioInputStream( file2 );
        AudioFormat newFormat2 = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 16000, false) ;
        AudioInputStream lowResAIS2 = AudioSystem.getAudioInputStream(newFormat2, ais2);
        clip2.open(lowResAIS2);

        // loop continuously
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip2.loop(Clip.LOOP_CONTINUOUSLY);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // A GUI element to prevent the Clip's daemon Thread
                // from terminating at the end of the main()
                JOptionPane.showMessageDialog(null, "Close to exit!");
            }
        });
    }
}
