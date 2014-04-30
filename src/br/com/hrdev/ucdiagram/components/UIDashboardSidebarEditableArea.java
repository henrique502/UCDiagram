package br.com.hrdev.ucdiagram.components;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.com.hrdev.ucdiagram.models.Element;
import br.com.hrdev.ucdiagram.utils.Icons;

public class UIDashboardSidebarEditableArea extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JTextField nome, x, y;
	private JButton salvar, cancelar;
	private Element item = null;
	private UIDashboardSidebar sidebar;
	
	public UIDashboardSidebarEditableArea(UIDashboardSidebar sidebar){
		super(new GridLayout(4, 2, 5, 5));
		this.sidebar = sidebar;
		setup();
	}
	
	private void setup(){
		add(new JLabel("Nome:"));
		nome = new JTextField(10);
		add(nome);
		add(new JLabel("X:"));
		x = new JTextField("0",10);
		x.setEnabled(false);
		
		add(x);
		add(new JLabel("Y:"));
		y = new JTextField("0",10);
		y.setEnabled(false);
		add(y);
		salvar = new JButton("Salvar", Icons.Accept);
		salvar.addActionListener(this);
		add(salvar);
		cancelar = new JButton("Cancelar", Icons.Delete);
		cancelar.addActionListener(this);
		add(cancelar);
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		nome.setEnabled(enabled);
		salvar.setEnabled(enabled);
		cancelar.setEnabled(enabled);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Salvar")){
			String text = nome.getText();
			if(text == null || text.trim().equals("")) return;
			
			item.setNome(nome.getText());
			sidebar.updateDataTree();
		}
		clear();
	}
	
	public void clear(){
		if(item != null){
			item.setSelected(false);
		}
		
		nome.setText("");
		x.setText("0");
		y.setText("0");
		item = null;
		setEnabled(false);
	}
	
	public void setItem(Element i){
		clear();
		
		item = i;
		item.setSelected(true);
		nome.setText(item.getNome());
		setEnabled(true);
		x.setText(item.getX() + "");
		y.setText(item.getY() + "");
	}

	public Element getItem() {
		return item;
	}

	public void updateItem() {
		x.setText(item.getX() + "");
		y.setText(item.getY() + "");
	}
}