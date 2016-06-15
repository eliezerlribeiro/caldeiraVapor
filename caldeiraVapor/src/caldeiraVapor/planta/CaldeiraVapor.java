package caldeiraVapor.planta;

import java.util.logging.Level;
import java.util.logging.Logger;
import caldeiraVapor.planta.*;
import caldeiraVapor.mensagens.*;

public class CaldeiraVapor extends Thread {

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

    public CaldeiraVapor(FilaPlantaParaControle paraControle, FilaControleParaPlanta doControle) {

		this.filaParaControle = paraControle;
		this.filaDoControle = doControle;

        totalBombas = 4;
        arrayBombas = new Bomba[totalBombas];
        for(int i =0 ; i < totalBombas; i++ ){
            arrayBombas[i] = new Bomba();
        }
        capacidade = 10000; //Litros
        temperatura = 100; //celsius
        botao = new BotaoEmergencia();
        sensorAgua = new SensorNivelAgua();
        sensorVapor = new SensorVapor();
        valvula = new ValvulaSaidaCaldeira();
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
        while(true) {
			// Recalcula o nivel da agua
			calculaNivel();
			System.out.print("Nível: ");
			System.out.println(sensorAgua.getNivel());

			// Despacha mensagens
			if (filaParaControle.isEmpty()) {
				MensagemDaPlanta msg = MensagemDaPlanta.LEVEL;
				msg.setConteudo(sensorAgua.getNivel());
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
				default:
					break;
				}
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
