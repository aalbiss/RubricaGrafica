import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class MainGrafica extends JFrame implements ActionListener, MouseListener {
    
    Rubrica r = new Rubrica();
    JScrollPane scrollPane;
    JLabel top;
    JPanel bottom;
    JPanel panel;
    JLabel plus;
    JLabel modify;
    JLabel delete;
    JLabel search;
    
    String nome;
    String cognome;
    String telefono;
    ArrayList<Contatto> contatti;
    
    Font fontNome = new Font("Rage", Font.PLAIN , 25);
    Font fontTelefono = new Font("Arial", Font.PLAIN, 17);
    Font fontTitolo = new Font("Arial", Font.PLAIN, 32);
    
    public MainGrafica(){
        super("Rubrica telefonica");
        
        try {
            //choose one
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
//            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        
        homePage();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        
    }
    
    public void homePage(){
        setPreferredSize(new Dimension(400, 600));
        setLayout(null);
        
        contatti = new ArrayList<>();
        
        
        
        top = new JLabel("Rubrica telefonica", SwingConstants.CENTER);
        
        panel = new JPanel();
        scrollPane = new JScrollPane(panel);
        
        bottom = new JPanel();
        
        plus = new JLabel(new ImageIcon("images/plus.png"));
        modify = new JLabel(new ImageIcon("images/modify.png"));
        delete = new JLabel(new ImageIcon("images/delete.png"));
        search = new JLabel(new ImageIcon("images/search.png"));
        
        r.importa();
        contatti = r.getContatti();
        getSalvati();
        
        if(UIManager.getLookAndFeel().getName().equals("Nimbus")){
            top.setBackground(new Color(214,217,223));
            top.setOpaque(true);
        }
        
        top.setBounds(0,0, 400, 60);
        top.setFont(fontTitolo);
        add(top);
        
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        scrollPane.setBounds(0,60,385,440);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane);
        
        plus.addMouseListener(this);
        plus.setOpaque(false);
        
        modify.addMouseListener(this);
        modify.setOpaque(false);
        
        search.addMouseListener(this);
        search.setOpaque(false);
        
        delete.addMouseListener(this);
        delete.setOpaque(false);
        
        bottom.setLayout(new GridLayout(1, 4));
        bottom.add(plus);
        bottom.add(modify);
        bottom.add(search);
        bottom.add(delete);
        bottom.setBounds(0,500,384,60);
        add(bottom);
        
        pack();
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == plus){
            
            boolean telefono_trovato = false;
            
            nome  = JOptionPane.showInputDialog(null, "Inserisci nome contatto", "Aggiunta contatto", JOptionPane.QUESTION_MESSAGE);
            while (nome.isEmpty())
                nome  = JOptionPane.showInputDialog(null, "Inserisci il nome", "Aggiunta contatto", JOptionPane.ERROR_MESSAGE);
            
            cognome  = JOptionPane.showInputDialog(null, "Inserisci cognome contatto", "Aggiunta contatto", JOptionPane.QUESTION_MESSAGE);
            while (cognome.isEmpty())
                cognome = JOptionPane.showInputDialog(null, "Inserisci il nome", "Aggiunta contatto", JOptionPane.ERROR_MESSAGE);
            
            telefono = JOptionPane.showInputDialog(null, "Inserisci telefono contatto", "Aggiunta contatto", JOptionPane.QUESTION_MESSAGE);
            while(telefono.isEmpty())
                telefono = JOptionPane.showInputDialog(null, "Inserisci  contatto", "Aggiunta contatto", JOptionPane.ERROR_MESSAGE);
            
            if(nome != null && cognome != null && telefono != null){
//                while(telefono.length() != 10){
//                    telefono = JOptionPane.showInputDialog(null, "Numero di telefono errato", "Errore numero di telefono", JOptionPane.ERROR_MESSAGE);
//                }
                for(Contatto cc : contatti) {
                    if(cc.getTelefono().equalsIgnoreCase(telefono) || (cc.getNome().equalsIgnoreCase(nome) || (cc.getNome().equalsIgnoreCase(nome) && cc.getCognome().equalsIgnoreCase(cognome)) )) {
                        telefono_trovato = true;
                        break;
                    }
                }
                
                if(!telefono_trovato) {
                    contatti.add(new Contatto(nome, cognome, telefono));
                    r.salvataggio();
                    JPanel paneContatto = getPanelContact();
                    panel.add(paneContatto);
                    panel.add(Box.createVerticalStrut(7));
                    repaint();
                    revalidate();
                }else {
                    JOptionPane.showMessageDialog(null, "Contatto già esistente, impossibile aggiungerlo", "Errore aggiunta", JOptionPane.ERROR_MESSAGE);
                }
                
                r.inserimento(nome, cognome, telefono);
            }
            
        }
        
        else if(e.getSource() == modify){
            int index = -1;

            nome  = JOptionPane.showInputDialog(null, "Inserisci nome contatto da modificare", "Aggiunta contatto", JOptionPane.QUESTION_MESSAGE);
            while (nome.isEmpty())
                nome  = JOptionPane.showInputDialog(null, "Inserisci il nome", "Aggiunta contatto", JOptionPane.ERROR_MESSAGE);
            
            cognome = JOptionPane.showInputDialog(null, "Inserisci cognome contatto da modificare", "Aggiunta contatto", JOptionPane.QUESTION_MESSAGE);
            while (cognome.isEmpty())
                cognome = JOptionPane.showInputDialog(null, "Inserisci il nome", "Aggiunta contatto", JOptionPane.ERROR_MESSAGE);
            
            if(nome != null && cognome != null){
                for(Contatto cc : contatti) {
                    if(cc.getTelefono().equalsIgnoreCase(telefono) || (cc.getNome().equalsIgnoreCase(nome) && cc.getCognome().equalsIgnoreCase(cognome))) {
                        index = contatti.indexOf(cc);
                        break;
                    }
                }
                if(index != -1) {
                    nome  = JOptionPane.showInputDialog(null, "Inserisci nuovo nome", "Aggiunta contatto", JOptionPane.QUESTION_MESSAGE);
                    while (nome.isEmpty())
                        nome  = JOptionPane.showInputDialog(null, "Obbligatorio inserire nome", "Aggiunta contatto", JOptionPane.ERROR_MESSAGE);
                    
                    cognome = JOptionPane.showInputDialog(null, "Inserisci nuovo cognome", "Aggiunta contatto", JOptionPane.QUESTION_MESSAGE);
                    while (cognome.isEmpty())
                        cognome = JOptionPane.showInputDialog(null, "Inserisci il nome", "Aggiunta contatto", JOptionPane.ERROR_MESSAGE);
                    repaint();
                    revalidate();
                }else {
                    JOptionPane.showMessageDialog(null, "Contatto già esistente, impossibile aggiungerlo", "Errore aggiunta", JOptionPane.ERROR_MESSAGE);
                }
                
            }
        }
        else if (e.getSource() == search) {}
        else if(e.getSource() == delete){
            int index = -1;
            nome = JOptionPane.showInputDialog(null, "Inserisci nome contatto da eliminare", "Eliminazione contatto", JOptionPane.QUESTION_MESSAGE);
            cognome = JOptionPane.showInputDialog(null, "Inserisci cognome contatto da eliminare", "Eliminazione contatto", JOptionPane.QUESTION_MESSAGE);
            if(nome != null && cognome != null){
                for(Contatto cc : contatti) {
                    if(cc.getNome().equalsIgnoreCase(nome) && cc.getCognome().equalsIgnoreCase(cognome)) {
                        index = contatti.indexOf(cc);
                        break;
                    }
                }
                
                if(index != -1) {
                    contatti.remove(index);
                    panel.remove(index*2);
                    panel.remove(index*2);
                    r.salvataggio();
                    repaint();
                    revalidate();
                }else {
                    JOptionPane.showMessageDialog(null, "Contatto non presente, impossibile rimuoverlo", "Errore eliminazione", JOptionPane.ERROR_MESSAGE);
                }
                
            }
            
            
        }
        
    }
    
    private JPanel getPanelContact() {
        JLabel labelNomeContatto = new JLabel(nome + " " + cognome, SwingConstants.LEFT);;
        JLabel labelTelefonoContatto = new JLabel(telefono,SwingConstants.LEFT);
        JPanel paneContatto = new JPanel();
        
        paneContatto.setLayout(new BoxLayout(paneContatto, BoxLayout.PAGE_AXIS));
        labelNomeContatto.setFont(fontNome);
        labelTelefonoContatto.setFont(fontTelefono);
        paneContatto.add(labelNomeContatto);
        paneContatto.add(labelTelefonoContatto);
        r.salvataggio();
        return paneContatto;
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
    
    private void getSalvati(){
        for (Contatto c : contatti) {
            JLabel labelNomeContatto = new JLabel(c.getNome() + " " + c.getCognome(), SwingConstants.LEFT);
            JLabel labelTelefonoContatto = new JLabel(c.getTelefono(),SwingConstants.LEFT);
            JPanel paneContatto = new JPanel();
            paneContatto.setLayout(new BoxLayout(paneContatto, BoxLayout.PAGE_AXIS));
            labelNomeContatto.setFont(fontNome);
            labelTelefonoContatto.setFont(fontTelefono);
            paneContatto.add(labelNomeContatto);
            paneContatto.add(labelTelefonoContatto);
            r.salvataggio();
            panel.add(paneContatto);
            panel.add(Box.createVerticalStrut(7));
            repaint();
            revalidate();
        }
        
    }
    
    public static void main(String[] args) {
        new MainGrafica();
    }
}
