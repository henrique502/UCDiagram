package br.com.hrdev.ucdiagram.models.figures;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;

public class Case extends Figure {

	private static final long serialVersionUID = 1L;
	private static final Dimension Padding = new Dimension(20, 10);
	public static final Dimension Size = new Dimension(150, 60);
	
	public Case(String nome, Graphics graphics, Point point){
		super((Graphics2D) graphics);
		setLocation(point);
		setNome(nome);
	}
	
	@Override
	public void setNome(String nome) {
		int width = fm.stringWidth(nome) + (Padding.width * 2);
		int height = fm.getHeight() + (Padding.height * 2);
		
		super.setSize(new Dimension(width,height));
		super.setNome(nome);
	}

	@Override
	public void paint(Graphics2D g) {
		paintEllipse(g);
		paintText(g);
		paintSelectedBorder(g);
	}

	private void paintText(Graphics2D g) {
		g.setFont(getFont());
	    g.setColor(Color.black);
	    
	    int x = (getWidth() - fm.stringWidth(nome)) / 2;
	    int y = (fm.getAscent() + (getHeight() - (fm.getAscent() + fm.getDescent())) / 2);
		g.drawString(this.nome, getX() + x,getY() + y);
	}

	private void paintEllipse(Graphics2D g) {
		Ellipse2D elilpse = new Ellipse2D.Double(getX(), getY(), getWidth(), getHeight());
		
		g.setColor(new Color(122,188,255));
		g.fill(elilpse);
		
		g.setColor(Color.black);
		g.draw(elilpse);
	}
}
