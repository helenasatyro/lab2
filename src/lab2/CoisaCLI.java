package lab2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Classe que implementa uma interface de Linha de Comando
 * para auxiliar na organização de estudantes.
 *
 * @author Maria Helena Sátyro Gomes Alves
 */
public class CoisaCLI {
    public static void main(String[] args) {
        // Inicialização dos ArrayLists para o menu:
        ArrayList<RegistroTempoOnline> registrosTempo = new ArrayList<>();
        Descanso registroDescanso = new Descanso();
        ArrayList<Disciplina> disciplinas = new ArrayList<>();
        RegistroResumos resumos = new RegistroResumos(0);

        // Inicialização do menu
        CoisaCLI menu = new CoisaCLI(registrosTempo, registroDescanso, disciplinas, resumos);
        menu.abrirMenu();
    }
    /**
     * Constante ArrayList contendo os objetos RegistroTempoOnline para o menu de tempo online.
     */
    private final ArrayList<RegistroTempoOnline> registrosTempoOnline;

    /**
     * Constante ArrayList contendo os objetos Disciplina para o menu de disciplinas.
     */
    private final ArrayList<Disciplina> disciplinas;

    /**
     * Constante Descanso para controle de informações sobre descanso.
     */
    private final Descanso registrosDescanso;

    /**
     * Objeto RegistroResumos para controle de resumos.
     */
    private RegistroResumos resumos;

    /**
     * Scanner que será usado na interação com o usuário.
     */
    private final Scanner sc = new Scanner(System.in);

    /**
     * Construtor de menu, aceita como parâmetros arrays de objetos que podem ser usados no programa.
     * Inicializa os ArrayLists e imprime a mensagem de abertura do programa.
     *
     * @param registrosTempoOnline ArrayList de objetos do tipo RegistroTempoOnline, pré-inicializado.
     * @param registrosDescanso objeto Descanso.
     * @param disciplinas ArrayList de objetos Disciplinas.
     * @param resumos objeto resumos inicializado com 0 de espaço.
     */
    public CoisaCLI(ArrayList<RegistroTempoOnline> registrosTempoOnline, Descanso registrosDescanso, ArrayList<Disciplina> disciplinas, RegistroResumos resumos ) {
        this.registrosTempoOnline = registrosTempoOnline;
        this.registrosDescanso = registrosDescanso;
        this.disciplinas = disciplinas;
        this.resumos = resumos;
        // Texto de cabeçalho da CLI
        System.out.println("""

                		      └[ ∵ ]┘\s
                -- Olá! Sou RoBetinho, seu assistente acadêmico!"""
                );
    }

    /**
     * Exibe o menu inicial e aguarda a escolha de uma opção.
     */
    public void abrirMenu() {
        String textoMenu = """

                -- Como posso ajudá-lo?

                 	1. DISCIPLINAS
                 	2. RESUMOS
                 	3. DESCANSO
                 	4. TEMPO ONLINE

                 Digite uma opção (-1 para sair):\s""";

        System.out.print(textoMenu);
        String escolha = sc.nextLine();

// A única camada de proteção dessa cli contra entradas
// inválidas é encerrar o programa, o que não é uma forma ideal de se tratar erros.
// Por motivos de simplicidade da implementação, não foi adicionado tratamento de erros.
        switch (escolha) {
            case "1" -> menuDisciplinas();
            case "2" -> menuResumos();
            case "3" -> menuDescanso();
            case "4" -> menuTempoOnline();
            default -> sair();
        }
    }

    /**
     * Exibe o menu para controle de tempo online.
     */
    private void menuTempoOnline() {
        String textoRegistros = "";
        if (registrosTempoOnline.size() != 0) {
            for (int i = 0; i < registrosTempoOnline.size(); i++) {
                textoRegistros += " 	" + (i + 2) + ". " + registrosTempoOnline.get(i) + "\n";
            }
        }

        String menuTempo = "\n"
                + "-- TEMPO ONLINE --\n"
                + " 	1. Criar Registro\n"
                + textoRegistros
                + " 	0. Voltar ao menu."
                + " Digite uma opção (-1 para sair): ";

        System.out.print(menuTempo);

        int escolha = Integer.parseInt(sc.nextLine());

        switch (escolha) {
            case 1 -> registrosTempoOnline.add(novoRegistro());
            case 0 -> this.abrirMenu();
            case -1 -> sair();
            default -> {
                System.out.println("\n--O que deseja fazer com " + registrosTempoOnline.get(escolha - 2) + "?");
                System.out.print(
                        """
                                        
                                ADD <horas> --Adicionar horas:\s
                                VER --Verificar se a meta foi cumprida:\s
                                 A:\s"""
                );
                String comando = sc.next();
                switch (comando.toUpperCase()) {
                    case "ADD" -> {
                        registrosTempoOnline.get(escolha - 2).adicionaTempoOnline(sc.nextInt());
                        sc.nextLine();
                        System.out.println("Adicionado.");
                    }
                    case "VER" ->
                            System.out.println("\nMeta atingida: " + registrosTempoOnline.get(escolha - 2).atingiuMetaTempoOnline());
                }
                menuTempoOnline();
            }
        }
    }

