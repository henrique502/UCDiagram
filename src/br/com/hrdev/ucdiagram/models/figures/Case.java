package br.com.hrdev.ucdiagram.models.figures;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;

public class Case extends Figure implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Dimension Padding = new Dimension(20, 20);
	private Point fontPoint;
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
		
		setSize(new Dimension(width,height));

		int x = (getWidth() - fm.stringWidth(nome)) / 2;
	    int y = (fm.getAscent() + (getHeight() - (fm.getAscent() + fm.getDescent())) / 2);
	    this.fontPoint = new Point(getX() + x,getY() + y);
	    
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
		g.drawString(this.nome, fontPoint.x, fontPoint.y);
	}

	private void paintEllipse(Graphics2D g) {
		GradientPaint redtowhite = new GradientPaint(0,0, new Color(122,188,255), (getWidth() * 0.5f), 0, new Color(64,150,238));
		g.setPaint(redtowhite);
		g.fill(new Ellipse2D.Double(getX(), getY(), getX() + getWidth(), getY() + getHeight()));
		
		g.setColor(Color.black);
		g.drawOval(getX(), getY(), getX() + getWidth() - 1, getY() + getHeight() - 1);
	}
}
