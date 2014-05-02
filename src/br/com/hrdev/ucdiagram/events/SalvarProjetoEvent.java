package br.com.hrdev.ucdiagram.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import br.com.hrdev.ucdiagram.libraries.FileBrowser;
import br.com.hrdev.ucdiagram.libraries.FileManager;
import br.com.hrdev.ucdiagram.utils.Extension;
import br.com.hrdev.ucdiagram.utils.Text;
import br.com.hrdev.ucdiagram.views.View;

public class SalvarProjetoEvent implements ActionListener {
	
	private View view;
	private boolean newFile;

	public SalvarProjetoEvent(View view, boolean newfile){
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
			file = new File(file.toString().replaceAll("." + Extension.ucdiagram, "") + "." + Extension.ucdiagram);
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
		
		switch (fb.showSaveDialog(view.getWindow())){
			case FileBrowser.APPROVE_OPTION : return fb.getSelectedFile();
			default : return null;
		}
	}
	


}
