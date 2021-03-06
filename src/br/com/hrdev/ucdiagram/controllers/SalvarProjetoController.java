package br.com.hrdev.ucdiagram.controllers;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import br.com.hrdev.ucdiagram.libraries.FileBrowser;
import br.com.hrdev.ucdiagram.libraries.FileManager;
import br.com.hrdev.ucdiagram.utils.Extension;
import br.com.hrdev.ucdiagram.utils.Text;
import br.com.hrdev.ucdiagram.views.View;

public class SalvarProjetoController extends Controller {
	
	private View view;
	private boolean newFile;

	public SalvarProjetoController(View view, boolean newfile){
		this.view = view;
		this.newFile = newfile;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		salvar();
	}
	
	private void salvar(){
		File file = null;
		if(view.getWindow().getProjetoArquivo() == null || newFile){
			
			file = setSaveFile();
			
			String ext = Extension.getExtension(file);
			if(ext == null || !ext.equalsIgnoreCase(Extension.ucdiagram)){
				file = new File(file.getAbsolutePath(),file.getName() + "." + Extension.ucdiagram);
			}
			
			if(!file.exists()){
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			file = view.getWindow().getProjetoArquivo();
		}
		
		if(file == null) return;
		
		FileManager fm = new FileManager();
		if(fm.checkFile(file)){
			fm.save(view.getWindow().getProjeto(), file);
			view.getWindow().setProjetoArquivo(file);
			JOptionPane.showMessageDialog(view.getWindow(), Text.key("salvar_sucesso"));
		} else {
			JOptionPane.showMessageDialog(view.getWindow(), Text.key("salvar_erro"));
		}
	}
	
	private File setSaveFile(){
		FileBrowser fb = new FileBrowser();
		fb.setSelectedFile(new File(view.getWindow().getProjeto().getNome() + "." + Extension.ucdiagram));
		
		switch (fb.showSaveDialog(view.getWindow())){
			case FileBrowser.APPROVE_OPTION : return fb.getSelectedFile();
			default : return null;
		}
	}
	


}
