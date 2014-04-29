package br.com.hrdev.ucdiagram.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JOptionPane;

import br.com.hrdev.ucdiagram.UCDiagram;
import br.com.hrdev.ucdiagram.libraries.FileBrowser;
import br.com.hrdev.ucdiagram.libraries.FileManager;
import br.com.hrdev.ucdiagram.models.Projeto;
import br.com.hrdev.ucdiagram.views.View;

public class CarregarProjetoEvent implements ActionListener {
	
	private UCDiagram window;
	private View view;

	public CarregarProjetoEvent(UCDiagram window, View view){
		this.window = window;
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
			window.setProjeto(projeto);
			window.setProjetoArquivo(file);
			window.changeView(UCDiagram.Dashboard);
			if(view != null)
				view.updateUIContents();
			
		} else {
			JOptionPane.showMessageDialog(window, "Erro ao carregar o projeto");
			System.out.println(file);
		}
	}
	
	private File getFile(){
		FileBrowser fb = new FileBrowser();
		
		switch (fb.showOpenDialog(window)){
			case FileBrowser.APPROVE_OPTION : return fb.getSelectedFile();
			default : return null;
		}
	}
	


}
