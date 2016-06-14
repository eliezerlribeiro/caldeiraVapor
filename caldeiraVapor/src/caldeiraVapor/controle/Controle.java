package caldeiraVapor.controle;

import caldeiraVapor.controle.*;

import javax.realtime.PriorityScheduler;
import javax.realtime.PriorityParameters;
import javax.realtime.PeriodicParameters;
import javax.realtime.RelativeTime;
import javax.realtime.RealtimeThread;

public class Controle {

	Analise tarefaAnalise;
	Comunicacao tarefaComunicacao;
	Diagnostico tarefaDiagnostico;

	public Controle() {

		// Parametriza a tarefa de comunicacao
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

		// Parametriza a tarefa de analise
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

		// Parametriza a tarefa de diagnostico
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

		// Inicializa as tarefas
	    tarefaAnalise =
				new Analise(prioridadeAnalise, periodoAnalise);

	    tarefaComunicacao = 
				new Comunicacao(prioridadeComunicacao, periodoComunicacao);

	    tarefaDiagnostico = 
				new Diagnostico(prioridadeDiagnostico, periodoDiagnostico);

		// Bota pra rodar
		tarefaAnalise.start();
		tarefaComunicacao.start();
		tarefaDiagnostico.start();
	}
}
