package br.com.hrdev.ucdiagram.controllers;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.JToggleButton;

import br.com.hrdev.ucdiagram.models.Diagrama;
import br.com.hrdev.ucdiagram.models.Element;
import br.com.hrdev.ucdiagram.models.arrows.Arrow;
import br.com.hrdev.ucdiagram.models.arrows.Association;
import br.com.hrdev.ucdiagram.models.arrows.Dependency;
import br.com.hrdev.ucdiagram.models.figures.Figure;
import br.com.hrdev.ucdiagram.views.Dashboard;
import br.com.hrdev.ucdiagram.views.dashboard.Toolbar;

public class ToolbarArrowController extends Controller {

	private Dashboard dashboard;
	private Arrow arrow;

	public ToolbarArrowController(Dashboard dashboard) {
		this.dashboard = dashboard;
	}
	
	private Arrow getArrow(JToggleButton source){
		if(source.getName().equals(Toolbar.ASSOCIATION))
			return new Association();
		
		if(source.getName().equals(Toolbar.DEPENDENCY))
				return new Dependency();
		
		return null;
	}
	
	private void configArrow(Diagrama diagrama, Figure elemento) {
		if(arrow.getStart() == null){
			arrow.setStart(elemento);
			return;
		}
		
		if(arrow.getStart() == elemento) return;
		
		arrow.setEnd(elemento);
		diagrama.addFirst(arrow);
		dashboard.getToolbar().reset();
		dashboard.repaint();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Diagrama diagrama = dashboard.getDiagrama();
		arrow = getArrow((JToggleButton) e.getSource());
		if(arrow == null) return;
		
		diagrama.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		dashboard.removeDiagramaListiners(diagrama);
		
		diagrama.addMouseListener(this);
		diagrama.addMouseMotionListener(this);
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		Diagrama diagrama = dashboard.getDiagrama();

		for(Element elemento : diagrama.getAll()){
			if(elemento.contains(e.getPoint())){
				if(elemento instanceof Figure){
					diagrama.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
				return;
			}
		}
		diagrama.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Diagrama diagrama = dashboard.getDiagrama();

		for(Element elemento : diagrama.getAll()){
			if(elemento.contains(e.getPoint())){
				if(elemento instanceof Figure){
					configArrow(diagrama,(Figure) elemento);
				}
				return;
			}
		}
	}
}
