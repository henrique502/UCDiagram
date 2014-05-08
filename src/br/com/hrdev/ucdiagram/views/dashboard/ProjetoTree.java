package br.com.hrdev.ucdiagram.views.dashboard;

import java.awt.Component;
import java.util.Enumeration;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import br.com.hrdev.ucdiagram.controllers.TreeMenuController;
import br.com.hrdev.ucdiagram.models.Diagrama;
import br.com.hrdev.ucdiagram.models.Projeto;
import br.com.hrdev.ucdiagram.models.figures.Actor;
import br.com.hrdev.ucdiagram.models.figures.Case;
import br.com.hrdev.ucdiagram.utils.Icons;
import br.com.hrdev.ucdiagram.views.Dashboard;

public class ProjetoTree extends JTree {

	private static final long serialVersionUID = 1L;
	private TreeMenuController controller;

	public ProjetoTree(Dashboard dashboard){
		super(new DefaultMutableTreeNode());
		controller = new TreeMenuController(dashboard,this);
		setDragEnabled(false);
		getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		setFocusable(true);
		setCellRenderer(new UITreeCellRenderer());
		addMouseListener(controller);
		addMouseMotionListener(controller);
		addTreeSelectionListener(controller);
	}
	
	public void reload() {
		DefaultTreeModel model = (DefaultTreeModel) getModel();
		model.reload();
		expandAll();
	}
	
	public void updateAll(DefaultMutableTreeNode rootNode) {
		DefaultTreeModel model = (DefaultTreeModel) getModel();
		model.setRoot(rootNode);
		expandAll();
	}
	
	public void expandAll(){
		TreeNode root = (TreeNode) getModel().getRoot();
		expandAll(this, new TreePath(root));
	}
	
	@SuppressWarnings("rawtypes")
	private void expandAll(JTree tree, TreePath parent) {
		TreeNode node = (TreeNode) parent.getLastPathComponent();
		if (node.getChildCount() >= 0) {
			for (Enumeration e = node.children(); e.hasMoreElements();) {
				TreeNode n = (TreeNode) e.nextElement();
		        TreePath path = parent.pathByAddingChild(n);
		        expandAll(tree, path);
			}
		}
		tree.expandPath(parent);
	}
	
	private class UITreeCellRenderer extends DefaultTreeCellRenderer implements TreeCellRenderer {

		private static final long serialVersionUID = 1L;
		private DefaultTreeCellRenderer diagramaIconRenderer, atorIconRenderer,
										caseIconRenderer, projetoIconRenderer,
										padraoIconRenderer;
		
		public UITreeCellRenderer(){
			super();
			
			diagramaIconRenderer = new DefaultTreeCellRenderer();
			diagramaIconRenderer.setLeafIcon(Icons.Diagrama);
			diagramaIconRenderer.setOpenIcon(Icons.Diagrama);
			diagramaIconRenderer.setClosedIcon(Icons.Diagrama);
	        
			atorIconRenderer = new DefaultTreeCellRenderer();
			atorIconRenderer.setLeafIcon(Icons.Ator);
			atorIconRenderer.setOpenIcon(null);
			atorIconRenderer.setClosedIcon(null);
			
			caseIconRenderer = new DefaultTreeCellRenderer();
			caseIconRenderer.setLeafIcon(Icons.Caso);
			caseIconRenderer.setOpenIcon(null);
			caseIconRenderer.setClosedIcon(null);
			
			projetoIconRenderer = new DefaultTreeCellRenderer();
			projetoIconRenderer.setLeafIcon(null);
			projetoIconRenderer.setOpenIcon(Icons.Projeto);
			projetoIconRenderer.setClosedIcon(Icons.Projeto);
			
			padraoIconRenderer = new DefaultTreeCellRenderer();
			padraoIconRenderer.setLeafIcon(Icons.Folder_close);
			padraoIconRenderer.setOpenIcon(Icons.Folder_open);
			padraoIconRenderer.setClosedIcon(Icons.Folder_close);
	         
		}
		
		public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
			Object objeto = node.getUserObject();
			
			if(objeto instanceof Projeto)
				return projetoIconRenderer.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
			
			if(objeto instanceof Diagrama)
				return diagramaIconRenderer.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
			
			if(objeto instanceof Actor)
				return atorIconRenderer.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
			
			if(objeto instanceof Case)
				return caseIconRenderer.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);

			return padraoIconRenderer.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
		}
	}
}
