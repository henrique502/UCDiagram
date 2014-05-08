package br.com.hrdev.ucdiagram.views;

import java.awt.BorderLayout;
import java.awt.Event;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import br.com.hrdev.ucdiagram.UCDiagram;
import br.com.hrdev.ucdiagram.controllers.CarregarProjetoController;
import br.com.hrdev.ucdiagram.controllers.CloseController;
import br.com.hrdev.ucdiagram.controllers.ExportController;
import br.com.hrdev.ucdiagram.controllers.NovoProjetoController;
import br.com.hrdev.ucdiagram.controllers.SalvarProjetoController;
import br.com.hrdev.ucdiagram.models.Diagrama;
import br.com.hrdev.ucdiagram.utils.ComponentsUtil;
import br.com.hrdev.ucdiagram.utils.Text;
import br.com.hrdev.ucdiagram.views.dashboard.DrawArea;
import br.com.hrdev.ucdiagram.views.dashboard.Sidebar;
import br.com.hrdev.ucdiagram.views.dashboard.Toolbar;

public class Dashboard extends View {

	private static final long serialVersionUID = 1L;
	private Sidebar sidebar;
	private DrawArea diagramArea;
	
	private JMenuItem itemExportar;
	
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
		diagramArea = new DrawArea(this);
		
		add(sidebar,BorderLayout.WEST);
		add(diagramArea,BorderLayout.CENTER);
	}
	
	public void showDiagram(Diagrama diagrama) {
		diagramArea.showDiagrama(diagrama);
	}

	public Sidebar getSidebar() {
		return sidebar;
	}
	
	public Toolbar getToolbar() {
		return diagramArea.getToolbar();
	}
	
	public Diagrama getDiagrama() {
		return diagramArea.getCurrentDiagrama();
	}
	
	public void removeDiagramaListiners(Diagrama diagrama){
		if(diagrama == null) return;
			
		ComponentsUtil.clearMouseListeners(diagrama);
		ComponentsUtil.clearMouseMotionListeners(diagrama);
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
		itemNovo.addActionListener(new NovoProjetoController(this));
		
		/* Item Carregar Projeto */
		JMenuItem itemCarregar = new JMenuItem(Text.key("dashboard_menu_arquivo_carregar"));  
		itemCarregar.addActionListener(new CarregarProjetoController(this));
		
		/* Item Salvar */
		JMenuItem itemSalvar = new JMenuItem(Text.key("dashboard_menu_arquivo_salvar"));  
		itemSalvar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK));
		itemSalvar.setMnemonic(KeyEvent.VK_S);
		itemSalvar.addActionListener(new SalvarProjetoController(this,false));
		
		/* Item Salvar Como */
		JMenuItem itemSalvarComo = new JMenuItem(Text.key("dashboard_menu_arquivo_salvar_como"));  
		itemSalvarComo.addActionListener(new SalvarProjetoController(this,true));
		
		/* Item Exportar */
		itemExportar = new JMenuItem(Text.key("dashboard_menu_arquivo_exportar"));
		itemExportar.addActionListener(new ExportController(this));
		itemExportar.setEnabled(false);
		
		/* Item Sair */
		JMenuItem itemSair = new JMenuItem(Text.key("sair"));  
		itemSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, Event.CTRL_MASK));
		itemSair.setMnemonic(KeyEvent.VK_Q);
		itemSair.addActionListener(new CloseController());

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
	
	public JMenuItem getExportOption(){
		return itemExportar;
	}

	@Override
	public void updateAll() {
		sidebar.updateDataTree();
		diagramArea.updateDiagramAreaData();
		repaint();
	}
}
