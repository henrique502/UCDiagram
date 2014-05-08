package br.com.hrdev.ucdiagram.controllers;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import br.com.hrdev.ucdiagram.libraries.FileBrowser;
import br.com.hrdev.ucdiagram.models.Diagrama;
import br.com.hrdev.ucdiagram.utils.Extension;
import br.com.hrdev.ucdiagram.views.Dashboard;

public class ExportController extends Controller {
	
	
	private Dashboard dashboard;
	
	public ExportController(Dashboard dashboard) {
		this.dashboard = dashboard;
	}
	
	public void actionPerformed(java.awt.event.ActionEvent e) {
		Diagrama diagrama = dashboard.getDiagrama();
		if(diagrama == null) return;
		
		export(diagrama);
	}

	public void export(Diagrama diagrama) {
		try {
			dashboard.getSidebar().clear();
			String filename = diagrama.getNome() + " - " + dashboard.getWindow().getProjeto().getNome();
			
			
			FileBrowser fb = new FileBrowser(true);
			fb.setSelectedFile(new File(filename + "." + Extension.png));

			switch (fb.showSaveDialog(dashboard)){
				case FileBrowser.APPROVE_OPTION : break;
				default : return;
			}
			
			File file = fb.getSelectedFile();
			File absolute = new File(fb.getCurrentDirectory(), file.getName());
			
			BufferedImage image = diagrama.createImage();
			ImageIO.write(image, Extension.png,  absolute);
		} catch(Exception ex){
			ex.printStackTrace();
		}
	};
}
