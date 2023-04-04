package lab2;

/**
* Classe que pretende auxiliar no 
* controle das horas de descanso para gerenciamento de tempo
* de um aluno. O aluno está cansado por default
* caso não tenha registrado horas de descanso ou 
* número de semanas.
*  
* @author Maria Helena Sátyro Gomes Alves
*/

public class Descanso {
	private int horasDescanso;
	private int numeroSemanas;
	private String statusGeral;
	private String emoji;
	/**
	* Constrói um objeto Descanso, sem parâmetros. 
	* O objeto começa com zero horas e zero semanas contabilizadas, 
	* o status geral cansado, e sem emoji.
	*/
	public Descanso() {
		this.horasDescanso = 0;
		this.numeroSemanas = 0;
		this.statusGeral = "cansado";
		this.emoji = "";
	}
	
	/**
	* Retorna a String que representa o estado de descanso do aluno. 
	* A representação informa "cansado" caso não tenha atingido a meta de horas de 
	* descanso semanal, e "descansado" caso tenha.
	* Além disso, informa o emoji escolhido pelo usuário, separado por um espaço 
	* e um hífen após o estado geral.
	*
	* @return a situação de descanso e o emoji quando aplicável.
	*/
	public String getStatusGeral() {
		String ultimoStatus = String.valueOf(statusGeral);
		if (this.numeroSemanas != 0) {
			if (horasDescanso / numeroSemanas >= 26) {
				statusGeral = "descansado";
			} else {
				statusGeral = "cansado";
			}
		} else {
			statusGeral = "cansado";
		}
		
		// se o estado de cansaço muda, o emoji é removido
		if (!ultimoStatus.equals(statusGeral) | emoji.equals("")) {
			definirEmoji("");
			return statusGeral;
		// caso contrário, o emoji é adicionado ao estado com sua formatação
		} else {
			return statusGeral + " -" + emoji;
		}
		
	}
	
	/**
	* Setter das horas de descanso, recebe o número de horas descansadas.
	* @param horas descansadas, em int.
	*/
	public void defineHorasDescanso(int horas) {
		horasDescanso = horas;
		
	}
	
	/**
	* Setter do emoji, recebe o emoji como string, o emoji é resetado quando troca o
	* estado geral de descanso.
	* @param emoji em string.
	*/
	public void definirEmoji(String emoji) {
		if (!emoji.equals("")) {
			this.emoji = emoji;
		} else {
			this.emoji = "";
		}
		
	}
	
	/**
	* Setter do numero de semanas, recebe o número de semanas.
	* @param numero de semanas, em int.
	*/
	public void defineNumeroSemanas(int numero) {
		numeroSemanas = numero;
	}
	
}
