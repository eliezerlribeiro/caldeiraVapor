package caldeiraVapor;

import caldeiraVapor.planta.*;
import java.io.BufferedReader;

import java.net.Socket;
import java.net.ServerSocket;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerThreadRecebeInit extends Thread {

    protected Socket s;
    protected CaldeiraVapor planta;

    public ServerThreadRecebeInit(Socket s, CaldeiraVapor planta) throws IOException {
        this.s = s;
        this.planta = planta;
    }

    public void run() {
        try {
            BufferedReader input =
                new BufferedReader(new InputStreamReader(s.getInputStream()));

            String answer = input.readLine();
            String words[] = answer.split(",");

            //SPLIT answer
            switch(words[0]) {
            case "BOMBA":
                if (words[2].equals("ESTRAGA"))
                    planta.estragaBomba(Integer.parseInt(words[1]) - 1);
                else
                    planta.consertaBomba(Integer.parseInt(words[1]) - 1);
                System.out.println(answer);
                break;
            case "VALVULA":
                planta.setValvula(words[1]);
                System.out.println(answer);
                break;
            default:
                break;
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerThreadRecebeInit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
