package br.com.hrdev.ucdiagram.views;

import java.awt.BorderLayout;
import java.awt.Event;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import br.com.hrdev.ucdiagram.UCDiagram;
import br.com.hrdev.ucdiagram.events.CarregarProjetoEvent;
import br.com.hrdev.ucdiagram.events.CloseEvent;
import br.com.hrdev.ucdiagram.events.NovoProjetoEvent;
import br.com.hrdev.ucdiagram.events.SalvarProjetoEvent;
import br.com.hrdev.ucdiagram.models.Diagrama;
import br.com.hrdev.ucdiagram.utils.Text;
import br.com.hrdev.ucdiagram.views.dashboard.DiagramArea;
import br.com.hrdev.ucdiagram.views.dashboard.Sidebar;

public class Dashboard extends View {

	private static final long serialVersionUID = 1L;
	private Sidebar sidebar;
	private DiagramArea diagramArea;
	
	public Dashboard(UCDiagram window){
		super(window);
		this.setup();
	}
	
	private void setup() {
		setPanels();
	}

	private void setPanels(){
		setLayout(new BorderLayout(10,0));
		
		sidebar = new Sidebar(this);
		diagramArea = new DiagramArea(this);
		
		add(sidebar,BorderLayout.WEST);
		add(diagramArea,BorderLayout.CENTER);
		
	}
	
	public void showDiagram(Diagrama diagrama) {
		diagramArea.showDiagram(diagrama);
	}

	public Sidebar getSidebar() {
		return sidebar;
	}
	
	@Override
	public void updateUIContents() {
		updateAll();
	}

	@Override
	protected void updateUIMenu(JMenuBar menubar) {
		JMenu menuArquivo = new JMenu(Text.key("dashboard_menu_arquivo"));
		menuArquivo.setMnemonic('A');
		
		/* Item Novo Projeto */
		JMenuItem itemNovo = new JMenuItem(Text.key("dashboard_menu_arquivo_novo"));  
		itemNovo.addActionListener(new NovoProjetoEvent(this));
		
		/* Item Carregar Projeto */
		JMenuItem itemCarregar = new JMenuItem(Text.key("dashboard_menu_arquivo_carregar"));  
		itemCarregar.addActionListener(new CarregarProjetoEvent(this));
		
		/* Item Salvar */
		JMenuItem itemSalvar = new JMenuItem(Text.key("dashboard_menu_arquivo_salvar"));  
		itemSalvar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK));
		itemSalvar.setMnemonic(KeyEvent.VK_S);
		itemSalvar.addActionListener(new SalvarProjetoEvent(this,false));
		
		/* Item Salvar Como */
		JMenuItem itemSalvarComo = new JMenuItem(Text.key("dashboard_menu_arquivo_salvar"));  
		itemSalvarComo.addActionListener(new SalvarProjetoEvent(this,true));
		
		/* Item Exportar */
		JMenuItem itemExportar = new JMenuItem(Text.key("dashboard_menu_arquivo_salcar_como"));
		itemExportar.setEnabled(false);
		
		/* Item Sair */
		JMenuItem itemSair = new JMenuItem(Text.key("sair"));  
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
	}

	@Override
	public void updateAll() {
		sidebar.updateDataTree();
		diagramArea.updateDiagramAreaData();
		repaint();
	}
}
