package br.com.hrdev.ucdiagram.views.dashboard;

import java.awt.Cursor;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import br.com.hrdev.ucdiagram.controllers.Controller;
import br.com.hrdev.ucdiagram.controllers.ToolbarArrowController;
import br.com.hrdev.ucdiagram.controllers.ToolbarCursorController;
import br.com.hrdev.ucdiagram.controllers.ToolbarFigureController;
import br.com.hrdev.ucdiagram.models.Diagrama;
import br.com.hrdev.ucdiagram.utils.Icons;
import br.com.hrdev.ucdiagram.utils.Text;
import br.com.hrdev.ucdiagram.views.Dashboard;

public class Toolbar extends JToolBar {

	private static final long serialVersionUID = 1L;
	private Dashboard dashboard;
	private ArrayList<JToggleButton> toolbarButtons;
	private ButtonGroup buttonGroup;
	private ToolbarCursorController cursorController;
	
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
		cursorController = new ToolbarCursorController(dashboard);
		
		createButton(CURSOR,Icons.Cursor,cursorController);
		addSeparator();
		createButton(ACTOR,Icons.Ator,new ToolbarFigureController(dashboard));
		createButton(CASE,Icons.Caso,new ToolbarFigureController(dashboard));
		addSeparator();
		createButton(DEPENDENCY,Icons.Dependency,new ToolbarArrowController(dashboard));
		createButton(ASSOCIATION,Icons.Association,new ToolbarArrowController(dashboard));
	}
	
	private void createButton(String label, ImageIcon icon, Controller controller){
		JToggleButton button = new JToggleButton("", icon);
		
		button.setName(label);
		button.addActionListener(controller);
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
	
	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		for(JToggleButton b : toolbarButtons)
			b.setEnabled(enabled);
	}
	
	public void reset(){
		Diagrama diagrama = dashboard.getDiagram();
		
		buttonGroup.clearSelection();
		toolbarButtons.get(0).setSelected(true);
		toolbarButtons.get(0).requestFocus();
		
		if(diagrama != null){
			diagrama.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			dashboard.removeDiagramaListiners(diagrama);
			cursorController.actionPerformed(null);
		} else {
			setEnabled(false);
		}
	}
	
}
