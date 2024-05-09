import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GestioneEventi {
    
    boolean visualizzato;
    
    Rubrica r = new Rubrica();
    JScrollPane scrollPane;
    JScrollPane scrollPaneCerca;
    JLabel top;
    JPanel bottom;
    JPanel panel;
    JPanel panelCerca;
    JLabel plus;
    JLabel modify;
    JLabel delete;
    JLabel search;
    JLabel home;
    
    String nome;
    String cognome;
    String telefono;
    ArrayList<Contatto> contatti;
    ArrayList<Contatto> contattiRicerca;
    ArrayList<JPanel> pannelliContatti;
    
    Font fontNome = new Font("Arial", Font.PLAIN, 25);
    Font fontTelefono = new Font("Arial", Font.PLAIN, 17);
    Font fontTitolo = new Font("Arial", Font.PLAIN, 32);
    
}
