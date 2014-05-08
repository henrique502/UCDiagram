package br.com.hrdev.ucdiagram.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import br.com.hrdev.ucdiagram.models.Diagrama;
import br.com.hrdev.ucdiagram.models.figures.Figure;
import br.com.hrdev.ucdiagram.utils.Text;
import br.com.hrdev.ucdiagram.views.Dashboard;
import br.com.hrdev.ucdiagram.views.dashboard.ProjetoTree;

public class TreeMenuController extends Controller {

	private Dashboard dashboard;
	private ProjetoTree tree;
	private JPopupMenu menu;
	private boolean show;
	
	public TreeMenuController(Dashboard dashboard, ProjetoTree tree) {
		this.dashboard = dashboard;
		this.tree = tree;
		this.menu = new JPopupMenu();
		this.menu.setLightWeightPopupEnabled(true);
		this.show = false;
	}
	
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		if (tree.isSelectionEmpty()) return;
		TreePath path = tree.getSelectionPath();
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
		Object objeto = node.getUserObject();
		
		if(objeto instanceof Diagrama)
			dashboard.showDiagram((Diagrama) objeto);
		
		show = false;
		menu.removeAll();
		buildOption(objeto,node);		
		menu.revalidate();
	}
	
	private void buildOption(Object objeto, DefaultMutableTreeNode current) {
		if(current.isLeaf()){
			if(objeto instanceof Figure){
				DefaultMutableTreeNode parent = (DefaultMutableTreeNode) current.getParent();
				Diagrama diagrama = (Diagrama) parent.getUserObject();
			
				createFigureOptions((Figure) objeto, diagrama, current, parent);
			}
		} else {
			if(objeto instanceof Diagrama){
				Diagrama diagrama = (Diagrama) objeto;
				createDiagramaOptions(diagrama);
			}
			
		}
	}

	private void createDiagramaOptions(Diagrama diagrama) {
		show = true;
		
		JMenuItem remover = new JMenuItem(Text.key("uitree_remover") + " " + diagrama);
		TreeMenuRemoverDiagramaController removeController = new TreeMenuRemoverDiagramaController();
		removeController.setup(diagrama);
		remover.addActionListener(removeController);
		menu.add(remover);
		
		JMenuItem exportar = new JMenuItem(Text.key("uitree_exportar") + " " + diagrama);
		TreeMenuExportarDiagramaController exportController = new TreeMenuExportarDiagramaController();
		exportController.setup(diagrama);
		exportar.addActionListener(exportController);
		menu.add(exportar);
	}

	private void createFigureOptions(Figure figure, Diagrama diagrama, DefaultMutableTreeNode current, DefaultMutableTreeNode parent) {
		show = true;
		
		JMenuItem remover = new JMenuItem(Text.key("uitree_remover") + " " + figure);
		TreeMenuRemoverFigureController removeController = new TreeMenuRemoverFigureController();
		removeController.setup(figure,diagrama,current,parent);
		remover.addActionListener(removeController);
		menu.add(remover);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(show && SwingUtilities.isRightMouseButton(e)){
			menu.show(tree, e.getX(), e.getY());
		}
	}
	
	private class TreeMenuRemoverFigureController extends Controller {
		
		private Figure figure;
		private Diagrama diagrama;
		private DefaultMutableTreeNode current;
		private DefaultMutableTreeNode parent;

		@Override
		public void actionPerformed(ActionEvent e) {
			dashboard.getSidebar().clear();
			diagrama.remove(figure);
			diagrama.repaint();
			parent.remove(current);
			tree.reload();
		}

		public void setup(Figure figure, Diagrama diagrama, DefaultMutableTreeNode current, DefaultMutableTreeNode parent) {
			this.figure = figure;
			this.diagrama = diagrama;
			this.current = current;
			this.parent = parent;
			
		}
	}
	
	private class TreeMenuRemoverDiagramaController extends Controller {
		
		private Diagrama diagrama;
		
		public void setup(Diagrama diagrama) {
			this.diagrama = diagrama;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			dashboard.getWindow().getProjeto().removerDiagrama(diagrama);
			dashboard.updateAll();
		}
	}
	
	private class TreeMenuExportarDiagramaController extends Controller {
		
		private Diagrama diagrama;
		
		public void setup(Diagrama diagrama) {
			this.diagrama = diagrama;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			new ExportController(dashboard).export(diagrama);
		}
	}
}
