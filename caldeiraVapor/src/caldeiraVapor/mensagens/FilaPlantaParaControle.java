package caldeiraVapor.mensagens;

import java.util.LinkedList;
import caldeiraVapor.mensagens.MensagemDaPlanta;

public class FilaPlantaParaControle {
	
	private LinkedList<MensagemDaPlanta> fila;

	public FilaPlantaParaControle() {
		fila = new LinkedList<>();
	}

	public synchronized void post(MensagemDaPlanta msg) {
		fila.addLast(msg);
	}

	public synchronized MensagemDaPlanta read() {
		return fila.removeFirst();
	}

	public synchronized boolean isEmpty() {
		return(fila.size() == 0);
	}
}
