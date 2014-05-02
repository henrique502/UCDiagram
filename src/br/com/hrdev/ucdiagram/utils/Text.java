package br.com.hrdev.ucdiagram.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Henrique Rieger Schmidt
 */
public class Text {
	
	private static Properties prop = null;
	
	/**
	 * @param key 
	 * @param labels
	 * @return Texto
	 */
	public static String key(String key,Object... labels){
		return String.format(key(key),labels);
	}
	
	/**
	 * @param key
	 * @return Texto
	 */
	public static String key(String key){
		load();
		return prop.getProperty(key);
	}
	
	private static void load(){
		if(prop == null){
			prop = new Properties();
			InputStream input = null;
			try {
				File file = new File(new Text().getClass().getResource("/assets/properties/text.properties").toURI());
				input = new FileInputStream(file);
				prop.load(input);
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				if (input != null) {
					try {
						input.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
