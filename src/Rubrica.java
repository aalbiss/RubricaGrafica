import java.io.*;
import java.util.ArrayList;

public class Rubrica {
    
    ArrayList<Contatto> aL = new ArrayList<>();
    
    
    public void inserimento(String nome, String cognome, String tel) {
        Contatto c = new Contatto();
        
        
        boolean telefono_trovato = false;
        
        c.inserimento(nome, cognome, tel);
        
        
        // CONTROLLO CONTATTO GIA' ESISTENTE (controllo del numero di telefono):
        for (Contatto cc : aL) {
            if (cc.getTelefono().equalsIgnoreCase(c.getTelefono())) {
                telefono_trovato = true;
                break;
            }
        }
        
        if (!telefono_trovato) {
            aL.add(c);
        }
    }
    
    public void salvataggio() {
        File file = new File("rubrica.dat");
        
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            
            for (Contatto c : aL) {
                Contatto contatto = new Contatto(c.getNome(), c.getCognome(), c.getTelefono());
                oos.writeObject(contatto);
            }
            
            oos.flush();
            oos.close();
            
        } catch (Exception e) {
            System.out.println("Eccezione " + e);
        }
    }
    
    public void importa() {
        File file = new File("rubrica.dat");
        
        boolean finito;
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            finito = false;
            
            while (!finito) {
                Object ob = ois.readObject();
                Contatto c = (Contatto) ob;
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
    
    public ArrayList<Contatto> ricerca(String nome, String cognome) {
        ArrayList<Contatto> contattiTrovati = new ArrayList<>();
        nome = nome.toLowerCase();
        cognome = cognome.toLowerCase();
        
        
        for (Contatto c : aL) {
            String n = c.getNome().toLowerCase();
            String cog = c.getCognome().toLowerCase();
            if (n.startsWith(nome) && cog.startsWith(cognome)) {
                contattiTrovati.add(c);
            }
        }
        return contattiTrovati;
    }
    
    public void aggiorna(String nome, String cognome, String tel, String nomeDaRicercare, String cognomeDaRicercare) {
        
        for (Contatto c : aL) {
            if (c.getCognome().equalsIgnoreCase(cognomeDaRicercare) && c.getNome().equalsIgnoreCase(nomeDaRicercare)) {
                c.setNome(nome);
                c.setCognome(cognome);
                c.setTelefono(tel);
                break;
            }
        }
    }
    
    
    public ArrayList<Contatto> getContatti() {
        return aL;
    }
}