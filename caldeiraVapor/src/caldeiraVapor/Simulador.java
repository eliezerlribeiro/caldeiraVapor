package caldeiraVapor;

import caldeiraVapor.planta.CaldeiraVapor;
import caldeiraVapor.controle.Controle;
import caldeiraVapor.mensagens.FilaPlantaParaControle;
import caldeiraVapor.mensagens.FilaControleParaPlanta;
import caldeiraVapor.ServerThread;

import java.net.Socket;
import java.net.ServerSocket;
import java.io.IOException;
import java.io.PrintWriter;

public class Simulador {

    private static FilaPlantaParaControle fila1;
    private static FilaControleParaPlanta fila2;

    public static void main(String args[]) throws IOException {
      
        // Cria as filas
        fila1 = new FilaPlantaParaControle();
        fila2 = new FilaControleParaPlanta();

        // Cria o controle e a planta
        CaldeiraVapor caldeiraSimulada = new CaldeiraVapor(fila1, fila2);
        caldeiraSimulada.start();
        Controle controle = new Controle(fila1, fila2);
        
                
	ServerThreadRecebe recebeDaGui = new ServerThreadRecebe(31314, caldeiraSimulada);
        recebeDaGui.start();
        
        //Fica esperando que as interfaces conectem
        ServerSocket listener = new ServerSocket(31313);
        try {
            while (true) {
                    Socket socket = listener.accept();
                    new ServerThread(socket, caldeiraSimulada).start();
            }
        } catch (IOException ioe) {
            System.out.println("Não foi possível estabelecer conectividade com as interfaces gráficas.");
        } finally {
            listener.close();
        }

    }
}

