package br.com.hrdev.ucdiagram.models.figures;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.io.Serializable;

import br.com.hrdev.ucdiagram.utils.Images;

public class Actor extends Figure implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Dimension Padding = new Dimension(2, 2);
	private static final Dimension Image = new Dimension(39, 100);
	
	private Point fontPoint = null;
	
	public Actor(String nome, Graphics2D g){
		super(g);
		setNome(nome);
	}
	
	@Override
	public void setNome(String nome) {
		int width = fm.stringWidth(nome) + (Padding.width * 2);
		int height = fm.getHeight() + Image.height + (Padding.height * 2);
		
		if(width < Image.width)
			width = Image.width + (Padding .width * 2);

		setSize(new Dimension(width,height));

		int x = (getWidth() - fm.stringWidth(nome)) / 2;
	    int y = (fm.getAscent() + (getHeight() - (fm.getAscent() + fm.getDescent())) / 2);
	    this.fontPoint  = new Point(getX() + x,getY() + y);
	    
		super.setNome(nome);
	}

	public void paint(Graphics2D g){
		paintImage(g);
		paintSelectedBorder(g);
	}
	
	private void paintImage(Graphics2D g) {
		Image image = Images.Ator.getImage();
		
		int x = (this.getWidth() - image.getWidth(null)) / 2;
	    int y = Padding.height;

		g.drawImage(image,getX() + x,getY() + y,null);
		
		paintText(g,y + Image.height + 14);
	}

	private void paintText(Graphics2D g, int y) {
		g.setColor(Color.black);
		g.setFont(getFont());
		g.drawString(this.nome,getX() + fontPoint.x, getY() + y);
	}
}