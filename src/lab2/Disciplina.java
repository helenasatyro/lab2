package lab2;
import java.util.Arrays;

/**
* Classe que auxilia no controle de informações sobre uma disciplina.
* A classe guarda um array de notas que podem ser repostas, um registro de horas estudadas,
* o nome da disciplina, e algumas funcionalidades pertinentes ao contexto.
*  
* @author Maria Helena Sátyro Gomes Alves
*/
public class Disciplina {
	/**
	* Constante que contém a média mínima para ser aprovado na disciplina.
	*/
	final private double MEDIA_MINIMA = 7.0;
	/**
	* Array de notas da disciplina. Até quatro.
	*/
	private double[] notas;
	/**
	* Horas de estudo cadastradas.
	*/
	private int horasCadastradas;
	/**
	* Nome da disciplina em string.
	*/
	private String nomeDisciplina;
	private double[] pesos;
	
	/**
	* Constrói um objeto Disciplina a partir do nome, quantidade de notas 
	* e array de pesos passados como parâmetros.
	*
	* @param nome da disciplina em string.
	* @param quantidade de notas em int.
	* @param array de pesos em double (passados por nome).
	*/
	public Disciplina(String nomeDisciplina, int quantNotas, double[] pesos) {
		this.nomeDisciplina = nomeDisciplina;
		this.horasCadastradas = 0;
		this.notas = new double[quantNotas];
		this.pesos = pesos;
	}
	
	/**
	* Constrói um objeto Disciplina a partir do nome e quantidade de notas passado como parâmetros e dados pré definidos.
	*
	* @param nome da disciplina em string.
	* @param quantidade de notas em int
	*/
	public Disciplina(String nomeDisciplina, int quantNotas) {
		this(nomeDisciplina, quantNotas, new double[quantNotas]);
		Arrays.fill(pesos, 1.0);
	}
	
	/**
	* Constrói um objeto Disciplina a partir do nome passado como parâmetro 
	* e dados pré definidos.
	*
	* @param nome da disciplina em string.
	*/
	public Disciplina(String nomeDisciplina) {
		this(nomeDisciplina, 4, new double[4]);
		Arrays.fill(pesos, 1.0);
	}
	
	/**
	* Sem retorno, cadastra uma nova nota ao array de notas, acessado por posição.
	*
	* @param em int, a nota (dentre as 4 possíveis) a ser adicionada, tratamos com -1 para corresponder à indexação do array.
	* @param em double, o valor da nota a ser adicionada.
	*/
	public void cadastraNota(int nota, double valorNota) {
		notas[nota - 1] = valorNota;
	}
	
	/**
	* Sem retorno, adiciona horas de estudo ao registro (cumulativo).
	*
	* @param em int, a quantidade de horas a serem somadas.
	*/
	public void cadastraHoras(int horas) {
		horasCadastradas += horas;
	}
	
	/**
	* Retorna a média entre as notas, não arredondada.
	*
	* @return em double, a média entre as notas, com ou sem peso.
	*/
	private double media() {
		double soma = 0;
		double somaPesos = 0;
		for (int i = 0; i < notas.length; i++) {
			soma += notas[i] * pesos[i];
			somaPesos += pesos[i];
		}
		return soma / somaPesos;
	}
	
	/**
	* Retorna a situação de aprovação.
	*
	* @return boolean, false se foi reprovado, true se foi aprovado.
	*/
	public boolean aprovado() {
		if (media() >= MEDIA_MINIMA) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	* Retorna a representação em string do objeto disciplina.
	*
	* @return em string, a representação com nome, horas, média e notas na disciplina.
	*/
	@Override
	public String toString() {
		return nomeDisciplina + " " + horasCadastradas + " " + media() + " " + Arrays.toString(notas);
	}
	
}
