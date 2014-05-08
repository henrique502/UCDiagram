package br.com.hrdev.ucdiagram.controllers;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JOptionPane;

import br.com.hrdev.ucdiagram.UCDiagram;
import br.com.hrdev.ucdiagram.libraries.FileBrowser;
import br.com.hrdev.ucdiagram.libraries.FileManager;
import br.com.hrdev.ucdiagram.models.Projeto;
import br.com.hrdev.ucdiagram.views.View;

public class CarregarProjetoController extends Controller {
	
	private View view;

	public CarregarProjetoController(View view){
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		carregar();
	}
	
	private void carregar(){
		File file = getFile();
		
		if(file == null) return;
		
		
		FileManager fm = new FileManager();
		if(fm.checkFile(file)){
			Projeto projeto = (Projeto) fm.load(file);
			view.getWindow().setProjeto(projeto);
			view.getWindow().setProjetoArquivo(file);
			view.getWindow().changeView(UCDiagram.Dashboard);
			if(view != null)
				view.updateUIContents();
			
		} else {
			JOptionPane.showMessageDialog(view.getWindow(), "Erro ao carregar o projeto");
		}
	}
	
	private File getFile(){
		FileBrowser fb = new FileBrowser();
		
		switch (fb.showOpenDialog(view.getWindow())){
			case FileBrowser.APPROVE_OPTION : return fb.getSelectedFile();
			default : return null;
		}
	}
	


}
