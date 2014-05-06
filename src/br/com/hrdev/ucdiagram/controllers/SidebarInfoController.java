package br.com.hrdev.ucdiagram.controllers;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

import br.com.hrdev.ucdiagram.views.Dashboard;

public class SidebarInfoController extends Controller {
	
	public static final String OK = "dashboard_sidebar_info_salvar";
	public static final String CANCEL = "dashboard_sidebar_info_cancelar";
	
	private Dashboard dashboard;
	
	public SidebarInfoController(Dashboard dashboard) {
		this.dashboard = dashboard;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		if(button.getName().equals(OK)){
			String text = dashboard.getSidebar().getInfoText();
			if(text == null || text.trim().equals("")) return;
			
			dashboard.getSidebar().getItem().setNome(text);
			dashboard.getSidebar().updateDataTree();
		}
		dashboard.getSidebar().clear();
	}
}
