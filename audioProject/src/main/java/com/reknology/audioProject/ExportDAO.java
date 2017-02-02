package com.reknology.audioProject;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.io.ByteStreams.readFully;
import static com.google.common.io.ByteStreams.skipFully;
import static java.lang.String.format;
import static java.util.concurrent.TimeUnit.SECONDS;
import static javax.sound.sampled.AudioFileFormat.Type.WAVE;
import static javax.sound.sampled.AudioFormat.Encoding.PCM_SIGNED;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import static com.google.common.base.Preconditions.*;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.io.ByteArrayOutputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class ExportDAO {

	
    public final static AudioFormat SUPPORTED_FORMAT = new AudioFormat(PCM_SIGNED, 44100, 16, 2, 4,
			44100, false);
    
    public void export(ArrayList<AudioInputStream> input, int startTime, int endTime, String filename) throws IOException{
    	
    	int startByte = (int) (bytesPerMillis(SUPPORTED_FORMAT) * startTime);
    	int endByte = (int) (bytesPerMillis(SUPPORTED_FORMAT) * endTime);
    	byte[] fullTrack = null;
    	FileOutput fileOutput = new FileOutput();
    	for(AudioInputStream ais:input){
    		byte[] bytes = cut(ais,  startByte, endByte);
    		try (FileOutputStream output = new FileOutputStream("./Output/"+filename, true)) {
    		    output.write(bytes);
    		}
//    		int flength = 0;
//    		if(fullTrack != null){
//    			flength = fullTrack.length;
//    		}
//    		byte[] combined =  new byte[flength + bytes.length];
//    		for (int i = 0; i < combined.length; ++i)
//    		{
//    			if(flength != 0){
//    			combined[i] = i < fullTrack.length ? fullTrack[i] : fullTrack[i - bytes.length];
//    			}
//    			else{
//    				combined = bytes;
//    			}
//    		}    		
//    		fullTrack = combined;
    		byte[] empty = new byte[((int) (bytesPerMillis(SUPPORTED_FORMAT)) * 1000)];
    		try (FileOutputStream output = new FileOutputStream("./Output/"+filename, true)) {
    		    output.write(empty);
    		}
    		
    		
    	}
    	Path path = Paths.get("./Output/"+filename);
    	byte[] data = Files.readAllBytes(path);
    	
    	
    	AudioInputStream ais = createAudioInputStream(data);
    	write(ais, "./Output/"+filename);    	
    	
    }
    
    private double bytesPerMillis(AudioFormat format) {
		return 1d * format.getFrameSize() * format.getFrameRate() / SECONDS.toMillis(1);
	}
    
    private byte[] cut(AudioInputStream input, int startByte, int endByte) throws IOException {
		assert startByte <= endByte;
		skipFully(input, startByte);
		byte[] cut = new byte[endByte - startByte];
		readFully(input, cut);
		assert cut.length % input.getFormat().getFrameSize() == 0;
		return cut;
	}

	public AudioInputStream createAudioInputStream(byte[] bytes) {
		InputStream inputStream = new ByteArrayInputStream(bytes);
		int frames = bytes.length / SUPPORTED_FORMAT.getFrameSize();
		return new AudioInputStream(inputStream, SUPPORTED_FORMAT, frames);
	}

	public void write(AudioInputStream input, String outputFile) throws IOException {
		AudioSystem.write(input, WAVE, new File(outputFile));
	}
	
}
