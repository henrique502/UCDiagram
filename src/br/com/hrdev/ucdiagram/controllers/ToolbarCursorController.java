package br.com.hrdev.ucdiagram.controllers;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import br.com.hrdev.ucdiagram.models.Diagrama;
import br.com.hrdev.ucdiagram.models.Element;
import br.com.hrdev.ucdiagram.models.figures.Figure;
import br.com.hrdev.ucdiagram.views.Dashboard;

public class ToolbarCursorController extends Controller {

	private Dashboard dashboard;
	
	private volatile int screenX = 0;
	private volatile int screenY = 0;
	private volatile int myX = 0;
	private volatile int myY = 0;

	public ToolbarCursorController(Dashboard dashboard) {
		this.dashboard = dashboard;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Diagrama diagrama = dashboard.getDiagram();
		
		diagrama.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		dashboard.removeDiagramaListiners(diagrama);
		
		diagrama.addMouseListener(this);
		diagrama.addMouseMotionListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Diagrama diagrama = dashboard.getDiagram();

		for(Element elemento : diagrama.getAll()){
			if(elemento.contains(e.getPoint())){
				if(elemento instanceof Figure){
					dashboard.getSidebar().setItem((Figure) elemento);
					dashboard.repaint();
				}
				return;
			}
		}
		
		dashboard.getSidebar().clear();
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		dashboard.getDiagram().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		Diagrama diagrama = dashboard.getDiagram();
		Figure figure = dashboard.getSidebar().getItem();
		if(figure == null || !figure.contains(e.getPoint())) return;
		

		diagrama.setCursor(new Cursor(Cursor.MOVE_CURSOR));
		
		screenX = e.getXOnScreen();
        screenY = e.getYOnScreen();

        myX = figure.getX();
        myY = figure.getY();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		Figure figure = dashboard.getSidebar().getItem();
		if(figure == null || !figure.contains(e.getPoint())) return;
		
		int deltaX = e.getXOnScreen() - screenX;
        int deltaY = e.getYOnScreen() - screenY;
        
        dashboard.getSidebar().updateItem();
        figure.setLocation(myX + deltaX, myY + deltaY);
        dashboard.repaint();
	}

}
