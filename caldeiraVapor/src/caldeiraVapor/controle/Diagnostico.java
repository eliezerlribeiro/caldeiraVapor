package caldeiraVapor.controle;

import javax.realtime.PriorityScheduler;
import javax.realtime.PriorityParameters;
import javax.realtime.PeriodicParameters;
import javax.realtime.RelativeTime;
import javax.realtime.RealtimeThread;

public class Diagnostico extends RealtimeThread {

    private Controle controle;

    public Diagnostico(
            PriorityParameters priorityParameters,
            PeriodicParameters periodicParameters,
            Controle controle
    ) {
        super(priorityParameters, periodicParameters);
        this.controle = controle;
    }

    public void run() {
        while (true) {
            // Stub do metodo de diagnostico

            System.out.println("Tarefa de diagnóstico escalonada.");
            waitForNextPeriod();
        }
    }

}
