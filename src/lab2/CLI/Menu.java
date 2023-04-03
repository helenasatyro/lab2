package lab2.CLI;

import java.net.StandardSocketOptions;
import java.util.ArrayList;
import java.util.Scanner;

import lab2.*;

/**
 * Classe que implementa uma interface de Linha de Comando
 * para auxiliar na organização de estudantes.
 *
 * @author Maria Helena Sátyro Gomes Alves
 */
public class Menu {
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
    /**
     * Construtor de menu, aceita como parâmetros arrays de objetos que podem ser usados no programa.
     * Inicializa os ArrayLists e imprime a mensagem de abertura do programa.
     *
     * @param registrosTempoOnline ArrayList de objetos do tipo RegistroTempoOnline, pré-inicializado
     */
    public Menu(ArrayList<RegistroTempoOnline> registrosTempoOnline, Descanso registrosDescanso, ArrayList<Disciplina> disciplinas) {
        this.registrosTempoOnline = registrosTempoOnline;
        this.registrosDescanso = registrosDescanso;
        this.disciplinas = disciplinas;
        System.out.println("""

                		      └[ ∵ ]┘\s
                -- Olá! Sou RoBetinho, seu assistente acadêmico!"""
                );
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
            case 1:
                registrosTempoOnline.add(novoRegistro());
                menuTempoOnline();
                break;
            case 0:
                this.abrirMenu();
                break;
            case -1:
                sair();
                break;
            default:
                System.out.println("\n--O que deseja fazer com " + registrosTempoOnline.get(escolha - 2) + "?");
                System.out.print(
                        """
                                        
                                ADD <horas> --Adicionar horas:\s
                                VER --Verificar se a meta foi cumprida:\s
                                 A:\s"""
                );

                String acao = sc.next();
                switch (acao.toUpperCase()) {
                    case "ADD":
                        registrosTempoOnline.get(escolha - 2).adicionaTempoOnline(sc.nextInt());
                        sc.nextLine();
                        System.out.println("Adicionado.");
                        break;
                    case "VER":
                        System.out.println("\nMeta atingida: " + registrosTempoOnline.get(escolha - 2).atingiuMetaTempoOnline());
                        break;
                }
                menuTempoOnline();
        }
    }

    public void abrirMenu() {
        String textoMenu = """

                -- Como posso ajudá-lo?

                 	1. DISCIPLINAS
                 	2. RESUMOS
                 	3. DESCANSO
                 	4. TEMPO ONLINE

                 Digite uma opção (outra para sair):\s""";

        System.out.print(textoMenu);
        String escolha = sc.nextLine();

        switch (escolha) {
            case "1":
                menuDisciplinas();
                break;
            case "2":
                menuResumos();
                break;
            case "3":
                menuDescanso();
                break;
            case "4":
                menuTempoOnline();
                break;
            default:
                sair();
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

        String textoDescanso = "\n"
                + "-- DESCANSO --\n"
                + "     C --Consultar status geral\n"
                + "     H <horas> --Definir Horas de Descanso (ex: 2 14 define 14 horas)\n"
                + "     S <semanas> --Definir Número de Semanas\n"
                + "     E <emoji> --Definir emoji\n"
                + "     0. Voltar ao menu.\n"
                + " Digite um comando (outro para sair): ";

        System.out.print(textoDescanso);
        String escolha = sc.next();

        switch (escolha.toUpperCase()) {
            case "C":
                System.out.println("    >>>STATUS: " + registrosDescanso.getStatusGeral());
                sc.nextLine();
                menuDescanso();
                break;
            case "H":
                int horas = sc.nextInt();
                sc.nextLine();
                registrosDescanso.defineHorasDescanso(horas);
                System.out.println("    >>>Adicionadas " + horas + " horas." );
                menuDescanso();
                break;
            case "S":
                int semanas = sc.nextInt();
                sc.nextLine();
                registrosDescanso.defineNumeroSemanas(semanas);
                System.out.println("    >>>Adicionadas " + semanas + " semanas." );
                menuDescanso();
                break;
            case "E":
                String emoji = sc.nextLine();
                registrosDescanso.definirEmoji(emoji);
                System.out.println("    >>>Definido emoji:  " + emoji );
                menuDescanso();
                break;
            case "0":
                sc.nextLine();
                this.abrirMenu();
                break;
            default:
                sair();
        }
        menuDescanso();
    }

        private static void menuResumos () {
            System.out.println("hi");
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
                case 1:
                    disciplinas.add(novaDisciplina());
                    menuDisciplinas();
                    break;
                case 0:
                    this.abrirMenu();
                    break;
                case -1:
                    sair();
                    break;
                default:
                    System.out.println("\n--O que deseja fazer com " + disciplinas.get(escolha - 2) + "?");
                    System.out.print(
                            """
                                            
                                    ADD <horas> --Adicionar horas:\s
                                    VER --Verificar se a meta foi cumprida:\s
                                     A:\s"""
                    );

                    String acao = sc.next();
                    switch (acao.toUpperCase()) {
                        case "ADD":
                            disciplinas.get(escolha - 2).cadastraNota(sc.nextInt(), sc.nextDouble());
                            sc.nextLine();
                            System.out.println("Adicionado.");
                            break;
                        case "VER":
                            System.out.println("\nMeta atingida: " + registrosTempoOnline.get(escolha - 2).atingiuMetaTempoOnline());
                            break;
                    }
                    menuDisciplinas();
            }

        }

    private Disciplina novaDisciplina() {

        Disciplina disciplina;
        System.out.print("\n--Nome da disciplina: ");
        String nome = sc.nextLine();
        System.out.print("--Quantidade de notas(enter para omitir): ");
        String strQtdNotas = sc.nextLine();
        System.out.println("Especificar pesos? (s/n): ");
        if (strQtdNotas.equals("")) {
            Double[] pesos = new Double[4];
            for(int i = 0; i <= 4; i++) {
                System.out.println("Peso da " + (i + 1) + "a nota: ");
                pesos[i] = sc.nextDouble();
            }
            sc.nextLine();
        } else {

        }
        System.out.print("--Quantidade de notas(enter para omitir): ");
        String strPesos = sc.nextLine();
        if (!strQtdNotas.equals("")) {
            registro = new RegistroTempoOnline(nome, Integer.parseInt(strHoras));
        } else {
            registro = new RegistroTempoOnline(nome);
        }
        return registro;    }

    private static void sair () {
            System.out.println("\n-- Até a próxima!\n\n			Cc--- [┐∵]┘");
            System.exit(0);
        }

}

