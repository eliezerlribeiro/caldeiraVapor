package caldeiraVapor.controle;

import javax.realtime.PriorityScheduler;
import javax.realtime.PriorityParameters;
import javax.realtime.PeriodicParameters;
import javax.realtime.RelativeTime;
import javax.realtime.RealtimeThread;

import caldeiraVapor.controle.Controle;

public class Analise extends RealtimeThread {

	private Controle controle;

	public Analise(PriorityParameters priorityParameters, PeriodicParameters periodicParameters, Controle controle) {
		super(priorityParameters, periodicParameters);
		this.controle = controle;
	}

	public void run() {
		while(true) {
			// Algoritmo tosco, so para exemplo
			if (controle.nivelAgua >= 150) {
				controle.desligaBombas = true;
			} else if (controle.nivelAgua <= 20) {
				controle.ligaBombas = true;
			}

			// Proxima iteracao
			System.out.println("Tarefa de análise escalonada.");
			waitForNextPeriod();
		}
	}

}
