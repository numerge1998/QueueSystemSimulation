package userInterface;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import strategy.SimulationManager;


public class Interface extends JFrame{
	/**
	 * 
	 */
	private JFrame frame = new JFrame() ;
	private static final long serialVersionUID = 1L;
	private JTextField arriveMin=new JTextField();
	private JLabel label1=new JLabel("Minimum arriving interval:");
	private JTextField arriveMax=new JTextField();
	private JLabel label2=new JLabel("Maximum arriving interval:");
	private JTextField processMin=new JTextField();
	private JLabel label3=new JLabel("Minimum processing time:");
	private JTextField processMax=new JTextField();
	private JLabel label4=new JLabel("Maximum processing time:");
	private JTextField numberOfServers=new JTextField();
	private JLabel label5=new JLabel("Number of queues:");
	private JTextField simulationTime=new JTextField();
	private JLabel label6=new JLabel("Simulation time:");
	private JButton start=new JButton("Start");
	private JPanel display=new JPanel();


	public Interface()
	{
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(470,488);
		label1.setBounds(20,20,300,30);
		arriveMin.setBounds(180, 20, 250, 30);
		label2.setBounds(20,80,300,30);
		arriveMax.setBounds(180, 80, 250, 30);
		label3.setBounds(20,140,300,30);
		processMin.setBounds(180, 140, 250, 30);
		label4.setBounds(20,200,300,30);
		processMax.setBounds(180, 200, 250, 30);
		label5.setBounds(20,260,300,30);
		numberOfServers.setBounds(180, 260, 250, 30);
		label6.setBounds(20,320,300,30);
		simulationTime.setBounds(180, 320,250, 30);
		start.setBounds(200, 365, 80, 80);

		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				SimulationManager actiune=new SimulationManager(Integer.parseInt(simulationTime.getText()), Integer.parseInt(numberOfServers.getText()),Integer.parseInt(arriveMin.getText()),
						Integer.parseInt(arriveMax.getText()), Integer.parseInt(processMin.getText()), Integer.parseInt(processMax.getText()));
				Thread miscare=new Thread(actiune);
				miscare.start();
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(display, "Datele nu sunt corecte", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		display.add(label1);
		display.add(arriveMin);
		display.add(label2);
		display.add(arriveMax);
		display.add(label3);
		display.add(processMin);
		display.add(label4);
		display.add(processMax);
		display.add(label5);
		display.add(numberOfServers);
		display.add(label6);
		
		
		
		
		
		display.add(simulationTime);
		display.add(start);
		Color culoare = new Color(120,120,120);
		display.setBackground( culoare ); 
		display.setLayout(null);
		
		frame.add(display);
		frame.setVisible(true);
	}
	


		public static void main(String[] args) {
			new Interface();
		}

	
}

