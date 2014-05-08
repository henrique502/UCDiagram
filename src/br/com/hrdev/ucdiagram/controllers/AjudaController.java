package br.com.hrdev.ucdiagram.controllers;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.event.MenuEvent;

import br.com.hrdev.ucdiagram.utils.Text;
import br.com.hrdev.ucdiagram.views.View;

public class AjudaController extends Controller {

	private View view;
	
	public AjudaController(View view){
		this.view = view;
	}
	
	private void openText(){
		JOptionPane.showMessageDialog(view.getWindow(), Text.key("ajuda_texto"), Text.key("ajuda"), JOptionPane.PLAIN_MESSAGE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		openText();
	}

	@Override
	public void menuSelected(MenuEvent e) {
		openText();
	}

}
