package br.com.hrdev.ucdiagram.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Projeto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nome;
	private ArrayList<Ator> atores;
	private ArrayList<Diagrama> diagramas;
	
	public Projeto(){
		this("sem nome");
	}
	
	public Projeto(String nome){
		this.nome = nome;
		this.atores = new ArrayList<Ator>();
		this.diagramas = new ArrayList<Diagrama>();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ArrayList<Ator> getAtores() {
		return atores;
	}

	public ArrayList<Diagrama> getDiagramas() {
		return diagramas;
	}
	
	public void removerAtor(Ator ator) {
		if(ator == null) return;
		
		for(Diagrama diagrama : diagramas)
			diagrama.remove(ator);
		
		atores.remove(ator);
	}
	
	public void removerDiagrama(Diagrama diagrama){
		diagramas.remove(diagrama);
	}
	
	public String toString(){
		return this.nome;
	}
}
