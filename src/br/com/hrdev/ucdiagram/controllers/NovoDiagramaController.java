package br.com.hrdev.ucdiagram.controllers;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.com.hrdev.ucdiagram.models.Diagrama;
import br.com.hrdev.ucdiagram.utils.Text;
import br.com.hrdev.ucdiagram.views.Dashboard;

public class NovoDiagramaController extends Controller {

	private Dashboard dashboard;

	public NovoDiagramaController(Dashboard dashboard){
		this.dashboard = dashboard;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		addDiagrama();
	}
	
	private void addDiagrama(){
		String titulo = JOptionPane.showInputDialog(dashboard, Text.key("dashboard_sidebar_add_diagrama_nome"));
		if(titulo == null || titulo.equals("")) return;
		
		ArrayList<Diagrama> diagramas = dashboard.getWindow().getProjeto().getDiagramas();
		for(Diagrama diagrama : diagramas){
			if(diagrama.getNome().equalsIgnoreCase(titulo)){
				JOptionPane.showMessageDialog(dashboard, Text.key("dashboard_sidebar_add_diagrama_erro"));
				addDiagrama();
				return;
			}
		}

		diagramas.add(new Diagrama(titulo));
		dashboard.updateAll();
	}
	
}
