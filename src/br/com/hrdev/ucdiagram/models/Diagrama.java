package br.com.hrdev.ucdiagram.models;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import br.com.hrdev.ucdiagram.models.arrows.Arrow;
import br.com.hrdev.ucdiagram.models.figures.Figure;

public class Diagrama extends JPanel {

	private static final long serialVersionUID = 1L;
	private String nome;
	private ArrayList<Element> elementos;
	
	public Diagrama(String nome){
		this.nome = nome;
		setLayout(null);
		setBackground(Color.white);
		setFocusable(true);
		setDoubleBuffered(true);
		this.elementos = new ArrayList<Element>();
	}
	
	public String getNome(){
		return this.nome;
	}
	
	@Override
	public void paint(Graphics graphics) {
		super.paint(graphics);

		for (Element elemento : elementos) {
			Graphics2D g = (Graphics2D) graphics.create();
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			
			elemento.paint(g);
			g.dispose();
		}
	}
	
	public ArrayList<Arrow> getArrowsFromFigure(Figure figure){
		ArrayList<Arrow> arrows = new ArrayList<Arrow>();	
		
		for(Element e : elementos){
			if(e instanceof Arrow){
				Arrow arrow = (Arrow) e;
				if(arrow.hasFigure(figure)){
					arrows.add(arrow);
				}
			}
		}
					
		return arrows;
	}
	
	public void remove(Element elemento) {
		if(elemento instanceof Figure){
			removeArrowsFromFigure((Figure) elemento);
		}
		
		elementos.remove(elemento);
	}
	
	private void removeArrowsFromFigure(Figure figure) {
		for(Arrow arrow : getArrowsFromFigure(figure))
			elementos.remove(arrow);
	}

	public void add(Element elemento) {
		elementos.add(elemento);
	}
	
	public void addFirst(Element elemento) {
		elementos.add(0, elemento);
	}
	
	public ArrayList<Element> getAll() {
		return elementos;
	}
	
	public BufferedImage createImage(){
	    int w = getWidth();
	    int h = getHeight();
	    BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
	    Graphics2D g = bi.createGraphics();
	    paint(g);
	    return bi;
	}
	
	public String toString(){
		return this.nome;
	}
}
