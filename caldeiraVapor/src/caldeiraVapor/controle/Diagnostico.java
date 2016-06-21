package caldeiraVapor.controle;

import javax.realtime.PriorityScheduler;
import javax.realtime.PriorityParameters;
import javax.realtime.PeriodicParameters;
import javax.realtime.RelativeTime;
import javax.realtime.RealtimeThread;

public class Diagnostico extends RealtimeThread {

    private Controle controle;
    
    private int[] contaFalhas; // da bomba
    public boolean[] bombaOk;

    public Diagnostico(
            PriorityParameters priorityParameters,
            PeriodicParameters periodicParameters,
            Controle controle
    ) {
        super(priorityParameters, periodicParameters);
        this.controle = controle;
        
        contaFalhas = new int[4];
        bombaOk = new boolean[4];
        for (int i = 0; i < 4; i++) {
            contaFalhas[i] = 0;
            bombaOk[i] = true;
        }
    }

    public void run() {
        while (true) {
            System.out.println("Tarefa de diagnóstico escalonada.");
            
            if (controle.mode == 2) {
                for (int i = 0; i < 4; i++) {
                    // A bomba esta defeituosa se esta em um estado inconsistente
                    // com a ultima determinacao do controle
                    if (controle.deveriaEstarAberta[i] != controle.isBombaAberta[i]) {
                        contaFalhas[i]++;
                    } else {
                        contaFalhas[i] = 0;
                    }

                    // Como a informacao de que a bomba ligou tem um ciclo de atraso,
                    // damos tres ciclos para detectar um defeito de fato
                    if (contaFalhas[i] > 2) {
                        bombaOk[i] = false;
                        System.out.print("Defeito diagnosticado na bomba ");
                        System.out.println(i);
                    }
                }
            }
            waitForNextPeriod();
        }
    }

}
