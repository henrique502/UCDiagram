package br.com.hrdev.ucdiagram.components;

import javax.swing.Box;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import br.com.hrdev.ucdiagram.UCDiagram;
import br.com.hrdev.ucdiagram.events.AjudaAction;
import br.com.hrdev.ucdiagram.utils.Icons;

@SuppressWarnings("serial")
public class UIMenuBar extends JMenuBar {
	
	private UCDiagram window;
	
	public UIMenuBar(UCDiagram window){
		super();
	}
	
	public void setHelp(){
		JMenu ajuda = new JMenu();
		ajuda.setIcon(Icons.Help);
		ajuda.addMenuListener(new AjudaAction(window));
		add(Box.createHorizontalGlue());
		add(ajuda);
	}
	
	
}
