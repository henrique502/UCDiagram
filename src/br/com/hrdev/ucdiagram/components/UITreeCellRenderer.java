package br.com.hrdev.ucdiagram.components;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;

import br.com.hrdev.ucdiagram.models.Ator;
import br.com.hrdev.ucdiagram.models.Diagrama;
import br.com.hrdev.ucdiagram.models.Projeto;
import br.com.hrdev.ucdiagram.utils.Icons;

// https://community.oracle.com/thread/2075008?start=0&tstart=0

@SuppressWarnings("serial")
public class UITreeCellRenderer extends DefaultTreeCellRenderer implements TreeCellRenderer {
	
	private DefaultTreeCellRenderer diagramaIconRenderer, atorIconRenderer, projetoIconRenderer, padraoIconRenderer;
	
	public UITreeCellRenderer(){
		super();
		
		diagramaIconRenderer = new DefaultTreeCellRenderer();
		diagramaIconRenderer.setLeafIcon(Icons.Diagrama);
		diagramaIconRenderer.setOpenIcon(null);
		diagramaIconRenderer.setClosedIcon(null);
        
		atorIconRenderer = new DefaultTreeCellRenderer();
		atorIconRenderer.setLeafIcon(Icons.Ator);
		atorIconRenderer.setOpenIcon(null);
		atorIconRenderer.setClosedIcon(null);
		
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
		
		if(objeto instanceof Ator)
			return atorIconRenderer.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);

		return padraoIconRenderer.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
	}
}
