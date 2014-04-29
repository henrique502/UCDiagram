package br.com.hrdev.ucdiagram.utils;

import java.io.File;

/**
 * @author Oracle
 * @see http://docs.oracle.com/javase/tutorial/uiswing/components/filechooser.html
 */

public class Extension {
	
	 public final static String ucdiagram = "ucdiagram";

	 public static String getExtension(File f) {
		 String ext = null;
	     String s = f.getName();
	     int i = s.lastIndexOf('.');

	     if (i > 0 &&  i < s.length() - 1) {
	    	 ext = s.substring(i+1).toLowerCase();
	     }
	     return ext;
	 }
}