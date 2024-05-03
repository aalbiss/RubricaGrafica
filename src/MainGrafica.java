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
    ArrayList<JLabel> contatti;
    
    Font font = new Font("Arial", Font.PLAIN, 25);
    Font fontTitolo = new Font("Arial", Font.PLAIN, 32);
    
    public MainGrafica(){
        super("Rubrica telefonica");
        
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
        
//        for (int i = 0; i < 400; i++) {
//            JLabel l = new JLabel("Label" + i);
//            l.setFont(font);
//            contatti.add(l);
//        }

//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//        for (JLabel l : contatti) {
//            panel.add(l);
//            panel.add(Box.createHorizontalStrut(6));
//            panel.add(Box.createVerticalStrut(8));
//        }
        
        scrollPane = new JScrollPane(panel);
        scrollPane.setBounds(0,60,385,440);
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
            
            nome  = JOptionPane.showInputDialog(null, "Inserisci nome contatto", "Aggiunta contatto", JOptionPane.QUESTION_MESSAGE);
            cognome = JOptionPane.showInputDialog(null, "Inserisci cognome contatto", "Aggiunta contatto", JOptionPane.QUESTION_MESSAGE);
            telefono = JOptionPane.showInputDialog(null, "Inserisci telefono contatto", "Aggiunta contatto", JOptionPane.QUESTION_MESSAGE);
            while(telefono.length() != 10){
                telefono = JOptionPane.showInputDialog(null, "Numero di telefono errato", "Aggiunta contatto", JOptionPane.ERROR_MESSAGE);
            }
            
            r.inserimento(nome, cognome, telefono);
            
            //TODO try to use JTABLE instead of JLABEL
            
            JLabel contatto = new JLabel(nome + " " + cognome + " " + telefono, SwingConstants.LEFT);
//            contatti.add(contatto);
            contatto.setFont(font);
            panel.add(contatto);
            panel.add(Box.createHorizontalStrut(6));
            panel.add(Box.createVerticalStrut(8));
            repaint();
            revalidate();
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
