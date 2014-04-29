package br.com.hrdev.ucdiagram.libraries;

/**
 * @author Henrique Rieger
 */

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import br.com.hrdev.ucdiagram.utils.Extension;

public class FileBrowser extends JFileChooser {
	
	private static final long serialVersionUID = 1L;
	
	public FileBrowser(){
		super();
		
		String userDir = System.getProperty("user.dir");
		if(userDir != null)
			setCurrentDirectory(new File(userDir));
		
		setFileHidingEnabled(false);
		setFileFilter(new FileBrowserFileFilter());
		setAcceptAllFileFilterUsed(false);
	}
	
	private class FileBrowserFileFilter extends FileFilter {

		@Override
		public boolean accept(File f) {
			if (f.isDirectory()) {
		        return true;
		    }

		    String extension = Extension.getExtension(f);
		    if (extension != null) {
		        if (extension.equals(Extension.ucdiagram)){
		        	return true;
		        }
		    }

		    return false;
		}

		@Override
		public String getDescription() {
			return "*." + Extension.ucdiagram;
		}
		
	}
}