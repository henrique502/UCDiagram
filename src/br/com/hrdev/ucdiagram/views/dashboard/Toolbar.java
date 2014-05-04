package br.com.hrdev.ucdiagram.views.dashboard;

import java.awt.Cursor;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import br.com.hrdev.ucdiagram.controllers.ToolbarController;
import br.com.hrdev.ucdiagram.models.Diagrama;
import br.com.hrdev.ucdiagram.utils.ComponentsUtil;
import br.com.hrdev.ucdiagram.utils.Icons;
import br.com.hrdev.ucdiagram.utils.Text;
import br.com.hrdev.ucdiagram.views.Dashboard;

public class Toolbar extends JToolBar {

	private static final long serialVersionUID = 1L;
	private Dashboard dashboard;
	private ArrayList<JToggleButton> toolbarButtons;
	private ButtonGroup buttonGroup;
	
	public static final String CURSOR = "dashboard_toolbar_cursor";
	public static final String ACTOR = "dashboard_toolbar_actor";
	public static final String CASE = "dashboard_toolbar_case";
	public static final String DEPENDENCY = "dashboard_toolbar_dependency";
	public static final String ASSOCIATION = "dashboard_toolbar_association";
	
	public Toolbar(Dashboard dashboard) {
		super(SwingConstants.HORIZONTAL);
		setFloatable(false);
		this.dashboard = dashboard;
		setup();
	}
	
	private void setup(){
		buttonGroup = new ButtonGroup();
		toolbarButtons = new ArrayList<JToggleButton>();
		
		createButton(CURSOR,Icons.Cursor);
		addSeparator();
		createButton(ACTOR,Icons.Ator);
		createButton(CASE,Icons.Caso);
		addSeparator();
		createButton(DEPENDENCY,Icons.Dependency);
		createButton(ASSOCIATION,Icons.Association);
	}
	
	private void createButton(String label, ImageIcon icon){
		JToggleButton button = new JToggleButton("", icon);
		if(label.equals(CURSOR))
			button.setSelected(true);
		
		button.setName(label);
		button.addActionListener(new ToolbarController(dashboard));
		button.setToolTipText(Text.key(label));
		buttonGroup.add(button);
		toolbarButtons.add(button);
		add(button);
	}
	
	public String getCurrent(){
		for(JToggleButton button : toolbarButtons)
			if(button.isSelected())
				return button.getName();
		
		return null;
	}
	
	public void reset(){
		Diagrama diagrama = dashboard.getDiagram();
		
		if(diagrama != null){
			diagrama.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			ComponentsUtil.clearMouseListeners(diagrama);
			ComponentsUtil.clearMouseMotionListeners(diagrama);
		}
		
		for(JToggleButton button : toolbarButtons){
			if(button.equals(CURSOR)){
				button.setSelected(true);
			} else {
				button.setSelected(false);
			}
		}
	}
	
}
