package strategy;

import java.util.ArrayList;
import java.util.LinkedList;

import shop.*;

public interface Strategy {
	public void addTask1(LinkedList<Server> servers,Task t);
}
