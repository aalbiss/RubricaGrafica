//import java.util.Scanner;
//
//public class Main{
//
//	public static void main(String[] args) {
//
//		Rubrica r = new Rubrica();
//
//		Scanner keyboard = new Scanner(System.in);
//
//		int cont = 0;
//		int scelta = 0;
//
//        r.importa();
//
//		do {
//			System.out.println();
//			System.out.println(" - MENU - ");
//			System.out.println("1) Inserimento");
//			System.out.println("2) Visualizza");
//			System.out.println("3) Ricerca contatto");
//			System.out.println("4) Aggiorna numero");
//			System.out.println("5) Elimina contatto");
//			System.out.println("6) Salva dati su file");
//			System.out.println("7) Importa dati dal file");
//			System.out.println("8) Esci dal programma");
//			System.out.println();
//			System.out.print("Opzione scelta: ");
//			scelta = keyboard.nextInt();
//
//			switch(scelta) {
//
//			case 1:
////				r.inserimento();
//				cont++;
//				break;
//
//			case 2:
//				r.visualizza();
//				break;
//
//			case 3:
//				r.ricerca();
//				break;
//
//			case 4:
//				r.aggiorna();
//				break;
//
//			case 5:
//				r.elimina();
//				break;
//
//			case 6, 8:
//				r.salvataggio();
//				break;
//
//			case 7:
//				r.importa();
//				break;
//            }
//
//		}while(scelta != 8);
//
//	}
//
//}
