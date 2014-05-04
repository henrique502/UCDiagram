package br.com.hrdev.ucdiagram.utils;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.event.MenuListener;

public class ComponentsUtil {

	public static void clearActionListeners(AbstractButton item){
		if(item == null) return;
		
		for(ActionListener action : item.getActionListeners())
			item.removeActionListener(action);
	}
	
	public static void clearMouseListeners(JComponent item){
		if(item == null) return;
		
		for(MouseListener action : item.getMouseListeners())
			item.removeMouseListener(action);
	}
	
	public static void clearMouseMotionListeners(JComponent item){
		if(item == null) return;
		
		for(MouseMotionListener action : item.getMouseMotionListeners())
			item.removeMouseMotionListener(action);
	}
			
	public static void clearMenuListeners(JMenu item){
		if(item == null) return;
		
		for(MenuListener action : item.getMenuListeners())
			item.removeMenuListener(action);
	}		
}
