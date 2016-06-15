package caldeiraVapor.mensagens;

import java.util.LinkedList;
import caldeiraVapor.mensagens.MensagemDoControle;

public class FilaControleParaPlanta {
	
	private LinkedList<MensagemDoControle> fila;

	public FilaControleParaPlanta() {
		fila = new LinkedList<>();
	}

	public synchronized void post(MensagemDoControle msg) {
		fila.addLast(msg);
	}

	public synchronized MensagemDoControle read() {
		return fila.removeFirst();
	}

	public synchronized boolean isEmpty() {
		return(fila.size() == 0);
	}
}
