package br.com.hrdev.ucdiagram.models.figures;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import br.com.hrdev.ucdiagram.models.Element;

public abstract class Figure extends Element {

	private static final long serialVersionUID = 1L;
	
	protected FontMetrics fm = null;
	
	public Figure(Graphics2D g){
		super();
		setFont(new Font("Arial", Font.PLAIN, 14));
		this.fm = g.getFontMetrics(getFont());
	}
}
