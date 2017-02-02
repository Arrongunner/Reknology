package com.reknology.audioProject;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.AudioFormat.Encoding;

public class Encoder {

	public Clip Encode(File file)
			throws LineUnavailableException, IOException, EncodingNotSupportedException, UnsupportedAudioFileException {

		AudioInputStream oldFormat = AudioSystem.getAudioInputStream(file);
		AudioFormat format = AudioSystem.getAudioFileFormat(file).getFormat();
		int bits = format.getSampleSizeInBits();
		String encoding = format.getEncoding().toString();
		if (encoding.equals("PCM_SIGNED") && bits == 16) {
			Clip clip = AudioSystem.getClip();
			clip.open(oldFormat);
			return clip;
		}

		Encoding[] array = AudioSystem.getTargetEncodings(oldFormat.getFormat());
		for (int i = 0; i < array.length; i++) {
			if (array[i].toString().equals("PCM_SIGNED")) {
				Clip clip = AudioSystem.getClip();
				AudioFormat newFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, false);
				AudioInputStream lowResAIS = AudioSystem.getAudioInputStream(newFormat, oldFormat);
				clip.open(lowResAIS);
				return clip;
			}
		}
		throw new EncodingNotSupportedException();

	}
	public AudioInputStream EncodeAIS(File file)
			throws LineUnavailableException, IOException, EncodingNotSupportedException, UnsupportedAudioFileException {

		AudioInputStream oldFormat = AudioSystem.getAudioInputStream(file);
		AudioFormat format = AudioSystem.getAudioFileFormat(file).getFormat();
		int bits = format.getSampleSizeInBits();
		String encoding = format.getEncoding().toString();
		if (encoding.equals("PCM_SIGNED") && bits == 16) {
			
			return oldFormat;
		}

		Encoding[] array = AudioSystem.getTargetEncodings(oldFormat.getFormat());
		for (int i = 0; i < array.length; i++) {
			if (array[i].toString().equals("PCM_SIGNED")) {
				
				AudioFormat newFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, false);
				AudioInputStream lowResAIS = AudioSystem.getAudioInputStream(newFormat, oldFormat);
				
				return lowResAIS;
			}
		}
		throw new EncodingNotSupportedException();

	}
}
