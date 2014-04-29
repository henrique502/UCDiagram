package br.com.hrdev.ucdiagram;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Run implements Runnable {
	
	@Override
	public void run() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new UCDiagram();
		} catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro Fatal", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Run());
	}
}
