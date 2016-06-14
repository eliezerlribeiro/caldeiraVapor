package caldeiraVapor;

public class Simulador {
    public static void main(String args[]) {
        Thread caldeiraSimulada = new CaldeiraVapor ();
        caldeiraSimulada.start();
    }
}
