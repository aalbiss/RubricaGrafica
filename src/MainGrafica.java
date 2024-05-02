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
    JLabel titolo;
    JLabel plus;
    JPanel panel;
    JPanel bottom;
    ArrayList<JLabel> contatti;
    
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
        Font font = new Font("Arial", Font.PLAIN, 25);
        Font fontTitolo = new Font("Arial", Font.PLAIN, 32);
        
        r.importa();
        
        titolo = new JLabel("Rubrica telefonica", SwingConstants.CENTER);
        titolo.setBounds(0,0, 400, 60);
        titolo.setFont(fontTitolo);
        add(titolo);
        
        
        contatti = new ArrayList<>();
        
        for (int i = 0; i < 400; i++) {
            JLabel l = new JLabel("Label" + i);
            l.setFont(font);
            
            contatti.add(l);
        }
        
        panel = new JPanel();
        
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        for (JLabel l : contatti) {
            panel.add(l);
            panel.add(Box.createHorizontalStrut(6));
            panel.add(Box.createVerticalStrut(8));
        }
        
        scrollPane = new JScrollPane(panel);
        scrollPane.setBounds(0,60,385,440);
        
        
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
        
        
        getContentPane().add(scrollPane);
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
            r.inserimento();
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
