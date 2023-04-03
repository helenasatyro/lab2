package lab2;

import java.util.Arrays;

public class CoisaBonus {
  public static void main(String[] args) {
      testaBonusDescanso();
      System.out.println("-----");
      registrarTempoOnline();
      System.out.println("-----");
      testaBonusDisciplina();
      System.out.println("-----");
      testaBonusResumos();
  }
  public static void testaBonusDescanso() {
	  // está cansado 
	  Descanso descanso = new Descanso();
      System.out.println(descanso.getStatusGeral());
  
      // agora está descansado
      descanso.defineHorasDescanso(26);
      descanso.defineNumeroSemanas(1);
      System.out.println(descanso.getStatusGeral());
      
      // definimos o emoji, como nada mudou no estado de descanso, ele aparece
      descanso.definirEmoji(":D");
      System.out.println(descanso.getStatusGeral());
      
      // e aparece novamente      
      descanso.defineHorasDescanso(26);
      descanso.defineNumeroSemanas(1);
      System.out.println(descanso.getStatusGeral());
      
      // agora está cansado
      descanso.defineHorasDescanso(20);
      descanso.defineNumeroSemanas(1);
      
      // e o emoji sumiu
      System.out.println(descanso.getStatusGeral());
      

      



  }
  private static void registrarTempoOnline() {
      RegistroTempoOnline tempoLP2 = new RegistroTempoOnline("LP2", 30);
      tempoLP2.adicionaTempoOnline(10);
      System.out.println(tempoLP2.atingiuMetaTempoOnline());
      tempoLP2.adicionaTempoOnline(10);
      tempoLP2.adicionaTempoOnline(10);
      System.out.println(tempoLP2.atingiuMetaTempoOnline());
      tempoLP2.adicionaTempoOnline(2);
      System.out.println(tempoLP2.atingiuMetaTempoOnline());
      System.out.println(tempoLP2.toString());
      RegistroTempoOnline tempoP2 = new RegistroTempoOnline("P2");
      System.out.println(tempoP2.toString());
  }
  private static void testaBonusDisciplina() {
	  double[] meusPesos = {1.0, 1.0, 10.0};
      Disciplina prog2 = new Disciplina("PROGRAMACAO 2", 3, meusPesos);
      prog2.cadastraHoras(4);
      prog2.cadastraNota(1, 5.0);
      prog2.cadastraNota(2, 6.0);
      prog2.cadastraNota(3, 7.0);
      System.out.println(prog2.aprovado());
      System.out.println(prog2.aprovado());
      System.out.println(prog2.toString());
  }
  private static void testaBonusResumos() {
      RegistroResumos meusResumos = new RegistroResumos(100);  // 100 resumos
     
      meusResumos.adiciona("Classes", "Classes definem um tipo e a base de código para criação de objetos.");
      meusResumos.adiciona("Tipo", "Identifica a semântica (operações e significados) de um conjunto de dados.");


      String[] resumos = meusResumos.pegaResumos();


      for (int i = 0; i < meusResumos.conta(); i++) {
          System.out.println(resumos[i]);
      }


      System.out.println();
      System.out.println("Resumos: ");
      System.out.println(meusResumos.imprimeResumos());
      System.out.println(meusResumos.temResumo("Classes"));
      System.out.println(meusResumos.temResumo("Objetos"));
      System.out.println(Arrays.toString(meusResumos.busca("UM")));
  }
}