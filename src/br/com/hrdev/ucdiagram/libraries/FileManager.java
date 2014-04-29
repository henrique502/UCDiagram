package br.com.hrdev.ucdiagram.libraries;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author Henrique Rieger
 */

public class FileManager {
	
	public boolean checkFile(File file) {
		if(!file.exists()){
			return false;
		}
		
		if(!file.exists()){
			return false;
		}
		
		if(!file.canWrite()){
			return false;
		}
		
		return true;
	}
	
	public Object load(File file){
		FileInputStream fileIn = null;
        ObjectInputStream in = null;
		Object retorno = null;
		try {
			fileIn = new FileInputStream(file);
		    in = new ObjectInputStream(fileIn);
		    retorno = in.readObject();
		    fileIn.close();
		    in.close();
	    } catch(Exception e){}
		return retorno;
	}
	
	public void save(Object objeto, File file){
		FileOutputStream fileOut = null;
		ObjectOutputStream out = null;
		
		try {
			if(!file.exists())
				file.createNewFile();
			
			fileOut = new FileOutputStream(file);
			out = new ObjectOutputStream(fileOut);
			out.writeObject(objeto);
			out.close();
			fileOut.close();
			
		} catch(Exception e){}
	}
	
}
