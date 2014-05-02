package br.com.hrdev.ucdiagram;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.hrdev.ucdiagram.models.Projeto;
import br.com.hrdev.ucdiagram.utils.Icons;
import br.com.hrdev.ucdiagram.utils.Text;
import br.com.hrdev.ucdiagram.views.Dashboard;
import br.com.hrdev.ucdiagram.views.Welcome;

public class UCDiagram extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private static final Dimension Size = new Dimension(1024, 700);

	public static String Title = null;
	
	private JPanel painel;
	private CardLayout layout;
	
	private File projetoArquivo;
	private Projeto projeto;
	
	public static final String Welcome = "welcome";
	public static final String Dashboard = "dashboard";
	
	public UCDiagram(){
		super();
		Title = Text.key("title");
		run();
	}
	
	private void run(){
		setAtributos();
		setViews();
		setVisible(true);
	}
	
	private void setAtributos() {
		super.setTitle(Title);
		setPreferredSize(Size);
		setMinimumSize(Size);
		setResizable(true);
		setLocationRelativeTo(null);
		setJMenuBar(new JMenuBar());
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setIconImage(Icons.Favicon.getImage());
		layout = new CardLayout();
		painel = new JPanel();
		painel.setBorder(new EmptyBorder(10,10,10,10));
		painel.setLayout(layout);
		
		getContentPane().add(painel,BorderLayout.CENTER);
		
	}
	
	private void setViews(){
		painel.add(new JPanel(),"blank");
		painel.add(new Welcome(this),Welcome);
		painel.add(new Dashboard(this),Dashboard);
		
		changeView(Welcome);
	}
	
	public void changeView(String idView){
		layout.show(painel, idView);
	}
	
	public void setTitle(String title){
		super.setTitle((Title + " " + title).trim());
	}
	
	public Projeto getProjeto(){
		return this.projeto;
	}
	
	public void setProjeto(Projeto projeto){
		if(projeto == null){
			this.projeto = null;
			setTitle("");
			changeView(Welcome);
		} else {
			setTitle(" - " + projeto.getNome());
			this.projeto = projeto;
		}
		this.projeto = projeto;
	}
	
	public File getProjetoArquivo() {
		return projetoArquivo;
	}

	public void setProjetoArquivo(File projetoArquivo) {
		this.projetoArquivo = projetoArquivo;
	}
}
