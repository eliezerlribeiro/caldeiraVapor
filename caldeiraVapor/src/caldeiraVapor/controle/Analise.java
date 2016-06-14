package caldeiraVapor.controle;

import javax.realtime.PriorityScheduler;
import javax.realtime.PriorityParameters;
import javax.realtime.PeriodicParameters;
import javax.realtime.RelativeTime;
import javax.realtime.RealtimeThread;

public class Analise extends RealtimeThread {

	public Analise(PriorityParameters priorityParameters, PeriodicParameters periodicParameters) {
		super(priorityParameters, periodicParameters);
	}

	public void run() {
		while(true) {
			System.out.println("Tarefa de análise escalonada.");
			waitForNextPeriod();
		}
	}

}
