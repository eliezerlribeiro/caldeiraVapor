package caldeiraVapor.mensagens;

public enum MensagemDaPlanta {
	// Lista direto da especificacao
	// nao quer dizer que todas sejam usadas
	UNSPECIFIED_MESSAGE,
	STOP,
	STEAM_BOILER_WAITING,
	PHYSICAL_UNITS_READY,
	PUMP_STATE,
	PUMP_CONTROL_STATE,
	LEVEL,
	STEAM,
	PUMP_REPAIRED,
	PUMP_CONTROL_REPAIRED,
	LEVEL_REPAIRED,
	STEAM_REPAIRED,
	PUMP_FAILURE_ACKNOWLEDGEMENT,
	PUMP_CONTROL_FAILURE_ACKNOWLEDGEMENT,
	LEVEL_FAILURE_ACKNOWLEDGEMENT,
	STEAM_OUTCOME_FAILURE_ACKNOWLEDGEMENT;

	private int n; // usados quando a mensagem precisa carregar um valor
	private boolean b; // p. ex. bomba 'n' esta fechada
					// o significado varia de acordo com a mensagem

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
