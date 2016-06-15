package caldeiraVapor.controle;

import caldeiraVapor.controle.*;
import caldeiraVapor.mensagens.FilaControleParaPlanta;
import caldeiraVapor.mensagens.FilaPlantaParaControle;

import javax.realtime.PriorityScheduler;
import javax.realtime.PriorityParameters;
import javax.realtime.PeriodicParameters;
import javax.realtime.RelativeTime;
import javax.realtime.RealtimeThread;

public class Controle {

	Analise tarefaAnalise;
	Comunicacao tarefaComunicacao;
	Diagnostico tarefaDiagnostico;

	public int nivelAgua;
	public boolean ligaBombas;
	public boolean desligaBombas;

	public Controle(FilaPlantaParaControle filaEntrada, FilaControleParaPlanta filaSaida) {

		// Parametriza a tarefa de comunicacao (diz o periodo e a prioridade)
		PriorityParameters prioridadeComunicacao = 
				new PriorityParameters(
						PriorityScheduler.instance().getMinPriority()+10
				);

		PeriodicParameters periodoComunicacao =
				new PeriodicParameters(
						null,
						new RelativeTime(5000 /* ms */, 0 /* ns */),
						null,null,null,null
				);

		// Parametriza a tarefa de analise (diz o periodo e a prioridade)
		PriorityParameters prioridadeAnalise = 
				new PriorityParameters(
						PriorityScheduler.instance().getMinPriority()+10
				);

		PeriodicParameters periodoAnalise =
				new PeriodicParameters(
						null,
						new RelativeTime(5000 /* ms */, 0 /* ns */),
						null,null,null,null
				);

		// Parametriza a tarefa de diagnostico (diz o periodo e a prioridade)
		PriorityParameters prioridadeDiagnostico = 
				new PriorityParameters(
						PriorityScheduler.instance().getMinPriority()+10
				);

		PeriodicParameters periodoDiagnostico =
				new PeriodicParameters(
						null,
						new RelativeTime(5000 /* ms */, 0 /* ns */),
						null,null,null,null
				);

		// Inicializa as tarefas (aplica os parametros acima)
	    tarefaAnalise =
				new Analise(prioridadeAnalise, periodoAnalise, this);

	    tarefaComunicacao = 
				new Comunicacao(
					prioridadeComunicacao, periodoComunicacao, 
					this, filaEntrada, filaSaida
				);

	    tarefaDiagnostico = 
				new Diagnostico(prioridadeDiagnostico, periodoDiagnostico, this);

		// Bota as tarefas pra rodar
		tarefaAnalise.start();
		tarefaComunicacao.start();
		tarefaDiagnostico.start();
	}
}
