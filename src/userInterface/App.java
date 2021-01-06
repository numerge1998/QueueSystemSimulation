package userInterface;

import java.awt.Color;
import java.util.LinkedList;

import javax.swing.*;

import shop.Server;

import shop.Task;


public class App extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel display;
	
	public App() {
		display = new JPanel();
		Color culoare = new Color(120,120,120);
		display.setBackground( culoare ); 
		this.add(display);
		this.setSize(599,599);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public void afisareServere(LinkedList<Server> tasksList,int nrServers){
		display.removeAll();
		display.validate();
		int k=0;
		JScrollPane[] scroll = new JScrollPane[nrServers];
		JList<String> []list = new JList[nrServers];
		for(int i=0;i<nrServers;i++) {
		String[][] lista=new String[nrServers][50];
		k=0; 
		for(Task j:tasksList.get(i).getTask()) {
			lista[i][k++]=("Clientul cu numarul : " + j.getNrTask());
		}
		list[i]= new JList<String>(lista[i]);
		scroll[i] = new JScrollPane(list[i]);
		}
		
		for(int i=0;i<nrServers;i++)
			display.add(scroll[i]);
		display.revalidate();
		display.repaint();
	}
	
}