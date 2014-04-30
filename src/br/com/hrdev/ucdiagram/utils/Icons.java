package br.com.hrdev.ucdiagram.utils;

import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Icons {
	
	public static final ImageIcon Accept = new Icons().getIcon("accept");
	public static final ImageIcon Add = new Icons().getIcon("add");
	public static final ImageIcon Delete = new Icons().getIcon("delete");
	public static final ImageIcon Edit = new Icons().getIcon("edit");
	public static final ImageIcon Help = new Icons().getIcon("help");
	public static final ImageIcon Favicon = new Icons().getIcon("favicon");
	public static final ImageIcon Error = new Icons().getIcon("error");
	public static final ImageIcon Folder = new Icons().getIcon("folder");
	public static final ImageIcon Folder_close = new Icons().getIcon("folder_close");
	public static final ImageIcon Folder_open = new Icons().getIcon("folder_open");
	public static final ImageIcon Page_empty = new Icons().getIcon("page_empty");
	public static final ImageIcon Page = new Icons().getIcon("page");
	
	public static final ImageIcon Ator = new Icons().getIcon("ator");
	public static final ImageIcon Caso = new Icons().getIcon("caso");
	public static final ImageIcon Association = new Icons().getIcon("association");
	public static final ImageIcon Dependency = new Icons().getIcon("dependency");
	
	public static final ImageIcon Diagrama = new Icons().getIcon("diagrama");
	public static final ImageIcon Projeto = new Icons().getIcon("projeto");
	
	private static final String Ext = ".png";
	public static final Icon Ok = null;
	
	private ImageIcon getIcon(String iconName) {
		URL imgURL = getClass().getResource("/assets/icons/" + iconName + Ext);
	    if (imgURL != null) {
	        return new ImageIcon(imgURL);
	    } else {
	        System.err.println("Icone nao existe: " + "/assets/icons/" + iconName + Ext);
	        return null;
	    }
	}
}
