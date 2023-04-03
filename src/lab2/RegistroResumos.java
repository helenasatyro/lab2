package lab2;
import java.util.Arrays;

/**
* Classe que auxilia no controle de resumos de um aluno, organizada por tema.
* Permite adicionar, contabilizar, buscar e exibir resumos.
*  
* @author Maria Helena Sátyro Gomes Alves
*/
public class RegistroResumos {
	/**
	* Array que irá conter os dados literais dos resumos, o texto em si.
	*/
	private final String[] resumos;
	/**
	* Array que contém os temas dos resumos.
	*/
	private final String[] temas;
	/**
	* Quantidade de vezes que foram adicionados resumos, independente do número de resumos que permanecem.
	*/
	private int numeroRegistros;
	
	/**
	* Constrói um objeto RegistroResumos inicializando e preenchendo com strings vazias dois 
	* arrays de tamanho definido pelo usuário, correspondentes aos resumos e temas. 
	* Também inicializa em zero o número de registros.
	* 
	* @param numeroDeResumos int, a capacidade do array de resumos.
	*/
	
	public RegistroResumos(int numeroDeResumos) {
		this.resumos = new String[numeroDeResumos];
		this.temas = new String[numeroDeResumos];
		Arrays.fill(resumos, "");
		Arrays.fill(temas, "");
		this.numeroRegistros = 0;
	}
	
	/**
	* Sem retorno, adiciona um resumo ao array de resumos, e o tema ao de temas, 
	* também contabiliza a adição na variável numeroRegistros.
	* O tema e o resumo são adicionados a indexes correspondentes nos seus arrays.
	* 
	* @param tema, String o tema do resumo.
	* @param resumo, String o conteúdo do resumo.
	*/
	public void adiciona(String tema, String resumo) {
		// o operador resto permite saber qual o próximo local que deverá ser
		// substituído em caso de sobreposição.
		resumos[numeroRegistros % resumos.length] = resumo;
		temas[numeroRegistros % resumos.length] = tema;

		numeroRegistros++;
	}
	
	/**
	* Método público para obter o array de resumos.
	* 
	* @return retorna o array de resumos com temas.
	*/
	public String[] pegaResumos() {
		String[] retorno = new String[conta()];
		for (int i = 0; i < conta(); i++) {
			retorno[i] = temas[i] + ": " + resumos[i];
		}
		
		return retorno;
	}
	
	/**
	* Retorna a quantidade de resumos registrados
	* Como a implementação do array de resumos nesta classe permite sobreposição, o número
	* de registros pode ser maior que o número de resumos realmente guardados na memória. 
	* Portanto, encontramos o número de resumos guardados procurando o menor entre o número de registros e o
	* tamanho do array.
	*
	* @return em int, a quantidade de resumos registrados na memória.
	*/
	public int conta() {
		return Math.min(numeroRegistros, resumos.length);
	}
	
	/**
	* Retorna uma string representando os resumos / a classe dos registros de resumo como um sumário.
	* A string segue o formato:
	* "-X resumo(s) registrado(s)
	*  - Lista | De | Temas"
	*  
	* @return em string, a representação de um sumário sobre os resumos registrados.
	*/
	public String imprimeResumos() {
		String impressao = "";
		
		for (int i = 0; i < conta(); i++) {
			// implementação imprime temas que se repetem apenas uma vez, pois
			// como lista de conteúdos, é suficiente
			// caso houvesse um sistema de "arquivos" poderiam ser
			// guardados resumos numa matriz de listas de temas?
			if (!impressao.contains(temas[i])) {
				if (! impressao.equals("")) {
					impressao += " | " + temas[i];
				} else {
					impressao += "\n- " + temas[i];
				}
			}
		}
		
		return "- " + conta() + " resumo(s) cadastrado(s)" + impressao;
	}
	
	/**
	* Informa se há resumo sobre o tema buscado.
	*
	* @param buscaTema String, o tema a ser buscado.
	* @return boolean, true se houver resumo sobre aquele tema, false se não houver.
	*/
	public boolean temResumo(String buscaTema) {
		for (String tema : temas) {
			if (tema.equals(buscaTema)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	* Busca a string informada como parâmetro no conteúdo dos resumos, e retorna
	* os temas nos quais foi encontrada a string buscada, em ordem lexicográfica.
	*
	* @param chaveDeBusca String, o trecho a ser buscado.
	* @return array de strings contendo os temas em que a string buscada aparece.
	*/
	public String[] busca(String chaveDeBusca) {
		String[] retorno = new String[conta()];
		int indiceAtual = 0;
		for (int i = 0; i < conta(); i++) {
			if (resumos[i].toUpperCase().contains(chaveDeBusca.toUpperCase()) && !Arrays.asList(retorno).contains(temas[i])) {
				retorno[indiceAtual++] = temas[i];
			}
		}
		Arrays.sort(retorno);
		return retorno;

	}

}
