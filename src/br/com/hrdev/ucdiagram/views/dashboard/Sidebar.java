package br.com.hrdev.ucdiagram.views.dashboard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;

import br.com.hrdev.ucdiagram.components.UITree;
import br.com.hrdev.ucdiagram.components.UITreeCellRenderer;
import br.com.hrdev.ucdiagram.controllers.SidebarInfoController;
import br.com.hrdev.ucdiagram.events.AdicionarDiagramaEvent;
import br.com.hrdev.ucdiagram.models.Diagrama;
import br.com.hrdev.ucdiagram.models.Element;
import br.com.hrdev.ucdiagram.models.Projeto;
import br.com.hrdev.ucdiagram.models.figures.Figure;
import br.com.hrdev.ucdiagram.utils.Icons;
import br.com.hrdev.ucdiagram.utils.Text;
import br.com.hrdev.ucdiagram.views.Dashboard;


public class Sidebar extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private Dashboard dashboard;
	private UITree tree;
	
	private JTextField infoText = null;
	private JTextField infoX = null;
	private JTextField infoY = null;
	private JButton infoSalvar = null;
	private JButton infoCancelar = null;
	private Figure infoItem = null;

	public Sidebar(Dashboard dashboard){
		super(new BorderLayout(0,5));
		setSize(new Dimension(150,getHeight()));
		setMaximumSize(getSize());
		setMaximumSize(getSize());
		this.dashboard = dashboard;
		setup();
	}
	
	private void setup() {
		setTreePanel();
		setInfoPanel();
	}
	
	private void setInfoPanel(){
		JPanel panel = new JPanel(new GridLayout(4, 1, 0, 5));
		
		infoText = new JTextField(10);
		infoX = new JTextField("0",10);
		infoY = new JTextField("0",10);
		
		panel.add(infoText);
		
		JPanel xy = new JPanel(new GridLayout(1, 2, 5, 0));
		infoX.setEnabled(false);
		infoY.setEnabled(false);
		xy.add(infoX);
		xy.add(infoY);
		panel.add(xy);
		
		infoSalvar = new JButton(Text.key(SidebarInfoController.OK), Icons.Accept);
		infoSalvar.setName(SidebarInfoController.OK);
		infoSalvar.addActionListener(new SidebarInfoController(dashboard));
		panel.add(infoSalvar);
		
		infoCancelar = new JButton(Text.key(SidebarInfoController.CANCEL), Icons.Delete);
		infoCancelar.setName(SidebarInfoController.CANCEL);
		infoCancelar.addActionListener(new SidebarInfoController(dashboard));
		panel.add(infoCancelar);

		panel.setSize(getSize().width, panel.getHeight());
		add(panel,BorderLayout.SOUTH);
	}

	private void setTreePanel(){
		tree = new UITree(dashboard);
		tree.setBorder(new EmptyBorder(4, 4, 4, 4));
		tree.setCellRenderer(new UITreeCellRenderer());
		
		JScrollPane scrollPane = new JScrollPane(tree);
		scrollPane.setBorder(BorderFactory.createLineBorder(Color.gray));
		scrollPane.setBackground(Color.white);
		scrollPane.setPreferredSize(new Dimension(180, getHeight()));
		
		add(scrollPane,BorderLayout.CENTER);
		
		JButton addDiagram = new JButton(Icons.Add);
		addDiagram.setText(Text.key("dashboard_sidebar_add_diagrama"));
		addDiagram.addActionListener(new AdicionarDiagramaEvent(dashboard));
		add(addDiagram,BorderLayout.NORTH);
	}
	
	private void sortTreeElements(ArrayList<Element> list){
		Collections.sort(list, new Comparator<Element>() {
			@Override
			public int compare(Element e1, Element e2) {
				return (e1.getClass().getCanonicalName().compareTo(e2.getClass().getCanonicalName()));
			}
		});
	}
	
	public Figure getItem() {
		return infoItem;
	}
	
	public void clearItem(){
		infoItem = null;
	}
	
	public void updateItem() {
		infoX.setText(infoItem.getX() + "");
		infoY.setText(infoItem.getY() + "");
	}
	
	public void clear(){
		if(infoItem != null){
			infoItem.setSelected(false);
		}
		
		infoText.setText("");
		infoX.setText("0");
		infoY.setText("0");
		infoItem = null;
		setInfoEnabled(false);
	}
	
	public void setItem(Figure i){
		clear();
		
		infoItem = i;
		infoItem.setSelected(true);
		infoText.setText(infoItem.getNome());
		setInfoEnabled(true);
		infoX.setText(infoItem.getX() + "");
		infoY.setText(infoItem.getY() + "");
	}
	
	public void setInfoEnabled(boolean enabled) {
		infoText.setEnabled(enabled);
		infoSalvar.setEnabled(enabled);
		infoCancelar.setEnabled(enabled);
	}

	public String getInfoText() {
		return infoText.getText();
	}

	public void updateDataTree() {
		Projeto projeto = dashboard.getWindow().getProjeto();
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(projeto);
		
		DefaultMutableTreeNode diagramas = new DefaultMutableTreeNode(Text.key("dashboard_sidebar_tree_diagramas"));
		for (Diagrama diagrama : projeto.getDiagramas()) {
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(diagrama);
			
			ArrayList<Element> list = diagrama.getAll();
			sortTreeElements(list);
			
			for(Element elemento : list)
				if(elemento instanceof Figure)
					node.add(new DefaultMutableTreeNode(elemento));
				
			diagramas.add(node);
		}

		rootNode.add(diagramas);
		
		tree.updateAll(rootNode);
		tree.expandRow(0);
	}
}
