package br.com.hrdev.ucdiagram.controllers;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import br.com.hrdev.ucdiagram.libraries.FileBrowser;
import br.com.hrdev.ucdiagram.models.Diagrama;
import br.com.hrdev.ucdiagram.utils.Extension;
import br.com.hrdev.ucdiagram.views.Dashboard;

public class MenuExportController extends Controller {
	
	
	private Dashboard dashboard;
	
	public MenuExportController(Dashboard dashboard) {
		this.dashboard = dashboard;
	}
	
	public void actionPerformed(java.awt.event.ActionEvent e) {
		Diagrama panel = dashboard.getDiagram();
		if(panel == null) return;
		
		try {
			dashboard.getSidebar().clear();
			
			FileBrowser fb = new FileBrowser(true);
			fb.setSelectedFile(new File(dashboard.getWindow().getProjeto().getNome() + "." + Extension.png));

			switch (fb.showSaveDialog(dashboard)){
				case FileBrowser.APPROVE_OPTION : break;
				default : return;
			}
			
			File file = fb.getSelectedFile();
			File absolute = new File(fb.getCurrentDirectory(), file.getName());
			
			BufferedImage image = new Robot().createScreenCapture(new Rectangle(panel.getLocationOnScreen().x, panel.getLocationOnScreen().y, panel.getWidth(), panel.getHeight()));
			ImageIO.write(image, Extension.png,  absolute);
		} catch(Exception ex){
			ex.printStackTrace();
		}
	};
}
