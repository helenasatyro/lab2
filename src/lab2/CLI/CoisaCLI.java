package lab2.CLI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import lab2.*;

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

        CoisaCLI menu = new CoisaCLI(registrosTempo, registroDescanso, disciplinas, resumos);
        menu.abrirMenu();
    }
    /**
     * Constante ArrayList contendo os objetos para o menu de tempo online.
     */
    private final ArrayList<RegistroTempoOnline> registrosTempoOnline;
    private final Descanso registrosDescanso;
    /**
     * Scanner que será usado na interação com o usuário.
     */
    private final Scanner sc = new Scanner(System.in);
    private final ArrayList<Disciplina> disciplinas;
    private RegistroResumos resumos;
    /**
     * Construtor de menu, aceita como parâmetros arrays de objetos que podem ser usados no programa.
     * Inicializa os ArrayLists e imprime a mensagem de abertura do programa.
     *
     * @param registrosTempoOnline ArrayList de objetos do tipo RegistroTempoOnline, pré-inicializado
     */
    public CoisaCLI(ArrayList<RegistroTempoOnline> registrosTempoOnline, Descanso registrosDescanso, ArrayList<Disciplina> disciplinas, RegistroResumos resumos ) {
        this.registrosTempoOnline = registrosTempoOnline;
        this.registrosDescanso = registrosDescanso;
        this.disciplinas = disciplinas;
        this.resumos = resumos;
        System.out.println("""

                		      └[ ∵ ]┘\s
                -- Olá! Sou RoBetinho, seu assistente acadêmico!"""
                );
    }
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

        switch (escolha) {
            case "1" -> menuDisciplinas();
            case "2" -> menuResumos();
            case "3" -> menuDescanso();
            case "4" -> menuTempoOnline();
            default -> sair();
        }
    }

    /**
     *
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
        int escolha = sc.nextInt();
        sc.nextLine();
        switch (escolha) {
            case 1 -> {
                registrosTempoOnline.add(novoRegistro());
                menuTempoOnline();
            }
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
                String acao = sc.next();
                switch (acao.toUpperCase()) {
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

    private RegistroTempoOnline novoRegistro() {
        RegistroTempoOnline registro;
        System.out.print("\n--Nome da disciplina: ");
        String nome = sc.nextLine();
        System.out.print("--Tempo online esperado (enter para omitir): ");
        String strHoras = sc.nextLine();
        if (!strHoras.equals("")) {
            registro = new RegistroTempoOnline(nome, Integer.parseInt(strHoras));
        } else {
            registro = new RegistroTempoOnline(nome);
        }
        return registro;
    }

    private void menuDescanso() {
        String textoDescanso = """

                -- DESCANSO --
                     C --Consultar status geral
                     H <horas> --Definir Horas de Descanso (ex: 2 14 define 14 horas)
                     S <semanas> --Definir Número de Semanas
                     E <emoji> --Definir emoji
                     0. Voltar ao menu.
                 Digite um comando (-1 para sair):\s""";

        System.out.print(textoDescanso);
        String escolha = sc.next();

        switch (escolha.toUpperCase()) {
            case "C" -> {
                System.out.println("    >>>STATUS: " + registrosDescanso.getStatusGeral());
                sc.nextLine();
            }
            case "H" -> {
                int horas = sc.nextInt();
                sc.nextLine();
                registrosDescanso.defineHorasDescanso(horas);
                System.out.println("    >>>Adicionadas " + horas + " horas.");
            }
            case "S" -> {
                int semanas = sc.nextInt();
                sc.nextLine();
                registrosDescanso.defineNumeroSemanas(semanas);
                System.out.println("    >>>Adicionadas " + semanas + " semanas.");
            }
            case "E" -> {
                String emoji = sc.nextLine();
                registrosDescanso.definirEmoji(emoji);
                System.out.println("    >>>Definido emoji:  " + emoji);
            }
            case "0" -> {
                sc.nextLine();
                this.abrirMenu();
            }
            default -> sair();
        }
        menuDescanso();
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
            menuResumos();
        }

        private void menuDisciplinas () {
            String textoDisciplinas = "";

            if (disciplinas.size() != 0) {
                for (int i = 0; i < disciplinas.size(); i++) {
                    textoDisciplinas += " 	" + (i + 2) + ". " + disciplinas.get(i) + "\n";
                }
            }

            String menuDisciplinas = "\n"
                    + "-- DISCIPLINAS --\n"
                    + " 	1. Adicionar Disciplina\n"
                    + textoDisciplinas
                    + " 	0. Voltar ao menu."
                    + " Digite uma opção (-1 para sair): ";

            System.out.print(menuDisciplinas);
            int escolha = sc.nextInt();
            sc.nextLine();
            switch (escolha) {
                case 1 -> {
                    disciplinas.add(novaDisciplina());
                    menuDisciplinas();
                }
                case 0 -> this.abrirMenu();
                case -1 -> sair();
                default -> {
                    System.out.println("\n--O que deseja fazer com esta disciplina?");
                    System.out.print(
                            """
                                            
                                    NOTA <nota int> <valor double com vírgula> --Adicionar nota:\s
                                    HORAS --Verificar se a meta foi cumprida:\s
                                    APR --Verificar se foi aprovado:\s
                                        Digite um comando (-1 para sair): \s"""
                    );
                    String acao = sc.next();
                    switch (acao.toUpperCase()) {
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
                            menuDescanso();
                        }
                    }
                    menuDisciplinas();
                }
            }

        }

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

    private void sair () {
            System.out.println("\n-- Até a próxima!\n\n			Cc--- [┐∵]┘");
            sc.close();
            System.exit(0);
        }

}

