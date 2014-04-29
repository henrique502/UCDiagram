package br.com.hrdev.ucdiagram.models;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;

import javax.swing.JComponent;

public abstract class ComponentItem extends JComponent {

	private static final long serialVersionUID = 1L;
	protected String nome;
	protected boolean hasSelected = false;
	
	public boolean contains(Point p) {
		if(p == null)
			return false;
		
		return contains(p.x, p.y);
	}
	
	public boolean contains(int x, int y) {
		Point location = getLocation();
		
		if(x <= location.x) return false;
		if(y <= location.y) return false;
		
		if(x >= getWidth() + location.x) return false;
		if(y >= getHeight() + location.y) return false;
		
		return true;
	}
	
	public void setNome(String nome){
		this.nome = nome;
	}
	
	public String getName() {
		return this.nome;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String toString(){
		return this.nome;
	}

	public void setSelected(boolean selected) {
		hasSelected = selected;
		repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		super.paint(g);
	}
}
