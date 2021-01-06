package shop;


public class Task implements Comparable<Task>{

	private int arrivalTime;
	private int processingTime;
	private int nrTask;
	private int finishTime;
	public Task(int a,int b, int c) {
		this.arrivalTime=a;
		this.processingTime=b;
		this.nrTask=c;
	}
	
	public int getNrTask() {
		return nrTask;
	}
	
	public int getArrivalTime() {
		return arrivalTime;
	}
	
	public void setArrivalTime(int t) {
		this.arrivalTime=t;
	}
	
	public int getProcessingTime() {
		return processingTime;
	}
	
	public void setProcessingTime(int t) {
		this.processingTime=t;
	}
	@Override
	public int compareTo(Task t) {
		// TODO Auto-generated method stub
		int time=((Task)t).getArrivalTime();
		return this.arrivalTime-time;
	
	}
	
	public String toString() {
        return arrivalTime + " " + processingTime + "\n";
    }

	public void setFinishTime(int i) {
		// TODO Auto-generated method stub
		this.finishTime=i;
	}

	public int getFinishTime() {
		return finishTime;
	}
}

