package com.reknology.audioProject;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.FileWriter;

public class FileOutput {

	public void write(String filename, byte[] output) {

		FileOutputStream fos;
		try {
			fos = new FileOutputStream("./Output/"+filename);
			fos.write(output);
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
