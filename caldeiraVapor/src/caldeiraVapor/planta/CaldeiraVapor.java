package caldeiraVapor.planta;

import java.util.logging.Level;
import java.util.logging.Logger;
import caldeiraVapor.planta.*;

public class CaldeiraVapor extends Thread {

    Bomba[] arrayBombas;
    BotaoEmergencia botao;
    SensorNivelAgua sensorAgua;
    SensorVapor sensorVapor;
    ValvulaSaidaCaldeira valvula;

    int totalBombas;
    int temperatura;
    int capacidade;

    public CaldeiraVapor() {
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

			// Essa tarefa de abrir e fechar as bombas eh do controle
			// estah aqui so para demonstracao
            if (sensorAgua.getNivel() >= 150) {
				for (int i = 0; i < totalBombas; i++) {
					arrayBombas[i].fecha();
				}
            } else if (sensorAgua.getNivel() <= 20) {
				for (int i = 0; i < totalBombas; i++) {
					arrayBombas[i].abre();
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
