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

public class ClienteThread extends Thread  {
	protected CaldeiraVaporGui plantaGui;
        private int porta;

	public ClienteThread(CaldeiraVaporGui plantaGui, int porta) throws IOException {
                this.porta=porta;
		this.plantaGui = plantaGui;
	}
        
        public ClienteThread(int porta) throws IOException {
                this.porta=porta;
	}

	public void run()  {

            try {
            Socket s = new Socket("127.0.0.1", porta);
        
                BufferedReader input =
                new BufferedReader(new InputStreamReader(s.getInputStream()));

                while (true) {
                    String answer = input.readLine();
                    String words[] = answer.split(",");
                 
                    //SPLIT answer
                    switch(words[0]) {
                         case "MODO":
                                plantaGui.setModo(words[1]);
                                System.out.println(answer);
                            break;
                        case "LEVEL":
                                plantaGui.setLevel(words[1]);
                                System.out.println(answer);
                            break;
                        case "BOMBA":
                                plantaGui.setBombaAberta(Integer.parseInt(words[1]));
                                 System.out.println(answer);
                            break;
                        case "VAPOR":
                                plantaGui.setVapor(words[1]);
                                 System.out.println(answer);
                            break;
                        case "VALVULA":
                                plantaGui.setValvula(words[1]);
                                System.out.println(answer);
                            break;
                        case "CAPACIDADE":
                                plantaGui.setCapacidade(words[1]);
                                System.out.println(answer);
                            break;
                        default:
                            break;
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(ClienteThread.class.getName()).log(Level.SEVERE, null, ex);
            }
	}

}
