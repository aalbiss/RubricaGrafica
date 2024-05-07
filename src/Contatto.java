import java.io.Serializable;

public class Contatto implements Serializable {
    
    private String nome;
    private String cognome;
    private String tel;
    
    Contatto() {
        this.nome = "";
        this.cognome = "";
        this.tel = "";
        
    }
    
    Contatto(String nome, String cognome, String tel) {
        this.nome = nome;
        this.cognome = cognome;
        this.tel = tel;
        
    }
    
    public String getCognome() {
        return cognome;
        
    }
    
    public void setCognome(String cognome) {
        if (!cognome.isEmpty()) {
            this.cognome = cognome;
            
        } else {
            this.cognome = "";
            
        }
    }
    
    public String getNome() {
        return nome;
        
    }
    
    public void setNome(String nome) {
        if (!nome.isEmpty()) {
            this.nome = nome;
            
        } else {
            this.nome = "";
            
        }
    }
    
    public String getTelefono() {
        return tel;
    }
    
    public void setTelefono(String tel) {
        if (tel.length() == 10) {
            this.tel = tel;
            
        } else {
            this.tel = "";
            
        }
    }
    
    public void inserimento(String nome, String cognome, String tel) {
        setNome(nome);
        setCognome(cognome);
        setTelefono(tel);
    }
    
}
