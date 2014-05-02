package br.com.hrdev.ucdiagram.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import br.com.hrdev.ucdiagram.UCDiagram;
import br.com.hrdev.ucdiagram.models.Projeto;
import br.com.hrdev.ucdiagram.utils.Text;
import br.com.hrdev.ucdiagram.views.View;

public class NovoProjetoEvent implements ActionListener {

	private View view;

	public NovoProjetoEvent(View view){
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		create();
	}
	
	private void create(){
		if(view.getWindow().getProjeto() != null){
			int option = JOptionPane.showConfirmDialog(view.getWindow(), Text.key("novo_aviso"), Text.key("novo_aviso_titulo"), JOptionPane.WARNING_MESSAGE);
			if(option != JOptionPane.OK_OPTION)
				return;
		}
		
		String titulo = JOptionPane.showInputDialog(view.getWindow(), Text.key("novo_nome"), Text.key("novo_titulo"), JOptionPane.QUESTION_MESSAGE);
		if(titulo == null || titulo.trim().length() == 0)
			return;

		view.getWindow().setProjeto(new Projeto(titulo));
		view.getWindow().changeView(UCDiagram.Dashboard);
		if(view != null)
			view.updateUIContents();
	}
}
