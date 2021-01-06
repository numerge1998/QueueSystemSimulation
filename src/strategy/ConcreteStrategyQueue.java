package strategy;

import java.util.ArrayList;
import java.util.LinkedList;

import shop.*;
public class ConcreteStrategyQueue implements Strategy{

	@Override
	public void addTask1(LinkedList<Server> server, Task t) {
		// TODO Auto-generated method stub
		int min = 0;
		for(int i = 0; i < server.size(); i++) {
			if(server.get(min).getSize() > server.get(i).getSize())
				min=i;
		}
		System.out.println("Taskul " + t.getNrTask() + " a accesat serverul " + min + " la momentul " + t.getArrivalTime());
		server.get(min).addTask(t);
	}}