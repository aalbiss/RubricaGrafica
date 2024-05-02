import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Rubrica {

	ArrayList<Contatto> aL = new ArrayList<>();

	public void inserimento() {
		Contatto c = new Contatto();

		boolean telefono_trovato = false;

		System.out.println();
		System.out.println("Inserire contatto numero " + (aL.size() + 1) + ": ");
		System.out.println();
		c.inserimento();


		// CONTROLLO CONTATTO GIA' ESISTENTE (controllo del numero di telefono):

		for(Contatto cc : aL) {
			if(cc.getTelefono().equalsIgnoreCase(c.getTelefono())) {
				telefono_trovato = true;

				break;

			}
		}

		if(!telefono_trovato) {
			aL.add(c);

		}else {
			System.out.println("Errore: il contatto è già presente nella rubrica!");

		}
	}

	public void visualizza() {
		if(aL.size() > 0) {
			for(Contatto c : aL) {
				c.visualizza();

			}
		}else {
			System.out.println("Errore: il programma è vuoto!");

		}
	}

	public void salvataggio() {
		if(aL.size() > 0) {
			File file = new File("rubrica.dat");

			try {
				FileOutputStream fos = new FileOutputStream(file);
				ObjectOutputStream oos = new ObjectOutputStream(fos);

				for(Contatto c : aL) {
					Contatto contatto = new Contatto(c.getCognome(), c.getNome(), c.getTelefono());
					oos.writeObject(contatto);
				}

				oos.flush();
				oos.close();

			}catch(Exception e) {
				System.out.println("Eccezione " + e);
			}
		}
	}

	public void importa() {
        File file = new File("rubrica.dat");

        boolean finito = false;
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
			finito = false;

            while (!finito) {
                Object ob = ois.readObject();
                Contatto c = (Contatto)ob;
                aL.add(c);
            }
			ois.close();
		} catch (EOFException e) {
			finito = true;
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
    }

	public void ricerca() {
		if(aL.size() > 0) {

			Scanner keyboard = new Scanner(System.in);

			String cognome;
			String nome;

			boolean trovato = false;

			do {
				System.out.print("Cognome: ");
				cognome = keyboard.nextLine();

			}while(cognome.length() <= 0);

			do {
				System.out.print("Nome: ");
				nome = keyboard.nextLine();

			}while(nome.length() <= 0);

			for(Contatto c : aL) {
				if(c.getCognome().equalsIgnoreCase(cognome) && c.getNome().equalsIgnoreCase(nome)) {
					trovato = true;
					System.out.println("Telefono: " + c.getTelefono());

					break;

				}
			}

			if(!trovato) {
				System.out.println("Errore: il contatto non è presente nella rubrica!");

			}
		}
	}

	public void aggiorna() {
		if(aL.size() > 0) {

			Scanner keyboard = new Scanner(System.in);

			String cognome;
			String nome;

			boolean trovato = false;

			do {
				System.out.print("Cognome: ");
				cognome = keyboard.nextLine();

			}while(cognome.length() <= 0);

			do {
				System.out.print("Nome: ");
				nome = keyboard.nextLine();

			}while(nome.length() <= 0);

			for(Contatto c : aL) {
				if(c.getCognome().equalsIgnoreCase(cognome) && c.getNome().equalsIgnoreCase(nome)) {
					trovato = true;

					String nuovo_numero;

					do {
						System.out.print("Nuovo numero: ");
						nuovo_numero = keyboard.nextLine();
					}while(nuovo_numero.length() < 10);
					c.setTelefono(nuovo_numero);

					break;

				}
			}

			if(!trovato) {
				System.out.println("Errore: il contatto non è presente nella rubrica!");

			}
		}
	}

	public void elimina() {
		if(aL.size() > 0) {

			Scanner keyboard = new Scanner(System.in);

			String cognome;
			String nome;

			boolean trovato = false;

			do {
				System.out.print("Cognome: ");
				cognome = keyboard.nextLine();

			}while(cognome.length() <= 0);

			do {
				System.out.print("Nome: ");
				nome = keyboard.nextLine();

			}while(nome.length() <= 0);

			for(Contatto c : aL) {
				int i = 0;
				i ++;

				if(c.getCognome().equalsIgnoreCase(cognome) && c.getNome().equalsIgnoreCase(nome)) {
					aL.remove(i);
					break;

				}
			}
		}
	}
}