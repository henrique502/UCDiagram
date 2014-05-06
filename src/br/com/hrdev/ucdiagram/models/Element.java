package br.com.hrdev.ucdiagram.models;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.RoundRectangle2D;
import java.io.Serializable;

public abstract class Element implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private boolean hasSelected = false;
	private Font font = null;
	private Point location = null;
	private Dimension size = null;
	
	protected String nome;
	
	public Element(){
		location = new Point();
		size = new Dimension();
	}
	
	public abstract void paint(Graphics2D g);
	
	public boolean contains(Point p){
		if(p == null)
			return false;
		
		return contains(p.x, p.y);
	}
	
	public boolean contains(double x, double y) {
		return (containX(x) && containY(y));
	}
	
	public boolean containX(double x) {
		if(x <= location.x) return false;
		if(x >= getWidth() + location.x) return false;
		return true;
	}
	
	public boolean containY(double y) {
		if(y <= location.y) return false;
		if(y >= getHeight() + location.y) return false;
		
		return true;
	}
	
	public void setSelected(boolean selected) {
		hasSelected = selected;
	}
	
	protected void paintSelectedBorder(Graphics2D g){
		if(hasSelected){
			float[] dash = {5.0f};
			g.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f));
			g.setColor(Color.lightGray);
			g.draw(new RoundRectangle2D.Double(getX(), getY(), getWidth() - 1, getHeight() - 1, 7, 7));
		}
	}

	public int getWidth() {
		if(size != null)
			return size.width;
		
		return 0;
	}
	
	public int getHeight() {
		if(size != null)
			return size.height;
		
		return 0;
	}
	
	public Rectangle getRectangle(){
		return new Rectangle(location,size);
	}

	public void setNome(String nome){
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(double x, double y) {
		setLocation(new Point((int) x, (int) y));
	}
	
	public void setLocation(Point location) {
		this.location = location;
	}

	public Dimension getSize() {
		return size;
	}

	public void setSize(Dimension size) {
		this.size = size;
	}
	
	public int getX(){
		return this.location.x;
	}
	
	public int getY(){
		return this.location.y;
	}

	public String toString(){
		return this.nome;
	}
}