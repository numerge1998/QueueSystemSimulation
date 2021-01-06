package strategy;

import java.awt.List;
import java.util.ArrayList;
import java.util.LinkedList;

import shop.*;

import userInterface.*;

public class Scheduler {

	private LinkedList<Server> servers;
	private int maxServers;
	Thread[] thred;
	private Strategy strategy=new ConcreteStrategyQueue();
	public Scheduler(int maxNoCozi) {
		this.maxServers=maxNoCozi;
		servers=new LinkedList<Server>();
		thred=new Thread[maxNoCozi];
		for(int i=0;i<maxNoCozi;i++) {
			Server c=new Server(i);
			servers.add(c);
			thred[i]=new Thread(c);
			thred[i].start();
		}
	}
	
	public void dispatchTask(Task c)
	{		
		strategy.addTask1(servers,c);
		
	}
	
	public void showAvrageForServers() {
		for(int i=0;i<maxServers;i++) {
			System.out.println("Avrage time for server " + i + " is = " + servers.get(i).getAvarageWaitingTime());
		}
	}
	
	public LinkedList<Server> getServers(){
		return servers;
	}
	

}

