package br.com.hrdev.ucdiagram.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Enumeration;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import br.com.hrdev.ucdiagram.models.Diagrama;
import br.com.hrdev.ucdiagram.models.figures.Figure;
import br.com.hrdev.ucdiagram.views.Dashboard;

public class UITree extends JTree implements MouseListener, TreeSelectionListener {

	private static final long serialVersionUID = 1L;
	private JPopupMenu popup;
	private Dashboard dashboard;
	
	private boolean canOpenMenu = false;
	
	
	public UITree(Dashboard dashboard){
		super(new DefaultMutableTreeNode());
		this.dashboard = dashboard;
		setDragEnabled(true);
		getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		setFocusable(true);
		addMouseListener(this);
		addTreeSelectionListener(this);
		popup = new JPopupMenu();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(canOpenMenu && SwingUtilities.isRightMouseButton(e)){
			popup.show(this, e.getX(), e.getY());
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {}
	
	@Override
	public void mouseExited(MouseEvent e) {}
	
	@Override
	public void mousePressed(MouseEvent e) {}
	
	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		if (isSelectionEmpty()) return;
		TreePath path = getSelectionPath();
		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
		Object objeto = selectedNode.getUserObject();
		
		canOpenMenu = false;
		popup.removeAll();
		
		if(objeto instanceof Diagrama){
			diagramaOptions((Diagrama) objeto);
		} else {
			if(selectedNode.isLeaf()){
				if(objeto instanceof Figure){
					DefaultMutableTreeNode parent = (DefaultMutableTreeNode) selectedNode.getParent();
					Diagrama diagrama = (Diagrama) parent.getUserObject();
					figureOptions((Figure) objeto, diagrama, parent, selectedNode);
				}
			}
		}
		popup.revalidate();
	}
	
	private void figureOptions(final Figure figure, final Diagrama diagrama, final DefaultMutableTreeNode parent, final DefaultMutableTreeNode node){
		canOpenMenu = true;
		JMenuItem option = new JMenuItem("Remover " + figure);
		option.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dashboard.getSidebar().clear();
				diagrama.remove(figure);
				diagrama.repaint();
				parent.remove(node);
				reload();
			}

		});
		popup.add(option);
	}
	
	private void diagramaOptions(final Diagrama diagrama){
		dashboard.showDiagram(diagrama); 
		canOpenMenu = true;
		JMenuItem option = new JMenuItem("Remover diagrama " + diagrama);
		option.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dashboard.getWindow().getProjeto().removerDiagrama(diagrama);
				dashboard.updateAll();
			}
		});
		popup.add(option);
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
}
