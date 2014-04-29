package br.com.hrdev.ucdiagram.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.com.hrdev.ucdiagram.models.Diagrama;
import br.com.hrdev.ucdiagram.views.DashboardView;

public class AdicionarDiagramaEvent implements ActionListener {

	private DashboardView view;

	public AdicionarDiagramaEvent(DashboardView view){
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		addDiagrama();
	}
	
	private void addDiagrama(){
		String titulo = JOptionPane.showInputDialog(view, "Digite o nome do novo diagrama");
		if(titulo == null || titulo.equals("")) return;
		
		ArrayList<Diagrama> diagramas = view.getWindow().getProjeto().getDiagramas();
		for(Diagrama diagrama : diagramas){
			if(diagrama.getNome().equalsIgnoreCase(titulo)){
				JOptionPane.showMessageDialog(view, "Esse nome j\u00e1 esta em uso.");
				addDiagrama();
				return;
			}
		}

		diagramas.add(new Diagrama(titulo));
		view.updateAll();
	}
}