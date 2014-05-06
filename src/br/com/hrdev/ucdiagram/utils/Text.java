package br.com.hrdev.ucdiagram.utils;

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
		return prop.getProperty(key);
	}
	
	public Text(){
		load();
	}
	
	private void load(){
		if(prop != null) return;
		
		prop = new Properties();
		InputStream input = null;
		try {
			input = getClass().getResourceAsStream("/assets/properties/text.properties");
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
