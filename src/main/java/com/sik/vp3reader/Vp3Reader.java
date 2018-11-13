package com.sik.vp3reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Vp3Reader {

	/**
	 * https://edutechwiki.unige.ch/en/Embroidery_format_VP3
	 */
	
	private static final int STITCH_LENGTH = 12;
	
	public static void main(String[] args) {
		File file = new File("/media/sf_vmShared/buffman_47.vp3");
				        FileInputStream fin = null;
				        try {
				            // create FileInputStream object
				            fin = new FileInputStream(file);
				 
				            byte byteArray[] = new byte[(int)file.length()];
				            
				            for (int i=0; i<file.length();i++) {
				            	byteArray[i] =  (byte) fin.read();
				    
				            } 
				            for (int i=0;i<byteArray.length;i++ ) {
				            	
				            	if (byteArray[i] == 0 && byteArray[i+1] == 3 && byteArray[i+2] == 0) {
				            		System.out.println("Design Block: " + byteArray[i] + byteArray[i+1] + byteArray[i+2]);
//				            		i=i+3;
//				            		int bytesToEndOfDesign = twoByteValue(byteArray[i], byteArray[i+1]);
//				            		System.out.println("#bytes to end of design: " + bytesToEndOfDesign + " : " + byteArray[i] + " & " + byteArray[i+1]);
//				            		i=i+2;
				            	} 
				            	if (byteArray[i] == 0 && byteArray[i+1] == 5 && byteArray[i+2] == 0) {
				            		System.out.println("Colour Block 050");
				            		int bytesToNextColourBlock = twoByteValue(byteArray[i+3], byteArray[i+4]);
				            		System.out.println("#bytes to next colour block: " + bytesToNextColourBlock);
				            		i=i+5;
				            	} 
				            	if (byteArray[i] == 0 && byteArray[i+1] == 1 && byteArray[i+2] == 0) {
				            		System.out.println("Stitch Data 010");
				            		int noStitchBytes = twoByteValue(byteArray[i+3], byteArray[i+4]);
				            		System.out.println("#bytes to end of stitch data: " + noStitchBytes);
//				            		for (int j= i+5; j < noStitchBytes;j++) {
//				            			System.out.println(byteArray[j] + "\t" + byteArray[++j] + "\t" + 
//				            					byteArray[++j] + 	"\t" + byteArray[++j] + "\t" + 
//				            					byteArray[j] + "\t" + byteArray[++j] + "\t" + 
//				            					byteArray[j] + "\t" + byteArray[++j] + "\t" +
//				            					byteArray[++j] + "\t" + byteArray[++j] + "\t" + 
//				            					byteArray[++j] + "\t" + byteArray[++j]);
//				            			i=j;
//				            		}
//				            		System.out.println("***end of stitch data***");
				            	} 
				            	System.out.println(byteArray[i] + " - " + (char) byteArray[i]);	
				            	
				            }
				            
				            
				           
				        }
				        catch (FileNotFoundException e) {
				            System.out.println("File not found" + e);
				        }
				        catch (IOException ioe) {
				            System.out.println("Exception while reading file " + ioe);
				        }
				        finally {
				            // close the streams using close method
				            try {
				                if (fin != null) {
				                    fin.close();
				                }
				            }
				            catch (IOException ioe) {
				                System.out.println("Error while closing stream: " + ioe);
				            }
				        }


	}

	private static int twoByteValue(byte hi, byte lo) {
		return (hi * 256) + lo ;
	}

}
