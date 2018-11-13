package com.sik.vp3reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Vp3Reader {

	/**
	 * Info sources:-
	 * https://edutechwiki.unige.ch/en/Embroidery_format_VP3
	 * http://www.jasonweiler.com/VP3FileFormatInfo.html
	 */
	
	private static final int STITCH_LENGTH = 12;
	
	public static void main(String[] args) {
		File file = new File("/media/sf_vmShared/buffman_47.vp3");
				        FileInputStream fin = null;
				        try {
				            // create FileInputStream object
				            fin = new FileInputStream(file);
				 
				            byte ba[] = new byte[(int)file.length()];
				            
				            for (int i=0; i<file.length();i++) {
				            	ba[i] =  (byte) fin.read();
				    
				            } 
				            for (int i=0;i<ba.length;i++ ) {
				            	
				            	if (ba[i] == 0 && ba[i+1] == 2 && ba[i+2] == 0) {
				            		System.out.println("File Data: " + ba[i] + ba[i+1] + ba[i+2]);
				            		i=i+3;
				            		int bytesToEndOfDesign = twoByteValue(ba[i], ba[i+1]);
				            		System.out.println("#bytes to end of design: " + bytesToEndOfDesign + " : " + ba[i] + " & " + ba[i+1]);
				            		i=i+2;
				            	} 
//				            	if (byteArray[i] == 0 && byteArray[i+1] == 3 && byteArray[i+2] == 0) {
//				            		System.out.println("Design Block: " + byteArray[i] + byteArray[i+1] + byteArray[i+2]);
//				            		i=i+3;
//				            		int bytesToEndOfDesign = twoByteValue(byteArray[i], byteArray[i+1]);
//				            		System.out.println("#bytes to end of design: " + bytesToEndOfDesign + " : " + byteArray[i] + " & " + byteArray[i+1]);
//				            		i=i+2;
//				            	} 
//				            	if (byteArray[i] == 0 && byteArray[i+1] == 5 && byteArray[i+2] == 0) {
//				            		System.out.println("Colour Block:" + byteArray[i] + byteArray[i+1] + byteArray[i+2]);
//				            		int bytesToNextColourBlock = twoByteValue(byteArray[i+3], byteArray[i+4]);
//				            		System.out.println("#bytes to next colour block: " + bytesToNextColourBlock);
//				            		i=i+5;
//				            	} 
//				            	if (byteArray[i] == 0 && byteArray[i+1] == 1 && byteArray[i+2] == 0) {
//				            		System.out.println("Stitch Data:" + byteArray[i] + byteArray[i+1] + byteArray[i+2]);
//				            		int noStitchBytes = twoByteValue(byteArray[i+3], byteArray[i+4]);
//				            		System.out.println("#bytes to end of stitch data: " + noStitchBytes);
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
//				            	}
				            	//Header
				            	if (ba[i] ==37  
				            			&& ba[i+1] == 118 && ba[i+2] == 115 && ba[i+3] == 109	//vsm
				            			&& ba[i+4] == 37 
				            			&& ba[i+5] == 0) {
				            		System.out.println("Header Section:");
				            		int noInfoBytes = twoByteValue(ba[i+6], ba[i+7]);
				            		System.out.println("#bytes to end of header data: " + noInfoBytes);
				            		int jStart = i+8;
				            		System.out.println("i=" + i + " jStart + noInfoBytes=" + (jStart + noInfoBytes));
				            		for (int j=0; j<noInfoBytes;j++) {
				            			System.out.print((char) twoByteValue(ba[j+jStart], ba[++j+jStart]));
				            			i=j+jStart;
				            		}
				            		i++;

				            		System.out.println("\n***end of header data***");
				            		
				            		System.out.println("Unknown 2B:" + twoByteValue(ba[i], ba[++i]));
				            		
				            		System.out.println("Unknown 1B:" + ba[++i]);
				            		
				            		System.out.println("Outer Section Size:" + fourByteValue(ba[++i], ba[++i], ba[++i], ba[++i]));

				            		System.out.println(ba[i+1] + " & " + ba[i+2]);
				            		
				            		int noUnknownBytes = twoByteValue(ba[i+1], ba[i+2]);
				            		int kStart = i+3;
				            		System.out.println("i=" + i + " kStart + noUnknownBytes=" + (kStart + noUnknownBytes));
				            		for (int k=0; k<noUnknownBytes;k++) {
				            			System.out.print((char) twoByteValue(ba[k+kStart], ba[++k+kStart]));
				            			//System.out.println(byteArray[k+kStart] + " - " + (char)byteArray[k+kStart]);
				            			i=k+kStart;
				            		}
				            		i++;
				            		
				            		//Hoop info
//				            		System.out.println("Positive X Hoop dimension:" + (fourByteValue(ba[i], ba[i+1], ba[i+2], ba[i+3])/1000) );
//				            		i=i+4;
//				            		System.out.println("Positive Y Hoop dimension:" + (fourByteValue(ba[i], ba[i+1], ba[i+2], ba[i+3])/1000) );
//				            		i=i+4;
//				            		System.out.println("Negative X Hoop dimension:" + (fourByteValue(ba[i], ba[i+1], ba[i+2], ba[i+3])/1000) );
//				            		i=i+4;
//				            		System.out.println("Negative Y Hoop dimension:" + (fourByteValue(ba[i], ba[i+1], ba[i+2], ba[i+3])/1000) );
//				            		i=i+4;
				            		
				            	} 
				            	
				            	//Stitch data
				            	if (ba[i] ==0x78  && ba[i+1] == 0x78 
				            			&& ba[i+2] == 0x50 && ba[i+3] == 0x50 
				            			&& ba[i+4] == 0x01 && ba[i+5] == 0x00) {
				            		System.out.println("Stitch Section:");
				            		int noStitchBytes = twoByteValue(ba[i+6], ba[i+7]);
				            		System.out.println("#bytes to end of stitch data: " + noStitchBytes);
				            		int jStart = i+8;
				            		System.out.println("i=" + i + " jStart + noStitchBytes=" + (jStart + noStitchBytes));
				            		for (int j=0; j<noStitchBytes;j++) {
				            			System.out.print((char) twoByteValue(ba[j+jStart], ba[++j+jStart]));
				            			i=j+jStart;
				            		}
				            		i++;
				            		System.out.println("\n***end of stitch data***");
				            	} 
				            	System.out.println(ba[i] + " - " + (char) ba[i]);	
				            	
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
	
	private static int fourByteValue(byte b1, byte b2, byte b3, byte b4) {
		return (b1 * 256 * 256 * 256) + (b2 * 256 * 256) + (b3 * 256) + b4 ;
	}

}
