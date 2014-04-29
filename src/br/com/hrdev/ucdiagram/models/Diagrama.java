package br.com.hrdev.ucdiagram.models;

import java.awt.Color;
import java.io.Serializable;

import javax.swing.JPanel;

public class Diagrama extends JPanel implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nome;
	
	public Diagrama(String nome){
		this.nome = nome;
		setLayout(null);
		setBackground(Color.white);
		setFocusable(true);
	}
	
	public String getNome(){
		return this.nome;
	}
	
	public String toString(){
		return this.nome;
	}
}