    /**
     * Implementa uma interface de usuário para o construtor de RegistroTempoOnline
     * @return objeto RegistroTempoOnline que será adicionado ao array de registros de tempo.
     */
    private RegistroTempoOnline novoRegistro() {
        RegistroTempoOnline registro;

        System.out.print("\n--Nome da disciplina: ");
        String nome = sc.nextLine();
        System.out.print("--Tempo online esperado (enter para omitir): ");
        String strHoras = sc.nextLine();

        if (strHoras.equals("")) {
            registro = new RegistroTempoOnline(nome);
        } else {
            registro = new RegistroTempoOnline(nome, Integer.parseInt(strHoras));
        }
        return registro;
    }

    /**
     * Exibe o menu para interação com o objeto Descanso.
     */
    private void menuDescanso() {
        System.out.print("""

                -- DESCANSO --
                     C --Consultar status geral
                     H <horas> --Definir Horas de Descanso (ex: 2 14 define 14 horas)
                     S <semanas> --Definir Número de Semanas
                     E <emoji> --Definir emoji
                     0. Voltar ao menu.
                 Digite um comando (-1 para sair):\s"""
        );

        String escolha = sc.next();

        switch (escolha.toUpperCase()) {
            case "C" -> {
                System.out.println("    >>>STATUS: " + registrosDescanso.getStatusGeral());
                sc.nextLine();
                menuDescanso();
            }
            case "H" -> {
                int horas = sc.nextInt();
                sc.nextLine();
                registrosDescanso.defineHorasDescanso(horas);
                System.out.println("    >>>Adicionadas " + horas + " horas.");
                menuDescanso();
            }
            case "S" -> {
                int semanas = sc.nextInt();
                sc.nextLine();
                registrosDescanso.defineNumeroSemanas(semanas);
                System.out.println("    >>>Adicionadas " + semanas + " semanas.");
                menuDescanso();
            }
            case "E" -> {
                String emoji = sc.nextLine();
                registrosDescanso.definirEmoji(emoji);
                System.out.println("    >>>Definido emoji:  " + emoji);
                menuDescanso();
            }
            case "0" -> {
                sc.nextLine();
                this.abrirMenu();
            }
            default -> sair();
        }
        // Observação:
        // A repetição do método menuDescanso pode parecer redundante, mas colocá-la aqui no final pode
        // gerar um ciclo recursivo se, por exemplo, repetidamente chamarmos abrirMenu() e menuDescanso(), o método
        // menuDescanso terminaria chamando a si mesmo, mesmo se saíssemos para o menu.
        // Essa implementação alternativa está presente no método menuResumos,
        // note que a IDE exibe um aviso sobre essa falha.
    }

        private void menuResumos () {
        if (resumos.conta() == 0) {
            System.out.print("  --Quantos resumos você deseja guardar?\n  :");
            resumos = new RegistroResumos(sc.nextInt());
            sc.nextLine();
        }
            String menuResumos = "\n"
                    + "-- RESUMOS --\n"
                    + "     Há " + resumos.conta() + " resumo(s) registrado(s).\n"
                    + "     1. Adicionar Resumo\n"
                    + "     2. Buscar por conteúdo\n"
                    + "     3. Buscar por tema\n"
                    + "     4. Ver todos os resumos\n"
                    + "     5. Ver todos os temas\n"
                    + "     0. Voltar ao menu\n"
                    + " Digite uma opção (-1 para sair): ";

            System.out.print(menuResumos);
            String escolha = sc.nextLine();

            switch (escolha) {
                case "1" -> {
                    System.out.println("Qual é o tema do resumo? ");
                    String tema = sc.nextLine();
                    System.out.println("Digite o resumo abaixo: ");
                    String conteudo = sc.nextLine();
                    resumos.adiciona(tema, conteudo);
                    System.out.println("    >>> RESUMO ADICIONADO");
                }
                case "2" -> {
                    System.out.println("    --Buscar: ");
                    String[] encontrados = resumos.busca(sc.nextLine());
                    if (encontrados.length == 0) {
                        System.out.println("Não há nenhum resumo contendo o trecho pesquisado :(");
                    } else {
                        for (String resumo : encontrados) {
                            System.out.println("Encontrado no tema: " + resumo);
                        }
                    }
                }
                case "3" -> {
                    System.out.println("    --Buscar: ");
                    if (resumos.temResumo(sc.nextLine())) {
                        System.out.println("    --Encontrei um ou mais resumos sobre esse tema :D");
                    } else {
                        System.out.println("    --Não encontrei nenhum resumo com esse tema :(");
                    }
                }
                case "4" -> {
                    for (String resumo : resumos.pegaResumos()) {
                        System.out.println(resumo);
                    }
                }
                case "5" -> System.out.println(resumos.imprimeResumos());
                case "0" -> this.abrirMenu();
                case "-1" -> sair();
            }
            menuResumos();  // implementação recursiva mantida de forma ilustrativa,
                            // poderia gerar gastos exagerados de memória.
                            // Na prática, esse programa é pequeno e tem propósitos educativos, então decidi manter
                            // a implementação para poder comentar sobre.
        }

