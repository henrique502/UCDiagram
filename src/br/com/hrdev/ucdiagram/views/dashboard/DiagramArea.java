package br.com.hrdev.ucdiagram.views.dashboard;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.com.hrdev.ucdiagram.components.UIToolBarButton;
import br.com.hrdev.ucdiagram.models.Diagrama;
import br.com.hrdev.ucdiagram.models.Element;
import br.com.hrdev.ucdiagram.models.figures.Figure;
import br.com.hrdev.ucdiagram.utils.Icons;
import br.com.hrdev.ucdiagram.views.Dashboard;

public class DiagramArea extends JPanel {

	private static final long serialVersionUID = 1L;
	private Dashboard dashboard;
	private JPanel diagramArea;
	private ArrayList<UIToolBarButton> toolbarButtons;
	private ButtonGroup buttonGroup;
	private AddDiagramItem diagramaMouseAdapter = new AddDiagramItem();
	
	private Cursor cursorMove = new Cursor(Cursor.MOVE_CURSOR);
	private Cursor cursorNormal = new Cursor(Cursor.DEFAULT_CURSOR);
	
	/* Temp Vars */
	private Diagrama currentDiagram = null;
	
	public DiagramArea(Dashboard dashboard){
		super(new BorderLayout(0,0));
		this.dashboard = dashboard;
		setBackground(Color.white);
		setBorder(BorderFactory.createLineBorder(Color.gray));
		setup();
	}
	
	private void setup(){
		setToolBar();
		setDiagramPanel();
	}
	
	private void setToolBar(){
		Toolbar toolbar = new Toolbar(dashboard);
		add(toolbar,BorderLayout.NORTH);
	}
	
	private void setDiagramPanel(){
		diagramArea = new JPanel(new CardLayout());
		diagramArea.setBackground(Color.gray);
		diagramArea.setBorder(new EmptyBorder(15,15,15,15));
		JScrollPane diagramScrollPane = new JScrollPane(diagramArea);
		diagramScrollPane.setBorder(new EmptyBorder(0,0,0,0));

		add(diagramScrollPane,BorderLayout.CENTER);
	}

	public void updateDiagramAreaData(){
		diagramArea.removeAll();
		currentDiagram = null;
		
		JPanel blank = new JPanel();
		blank.setOpaque(false);
		diagramArea.add(blank,"blank");
		
		for(Diagrama diagrama : dashboard.getWindow().getProjeto().getDiagramas()){
			diagramArea.add(diagrama,diagrama.getNome());
		}
	}
	
	public void showDiagram(Diagrama diagrama) {
		if(diagrama == null) return;
		
		if(currentDiagram != null){
			currentDiagram.removeMouseListener(diagramaMouseAdapter);
			currentDiagram.removeMouseMotionListener(diagramaMouseAdapter);
		}
		
		currentDiagram = diagrama;
		currentDiagram.addMouseListener(diagramaMouseAdapter);
		currentDiagram.addMouseMotionListener(diagramaMouseAdapter);
		
		CardLayout card = (CardLayout) diagramArea.getLayout();
		card.show(diagramArea, currentDiagram.getNome());

		dashboard.repaint();
	}
	
	public int getSelectedToolbarButton(){
		for(UIToolBarButton button : toolbarButtons)
			if(button.isSelected())
				return button.getTipo();
		
		return -1;
	}
	
	private Figure getSelectedComponent(Point p){
		return null;
		/*
		Component[] layers = currentDiagram.getComponents();
		for (Component layer : layers)
			if(layer instanceof Element)
				if(layer.contains(p))
					return (Figure) layer;
		
		return null;
		*/
	}
	

	private class AddDiagramItem extends MouseAdapter {
		
		private volatile int screenX = 0;
		private volatile int screenY = 0;
		private volatile int myX = 0;
		private volatile int myY = 0;
		
		private void reset(int tipo){
			
			
			dashboard.getSidebar().updateDataTree();
			buttonGroup.clearSelection();
			dashboard.getSidebar().clearItem();
			dashboard.repaint();
		}
		
		private void addItem(Point point, int tipo){
			switch(tipo){
				// Cria novo ator
				case UIToolBarButton.Ator : 
					//Actor ator = new Actor("Ator " + dashboard.getWindow().getProjeto().getAtores().size());
					//ator.setPoint(point);
					//currentDiagram.add(ator);		
					//window.getProjeto().getAtores().add(ator);
					
				break;
				// Criar novo caso
				case UIToolBarButton.Caso :
					//String titulo = JOptionPane.showInputDialog("Digite o caso:");
					//if(titulo == null || titulo.trim().length() == 0) break;
					
					//Case caos = new Case(titulo);
					//caos.setPoint(point);
					//currentDiagram.add(caos);
				break;
			}
			
			reset(tipo);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if(currentDiagram == null) return;
			
			Figure layer = getSelectedComponent(e.getPoint());
			int tipo = getSelectedToolbarButton();
			
			if(tipo >= 0){
				addItem(e.getPoint(),tipo);
			} else if(layer != null){
				if(dashboard.getSidebar().getItem() != layer)
					dashboard.getSidebar().editItem(layer);
			} else {
				dashboard.getSidebar().clearItem();
				buttonGroup.clearSelection();
			}
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
