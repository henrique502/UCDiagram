package br.com.hrdev.ucdiagram.controllers;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import br.com.hrdev.ucdiagram.components.UIToolBarButton;
import br.com.hrdev.ucdiagram.models.Diagrama;
import br.com.hrdev.ucdiagram.models.Element;
import br.com.hrdev.ucdiagram.views.Dashboard;

public class DiagramaController extends Controller {

	private Dashboard dashboard;
	private volatile int screenX = 0;
	private volatile int screenY = 0;
	private volatile int myX = 0;
	private volatile int myY = 0;
	
	private Cursor cursorMove = new Cursor(Cursor.MOVE_CURSOR);
	private Cursor cursorNormal = new Cursor(Cursor.DEFAULT_CURSOR);
	
	public DiagramaController(Dashboard dashboard) {
		this.dashboard = dashboard;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Diagrama diagrama = dashboard.getDiagram();
		if(diagrama == null) return;
		
		
		// TODO: Fazer o mover e ajustar model Case
		if(diagrama.getAll())
			
		
		
		/*
			if(currentDiagram == null) return;
			
			Figure layer = null;
			int tipo = -1;
			
			if(tipo >= 0){
				addItem(e.getPoint(),tipo);
			} else if(layer != null){
				if(dashboard.getSidebar().getItem() != layer)
					dashboard.getSidebar().editItem(layer);
			} else {
				dashboard.getSidebar().clearItem();
				buttonGroup.clearSelection();
			}
			*/
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			setCursor(cursorNormal);
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			Element item = dashboard.getSidebar().getItem();
			if(item == null || !item.contains(e.getPoint())) return;
			setCursor(cursorMove);
			
			screenX = e.getXOnScreen();
	        screenY = e.getYOnScreen();

	        myX = item.getX();
	        myY = item.getY();
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			Element item = dashboard.getSidebar().getItem();
			if(item == null || !item.contains(e.getPoint())) return;
			
			int deltaX = e.getXOnScreen() - screenX;
	        int deltaY = e.getYOnScreen() - screenY;
	        
	        dashboard.getSidebar().updateItem();
	        item.setLocation(myX + deltaX, myY + deltaY);
		}
		
	}
	
	
}
