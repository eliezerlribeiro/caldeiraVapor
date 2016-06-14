
package caldeiraVapor;

public class Simulador {
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new caldeiraVaporGui().setVisible(true);
            }
        });
        
        
    }
}
