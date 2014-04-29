package br.com.hrdev.ucdiagram.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CloseEvent implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}

}
