package lab2.CLI;
import java.util.ArrayList;
//import java.util.Scanner;
import lab2.*;

public class CoisaCLI {
	public static void main(String[] args) {
		// Inicialização dos ArrayLists para o menu:
		ArrayList<RegistroTempoOnline> registrosTempo = new ArrayList<>();
		Descanso registroDescanso = new Descanso();
		Menu menu = new Menu(registrosTempo, registroDescanso);
		menu.abrirMenu();
	}
}
		


	

	