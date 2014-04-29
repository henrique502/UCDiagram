package br.com.hrdev.ucdiagram.components;

import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

@SuppressWarnings("serial")
public class UIToolBarButton extends JToggleButton {
	
	
	
	public static final int Ator = 1;
	public static final int Caso = 2;
	public static final int Pointer = 0;
	
	private int tipo;
	
	public UIToolBarButton(ImageIcon icon, String tooltip,int tipo){
		super(icon);
		setToolTipText(tooltip);
		setFocusable(false);
		this.tipo = tipo;
	}

	public int getTipo() {
		return tipo;
	}
}
