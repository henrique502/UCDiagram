package br.com.hrdev.ucdiagram.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Projeto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nome;
	private ArrayList<Diagrama> diagramas;
	
	public Projeto(){
		this("sem nome");
	}
	
	public Projeto(String nome){
		this.nome = nome;
		this.diagramas = new ArrayList<Diagrama>();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public ArrayList<Diagrama> getDiagramas() {
		return diagramas;
	}
	
	public void removerDiagrama(Diagrama diagrama){
		diagramas.remove(diagrama);
	}
	
	public String toString(){
		return this.nome;
	}

	public boolean exists(Diagrama diagrama) {
		for(Diagrama d : diagramas)
			if(diagrama == d)
				return true;
			
		return false;
	}
}
