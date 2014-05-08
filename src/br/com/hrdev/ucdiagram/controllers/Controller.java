package br.com.hrdev.ucdiagram.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

public abstract class Controller implements ActionListener, MouseListener, MouseMotionListener, MenuListener, ListSelectionListener, TreeSelectionListener {
	
	@Override
	public void actionPerformed(ActionEvent e) {}
	
	@Override
	public void mouseDragged(MouseEvent e) {}

	@Override
	public void mouseMoved(MouseEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void menuCanceled(MenuEvent e) {}

	@Override
	public void menuDeselected(MenuEvent e) {}

	@Override
	public void menuSelected(MenuEvent e) {}

	@Override
	public void valueChanged(ListSelectionEvent e) {}

	@Override
	public void valueChanged(TreeSelectionEvent e) {}
}
