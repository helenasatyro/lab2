package lab2;
/**
* Classe que pretende auxiliar no 
* controle do tempo gasto na internet com uma disciplina remota pelo aluno. 
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
	/**
	* Nome da disciplina em string.
	*/
	private String nomeDisciplina;
	/**
	* Meta de tempo online que deve ser dedicado à disciplina, em horas.
	* Por default tratamos de disciplinas de 60 horas, onde a meta é o dobro, 120.
	* Pode ser passado como parâmetro do construtor.
	*/
	private int tempoOnlineEsperado;
	/**
	* Tempo online contabilizado em horas. Inicia em zero.
	*/
	private int tempoOnlineRegistrado;
	
	/**
	* Constrói um objeto Registro de Tempo Online a partir do nome da disciplina
	* e opcionalmente, da meta de horas passadas como parâmetro. Caso não sejam passadas as horas, 
	* se utiliza o segundo construtor.
	*
	* @param nome da disciplina em string.
	* @param meta de tempo online a ser dedicado à disciplina.
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
	* @param nome da disciplina em string.
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
	* @return void
	*/
	public void adicionaTempoOnline(int tempo) {
		tempoOnlineRegistrado += tempo;
	}
	
	/**
	* Checa se a meta de horas foi atingidas.
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