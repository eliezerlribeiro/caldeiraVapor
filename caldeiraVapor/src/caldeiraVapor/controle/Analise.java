package caldeiraVapor.controle;

import javax.realtime.PriorityScheduler;
import javax.realtime.PriorityParameters;
import javax.realtime.PeriodicParameters;
import javax.realtime.RelativeTime;
import javax.realtime.RealtimeThread;

import caldeiraVapor.controle.Controle;

public class Analise extends RealtimeThread {

	private Controle controle;

    public int minimoNormal;
    public int maximoNormal;
    public int minimoLimite;
    public int maximoLimite;

	public int[] thrBomba;

	public Analise(PriorityParameters priorityParameters, PeriodicParameters periodicParameters, Controle controle) {
		super(priorityParameters, periodicParameters);
		this.controle = controle;

        minimoLimite = 50;
        minimoNormal = 100; 
        maximoNormal = 600;        
        maximoLimite = 800;

		thrBomba = new int[4];
		thrBomba[0] = 200;
		thrBomba[1] = 300;
		thrBomba[2] = 400;
		thrBomba[3] = 500;
	}

	private void ligaTodas() {
		for (int i = 0; i < 4; i++) {
			controle.ligaBomba[i] = true;
		}
	}

	private void desligaTodas() {
		for (int i = 0; i < 4; i++) {
			controle.desligaBomba[i] = true;
		}
	}

	public void run() {
		while(true) {
            switch(controle.mode) {
                case 1: // modoInicializando
                    if (controle.fluxoVapor == 0) {
                        if (controle.nivelAgua > maximoNormal) {
                           controle.abreValvula=true; //OPEN VALVE
                        } else if (controle.nivelAgua <= minimoNormal){
                            controle.fechaValvula=true;  
                            ligaTodas();
                        } else {
                            controle.fechaValvula=true;
                            controle.programRead = true;
                        }
                    } else {
                        controle.paradaDeEmergencia = true;
                        //TROCAR DE MODE emergency stop 
                    }
                break;
                case 2:
					// Controla parada de emergência
                    if (controle.nivelAgua >= maximoLimite-200) {
                       controle.paradaDeEmergencia = true;
                       //TROCAR MODE EMERGENCY STOP
						break;
                    } else if (controle.nivelAgua <= minimoLimite+50) {
                       controle.paradaDeEmergencia = true;
                       //TROCAR MODE EMERGENCY STOP
						break;
                    }

					// Controla nivel
					int dn = controle.nivelAgua - controle.nivelAnterior;
					for (int i = 0; i < 4; i++) {
						if (dn < 0 && controle.nivelAgua < thrBomba[i] + 50) {
							controle.ligaBomba[i] = true;
						} else if (dn > 0 && controle.nivelAgua > thrBomba[i] - 50) {
							controle.desligaBomba[i] = true;
						}
					}

                    break;
                default:
                    break;
            }
			

			// Proxima iteracao
			System.out.println("Tarefa de análise escalonada.");
                        System.out.println("MODE: " + controle.mode);
			waitForNextPeriod();
		}
	}

}
