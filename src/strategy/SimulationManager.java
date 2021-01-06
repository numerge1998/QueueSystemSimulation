package strategy;

import strategy.*;
import userInterface.App;

import java.awt.List;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
import shop.*;

public class SimulationManager implements Runnable {

	public int timeLimit;
	public int maxProcessingTime = 10;
	public int minProcessingTime = 2;
	public int minArrivalTime =1;
	public int maxArrivalTime = 4;
	public int numberOfServers=3;
	public int numberOfClients=100;
	public int maxTasksServer=10;
	public int[] maxTasks=new int[1000];
	
	private Scheduler scheduler;
	
	private App app;
	
	private LinkedList<Task> randomTasks;
	
	public SimulationManager(int timeLimit,int numberOfServers,int minArrivalTime,int maxArrivalTime,int minProcessingTime,int maxProcessingTime) {
		randomTasks=new LinkedList<Task>();
		this.timeLimit = timeLimit;
		this.maxProcessingTime = maxProcessingTime;
		this.minProcessingTime = minProcessingTime;
		this.minArrivalTime = minArrivalTime;
		this.maxArrivalTime = maxArrivalTime;
		this.numberOfServers = numberOfServers;
		//this.numberOfClients = numberOfClients;
		//this.maxTasksServer = maxTasksServer;
		
		scheduler =new Scheduler(numberOfServers);
		
		app=new App();
		
		generateNRandomTasks();
	}

	private void generateNRandomTasks() {
		Random rand=new Random();
		int nrClient=0;
		//int processingTime=minProcessingTime + (int)(Math.random()*(maxProcessingTime-minProcessingTime+1));
		int processingTime=rand.nextInt((maxProcessingTime-minProcessingTime) + 1) +minProcessingTime;
		//int arrivalTime=minArrivalTime + (int)(Math.random()*(maxArrivalTime-minArrivalTime+1));
		int arrivalTime=rand.nextInt((maxArrivalTime-minArrivalTime) + 1) + minArrivalTime;
		Collections.sort(randomTasks);
		while(arrivalTime<=timeLimit){
			Task t=new Task(arrivalTime,processingTime,nrClient++);
			for(int i=arrivalTime;i<arrivalTime+processingTime;i++) //pentru peak time
				if(i<timeLimit)
					maxTasks[i]=maxTasks[i]+1; 
			
			
			randomTasks.add(t);
			processingTime=rand.nextInt((maxProcessingTime-minProcessingTime) + 1) +minProcessingTime;
			//processingTime=minProcessingTime + (int)(Math.random()*(maxProcessingTime-minProcessingTime+1));
			//arrivalTime+=minArrivalTime + (int)(Math.random()*(maxArrivalTime-minArrivalTime+1));
			arrivalTime+=rand.nextInt((maxArrivalTime-minArrivalTime) + 1) + minArrivalTime;
		}
	}
	private int currentTime=0;
	@Override
	public synchronized void run() {
		while(currentTime<=timeLimit){ // sau !randomTasks.isEmpty()
			if(randomTasks.peek().getArrivalTime()==currentTime) {
				System.out.println("Secunda " + currentTime + ":");
				scheduler.dispatchTask(randomTasks.remove());
			}
			app.afisareServere(scheduler.getServers(), numberOfServers);
			currentTime++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		scheduler.showAvrageForServers();
		int max=0,i;
		for(i=0;i<timeLimit;i++)
			if(max<=maxTasks[i])
				max=maxTasks[i];
		System.out.println("There were " + max + " tasks at peak time");
		
		}
		
	
	
		public static void main(String args[]) {
		LinkedList<Task> t1=new LinkedList<Task>();
		Task t=new Task(10,10,1);
		Task t2=new Task(2,5,1);
		Task t3=new Task(7,5,1);
		Task t4=new Task(4,5,1);
		t1.add(t);
		t1.add(t2);
		t1.add(t3);
		t1.add(t4);
		System.out.println(t1.toString());
		Collections.sort(t1);
		System.out.println(t1.toString());
		//SimulationManager sim=new SimulationManager();
		//Thread t = new Thread(sim);
		//t.start();

	}

}


