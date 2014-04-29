package br.com.hrdev.ucdiagram.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import br.com.hrdev.ucdiagram.UCDiagram;
import br.com.hrdev.ucdiagram.libraries.FileBrowser;
import br.com.hrdev.ucdiagram.libraries.FileManager;
import br.com.hrdev.ucdiagram.utils.Extension;

public class SalvarProjetoEvent implements ActionListener {
	
	private UCDiagram window;
	private boolean newFile;

	public SalvarProjetoEvent(UCDiagram window, boolean newfile){
		this.window = window;
		this.newFile = newfile;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		salvar();
	}
	
	private void salvar(){
		File file = null;
		if(window.getProjetoArquivo() == null || newFile){
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
			file = window.getProjetoArquivo();
		}
		
		if(file == null) return;
		
		FileManager fm = new FileManager();
		if(fm.checkFile(file)){
			fm.save(window.getProjeto(), file);
			window.setProjetoArquivo(file);
			JOptionPane.showMessageDialog(window, "Projeto salvo com sucesso!");
		} else {
			JOptionPane.showMessageDialog(window, "Erro ao salvar o projeto");
		}
	}
	
	private File setSaveFile(){
		FileBrowser fb = new FileBrowser();
		
		switch (fb.showSaveDialog(window)){
			case FileBrowser.APPROVE_OPTION : return fb.getSelectedFile();
			default : return null;
		}
	}
	


}
