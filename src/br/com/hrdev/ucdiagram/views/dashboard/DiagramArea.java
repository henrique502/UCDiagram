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
import javax.swing.border.EmptyBorder;

import br.com.hrdev.ucdiagram.components.UIToolBarButton;
import br.com.hrdev.ucdiagram.models.Diagrama;
import br.com.hrdev.ucdiagram.models.Element;
import br.com.hrdev.ucdiagram.models.figures.Figure;
import br.com.hrdev.ucdiagram.views.Dashboard;

public class DiagramArea extends JPanel {

	private static final long serialVersionUID = 1L;
	private Dashboard dashboard;
	private JPanel diagramArea;
	private ArrayList<UIToolBarButton> toolbarButtons;
	private ButtonGroup buttonGroup;
	
	private Toolbar toolbar;

	
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
		setDiagramPanel();
		setToolBar();
	}
	
	private void setToolBar(){
		this.toolbar = new Toolbar(dashboard);
		add(this.toolbar,BorderLayout.NORTH);
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
	
	public void showDiagram(Diagrama diagrama){
		CardLayout card = (CardLayout) diagramArea.getLayout();
		if(diagrama == null){
			card.show(diagramArea, "blank");
			return;
		}
		
		if(currentDiagram != null){

		}
		
		currentDiagram = diagrama;
		
		card.show(diagramArea, currentDiagram.getNome());

		dashboard.repaint();
	}
	
	public Toolbar getToolbar(){
		return this.toolbar;
	}
	
	public Diagrama getCurrentDiagrama() {
		return this.currentDiagram;
	}
}
