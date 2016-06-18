package caldeiraVapor.controle;

import javax.realtime.PriorityScheduler;
import javax.realtime.PriorityParameters;
import javax.realtime.PeriodicParameters;
import javax.realtime.RelativeTime;
import javax.realtime.RealtimeThread;

import caldeiraVapor.controle.Controle;
import caldeiraVapor.mensagens.*;

public class Comunicacao extends RealtimeThread {

	private Controle controle;
	private FilaPlantaParaControle filaEntrada;
	private FilaControleParaPlanta filaSaida;
        int countStop;
	public Comunicacao(
			PriorityParameters priorityParameters,
			PeriodicParameters periodicParameters,
			Controle controle,
			FilaPlantaParaControle filaEntrada,
			FilaControleParaPlanta filaSaida
	) {
		super(priorityParameters, periodicParameters);

		this.controle = controle;
		this.filaEntrada = filaEntrada;
		this.filaSaida = filaSaida;
                
                countStop=0;
	}

	public void run() {
		while(true) {
                        
			// Despacha todas as mensagens
			if (controle.ligaBombas) {
                            MensagemDoControle msg = MensagemDoControle.OPEN_PUMP;
                            filaSaida.post(msg);
                            controle.ligaBombas = false;
			}

			if (controle.desligaBombas) {
                            MensagemDoControle msg = MensagemDoControle.CLOSE_PUMP;
                            filaSaida.post(msg);
                            controle.desligaBombas = false;
			}
                        
                        if (controle.abreValvula) {
                            MensagemDoControle msg = MensagemDoControle.OPEN_VALVE;
                            filaSaida.post(msg);
                            controle.abreValvula = false;
			}
                        
                        if (controle.fechaValvula) {
                            MensagemDoControle msg = MensagemDoControle.CLOSE_VALVE;
                            filaSaida.post(msg);
                            controle.fechaValvula = false;
			}
                        
                        if (controle.programRead) {
                            MensagemDoControle msg = MensagemDoControle.PROGRAM_READY;
                            filaSaida.post(msg);
                            controle.programRead = false;
			}
                        
                        if (controle.paradaDeEmergencia) {
                            controle.mode = 3;
                            MensagemDoControle msg = MensagemDoControle.MODE;
                            msg.setConteudo(3); //3 representa mode emergency stop
                            filaSaida.post(msg);
                            controle.paradaDeEmergencia = false;
			}
                        
			// Recebe todas as mensagens
			while(!filaEntrada.isEmpty()) {
				MensagemDaPlanta msg = filaEntrada.read();
                                if(msg != MensagemDaPlanta.STOP){
                                    countStop=0;
                                }
				switch(msg) {
                                    case LEVEL:
					controle.nivelAgua = msg.getConteudo();
					break;
                                    case STEAM_BOILER_WAITING:
                                        controle.mode = 1; //SETANDO  mode Iniciando
                                        break;
                                    case PHYSICAL_UNITS_READY:
                                        controle.mode = 2; // SETANDO mode Normal
                                        MensagemDoControle msgC = MensagemDoControle.MODE;
                                        msgC.setConteudo(2); //2 representa mode normal
                                        filaSaida.post(msgC);
                                        break;
                                    case STEAM:
                                        controle.fluxoVapor = msg.getConteudo();
                                        break;
                                    case STOP:
                                        countStop++;
                                        if(countStop==3){
                                            controle.mode = 3; // SETANDO mode EMERGENCIA
                                            MensagemDoControle msgC1 = MensagemDoControle.MODE;
                                            msgC1.setConteudo(3);
                                            filaSaida.post(msgC1);
                                        }   
                                        break;
                                    default:
					break;
				}
			}

			// Espera a proxima iteracao
			System.out.println("Tarefa de comunicação escalonada.");
			waitForNextPeriod();
		}
	}

}
