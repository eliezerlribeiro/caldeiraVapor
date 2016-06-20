package caldeiraVapor;

import caldeiraVapor.planta.*;

import java.net.Socket;
import java.net.ServerSocket;
import java.io.IOException;
import java.io.PrintWriter;

public class ServerThreadRecebe extends Thread {

	protected Socket s;
	protected CaldeiraVapor planta;
	private int porta;

	public ServerThreadRecebe(int porta, CaldeiraVapor planta) throws IOException {
		this.porta =porta;
		this.planta = planta;
	}
	public void run() {
            //Fica esperando que as interfaces conectem
            try {
            ServerSocket listener = new ServerSocket(porta);
            while (true) {
                    Socket socket = listener.accept();
                    new ServerThreadRecebeInit(socket, planta).start();
            }
            } catch (IOException ioe) {
                    System.out.println("Não foi possível estabelecer conectividade com as interfaces gráficas.");
            }
	}

}
