package br.com.hrdev.ucdiagram.views;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.Box;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import br.com.hrdev.ucdiagram.UCDiagram;
import br.com.hrdev.ucdiagram.events.AjudaAction;
import br.com.hrdev.ucdiagram.utils.Icons;

public abstract class View extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private UCDiagram window = null;
	private JMenuBar menubar = null;
	
	public View(UCDiagram window){
		this.window = window;
		this.menubar = window.getJMenuBar();
		setUpdatePanel();
	}
	
	protected abstract void updateUIMenu(JMenuBar menubar);
	
	public abstract void updateUIContents();
	public abstract void updateAll();
	
	private void setUpdatePanel(){
		addComponentListener(new ComponentAdapter(){
	        @Override
			public void componentShown(ComponentEvent e){
	        	updateUIMenu();
	        	updateUIContents();
	        }

	        @Override
			public void componentHidden(ComponentEvent e){}
	    });
	}
	
	public UCDiagram getWindow(){
		return window;
	}
	
	private void updateUIMenu(){
		menubar.removeAll();
		updateUIMenu(menubar);
		
		JMenu ajuda = new JMenu();
		ajuda.setIcon(Icons.Help);
		ajuda.setFocusable(false);
		ajuda.addMenuListener(new AjudaAction(this));
		menubar.add(Box.createHorizontalGlue());
		menubar.add(ajuda);
		
		menubar.revalidate();
	}
	
	@Override
	public String toString() {
		Class<?> enclosingClass = getClass().getEnclosingClass();
		if(enclosingClass != null) {
			return enclosingClass.getName();
		} else {
			return getClass().getName();
		}
	}
	
}
