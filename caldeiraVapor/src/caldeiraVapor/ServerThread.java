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
		while (true) {
			out.print("LEVEL,");
			out.println(planta.getNivel());
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
