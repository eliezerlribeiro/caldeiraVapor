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

			// Recebe todas as mensagens
			while(!filaEntrada.isEmpty()) {
				MensagemDaPlanta msg = filaEntrada.read();

				switch(msg) {
				case LEVEL:
					controle.nivelAgua = msg.getConteudo();
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
