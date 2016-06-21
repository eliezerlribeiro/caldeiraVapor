package caldeiraVapor;

import caldeiraVapor.planta.*;

import java.net.Socket;
import java.net.ServerSocket;
import java.io.IOException;
import java.io.PrintWriter;

public class ServerThread extends Thread {

    protected Socket s;
    protected CaldeiraVapor planta;
    private PrintWriter out;

    public ServerThread(Socket s, CaldeiraVapor planta) throws IOException {
        this.s = s;
        this.planta = planta;
        this.out = new PrintWriter(this.s.getOutputStream(), true);
    }

    public void run() {
        out.print("CAPACIDADE,");
        out.println(planta.getCapacidade());
        
        while (true) {
            
            out.print("MODO,");
            out.println(planta.getModo());
            //Enviar LEVEL
            out.print("LEVEL,");
            out.println(planta.getNivel());
            //Enviar Bombas Abertas
            for (int i = 0; i < 4; i++) {
                out.print("BOMBA,");
                out.print(i);
                if (planta.getBombaAberta(i))
                    out.println(",ABERTA");
                else
                    out.println(",FECHADA");
            }
            //Enviar FluxoVapor
            out.print("VAPOR,");
            out.println(planta.getFluxoVapor());
            //Enviar valvula aberta
            out.print("VALVULA,");
            out.println(planta.getValvula());
            
            if (out.checkError()) {
                System.out.println("Desconexão de cliente");
                try {
                    s.close();
                } catch (IOException ioe) {
                    //
                }
                return;
            }
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                // sem problemas aqui na verdade
            }
        }
    }

}
