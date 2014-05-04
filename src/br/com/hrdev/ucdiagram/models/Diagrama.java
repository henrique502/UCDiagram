package br.com.hrdev.ucdiagram.models;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JPanel;

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
		Graphics2D g = (Graphics2D) graphics;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		for (Element elemento : elementos) {
			elemento.paint(g);
		}
		
		g.dispose();
	}
	
	public void remove(Element elemento) {
		elementos.remove(elemento);
	}
	
	public void add(Element elemento) {
		elementos.add(elemento);
	}
	
	public ArrayList<Element> getAll() {
		return elementos;
	}
	
	public String toString(){
		return this.nome;
	}
}
