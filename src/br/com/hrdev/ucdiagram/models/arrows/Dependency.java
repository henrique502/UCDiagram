package br.com.hrdev.ucdiagram.models.arrows;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

public class Dependency extends Arrow {

	private static final long serialVersionUID = 1L;
	private final int ARR_SIZE = 10;
	private float[] dash = {5.0f};
	
	public Dependency(){
		super("dependency");
	}

	@Override
	public void paint(Graphics2D g) {
		Rectangle s = start.getRectangle();
		Rectangle e = end.getRectangle();
		g.setColor(Color.black);
		g.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f));
		
		double x1 = s.getCenterX(), y1 = s.getCenterY();
		double x2 = e.getCenterX(), y2 = e.getCenterY();
		
		if(start.containX(x2)){
			y2 = (y1 > y2) ? y2 + (e.height * 0.5f) : y2 - (e.height * 0.5f);
			x2 = e.getCenterX();
		} else {
			y2 = e.getCenterY();
			x2 = (x1 > x2) ? x2 + (e.width * 0.5f) : x2 - (e.width * 0.5f);
		}
		
		drawArrow(g, x1, y1, x2, y2);
	}
	
	private void drawArrow(Graphics2D g, double x1, double y1, double x2, double y2) {
        double dx = x2 - x1, dy = y2 - y1;
        double angle = Math.atan2(dy, dx);
        int len = (int) Math.sqrt(dx*dx + dy*dy);
        AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
        at.concatenate(AffineTransform.getRotateInstance(angle));
        g.transform(at);

        g.drawLine(0, 0, len, 0);
        g.drawLine(len, 0, len - ARR_SIZE, ARR_SIZE);
        g.drawLine(len, 0, len - ARR_SIZE, -ARR_SIZE);
    }

}
