package br.com.hrdev.ucdiagram.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;

import br.com.hrdev.ucdiagram.UCDiagram;
import br.com.hrdev.ucdiagram.events.AdicionarDiagramaEvent;
import br.com.hrdev.ucdiagram.models.Diagrama;
import br.com.hrdev.ucdiagram.models.Element;
import br.com.hrdev.ucdiagram.models.Projeto;
import br.com.hrdev.ucdiagram.models.figures.Actor;
import br.com.hrdev.ucdiagram.models.figures.Figure;
import br.com.hrdev.ucdiagram.utils.Icons;
import br.com.hrdev.ucdiagram.views.DashboardView;


public class UIDashboardSidebar extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private UCDiagram window;
	private DashboardView dashboard;
	private UIDashboardSidebarEditableArea editableArea;
	private UITree tree;

	public UIDashboardSidebar(UCDiagram window, DashboardView dashboard){
		super(new BorderLayout(0,5));
		this.window = window;
		this.dashboard = dashboard;
		setup();
	}
	
	private void setup() {
		setTree();
		setEditableArea();
	}

	private void setTree(){
		tree = new UITree(dashboard);
		tree.setBorder(new EmptyBorder(4, 4, 4, 4));
		tree.setCellRenderer(new UITreeCellRenderer());
		
		JScrollPane scrollPane = new JScrollPane(tree);
		scrollPane.setBorder(BorderFactory.createLineBorder(Color.gray));
		scrollPane.setBackground(Color.white);
		scrollPane.setPreferredSize(new Dimension(180, getHeight()));
		
		add(scrollPane,BorderLayout.CENTER);
		
		JButton addDiagram = new JButton(Icons.Add);
		addDiagram.setText("Novo Diagrama");
		addDiagram.addActionListener(new AdicionarDiagramaEvent(dashboard));
		add(addDiagram,BorderLayout.NORTH);
	}
	
	private void setEditableArea(){
		editableArea = new UIDashboardSidebarEditableArea(this);
		editableArea.setSize(getWidth(), editableArea.getHeight());
		editableArea.setEnabled(false);
		add(editableArea,BorderLayout.SOUTH);
	}
	
	public void editItem(Figure item){
		editableArea.setItem(item);
	}
	
	public Element getItem() {
		return editableArea.getItem();
	}
	
	public void clearItem(){
		editableArea.clear();
	}
	
	public void updateItem() {
		editableArea.updateItem();
	}
	
	public void updateDataTree() {
		Projeto projeto = window.getProjeto();
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(projeto);
		
		DefaultMutableTreeNode diagramas = new DefaultMutableTreeNode("Diagramas");
		for (Diagrama diagrama : projeto.getDiagramas()) {
			diagramas.add(new DefaultMutableTreeNode(diagrama));
		}
		
		
		DefaultMutableTreeNode atores = new DefaultMutableTreeNode("Atores");
		for (Actor ator : projeto.getAtores()) {
			atores.add(new DefaultMutableTreeNode(ator));
		}

		rootNode.add(diagramas);
		rootNode.add(atores);
		
		tree.updateAll(rootNode);
		tree.expandRow(0);
	}	
}
