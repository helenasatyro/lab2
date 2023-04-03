package lab2.CLI;

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

    /**
     * Construtor de menu, aceita como parâmetros arrays de objetos que podem ser usados no programa.
     * Inicializa os ArrayLists e imprime a mensagem de abertura do programa.
     *
     * @param registrosTempoOnline ArrayList de objetos do tipo RegistroTempoOnline, pré-inicializado
     */
    public Menu(ArrayList<RegistroTempoOnline> registrosTempoOnline, Descanso registrosDescanso) {
        this.registrosTempoOnline = registrosTempoOnline;
        this.registrosDescanso = registrosDescanso;
        String textoInicial = """

                		      └[ ∵ ]┘\s
                -- Olá! Sou RoBetinho, seu assistente acadêmico!""";

        System.out.println(textoInicial);
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
                Menu.menuDisciplinas();
                break;
            case "2":
                Menu.menuResumos();
                break;
            case "3":
                Menu.menuDescanso();
                break;
            case "4":
                menuTempoOnline();
                break;
            default:
                Menu.sair();
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

        String textoRegistros = "";
        String menuDescanso = "\n"
                + "-- DESCANSO --\n"
                + " 	1. Adicionar Horas\n"
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

    private static void menuResumos() {
        // TODO Auto-generated method stub

    }

    private static void menuDisciplinas() {
        // TODO Auto-generated method stub

    }

    private static void sair() {
        System.out.println("\n-- Até a próxima!\n\n			Cc--- [┐∵]┘");
        System.exit(0);
    }
}

