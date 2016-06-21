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

    private Analise tarefaAnalise;
    private Comunicacao tarefaComunicacao;
    private Diagnostico tarefaDiagnostico;

    public int nivelAgua;
    public int nivelAnterior;
    public int fluxoVapor;
    public int mode; // 1 INICIACAO ; 2 NORMAL
    public int falha; /// 1 sensorNivelAgua ; 2 Bomba 1 ;...;

    public boolean[] ligaBomba;
    public boolean[] desligaBomba;
    public boolean[] isBombaAberta;
    public boolean[] deveriaEstarAberta;
    public boolean abreValvula;
    public boolean fechaValvula;
    public boolean programRead;
    public boolean paradaDeEmergencia;

    public Controle(
            FilaPlantaParaControle filaEntrada,
            FilaControleParaPlanta filaSaida
    ) {

        // Inicializa as variaveis
        nivelAgua = 0;
        nivelAnterior = 0;

        ligaBomba = new boolean[4];
        desligaBomba = new boolean[4];
        isBombaAberta = new boolean[4];
        deveriaEstarAberta = new boolean[4];
        for (int i = 0; i < 4; i++) {
            ligaBomba[i] = false;
            desligaBomba[i] = false;
            isBombaAberta[i] = false;
            deveriaEstarAberta[i] = false;
        }

        abreValvula = false;
        fechaValvula = false;
        paradaDeEmergencia = false;
        programRead = false;

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
                                        new RelativeTime(2500 /* ms */, 0 /* ns */),
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
        tarefaAnalise = new Analise(prioridadeAnalise, periodoAnalise, this);

        tarefaComunicacao = new Comunicacao(
                                    prioridadeComunicacao, periodoComunicacao, 
                                    this, filaEntrada, filaSaida);

        tarefaDiagnostico =
                new Diagnostico(prioridadeDiagnostico, periodoDiagnostico, this);

        // Bota as tarefas pra rodar
        tarefaAnalise.start();
        tarefaComunicacao.start();
        tarefaDiagnostico.start();
    }
}
