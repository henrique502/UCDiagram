package br.com.hrdev.ucdiagram.models;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
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
				if(arrow.hasElement(figure)){
					arrows.add(arrow);
				}
			}
		}
					
		return arrows;
	}
	
	public void remove(Element elemento) {
		if(elemento instanceof Figure){
			for(Element e : elementos){
				if(e instanceof Arrow){
					Arrow arrow = (Arrow) e;
					if(arrow.hasElement(elemento)){
						elementos.remove(arrow);
					}
				}
			}
		}
		
		elementos.remove(elemento);
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
	
	public String toString(){
		return this.nome;
	}
}
