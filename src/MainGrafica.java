import javax.swing.*;
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
    JPanel center;
    JPanel bottom;
    JPanel panel;
    JLabel plus;
    
    String nome;
    String cognome;
    String telefono;
    ArrayList<Contatto> contatti;
    
    Font font = new Font("Arial", Font.PLAIN, 25);
    Font fontTitolo = new Font("Arial", Font.PLAIN, 32);
    
    public MainGrafica(){
        super("Rubrica telefonica");
        
        try {
            //choose one
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
//            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedLookAndFeelException e) {
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
        
        r.importa();
        
        top = new JLabel("Rubrica telefonica", SwingConstants.CENTER);
        top.setBounds(0,0, 400, 60);
        top.setFont(fontTitolo);
        add(top);
        
        contatti = new ArrayList<>();
        center = new JPanel();
        panel = new JPanel();
        
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        
        scrollPane = new JScrollPane(panel);
        scrollPane.setBounds(0,60,385,440);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane);
        
        bottom = new JPanel();
        bottom.setLayout(new GridLayout(1, 3));
        plus = new JLabel(new ImageIcon("images/plus.png"));
        plus.addMouseListener(this);
        plus.setOpaque(false);
        
        bottom.add(plus);
        bottom.add(new JLabel("F"));
        bottom.add(new JLabel("L"));
        bottom.setBounds(0,500,400,60);
        add(bottom);
        
        pack();
        
    }
    
    public static void main(String[] args) {
        new MainGrafica();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == plus){
            
            boolean telefono_trovato = false;
            
            nome  = JOptionPane.showInputDialog(null, "Inserisci nome contatto", "Aggiunta contatto", JOptionPane.QUESTION_MESSAGE);
            cognome = JOptionPane.showInputDialog(null, "Inserisci cognome contatto", "Aggiunta contatto", JOptionPane.QUESTION_MESSAGE);
            telefono = JOptionPane.showInputDialog(null, "Inserisci telefono contatto", "Aggiunta contatto", JOptionPane.QUESTION_MESSAGE);
            while(telefono.length() != 10){
                telefono = JOptionPane.showInputDialog(null, "Numero di telefono errato", "Aggiunta contatto", JOptionPane.ERROR_MESSAGE);
            }
            
            r.inserimento(nome, cognome, telefono);
            
            for(Contatto cc : contatti) {
                if(cc.getTelefono().equalsIgnoreCase(telefono)) {
                    telefono_trovato = true;
                    break;
                }
            }
            
            if(!telefono_trovato) {
                contatti.add(new Contatto(nome, cognome, telefono));
                JLabel contatto = new JLabel(nome + " " + cognome + " " + telefono, SwingConstants.LEFT);
                contatto.setFont(font);
                panel.add(contatto);
                panel.add(Box.createVerticalStrut(2));
                repaint();
                revalidate();
            }else {
                JOptionPane.showMessageDialog(null, "Contatto con questo numero già esistente, impossibile aggiungerlo", "Errore aggiunta", JOptionPane.ERROR_MESSAGE);
            }
            
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
}
