package br.com.hrdev.ucdiagram.models.arrows;

import br.com.hrdev.ucdiagram.libraries.ConnectLine;

public class Dependency extends Arrow {

	private static final long serialVersionUID = 1L;
	
	public Dependency(){
		super("dependency",ConnectLine.CONNECT_LINE_TYPE_RECTANGULAR,ConnectLine.LINE_ARROW_DEST);
	}
}
