package caldeiraVapor;
import caldeiraVapor.planta.CaldeiraVapor;
import caldeiraVapor.controle.Controle;
import caldeiraVapor.mensagens.FilaPlantaParaControle;
import caldeiraVapor.mensagens.FilaControleParaPlanta;

public class Simulador {

	private static FilaPlantaParaControle fila1;
	private static FilaControleParaPlanta fila2;

    public static void main(String args[]) {
      
        // Cria as filas
        fila1 = new FilaPlantaParaControle();
        fila2 = new FilaControleParaPlanta();

        // Cria o controle e a planta
        CaldeiraVapor caldeiraSimulada = new CaldeiraVapor(fila1, fila2);
        caldeiraSimulada.start();
        Controle controle = new Controle(fila1, fila2);
        CaldeiraVaporGui gui = new CaldeiraVaporGui();
        
    }
}

