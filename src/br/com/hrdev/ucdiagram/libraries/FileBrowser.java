package br.com.hrdev.ucdiagram.libraries;

/**
 * @author Henrique Rieger
 */

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import br.com.hrdev.ucdiagram.utils.Extension;

public class FileBrowser extends JFileChooser {
	
	private static final long serialVersionUID = 1L;
	private static String userDir = null;
	
	public FileBrowser(){
		this(false);
	}
	
	public FileBrowser(boolean isImage){
		super();
		
		if(userDir == null)
			userDir = System.getProperty("user.dir");

		setCurrentDirectory(new File(userDir));
		
		addPropertyChangeListener(new PropertyChangeListener(){
			
			@Override
			public void propertyChange(PropertyChangeEvent evt){
				if(JFileChooser.DIRECTORY_CHANGED_PROPERTY.equals(evt.getPropertyName())) {
					File dir = (File) evt.getNewValue();
					
					userDir = dir.getAbsolutePath();
				}
			}
		});
		
		setFileHidingEnabled(false);
		if(isImage){
			setFileFilter(new FileBrowserImageFilter());
		} else {
			setFileFilter(new FileBrowserFileFilter());
		}
		
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
	
	private class FileBrowserImageFilter extends FileFilter {

		@Override
		public boolean accept(File f) {
			if (f.isDirectory()) {
		        return true;
		    }

		    String extension = Extension.getExtension(f);
		    if (extension != null) {
		        if (extension.equals(Extension.png)){
		        	return true;
		        }
		    }

		    return false;
		}

		@Override
		public String getDescription() {
			return "*." + Extension.png;
		}
	}
}