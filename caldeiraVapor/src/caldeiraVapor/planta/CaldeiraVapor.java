package caldeiraVapor.planta;

import java.util.logging.Level;
import java.util.logging.Logger;
import caldeiraVapor.planta.*;
import caldeiraVapor.mensagens.*;

public class CaldeiraVapor extends Thread {
    Boolean caldeiraLigada;
    Bomba[] arrayBombas;
    BotaoEmergencia botao;
    SensorNivelAgua sensorAgua;
    SensorVapor sensorVapor;
    ValvulaSaidaCaldeira valvula;

	private FilaPlantaParaControle filaParaControle;
	private FilaControleParaPlanta filaDoControle;

    int totalBombas;
    int temperatura;
    int capacidade;
    int mode;
    
    public CaldeiraVapor(FilaPlantaParaControle paraControle, FilaControleParaPlanta doControle) {

		this.filaParaControle = paraControle;
		this.filaDoControle = doControle;

        totalBombas = 4;
        arrayBombas = new Bomba[totalBombas];
        for(int i =0 ; i < totalBombas; i++ ){
            arrayBombas[i] = new Bomba();
        }
        capacidade = 1000; //Litros
        temperatura = 100; //celsius
        botao = new BotaoEmergencia();
        sensorAgua = new SensorNivelAgua();
        sensorVapor = new SensorVapor();
        valvula = new ValvulaSaidaCaldeira();
        caldeiraLigada =true;
        
        //PRIMEIRA COISA QUANDO A CALDEIRA É LIGADA
        MensagemDaPlanta msg = MensagemDaPlanta.STEAM_BOILER_WAITING;
        msg.setConteudo(1); // SETADO MODO INICIANDO
        filaParaControle.post(msg);
        mode = 1;
    }
    
	public synchronized int getNivel() {
		return sensorAgua.getNivel();
	}

    public void enchendoCaldeira() {
        int somatorio = 0;
       for(int i = 0 ; i < totalBombas; i++){
            if(arrayBombas[i].isBombaAberta()){
                somatorio += arrayBombas[i].getVazao();
            }
        }
        int b = sensorAgua.getNivel();
        System.out.println("Litros"+(somatorio+b));
        sensorAgua.setNivel(somatorio+b);
    }

	private void calculaNivel() {
            int variacao = 0;

            for (int i = 0; i < totalBombas; i++) {
                if (arrayBombas[i].isBombaAberta()) {
                    variacao += arrayBombas[i].getVazao();
                }
            }

            variacao -= sensorVapor.getFluxo();

            if (valvula.getAberta()) {
                variacao -= valvula.getFluxo();
            }

            int nivelAtual = sensorAgua.getNivel();
            sensorAgua.setNivel(nivelAtual + variacao);

            return;
	}
    
    public void run() {        
        while(caldeiraLigada) {
            // Recalcula o nivel da agua
            calculaNivel();
            System.out.println("Nível: " + sensorAgua.getNivel());
            System.out.println("Fluxo: "+ sensorVapor.getFluxo());
            System.out.println("FluxoValvula: "+ valvula.getFluxo() + "Abrto? "+valvula.getAberta());
            // Despacha mensagens
            if (filaParaControle.isEmpty()) {
                    MensagemDaPlanta msg = MensagemDaPlanta.LEVEL;
                    msg.setConteudo(sensorAgua.getNivel());
                    filaParaControle.post(msg);

                    msg = MensagemDaPlanta.STEAM;
                    msg.setConteudo(sensorVapor.getFluxo());
                    filaParaControle.post(msg);
            }

            // Recebe mensagens
            while(!filaDoControle.isEmpty()) {
                    MensagemDoControle msg = filaDoControle.read();
                    switch(msg) {
                        case OPEN_PUMP:
                                for (int i = 0; i < totalBombas; i++) {
                                        arrayBombas[i].abre();
                                }
                                break;
                        case CLOSE_PUMP:
                                for (int i = 0; i < totalBombas; i++) {
                                        arrayBombas[i].fecha();
                                }
                                break;
                        case OPEN_VALVE:
                             valvula.setAberta(true);
                            break;
                        case CLOSE_VALVE:
                            valvula.setAberta(false);
                            break;
                        case PROGRAM_READY:
                            MensagemDaPlanta msgP = MensagemDaPlanta.PHYSICAL_UNITS_READY;
                            filaParaControle.post(msgP);
                            break;
                        case MODE:
                            mode = msg.getConteudo();
                            if(mode==2){
                                sensorVapor.setFluxo(13);
                            }
                            
                            if(mode == 3){//MODO PARADA DE EMERGENCIA
                                botao.push();
                            }
                            break;
                        default:
                                break;
                    }
            }
            
            if(botao.getBotaoEmerg()){
                caldeiraLigada = false;
            }

			// Proxima iteracao
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
				// Tratar
            }
        }
    }
    
    
}
