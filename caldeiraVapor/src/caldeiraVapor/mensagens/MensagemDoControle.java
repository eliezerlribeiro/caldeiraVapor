package caldeiraVapor.mensagens;

import caldeiraVapor.mensagens.TipoDeMensagem;

public class MensagemDoControle {

	public TipoDeMensagem tipo;
	private int n; // usado quando a mensagem precisa carregar um valor
    private boolean b; // p. ex. qual bomba que tem que abrir
					// o significado varia de acordo com a mensagem

	public MensagemDoControle(TipoDeMensagem tipo) {
		this.tipo = tipo;
	}
        
	public void setConteudo(int valor) {
		n = valor;
	}

	public int getConteudo() {
		return n;
	}
        
        public boolean getFlag() {
		return b;
	}
}
