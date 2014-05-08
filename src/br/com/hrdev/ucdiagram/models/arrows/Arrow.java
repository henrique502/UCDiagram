package br.com.hrdev.ucdiagram.models.arrows;

import br.com.hrdev.ucdiagram.models.Element;
import br.com.hrdev.ucdiagram.models.figures.Figure;

public abstract class Arrow extends Element {
	
	private static final long serialVersionUID = 1L;
	
	protected Figure start = null;
	protected Figure end = null;
	protected String lineNome = null;
	
	public Arrow(String lineNome){
		super();
		this.lineNome = lineNome;
	}
	
	public Figure getStart() {
		return start;
	}

	public void setStart(Figure start) {
		this.start = start;
	}

	public Figure getEnd() {
		return end;
	}

	public void setEnd(Figure end) {
		this.end = end;
	}
	
	public boolean hasFigure(Figure figure) {
		if(figure == start)
			return true;
		
		if(figure == end)
			return true;
		
		return false;
	}
	
	@Override
	public String toString(){
		return "Arrow " + lineNome + "(" + start + "," + end + ")";
	}
}
