package br.com.hrdev.ucdiagram.views.dashboard;

import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import br.com.hrdev.ucdiagram.utils.Icons;
import br.com.hrdev.ucdiagram.utils.Text;
import br.com.hrdev.ucdiagram.views.Dashboard;

public class Toolbar extends JToolBar {

	private static final long serialVersionUID = 1L;
	private Dashboard dashboard;
	private ArrayList<JToggleButton> toolbarButtons;
	private ButtonGroup buttonGroup;
	
	public Toolbar(Dashboard dashboard) {
		super(SwingConstants.HORIZONTAL);
		setFloatable(false);
		this.dashboard = dashboard;
		setup();
	}
	
	private void setup(){
		buttonGroup = new ButtonGroup();
		toolbarButtons = new ArrayList<JToggleButton>();
		
		addSeparator();
		createButton("dashboard_toolbar_actor",Icons.Ator);
		createButton("dashboard_toolbar_case",Icons.Caso);
		addSeparator();
		createButton("dashboard_toolbar_dependency",Icons.Dependency);
		createButton("dashboard_toolbar_association",Icons.Association);
	}
	
	private void createButton(String label, ImageIcon icon){
		JToggleButton button = new JToggleButton("", icon);
		button.setName(label);
		button.setToolTipText(Text.key(label));
		buttonGroup.add(button);
		toolbarButtons.add(button);
		add(button);
	}
	
}
