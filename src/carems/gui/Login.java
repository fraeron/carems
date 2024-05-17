package carems.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Login extends JFrame {
    JTextField T2, T3;

    public Login() {
       
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 875, 490);
        panel.setBackground(new Color(42, 42, 42));
        panel.setLayout(null);
        add(panel);
        
        JLabel L1 = new JLabel("ADMIN LOGIN");
        L1.setBounds(350, 200, 160, 22);
        L1.setFont(new Font("Calibri", Font.BOLD, 20));
        L1.setForeground(new Color(255,127,39));
        panel.add(L1);

        JLabel L2 = new JLabel("Username:  ");
        L2.setBounds(225, 250, 155, 22);
        L2.setFont(new Font("Arial", Font.BOLD, 16));
        L2.setForeground(new Color(255,127,39));
        panel.add(L2);

        T2 = new JTextField();
        T2.setBounds(325, 250, 183, 20);
        T2.setFont(new Font("Arial", Font.PLAIN, 18));
        Color Orange = new Color(225, 127, 39);
        T2.setBackground(new Color(42, 42, 42));
        T2.setForeground(Orange);
        panel.add(T2);

        JLabel L3 = new JLabel("Password: ");
        L3.setBounds(225, 300, 200, 30);
        L3.setFont(new Font("Arial", Font.BOLD, 16));
        L3.setForeground(new Color(255,127,39));
        panel.add(L3);

        T3 = new JTextField();
        T3.setBounds(325, 300, 183, 20);
        T3.setFont(new Font("Arial", Font.PLAIN, 18));
        T3.setForeground(new Color(255,127,39));
        T3.setBackground(new Color(42, 42, 42));
        panel.add(T3);
        
        JLabel L4 = new JLabel("CAREMS ");
        L4.setBounds(350, 50, 200, 30);
        L4.setFont(new Font("Arial", Font.BOLD, 25));
        L4.setForeground(new Color(255,127,39));
        panel.add(L4);     
        
        JButton LoginButton = new JButton("LOGIN");
        LoginButton.setBounds(325, 375, 200, 30);
        LoginButton.setForeground(new Color(42,42,42));
        LoginButton.setBackground(new Color(255, 127, 39));
        panel.add(LoginButton);
       

        ImageIcon lockIcon = new ImageIcon("lock.png");
        Image lockImage = lockIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ImageIcon scaledLockIcon = new ImageIcon(lockImage);
        JLabel lockLabel = new JLabel(scaledLockIcon);
        lockLabel.setBounds(200, 305, 20, 20); 
        panel.add(lockLabel);

        
        ImageIcon PFPIcon = new ImageIcon("icon.png");
        Image IconImage = PFPIcon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        ImageIcon scaledPFPIcon = new ImageIcon(IconImage);
        JLabel IconLabel = new JLabel(scaledPFPIcon);
        IconLabel.setBounds( 200, 250, 20,20);
        panel.add(IconLabel);
        
        ImageIcon LogoIcon = new ImageIcon("CaremsLogoPic.png");
        JLabel Logoicon = new JLabel(LogoIcon);
        Logoicon.setBounds(300,10,250,250);
        panel.add(Logoicon);
        
        setTitle("Login Window");                  
        setSize(885, 500);
        setLayout(null);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        
           
         LoginButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e){
                
                String Username = T2.getText();
                String Password = T3.getText();
                
            if(Username.equals("Carems") && Password.equals("OOP")){
      
          JOptionPane.showMessageDialog(null, "Successfully Logged In!", "Admin Login",JOptionPane.INFORMATION_MESSAGE);

   }
            else{
      
          JOptionPane.showMessageDialog(null, "Login Failed. Please try again.", "Login Error", JOptionPane.ERROR_MESSAGE);

  }
            }
        
                });
    }
    
}
   