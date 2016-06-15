package caldeiraVapor.mensagens;

public enum MensagemDoControle {
	// Lista direto da especificacao
	// nao quer dizer que todas sejam usadas
	UNSPECIFIED_MESSAGE,
	MODE,
	PROGRAM_READY,
	VALVE,
	OPEN_PUMP,
	CLOSE_PUMP,
	PUMP_FAILURE_DETECTION,
	PUMP_CONTROL_FAILURE_DETECTION,
	LEVEL_FAILURE_DETECTION,
	STEAM_FAILURE_DETECTION,
	PUMP_REPAIRED_ACKNOWLEDGEMENT,
	PUMP_CONTROL_REPAIRED_ACKNOWLEDGEMENT,
	LEVEL_REPAIRED_ACKNOWLEDGEMENT,
	STEAM_REPAIRED_ACKNOWLEDGEMENT;

	private int n; // usado quando a mensagem precisa carregar um valor
					// p. ex. qual bomba que tem que abrir
					// o significado varia de acordo com a mensagem

	public void setConteudo(int valor) {
		n = valor;
	}

	public int getConteudo() {
		return n;
	}
}
