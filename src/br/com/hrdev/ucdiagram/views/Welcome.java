package br.com.hrdev.ucdiagram.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import br.com.hrdev.ucdiagram.UCDiagram;
import br.com.hrdev.ucdiagram.events.CarregarProjetoEvent;
import br.com.hrdev.ucdiagram.events.CloseEvent;
import br.com.hrdev.ucdiagram.events.NovoProjetoEvent;
import br.com.hrdev.ucdiagram.utils.Text;

public class Welcome extends View {
	
	private static final long serialVersionUID = 1L;

	public Welcome(UCDiagram window){
		super(window);
		this.setup();
	}
	
	public void setup(){
		setLayout(new BorderLayout(10,10));
		setWelcome();
		setButtons();
	}

	private void setWelcome(){
		JLabel mensagem = new JLabel();
		mensagem.setText(Text.key("welcome", UCDiagram.Title));
		mensagem.setFont(new Font("Arial", Font.PLAIN, 30));
		mensagem.setHorizontalAlignment(SwingConstants.CENTER);
		add(mensagem,BorderLayout.CENTER);
	}
	
	private void setButtons(){
		JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		JButton novo = new JButton();
		novo.setText(Text.key("welcome_novo"));
		novo.addActionListener(new NovoProjetoEvent(this));
		buttons.add(novo);
		
		JButton carregar = new JButton();
		carregar.setText(Text.key("welcome_carregar"));
		carregar.addActionListener(new CarregarProjetoEvent(this));
		buttons.add(carregar);
		
		JButton sair = new JButton();
		sair.setText(Text.key("sair"));
		sair.addActionListener(new CloseEvent());
		buttons.add(sair);
		
		add(buttons,BorderLayout.SOUTH);
	}

	@Override
	public void updateUIContents() {}

	@Override
	protected void updateUIMenu(JMenuBar menubar) {}

	@Override
	public void updateAll() {}
}
