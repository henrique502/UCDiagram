package br.com.hrdev.ucdiagram.models;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.RoundRectangle2D;
import java.io.Serializable;

import br.com.hrdev.ucdiagram.utils.Images;

public class Ator extends ComponentItem implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Dimension Padding = new Dimension(2, 2);
	private static final Dimension Image = new Dimension(39, 100);
	
	private Point fontPoint = null;
	
	public Ator(String nome) {
		setFont(new Font("Arial", Font.PLAIN, 14));
		setNome(nome);
	}
	
	@Override
	public void setNome(String nome) {
		FontMetrics fm = getFontMetrics(getFont());
		int width = fm.stringWidth(nome) + (Padding.width * 2);
		int height = fm.getHeight() + Image.height + (Padding.height * 2);
		
		if(width < Image.width)
			width = Image.width + (Padding .width * 2);

		setSize(new Dimension(width,height));
		setPreferredSize(getSize());

		int x = (getWidth() - fm.stringWidth(nome)) / 2;
	    int y = (fm.getAscent() + (getHeight() - (fm.getAscent() + fm.getDescent())) / 2);
	    this.fontPoint  = new Point(x,y);
	    
		super.setNome(nome);
	}

	public void setPoint(Point point) {
		setBounds(point.x, point.y, getWidth(), getHeight());
	}

	@Override
	public void paint(Graphics graphics) {
		super.paint(graphics);
		Graphics2D g = (Graphics2D) graphics;
		
		Image image = Images.Ator.getImage();
		Point imgPoint = getCenterImagePoint(image);
		g.drawImage(image,imgPoint.x,imgPoint.y,null);
		
		g.setFont(getFont());
		g.drawString(this.nome, fontPoint.x, imgPoint.y + Image.height + 14);
		
		if(hasSelected){
			float[] dash = {5.0f};
			g.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f));
			g.setColor(Color.black);
			g.draw(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, 7, 7));
		}
		
		g.dispose();
	}
	
	private Point getCenterImagePoint(Image image){
		int x = (this.getWidth() - image.getWidth(null)) / 2;
	    int y = Padding.height;
	    return new Point(x,y);
	}
}
