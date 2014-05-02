package br.com.hrdev.ucdiagram.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import br.com.hrdev.ucdiagram.utils.Text;
import br.com.hrdev.ucdiagram.views.View;

public class AjudaAction implements ActionListener, MenuListener {

	private View view;
	
	public AjudaAction(View view){
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
	public void menuCanceled(MenuEvent e) {}

	@Override
	public void menuDeselected(MenuEvent e) {}

	@Override
	public void menuSelected(MenuEvent e) {
		openText();
	}

}
