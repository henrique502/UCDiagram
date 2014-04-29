package br.com.hrdev.ucdiagram.models;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.io.Serializable;

public class Caso extends ComponentItem implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Dimension Padding = new Dimension(20, 20);
	private Point fontPoint;
	public static final Dimension Size = new Dimension(150, 60);
	
	// http://docs.oracle.com/javase/tutorial/2d/geometry/strokeandfill.html
	
	public Caso(String nome) {
		setFont(new Font("Arial", Font.PLAIN, 14));
		setNome(nome);
		setFocusable(true);
	}
	
	@Override
	public void setNome(String nome) {
		FontMetrics fm = getFontMetrics(getFont());
		int width = fm.stringWidth(nome) + (Padding.width * 2);
		int height = fm.getHeight() + (Padding.height * 2);
		
		setSize(new Dimension(width,height));
		setPreferredSize(getSize());

		int x = (getWidth() - fm.stringWidth(nome)) / 2;
	    int y = (fm.getAscent() + (getHeight() - (fm.getAscent() + fm.getDescent())) / 2);
	    this.fontPoint = new Point(x,y);
	    
		super.setNome(nome);
	}
	
	public void setPoint(Point point) {
		setBounds(point.x, point.y, getWidth(), getHeight());
	}

	@Override
	public void paint(Graphics graphics) {
		super.paint(graphics);
		Graphics2D g = (Graphics2D) graphics;

		GradientPaint redtowhite = new GradientPaint(0,0, new Color(122,188,255), (getWidth() * 0.5f), 0, new Color(64,150,238));
		g.setPaint(redtowhite);
		g.fill(new Ellipse2D.Double(0, 0, getWidth(), getHeight()));
		
		g.setColor(Color.black);
		g.drawOval(0, 0, getWidth() - 1, getHeight() - 1);

		g.setFont(getFont());
	    g.setColor(Color.black);
		g.drawString(this.nome, fontPoint.x, fontPoint.y);
		
		if(hasSelected){
			float[] dash = {5.0f};
			g.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f));
			g.setColor(Color.black);
			g.draw(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, 7, 7));
		}
		
		g.dispose();
	}
}
