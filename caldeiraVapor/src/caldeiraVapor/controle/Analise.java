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
                        switch(controle.mode) {
                            case 1: // modoInicializando
                                if(controle.fluxoVapor == 0){
                                    if(controle.nivelAgua > controle.nivelAguaMaximoNormal) {
                                       controle.abreValvula=true; //OPEN VALVE
                                    }else if (controle.nivelAgua <= controle.nivelAguaMinimoNormal){
                                        controle.fechaValvula=true;  
                                        controle.ligaBombas = true;
                                           
                                    }else{
                                        controle.fechaValvula=true;
                                        controle.programRead = true;
                                    }
                                }else{
                                    controle.paradaDeEmergencia = true;
                                    //TROCAR DE MODE emergency stop 
                                }
                            break;
                            case 2:
                                if(controle.nivelAgua > controle.nivelAguaMaximoNormal-100) {
                                    controle.desligaBombas=true; 
                                    if(controle.nivelAgua >= controle.nivelAguaMaximoLimite-200){
                                       controle.paradaDeEmergencia = true;
                                       //TROCAR MODE EMERGENCY STOP
                                    }
                                 }else if (controle.nivelAgua <= controle.nivelAguaMinimoNormal+100){
                                     controle.ligaBombas=true;  
                                      if(controle.nivelAgua <= controle.nivelAguaMinimoLimite+50){
                                       controle.paradaDeEmergencia = true;
                                       //TROCAR MODE EMERGENCY STOP
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
