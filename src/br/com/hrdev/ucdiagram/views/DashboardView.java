package br.com.hrdev.ucdiagram.views;

import java.awt.BorderLayout;
import java.awt.Event;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import br.com.hrdev.ucdiagram.UCDiagram;
import br.com.hrdev.ucdiagram.components.UIDashboardDiagramArea;
import br.com.hrdev.ucdiagram.components.UIDashboardSidebar;
import br.com.hrdev.ucdiagram.components.UIMenuBar;
import br.com.hrdev.ucdiagram.events.CarregarProjetoEvent;
import br.com.hrdev.ucdiagram.events.CloseEvent;
import br.com.hrdev.ucdiagram.events.NovoProjetoEvent;
import br.com.hrdev.ucdiagram.events.SalvarProjetoEvent;
import br.com.hrdev.ucdiagram.models.Diagrama;

@SuppressWarnings("serial")
public class DashboardView extends JPanel implements View {

	private UCDiagram window;
	private UIDashboardSidebar sidebar;
	private UIDashboardDiagramArea diagramArea;
	
	public DashboardView(UCDiagram window){
		this.window = window;
		this.setup();
	}
	
	private void setup() {
		setUpdatePanel();
		setPanels();
	}

	private void setMenu() {
		UIMenuBar menubar = (UIMenuBar) window.getJMenuBar();
		menubar.removeAll();
		
		JMenu menuArquivo = new JMenu("Arquivo");
		menuArquivo.setMnemonic('A');
		
		/* Item Novo Projeto */
		JMenuItem itemNovo = new JMenuItem("Novo Projeto");  
		itemNovo.addActionListener(new NovoProjetoEvent(window,this));
		
		/* Item Carregar Projeto */
		JMenuItem itemCarregar = new JMenuItem("Carregar Projeto");  
		itemCarregar.addActionListener(new CarregarProjetoEvent(window,this));
		
		/* Item Salvar */
		JMenuItem itemSalvar = new JMenuItem("Salvar");  
		itemSalvar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK));
		itemSalvar.setMnemonic(KeyEvent.VK_S);
		itemSalvar.addActionListener(new SalvarProjetoEvent(window,false));
		
		/* Item Salvar Como */
		JMenuItem itemSalvarComo = new JMenuItem("Salvar Como");  
		itemSalvarComo.addActionListener(new SalvarProjetoEvent(window,true));
		
		/* Item Exportar em PNG */
		JMenuItem itemExportar = new JMenuItem("Exportar em PNG");
		itemExportar.setEnabled(false);
		
		/* Item Sair */
		JMenuItem itemSair = new JMenuItem("Sair");  
		itemSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, Event.CTRL_MASK));
		itemSair.setMnemonic(KeyEvent.VK_Q);
		itemSair.addActionListener(new CloseEvent());

        menuArquivo.add(itemNovo);
        menuArquivo.add(itemCarregar);
        menuArquivo.addSeparator();
        menuArquivo.add(itemSalvar);
        menuArquivo.add(itemSalvarComo);

        menuArquivo.add(itemExportar);
        menuArquivo.addSeparator();
        menuArquivo.add(itemSair);
        
        menubar.add(menuArquivo);
		
        menubar.setHelp();
		menubar.revalidate();
	}
	
	private void setPanels(){
		setLayout(new BorderLayout(10,0));
		
		sidebar = new UIDashboardSidebar(window, this);
		diagramArea = new UIDashboardDiagramArea(window, this);
		
		
		add(sidebar,BorderLayout.WEST);
		add(diagramArea,BorderLayout.CENTER);
		
	}

	private void setUpdatePanel(){
		addComponentListener (new ComponentAdapter(){
	        @Override
			public void componentShown(ComponentEvent e){
	        	setMenu();
	        	updateAll();
	        }

	        @Override
			public void componentHidden(ComponentEvent e){}
	    });
	}

	public void updateAll() {
		sidebar.updateDataTree();
		diagramArea.updateDiagramAreaData();
		repaint();
	}
	
	public UCDiagram getWindow(){
		return this.window;
	}
	
	public void showDiagram(Diagrama diagrama) {
		diagramArea.showDiagram(diagrama);
	}

	@Override
	public void updateUIContents() {
		updateAll();
	}

	public UIDashboardSidebar getSidebar() {
		return sidebar;
	}
	
}
