/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carems.gui;

import carems.backend.UserData;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author gelodrei
 */
public class RegisterMenu extends JFrame implements ActionListener {
    private JTextField T2, txtName, txtEmail, txtContact;
    private JPasswordField T3;
    
    private UserData userData = new UserData();

    // Add logo path.
    ImageIcon logo = new ImageIcon("img/carems_icon.png");

    // Add footer path.
    private final String footerPath = "img/login_footer.png";

    public RegisterMenu() {
       
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 800, 600);
        panel.setBackground(new Color(42, 42, 42));
        panel.setLayout(null);
        add(panel);
        
        JLabel L1 = new JLabel("Registration");
        L1.setBounds(230, 15, 800, 100);
        L1.setFont(new Font("Arial", Font.PLAIN, 64));
        L1.setForeground(new Color(255,127,39));
        panel.add(L1);

        JLabel L2 = new JLabel("Username:");
        L2.setBounds(225, 110, 200, 20);
        L2.setFont(new Font("Arial", Font.PLAIN, 14));
        L2.setForeground(new Color(255,127,39));
        panel.add(L2);

        T2 = new JTextField();
        T2.setText("Carems");
        T2.setBounds(200, 130, 400, 25);
        T2.setFont(new Font("Arial", Font.PLAIN, 14));
        T2.setCaretColor(Color.WHITE);
        T2.setBackground(new Color(42, 42, 42));
        T2.setForeground(Color.WHITE);
        T2.setBorder(BorderFactory.createCompoundBorder(
            T2.getBorder(), 
            BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        panel.add(T2);

        JLabel L3 = new JLabel("Password:");
        L3.setBounds(225, 170, 200, 20);
        L3.setFont(new Font("Arial", Font.PLAIN, 14));
        L3.setForeground(new Color(255,127,39));
        panel.add(L3);

        T3 = new JPasswordField();
        T3.setText("OOP");
        T3.setBounds(200, 190, 400, 25);
        T3.setFont(new Font("Arial", Font.PLAIN, 14));
        T3.setCaretColor(Color.WHITE);
        T3.setForeground(Color.WHITE);
        T3.setBackground(new Color(42, 42, 42));
        T3.setBorder(BorderFactory.createCompoundBorder(
            T3.getBorder(), 
            BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        panel.add(T3);
        
        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(225, 230, 200, 20);
        lblName.setFont(new Font("Arial", Font.PLAIN, 14));
        lblName.setForeground(new Color(255,127,39));
        panel.add(lblName);
        
        txtName = new JTextField();
        txtName.setText("Guo Hua Ping");
        txtName.setBounds(200, 250, 400, 25);
        txtName.setFont(new Font("Arial", Font.PLAIN, 14));
        txtName.setCaretColor(Color.WHITE);
        txtName.setForeground(Color.WHITE);
        txtName.setBackground(new Color(42, 42, 42));
        txtName.setBorder(BorderFactory.createCompoundBorder(
            txtName.getBorder(), 
            BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        panel.add(txtName);
        
        JLabel lblEmail = new JLabel("Email Address:");
        lblEmail.setBounds(225, 290, 200, 20);
        lblEmail.setFont(new Font("Arial", Font.PLAIN, 14));
        lblEmail.setForeground(new Color(255,127,39));
        panel.add(lblEmail);
        
        txtEmail = new JTextField();
        txtEmail.setText("WPS@gmail.com");
        txtEmail.setBounds(200, 310, 400, 25);
        txtEmail.setFont(new Font("Arial", Font.PLAIN, 14));
        txtEmail.setCaretColor(Color.WHITE);
        txtEmail.setForeground(Color.WHITE);
        txtEmail.setBackground(new Color(42, 42, 42));
        txtEmail.setBorder(BorderFactory.createCompoundBorder(
            txtEmail.getBorder(), 
            BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        panel.add(txtEmail);
        
        JLabel lblContact = new JLabel("Contact Number:");
        lblContact.setBounds(225, 350, 200, 20);
        lblContact.setFont(new Font("Arial", Font.PLAIN, 14));
        lblContact.setForeground(new Color(255,127,39));
        panel.add(lblContact);
        
        txtContact = new JTextField();
        txtContact.setText("091234567");
        txtContact.setBounds(200, 370, 400, 25);
        txtContact.setFont(new Font("Arial", Font.PLAIN, 14));
        txtContact.setCaretColor(Color.WHITE);
        txtContact.setForeground(Color.WHITE);
        txtContact.setBackground(new Color(42, 42, 42));
        txtContact.setBorder(BorderFactory.createCompoundBorder(
            txtContact.getBorder(), 
            BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        panel.add(txtContact);
        
        JButton LoginButton = new JButton("REGISTER");
        LoginButton.setFont(new Font("Arial", Font.BOLD, 18));
        LoginButton.setBounds(300, 420, 200, 50);
        LoginButton.setForeground(new Color(42,42,42));
        LoginButton.setBackground(new Color(255, 127, 39));
        panel.add(LoginButton);
       
        ImageIcon lockIcon = new ImageIcon("img/lock.png");
        Image lockImage = lockIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ImageIcon scaledLockIcon = new ImageIcon(lockImage);
        JLabel lockLabel = new JLabel(scaledLockIcon);
        lockLabel.setBounds(200, 170, 20, 20); 
        panel.add(lockLabel);
        
        ImageIcon PFPIcon = new ImageIcon("img/icon.png");
        Image IconImage = PFPIcon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        ImageIcon scaledPFPIcon = new ImageIcon(IconImage);
        JLabel IconLabel = new JLabel(scaledPFPIcon);
        IconLabel.setBounds( 200, 110, 20,20);
        panel.add(IconLabel);
        
//        ImageIcon LogoIcon = new ImageIcon("img/carems_logo.png");
//        Image LogoImage = LogoIcon.getImage().getScaledInstance(100, 40, Image.SCALE_DEFAULT);
//        ImageIcon scaledLogoImage = new ImageIcon(LogoImage);
//        JLabel Logoicon = new JLabel(scaledLogoImage);
//        Logoicon.setBounds(10,0,200,200);
//        panel.add(Logoicon);

        // Add footer image.
        try {
            ImageIcon imgFooter = new ImageIcon(ImageIO.read(new File(footerPath)));
            Image prescaledImgFooter = imgFooter.getImage().getScaledInstance(800, 80, Image.SCALE_AREA_AVERAGING);
            ImageIcon scaledImgFooter = new ImageIcon(prescaledImgFooter);
            JLabel lblFooterImg = new JLabel(scaledImgFooter);
            lblFooterImg.setBounds(-10, 480 ,800 ,80);
            panel.add(lblFooterImg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        setTitle("Carems - Login");                  
        setSize(800, 600);
        setLayout(null);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        
        // Set settings.
        this.setIconImage(logo.getImage());
        this.getContentPane().setBackground(new Color(42, 42, 42));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
           
        LoginButton.addActionListener(this);
    }
        @Override
        public void actionPerformed(ActionEvent e) {
            String Username = T2.getText();
            String Password = String.valueOf(T3.getPassword());
            String name = txtName.getText();
            String email = txtEmail.getText();
            String contact = txtContact.getText();
                    
            if (userData.addUser(Username, Password, name, email, contact)){
                JOptionPane.showMessageDialog(null, "Added Successfully!", "Registered",JOptionPane.INFORMATION_MESSAGE);
                new LoginMenu();
                this.dispose();
            }
            else {
                JOptionPane.showMessageDialog(null, "Registration failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
    }
}
