import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class MainGrafica extends JFrame implements MouseListener {
    
    //TODO remove trail white spaces on name and surname
    
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
    
    public MainGrafica() {
        super("Rubrica Telefonica");
        
        visualizzato = false;
        
        try {
            //choose one
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
//            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        
        homePage();
        if (UIManager.getLookAndFeel().getName().equals("Nimbus")) {
            top.setBackground(new Color(214, 217, 223));
            top.setOpaque(true);
        }
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        
    }
    
    public static void main(String[] args) {
        new MainGrafica();
    }
    
    public void homePage() {
        setLayout(null);
        setPreferredSize(new Dimension(400, 600));
        contatti = new ArrayList<>();
        pannelliContatti = new ArrayList<>();
        
        top = new JLabel("Rubrica Telefonica", SwingConstants.CENTER);
        
        bottom = new JPanel();
        
        plus = new JLabel(new ImageIcon("images/plus.png"));
        modify = new JLabel(new ImageIcon("images/modify.png"));
        delete = new JLabel(new ImageIcon("images/delete.png"));
        search = new JLabel(new ImageIcon("images/search.png"));
        home = new JLabel(new ImageIcon("images/home.png"));
        
        top.setBounds(0, 0, 390, 60);
        top.setFont(fontTitolo);
        add(top);
        
        scrollPaneContatti();
        
        plus.addMouseListener(this);
        plus.setOpaque(false);
        
        modify.addMouseListener(this);
        modify.setOpaque(false);
        
        home.addMouseListener(this);
        home.setOpaque(false);
        
        search.addMouseListener(this);
        search.setOpaque(false);
        
        delete.addMouseListener(this);
        delete.setOpaque(false);
        
        bottom.setLayout(new GridLayout(1, 5));
        bottom.add(plus);
        bottom.add(modify);
        bottom.add(home);
        bottom.add(search);
        bottom.add(delete);
        bottom.setBounds(0, 500, 384, 60);
        add(bottom);
        
        pack();
        
    }
    
    public void scrollPaneContatti() {
        panel = new JPanel();
        scrollPane = new JScrollPane(panel);
        r.importa();
        contatti = r.getContatti();
        riordina();
        getSalvati();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        scrollPane.setBounds(0, 60, 385, 440);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(4);
        add(scrollPane);
    }
    
    public void scrollPaneCerca() {
        panelCerca = new JPanel();
        scrollPaneCerca = new JScrollPane(panelCerca);
        contattiRicerca = new ArrayList<>();
        remove(scrollPane);
        revalidate();
        nome = JOptionPane.showInputDialog(null, "Inserisci nome contatto", "Aggiunta contatto", JOptionPane.QUESTION_MESSAGE);
        cognome = JOptionPane.showInputDialog(null, "Inserisci cognome contatto", "Aggiunta contatto", JOptionPane.QUESTION_MESSAGE);
        contattiRicerca = r.ricerca(nome, cognome);
        getRicerca();
        panelCerca.setLayout(new BoxLayout(panelCerca, BoxLayout.PAGE_AXIS));
        scrollPaneCerca.setBounds(0, 60, 385, 440);
        scrollPaneCerca.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPaneCerca);
        visualizzato = true;
        repaint();
        revalidate();
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == plus) {
            
            boolean telefono_trovato = false;
            
            nome = JOptionPane.showInputDialog(null, "Inserisci nome contatto", "Aggiunta contatto", JOptionPane.QUESTION_MESSAGE);
            while (nome.isEmpty())
                nome = JOptionPane.showInputDialog(null, "Inserisci il nome", "Aggiunta contatto", JOptionPane.ERROR_MESSAGE);
            
            cognome = JOptionPane.showInputDialog(null, "Inserisci cognome contatto", "Aggiunta contatto", JOptionPane.QUESTION_MESSAGE);
            
            while (cognome.isEmpty())
                cognome = JOptionPane.showInputDialog(null, "Inserisci il nome", "Aggiunta contatto", JOptionPane.ERROR_MESSAGE);
            
            telefono = JOptionPane.showInputDialog(null, "Inserisci telefono contatto", "Aggiunta contatto", JOptionPane.QUESTION_MESSAGE);
            while (telefono.isEmpty())
                telefono = JOptionPane.showInputDialog(null, "Inserisci  contatto", "Aggiunta contatto", JOptionPane.ERROR_MESSAGE);
            
            if (nome != null && cognome != null && telefono != null) {
                while (telefono.length() != 10 || !isNumeric(telefono)) {
                    telefono = JOptionPane.showInputDialog(null, "Numero di telefono errato", "Errore numero di telefono", JOptionPane.ERROR_MESSAGE);
                }
                
                for (Contatto cc : contatti) {
                    if (cc.getTelefono().equalsIgnoreCase(telefono) || ((cc.getNome().equalsIgnoreCase(nome) && cc.getCognome().equalsIgnoreCase(cognome)))) {
                        telefono_trovato = true;
                        break;
                    }
                }
                // cc.getNome().equalsIgnoreCase(nome) ||
                if (!telefono_trovato) {
                    contatti.add(new Contatto(nome, cognome, telefono));
                    r.salvataggio();
                    JPanel paneContatto = getPanelContact();
                    panel.add(paneContatto);
                    panel.add(Box.createVerticalStrut(7));
                    pannelliContatti.add(paneContatto);
                    repaint();
                    revalidate();
                } else {
                    JOptionPane.showMessageDialog(null, "Contatto già esistente, impossibile aggiungerlo", "Errore aggiunta", JOptionPane.ERROR_MESSAGE);
                }
                
                r.inserimento(nome, cognome, telefono);
            }
            r.salvataggio();
        }
        else if (e.getSource() == modify) {
            if(visualizzato){
                top.setText("Rubrica Telefonica");
                remove(scrollPaneCerca);
                scrollPane.setBounds(0, 60, 385, 440);
                scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                add(scrollPane);
            }
            
            int index = -1;
            String nomeDaRicercare;
            String cognomeDaRicercare;
            
            nomeDaRicercare = JOptionPane.showInputDialog(null, "Inserisci nome contatto da modificare", "Aggiunta contatto", JOptionPane.QUESTION_MESSAGE);
            while (nomeDaRicercare.isEmpty())
                nomeDaRicercare = JOptionPane.showInputDialog(null, "Inserisci nome contatto da modificare", "Aggiunta contatto", JOptionPane.ERROR_MESSAGE);
            
            cognomeDaRicercare = JOptionPane.showInputDialog(null, "Inserisci cognome contatto da modificare", "Aggiunta contatto", JOptionPane.QUESTION_MESSAGE);
            while (cognomeDaRicercare.isEmpty())
                cognomeDaRicercare = JOptionPane.showInputDialog(null, "Inserisci cognome contatto da modificare", "Aggiunta contatto", JOptionPane.ERROR_MESSAGE);
            
            if (nomeDaRicercare != null && cognomeDaRicercare != null) {
                for (Contatto cc : contatti) {
                    if (cc.getNome().equalsIgnoreCase(nomeDaRicercare) && cc.getCognome().equalsIgnoreCase(cognomeDaRicercare)) {
                        index = contatti.indexOf(cc);
                        break;
                    }
                }
                if (index != -1) {
                    
                    int ripetuto = 0;
                    
                    do{
                        if(ripetuto > 0 ) {
                            JOptionPane.showConfirmDialog(null, "Contatto già esistente", "Contatto già esistente", JOptionPane.WARNING_MESSAGE);
                            
                            nome = JOptionPane.showInputDialog(null, "Inserisci nuovo nome", "Aggiunta contatto", JOptionPane.QUESTION_MESSAGE);
                            while (nome.isEmpty())
                                nome = JOptionPane.showInputDialog(null, "Obbligatorio inserire nuovo nome", "Aggiunta contatto", JOptionPane.ERROR_MESSAGE);
                            
                            cognome = JOptionPane.showInputDialog(null, "Inserisci nuovo cognome", "Aggiunta contatto", JOptionPane.QUESTION_MESSAGE);
                            while (cognome.isEmpty())
                                cognome = JOptionPane.showInputDialog(null, "Obbligatorio inserire nuovo cognome", "Aggiunta contatto", JOptionPane.ERROR_MESSAGE);
                            
                            
                            telefono = JOptionPane.showInputDialog(null, "Inserisci il nuovo numero di telefono", "Aggiunta contatto", JOptionPane.QUESTION_MESSAGE);
                            if (!telefono.isEmpty()) {
                                while (telefono.length() != 10 || !isNumeric(telefono)) {
                                    telefono = JOptionPane.showInputDialog(null, "Errore numero di telefono", "Aggiunta contatto", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        } else if (ripetuto == 0) {
                            nome = JOptionPane.showInputDialog(null, "Inserisci nuovo nome", "Aggiunta contatto", JOptionPane.QUESTION_MESSAGE);
                            while (nome.isEmpty())
                                nome = JOptionPane.showInputDialog(null, "Obbligatorio inserire nuovo nome", "Aggiunta contatto", JOptionPane.ERROR_MESSAGE);
                            
                            cognome = JOptionPane.showInputDialog(null, "Inserisci nuovo cognome", "Aggiunta contatto", JOptionPane.QUESTION_MESSAGE);
                            while (cognome.isEmpty())
                                cognome = JOptionPane.showInputDialog(null, "Obbligatorio inserire nuovo cognome", "Aggiunta contatto", JOptionPane.ERROR_MESSAGE);
                            
                            
                            telefono = JOptionPane.showInputDialog(null, "Inserisci il nuovo numero di telefono", "Aggiunta contatto", JOptionPane.QUESTION_MESSAGE);
                            if (!telefono.isEmpty()) {
                                while (telefono.length() != 10 || !isNumeric(telefono)) {
                                    telefono = JOptionPane.showInputDialog(null, "Errore numero di telefono", "Aggiunta contatto", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }
                        
                        ripetuto++;
                    }while(exists());
                    
                    JPanel paneContatto = pannelliContatti.get(index);
                    Component cNome = paneContatto.getComponent(0);
                    Component cTelefono = paneContatto.getComponent(1);
                    JLabel jNome = (JLabel) cNome;
                    JLabel jTelefono = (JLabel) cTelefono;
                    jNome.setText(nome + " " + cognome);
                    if (telefono.isEmpty()) {
                        telefono = jTelefono.getText();
                    }
                    jTelefono.setText(telefono);
                    
                    r.aggiorna(nome, cognome, telefono, nomeDaRicercare, cognomeDaRicercare);
                    r.salvataggio();
                    repaint();
                    revalidate();
                } else {
                    JOptionPane.showMessageDialog(null, "Contatto inesistente, impossibile modificare", "Errore modifica", JOptionPane.ERROR_MESSAGE);
                }
                
            }
        }
        else if (e.getSource() == search) {
            if(visualizzato){
                remove(scrollPaneCerca);
            }
            top.setText("Ricerca Contatto");
            scrollPaneCerca();
        }
        else if (e.getSource() == delete) {
            if(visualizzato) {
                top.setText("Rubrica Telefonica");
                remove(scrollPaneCerca);
                scrollPane.setBounds(0, 60, 385, 440);
                scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                add(scrollPane);
            }
                
                int index = -1;
            nome = JOptionPane.showInputDialog(null, "Inserisci nome contatto da eliminare", "Eliminazione contatto", JOptionPane.QUESTION_MESSAGE);
            cognome = JOptionPane.showInputDialog(null, "Inserisci cognome contatto da eliminare", "Eliminazione contatto", JOptionPane.QUESTION_MESSAGE);
            if (nome != null && cognome != null) {
                for (Contatto cc : contatti) {
                    if (cc.getNome().equalsIgnoreCase(nome) && cc.getCognome().equalsIgnoreCase(cognome)) {
                        index = contatti.indexOf(cc);
                        break;
                    }
                }
                if (index != -1) {
                    contatti.remove(index);
                    panel.remove(index * 2);
                    panel.remove(index * 2);
                    pannelliContatti.remove(index);
                    r.salvataggio();
                    repaint();
                    revalidate();
                } else {
                    JOptionPane.showMessageDialog(null, "Contatto non presente, impossibile rimuoverlo", "Errore eliminazione", JOptionPane.ERROR_MESSAGE);
                }
                
            }
        }
        else if (e.getSource() == home) {
//            remove(scrollPaneCerca);
            add(scrollPane);
            top.setText("Rubrica Telefonica");
            scrollPane.setBounds(0, 60, 385, 440);
            scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
    
    }
    
    private JPanel getPanelContact() {
        JLabel labelNomeContatto = new JLabel(nome + " " + cognome, SwingConstants.LEFT);
        JLabel labelTelefonoContatto = new JLabel(telefono, SwingConstants.LEFT);
        JPanel paneContatto = new JPanel();
        
        paneContatto.setLayout(new BoxLayout(paneContatto, BoxLayout.PAGE_AXIS));
        labelNomeContatto.setFont(fontNome);
        labelTelefonoContatto.setFont(fontTelefono);
        paneContatto.add(labelNomeContatto);
        paneContatto.add(labelTelefonoContatto);
        r.salvataggio();
        return paneContatto;
    }
    
    private void getSalvati() {
        for (Contatto c : contatti) {
            JLabel labelNomeContatto = new JLabel(c.getNome() + " " + c.getCognome(), SwingConstants.LEFT);
            JLabel labelTelefonoContatto = new JLabel(c.getTelefono(), SwingConstants.LEFT);
            JPanel paneContatto = new JPanel();
            paneContatto.setLayout(new BoxLayout(paneContatto, BoxLayout.PAGE_AXIS));
            labelNomeContatto.setFont(fontNome);
            labelTelefonoContatto.setFont(fontTelefono);
            paneContatto.add(labelNomeContatto);
            paneContatto.add(labelTelefonoContatto);
            panel.add(paneContatto);
            panel.add(Box.createVerticalStrut(7));
            pannelliContatti.add(paneContatto);
            repaint();
            revalidate();
        }
    }
    
    private void getRicerca() {
        if (!contattiRicerca.isEmpty()) {
            for (Contatto c : contattiRicerca) {
                JLabel labelNomeContatto = new JLabel(c.getNome() + " " + c.getCognome(), SwingConstants.LEFT);
                JLabel labelTelefonoContatto = new JLabel(c.getTelefono(), SwingConstants.LEFT);
                JPanel paneContatto = new JPanel();
                paneContatto.setLayout(new BoxLayout(paneContatto, BoxLayout.PAGE_AXIS));
                labelNomeContatto.setFont(fontNome);
                labelTelefonoContatto.setFont(fontTelefono);
                paneContatto.add(labelNomeContatto);
                paneContatto.add(labelTelefonoContatto);
                panelCerca.add(paneContatto);
                panelCerca.add(Box.createVerticalStrut(7));
            }
        } else {
            JLabel errore = new JLabel("Nessun contatto trovato");
            JPanel paneContatto = new JPanel();
            paneContatto.setLayout(new BoxLayout(paneContatto, BoxLayout.PAGE_AXIS));
            errore.setFont(fontNome);
            paneContatto.add(errore);
            panelCerca.add(paneContatto);
            panelCerca.add(Box.createVerticalStrut(7));
        }
    }
    
    public boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
    
    public boolean exists(){
        boolean telefono_trovato = false;
        for (Contatto cc : contatti) {
            if (cc.getTelefono().equalsIgnoreCase(telefono) || ((cc.getNome().equalsIgnoreCase(nome) && cc.getCognome().equalsIgnoreCase(cognome)))) {
                telefono_trovato = true;
                break;
            }
        }
        return  telefono_trovato;
    }
    
    public void riordina(){
        Collections.sort(contatti, Comparator.comparing(Contatto::getNome).thenComparing(Contatto::getCognome));
        for (Contatto c : contatti) {
            System.out.println(c.getNome() + " " + c.getCognome());
        }
    }
    
}
