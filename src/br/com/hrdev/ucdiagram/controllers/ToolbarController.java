package br.com.hrdev.ucdiagram.controllers;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import br.com.hrdev.ucdiagram.models.Diagrama;
import br.com.hrdev.ucdiagram.models.Element;
import br.com.hrdev.ucdiagram.models.figures.Actor;
import br.com.hrdev.ucdiagram.models.figures.Case;
import br.com.hrdev.ucdiagram.utils.ComponentsUtil;
import br.com.hrdev.ucdiagram.views.Dashboard;
import br.com.hrdev.ucdiagram.views.dashboard.Toolbar;

public class ToolbarController extends Controller {
	
	private Dashboard dashboard;

	public ToolbarController(Dashboard dashboard) {
		this.dashboard = dashboard;
	}
	
	private Element doAction(Diagrama diagrama, String action, Point point){
		Element elemento = null;
		
		if(action.equals(Toolbar.ACTOR))
			elemento = new Actor("Actor",dashboard.getGraphics(),point);
		
		if(action.equals(Toolbar.CASE))
			elemento = new Case("Case",dashboard.getGraphics(),point);

		return elemento;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(dashboard.getToolbar().getCurrent().equals(Toolbar.CURSOR))
			return;
		
		Diagrama diagrama = dashboard.getDiagram();
		
		diagrama.setCursor(new Cursor(Cursor.HAND_CURSOR));
		dashboard.removeDiagramaListiners(diagrama);
		
		diagrama.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Diagrama diagrama = dashboard.getDiagram();
		String action = dashboard.getToolbar().getCurrent();
		
		Element elemento = doAction(diagrama,action,e.getPoint());
		if(elemento == null) return;
		
		diagrama.add(elemento);
		
		dashboard.repaint();
		dashboard.getSidebar().updateDataTree();
		dashboard.getToolbar().reset();
	}
}
