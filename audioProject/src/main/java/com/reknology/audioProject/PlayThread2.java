package com.reknology.audioProject;

import java.io.File;
import java.util.HashMap;

import javax.sound.sampled.Clip;

public class PlayThread2 extends Thread{
	int loops;
	Clip clip;
	PlayThread2(Clip clip, int loops){
		this.loops = loops;
		this.clip = clip;
	}
	public void run(){
		clip.start();		
	}
	
}
