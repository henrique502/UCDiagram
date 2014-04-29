package br.com.hrdev.ucdiagram.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import br.com.hrdev.ucdiagram.UCDiagram;
import br.com.hrdev.ucdiagram.models.Projeto;
import br.com.hrdev.ucdiagram.views.View;

public class NovoProjetoEvent implements ActionListener {

	
	private UCDiagram window;
	private View view;

	public NovoProjetoEvent(UCDiagram window, View view){
		this.window = window;
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		create();
	}
	
	private void create(){
		if(window.getProjeto() != null){
			int option = JOptionPane.showConfirmDialog(window, "Voc\u00ea quer cirar um novo projeto?\nTodas as altera\u00e7\u00f5es n\u00e3o savas ser\u00e3o perdidas");
			if(option != JOptionPane.OK_OPTION)
				return;
		}
		
		String titulo = JOptionPane.showInputDialog(window, "Nome do Projeto:");
		if(titulo == null || titulo.trim().length() == 0)
			return;

		window.setProjeto(new Projeto(titulo));
		window.changeView(UCDiagram.Dashboard);
		if(view != null)
			view.updateUIContents();
	}
}
