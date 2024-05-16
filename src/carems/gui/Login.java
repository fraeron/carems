package carems.gui;

import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {
    JTextField T2, T3;

    public Login() {
       
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 875, 490);
        panel.setBackground(new Color(42, 42, 42));
        panel.setLayout(null);
        add(panel);
        
        JLabel L1 = new JLabel("ADMIN LOGIN");
        L1.setBounds(350, 10, 160, 22);
        L1.setFont(new Font("Calibri", Font.BOLD, 20));
        L1.setForeground(new Color(255,127,39));
        panel.add(L1);

        JLabel L2 = new JLabel("Username:  ");
        L2.setBounds(200, 70, 155, 22);
        L2.setFont(new Font("Arial", Font.BOLD, 16));
        L2.setForeground(new Color(255,127,39));
        panel.add(L2);

        T2 = new JTextField();
        T2.setBounds(290, 70, 200, 20);
        T2.setFont(new Font("Arial", Font.PLAIN, 18));
        Color Orange = new Color(225, 127, 39);
        T2.setBackground(new Color(42, 42, 42));
        T2.setForeground(Orange);
        panel.add(T2);

        JLabel L3 = new JLabel("Password: ");
        L3.setBounds(200, 110, 153, 22);
        L3.setFont(new Font("Arial", Font.BOLD, 16));
        L3.setForeground(new Color(255,127,39));
        panel.add(L3);

        T3 = new JTextField();
        T3.setBounds(300, 110, 183, 20);
        T3.setFont(new Font("Arial", Font.PLAIN, 18));
        T3.setForeground(new Color(255,127,39));
        T3.setBackground(new Color(42, 42, 42));
        panel.add(T3);

        JButton LoginButton = new JButton("LOGIN BUTTON");
        LoginButton.setBounds(300, 200, 200, 30);
        LoginButton.setForeground(new Color(42,42,42));
        LoginButton.setBackground(new Color(255, 127, 39));
        panel.add(LoginButton);
       

        ImageIcon lockIcon = new ImageIcon("D:\\Liam Pogi\\OOP\\Car Rental System Management\\icon\\lock.png");
        Image lockImage = lockIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ImageIcon scaledLockIcon = new ImageIcon(lockImage);
        JLabel lockLabel = new JLabel(scaledLockIcon);
        lockLabel.setBounds(175, 110, 20, 20); 
        panel.add(lockLabel);

        
        ImageIcon PFPIcon = new ImageIcon("D:\\Liam Pogi\\OOP\\Car Rental System Management\\icon\\icon.png");
        Image IconImage = PFPIcon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        ImageIcon scaledPFPIcon = new ImageIcon(IconImage);
        JLabel IconLabel = new JLabel(scaledPFPIcon);
        IconLabel.setBounds( 175, 70, 20,20);
        panel.add(IconLabel);

        
        setSize(885, 500);
        setLayout(null);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
    } 
    
 private void AdminLogin() {
     String username, password;
     
          username = JOptionPane.showInputDialog(null, "Enter your Username: ");
                if (username.equalsIgnoreCase("L14M"))
                {
                    password = JOptionPane.showInputDialog(null, "Enter your Password");
                    if (password.equals ("Pogiko123"))
                            {
                                JOptionPane.showMessageDialog(null, "Authentication Successful.");
                            }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Authentication Failed.","Wrong Password.",JOptionPane.ERROR_MESSAGE);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Cannot find username.","No Username Match.",JOptionPane.ERROR_MESSAGE);
                }
 }
}
