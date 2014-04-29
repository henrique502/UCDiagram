package br.com.hrdev.ucdiagram.utils;

import java.net.URL;

import javax.swing.ImageIcon;

public class Images {
	
	public static final ImageIcon Ator = new Images().getIcon("ator-image.png");

	private ImageIcon getIcon(String iconName) {
		URL imgURL = getClass().getResource("/assets/img/" + iconName);
	    if (imgURL != null) {
	        return new ImageIcon(imgURL);
	    } else {
	        System.err.println("Icone nao existe: " + "/assets/icons/" + iconName);
	        return null;
	    }
	}
}
