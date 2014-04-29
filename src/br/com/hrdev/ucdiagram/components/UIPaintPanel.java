package br.com.hrdev.ucdiagram.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class UIPaintPanel extends JPanel implements MouseMotionListener, MouseListener {
	
	private Point mouse, oldPoint;
	
	private boolean hasPressed = false;
	
	public UIPaintPanel(){
		setLayout(null);
		mouse = new Point();
		oldPoint = new Point();
		addMouseMotionListener(this);
		addMouseListener(this);
		setBackground(Color.white);
	}
	
	
	@Override
	public void paintComponent(Graphics graphics){
		super.paintComponent(graphics);
		Graphics2D g = (Graphics2D) graphics;
		
		

		
		g.dispose();
	}
	
	
	
	public void mouseDragged(MouseEvent e) {
		if(hasPressed){
			oldPoint.setLocation(e.getX(), e.getY());
			System.out.println(mouse + " - " + oldPoint);
		}
		repaint();
	}

	public void mouseMoved(MouseEvent e) {

	}


	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		
		if(!hasPressed)
			mouse.setLocation(e.getX(), e.getY());
		hasPressed = true;
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		hasPressed = false;
		
	}
}