    /**
     * Exibe o menu para controle de notas, pesos e tempo dedicado a uma disciplina.
     */
    private void menuDisciplinas () {
            String textoDisciplinas = "";

            if (disciplinas.size() != 0) {
                for (int i = 0; i < disciplinas.size(); i++) {
                    textoDisciplinas += " 	" + (i + 2) + ". " + disciplinas.get(i) + "\n";
                }
            }
            // Ao longo do programa, diversas formas de escrever blocos de texto foram implementadas.
            // A forma abaixo é mais intuitiva em alguns casos, mas também há o bloco de três aspas duplas
            // que é consideravelmente mais agradável esteticamente.
            String menuDisciplinas =
                    "\n"
                    + "-- DISCIPLINAS --\n"
                    + " 	1. Adicionar Disciplina\n"
                    + textoDisciplinas
                    + " 	0. Voltar ao menu."
                    + " Digite uma opção (-1 para sair): ";

            System.out.print(menuDisciplinas);

            int escolha = Integer.parseInt(sc.nextLine());
            switch (escolha) {
                case 1 -> {
                    disciplinas.add(novaDisciplina());
                    menuDisciplinas();
                }
                case 0 -> this.abrirMenu();
                case -1 -> sair();

                default -> {
                    System.out.println("\n--O que deseja fazer com esta disciplina?");
                    System.out.println(
                            """
                                            
                                    NOTA <nota int> <valor double com vírgula> --Adicionar nota:\s
                                    HORAS --Verificar se a meta foi cumprida:\s
                                    APR --Verificar se foi aprovado:\s
                                        Digite um comando (-1 para sair):"""
                    );
                    String comando = sc.next();
                    switch (comando.toUpperCase()) {
                        case "NOTA" -> {
                            disciplinas.get(escolha - 2).cadastraNota(sc.nextInt(), sc.nextDouble());
                            sc.nextLine();
                            System.out.println("    >>>Adicionadas.");
                        }
                        case "HORAS" -> {
                            disciplinas.get(escolha - 2).cadastraHoras(sc.nextInt());
                            sc.nextLine();
                            System.out.println("    >>>Adicionadas.");
                        }
                        case "APR" -> {
                            System.out.println("    >>>APROVADO?: " + disciplinas.get(escolha - 2).aprovado());
                            sc.nextLine();
                        }
                    }
                    menuDisciplinas();
                }
            }

        }

    /**
     * Implementa uma interface de usuário para o construtor de um objeto Disciplina, que será adicionado ao array de disciplinas.
     * @return Disciplina
     */
    private Disciplina novaDisciplina() {
        Disciplina disciplina;

        System.out.print("\n--Nome da disciplina: ");
        String nome = sc.nextLine();
        System.out.print("--Quantidade de notas(enter para omitir): ");
        String strQtdNotas = sc.nextLine();
        System.out.println("--Especificar pesos? (s/n): ");
        String especificarPesos = sc.nextLine();

        if(strQtdNotas.equals("")) {
            strQtdNotas = "4";
        }
        int qtdNotas = Integer.parseInt(strQtdNotas);
        double[] pesos = new double[qtdNotas];

        if (especificarPesos.equals("s")) {
            for(int i = 0; i < qtdNotas; i++) {
                System.out.println("Peso da " + (i + 1) + "a nota: ");
                pesos[i] = Double.parseDouble(sc.nextLine());
            }
        } else {
            Arrays.fill(pesos, 1.0);
        }
        disciplina = new Disciplina(nome, qtdNotas, pesos);
        return disciplina;
    }

    /**
     * Exibe uma mensagem de despedida, fecha o scanner e encerra o programa sem erros.
     */
    private void sair () {
            System.out.println("\n-- Até a próxima!\n\n			Cc--- [┐∵]┘");
            sc.close();
            System.exit(0);
        }

}

