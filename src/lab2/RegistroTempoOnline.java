package lab2;
/**
* Auxilia no controle do tempo gasto na internet com uma disciplina remota pelo aluno.
* Para cada disciplina, será criado um objeto para controle desse tempo.
* 
* O objeto deve armazenar o nome da disciplina (string), o tempo online esperado 
* (o dobro das horas da disciplina, por default 120) em horas, e o tempo online 
* registrado, também em horas.
* 
*  
* @author Maria Helena Sátyro Gomes Alves
*/
public class RegistroTempoOnline {
	private final String nomeDisciplina;
	private final int tempoOnlineEsperado;
	private int tempoOnlineRegistrado;
	
	/**
	* Constrói um objeto Registro de Tempo Online a partir do nome da disciplina
	* e opcionalmente, da meta de horas passadas como parâmetro. Caso não sejam passadas as horas, 
	* se utiliza o segundo construtor.
	*
	* @param nomeDisciplina nome da disciplina em string.
	* @param tempoOnlineEsperado meta de tempo online a ser dedicado à disciplina.
	*/
	public RegistroTempoOnline(String nomeDisciplina, int tempoOnlineEsperado) {
		this.nomeDisciplina = nomeDisciplina;
		this.tempoOnlineEsperado = tempoOnlineEsperado;
		this.tempoOnlineRegistrado = 0;
	}
	/**
	* Constrói um objeto Registro de Tempo Online a partir do nome da disciplina,
	* Não recebe número de horas, as define como 120 por default.
	*
	* @param nomeDisciplina da disciplina em string.
	*/
	public RegistroTempoOnline(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
		this.tempoOnlineEsperado = 120;
		this.tempoOnlineRegistrado = 0;
	}
	
	/**
	* Adiciona horas à variável tempoOnlineRegistrado
	*
	* @param tempo a ser registrado.
	*/
	public void adicionaTempoOnline(int tempo) {
		tempoOnlineRegistrado += tempo;
	}
	
	/**
	* Checa se a meta de horas foi atingida.
	* @return boolean, false se não foi atingida, true se foi.
	*/
	public boolean atingiuMetaTempoOnline() {
		return tempoOnlineRegistrado >= tempoOnlineEsperado;
	}
	
	/**
	* Sobrepõe o método toString() de java.
	* @return retorna uma representação do objeto em string.
	*/
	@Override
	public String toString() {
		return nomeDisciplina + " " + tempoOnlineRegistrado + "/" + tempoOnlineEsperado;
	}
	
}