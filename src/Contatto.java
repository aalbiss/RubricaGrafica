import java.io.Serializable;
import java.util.Scanner;

public class Contatto implements Serializable {

	private String cognome;
	private String nome;
	private String tel;

	Contatto(){
		this.cognome = "";
		this.nome = "";
		this.tel = "";
		
	}

	Contatto(String cognome, String nome, String tel){
		this.cognome = cognome;
		this.nome = nome;
		this.tel = tel;
		
	}

	public void setCognome(String cognome) {
		if(cognome.length() > 0) {
			this.cognome = cognome;
				
		}else {
			this.cognome = "";
			
		}
	}
	
	public String getCognome() {
		return cognome;
		
	}
	
	public void setNome(String nome) {
		if(nome.length() > 0) {
			this.nome = nome;
			
		}else {
			this.nome = "";
			
		}
	}
	
	public String getNome() {
		return nome;
		
	}
	
	public void setTelefono(String tel) {
		if(tel.length() == 10) {
			this.tel = tel;
			
		}else {
			this.tel = "";
			
		}
	}
	
	public String getTelefono() {
		return tel;
		
	}

	public void inserimento() {
		Scanner keyboard = new Scanner(System.in);
		
		String cognome;
		String nome;
		String tel;

		do {
			System.out.print("Cognome: ");
			cognome = keyboard.nextLine();
			
		}while(cognome.length() <= 0);
		setCognome(cognome);

		do {
			System.out.print("Nome: ");
			nome = keyboard.nextLine();
			
		}while(nome.length() <= 0);
		setNome(nome);

		do {
			System.out.print("Telefono: ");
			tel = keyboard.nextLine();
			
		}while(tel.length() < 10);
		setTelefono(tel);
		
	}

	public void visualizza() {
		System.out.println();
		System.out.println("Cognome: " + cognome);
		System.out.println("Nome: " + nome);
		System.out.println("Telefono: " + tel);
		System.out.println();
		
	}
}
