package caldeiraVapor;
import caldeiraVapor.planta.CaldeiraVapor;
import caldeiraVapor.controle.Controle;

public class Simulador {
    public static void main(String args[]) {
        Thread caldeiraSimulada = new CaldeiraVapor ();
        caldeiraSimulada.start();
		Controle controle = new Controle();
    }
}
