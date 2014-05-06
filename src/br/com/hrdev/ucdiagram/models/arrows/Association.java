package br.com.hrdev.ucdiagram.models.arrows;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Line2D;

public class Association extends Arrow {

	private static final long serialVersionUID = 1L;
	
	public Association(){
		super("association");
	}

	@Override
	public void paint(Graphics2D g) {
		Rectangle s = start.getRectangle();
		Rectangle e = end.getRectangle();
		
		g.setColor(Color.black);
		g.draw(new Line2D.Double(s.getCenterX(), s.getCenterY(), e.getCenterX(), e.getCenterY()));
	}
}
