package shop;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;



public class Server implements Runnable {

	private BlockingQueue<Task> tasks;
	private int waithingPeriod;
	private int nrServer;
	int nrTask;
	private boolean idee = true;
	private float averageWaith;
	
	public Server(int number) {
		tasks = new LinkedBlockingQueue<Task>();
		//waithingPeriod = new Integer(0);
		this.nrServer = number;
	}
	
	public void addTask(Task nou) {
		tasks.add(nou);
		nrTask++;
		waithingPeriod+=nou.getProcessingTime();
	}
	
	public int getNrQueue() {
		return nrServer;
	}
	
	public void setNrQueue(int n) {
		this.nrServer=n;
	}
	
	public void setWaithingTime(Integer i) {
		this.waithingPeriod=i;
	}
	
	public Integer getWaithingPeriod() {
		return waithingPeriod;
	}
	
	public int getSize() {
		return tasks.size();
	}
	
	public Task getTask1() {
		return tasks.peek();
	}
	
	public Task[] getTasks() {
		Task[] l=new Task[tasks.size()];
		int i=0;
		for (Task c:tasks) {
			l[i++]=c;
		}
		return l;
		
	}
	
	public BlockingQueue<Task> getTask() {
		return tasks;
	}
	
	/*@Override
	public synchronized void run() {
		// TODO Auto-generated method stub
		while(true) {
		while(!tasks.isEmpty())
		try {
			Thread.sleep(tasks.peek().getProcessingTime() * 1000);
			waithingPeriod -= tasks.peek().getProcessingTime();
			tasks.remove();
			
			// 
		} catch (Exception e) {
			System.out.println("ho ma!");
		}
		}
	}*/
	
	@Override
	public synchronized void run() {
		while(true) {
			while(tasks.peek()!=null) {
			try {
				
				Task currentTask=tasks.peek();
				currentTask.setFinishTime(currentTask.getArrivalTime()+waithingPeriod);
				averageWaith+=currentTask.getFinishTime()-currentTask.getArrivalTime();
				
				Thread.sleep(currentTask.getProcessingTime()*1000);
				
				int plecare=currentTask.getArrivalTime()+currentTask.getProcessingTime();
				System.out.println("Secunda " + plecare + ":");
				System.out.println("Taskul " + currentTask.getNrTask() + " a parasit serverul la momentul " + plecare );
				
				waithingPeriod -= currentTask.getProcessingTime();
				tasks.remove();
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
			
		}
	}

	public float getAvarageWaitingTime() {
		// TODO Auto-generated method stub
		nrTask -= tasks.size();
		if (nrTask == 0) return 0;
		return averageWaith/nrTask;
	}
	
}
