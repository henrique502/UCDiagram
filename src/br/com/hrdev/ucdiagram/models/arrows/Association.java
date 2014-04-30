package br.com.hrdev.ucdiagram.models.arrows;

import br.com.hrdev.ucdiagram.libraries.ConnectLine;

public class Association extends Arrow {

	private static final long serialVersionUID = 1L;
	
	public Association(){
		super("association",ConnectLine.CONNECT_LINE_TYPE_RECTANGULAR,ConnectLine.LINE_ARROW_NONE);
	}
}
