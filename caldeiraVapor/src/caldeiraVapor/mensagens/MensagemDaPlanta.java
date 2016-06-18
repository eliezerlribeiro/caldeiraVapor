package caldeiraVapor.mensagens;

import caldeiraVapor.mensagens.TipoDeMensagem;

public class MensagemDaPlanta {

	public TipoDeMensagem tipo;
	private int n; // usados quando a mensagem precisa carregar um valor
	private boolean b; // p. ex. bomba 'n' esta fechada
					// o significado varia de acordo com a mensagem

	public MensagemDaPlanta(TipoDeMensagem tipo) {
		this.tipo = tipo;
	}

	public void setConteudo(int valor) {
		n = valor;
	}

	public void setConteudo(int valor, boolean flag) {
		n = valor;
		b = flag;
	}

	public int getConteudo() {
		return n;
	}

	public boolean getFlag() {
		return b;
	}
}
