package carems.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import carems.backend.DataService;
import java.awt.Cursor;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;


public class LoginMenu extends JFrame implements ActionListener {
    private JTextField T2;
    private JPasswordField T3;
    private JButton btnRegister, LoginButton;
    
    private DataService userData = new DataService();

    // Add logo path.
    ImageIcon logo = new ImageIcon("img/carems_icon.png");

    // Add footer path.
    private final String footerPath = "img/login_footer.png";

    public LoginMenu() {
       
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 800, 600);
        panel.setBackground(new Color(42, 42, 42));
        panel.setLayout(null);
        add(panel);
        
        JLabel L1 = new JLabel("Welcome to Carems!");
        L1.setBounds(100, 120, 800, 100);
        L1.setFont(new Font("Arial", Font.PLAIN, 64));
        L1.setForeground(new Color(255,127,39));
        panel.add(L1);

        JLabel L2 = new JLabel("Username:");
        L2.setBounds(225, 230, 200, 20);
        L2.setFont(new Font("Arial", Font.PLAIN, 14));
        L2.setForeground(new Color(255,127,39));
        panel.add(L2);

        T2 = new JTextField();
        T2.setText("Carems");
        T2.setBounds(200, 250, 400, 25);
        T2.setFont(new Font("Arial", Font.PLAIN, 14));
        T2.setCaretColor(Color.WHITE);
        T2.setBackground(new Color(42, 42, 42));
        T2.setForeground(Color.WHITE);
        T2.setBorder(BorderFactory.createCompoundBorder(
            T2.getBorder(), 
            BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        panel.add(T2);

        JLabel L3 = new JLabel("Password:");
        L3.setBounds(225, 290, 200, 20);
        L3.setFont(new Font("Arial", Font.PLAIN, 14));
        L3.setForeground(new Color(255,127,39));
        panel.add(L3);
        
        T3 = new JPasswordField();
        T3.setText("OOP");
        T3.setBounds(200, 310, 400, 25);
        T3.setFont(new Font("Arial", Font.PLAIN, 14));
        T3.setCaretColor(Color.WHITE);
        T3.setForeground(Color.WHITE);
        T3.setBackground(new Color(42, 42, 42));
        T3.setBorder(BorderFactory.createCompoundBorder(
            T3.getBorder(), 
            BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        panel.add(T3);
        
        char echoChar = T3.getEchoChar();
        JCheckBox chbSeePassword = new JCheckBox("Show Password");
        chbSeePassword.setBounds(200, 340, 150, 25);
        chbSeePassword.setBackground(null);
        chbSeePassword.setFocusable(false);
        chbSeePassword.setForeground(Color.WHITE);
        chbSeePassword.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e){
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    T3.setEchoChar((char) 0);
                } else {
                    T3.setEchoChar(echoChar);
                }
            }
        });
        panel.add(chbSeePassword);
        
        LoginButton = new JButton("LOGIN");
        LoginButton.setFont(new Font("Arial", Font.BOLD, 18));
        LoginButton.setBounds(300, 375, 200, 50);
        LoginButton.setForeground(new Color(42,42,42));
        LoginButton.setBackground(new Color(255, 127, 39));
        panel.add(LoginButton);
        
        btnRegister = new JButton("<html><u>REGISTER</u></html>");
        btnRegister.setFont(new Font("Arial", Font.BOLD, 14));
        btnRegister.setBounds(300, 420, 200, 50);
        btnRegister.setForeground(new Color(255, 127, 39)); 
        btnRegister.setBackground(new Color(255, 255, 255, 0)); 
        btnRegister.setBorderPainted(false); 
        btnRegister.setFocusPainted(false); 
        btnRegister.setContentAreaFilled(false);
        btnRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(btnRegister);
       
        ImageIcon lockIcon = new ImageIcon("img/lock.png");
        Image lockImage = lockIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ImageIcon scaledLockIcon = new ImageIcon(lockImage);
        JLabel lockLabel = new JLabel(scaledLockIcon);
        lockLabel.setBounds(200, 290, 20, 20); 
        panel.add(lockLabel);
        
        ImageIcon PFPIcon = new ImageIcon("img/icon.png");
        Image IconImage = PFPIcon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        ImageIcon scaledPFPIcon = new ImageIcon(IconImage);
        JLabel IconLabel = new JLabel(scaledPFPIcon);
        IconLabel.setBounds( 200, 230, 20,20);
        panel.add(IconLabel);
        
        ImageIcon LogoIcon = new ImageIcon("img/carems_logo.png");
        Image LogoImage = LogoIcon.getImage().getScaledInstance(100, 40, Image.SCALE_DEFAULT);
        ImageIcon scaledLogoImage = new ImageIcon(LogoImage);
        JLabel Logoicon = new JLabel(scaledLogoImage);
        Logoicon.setBounds(280,0,200,200);
        panel.add(Logoicon);

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
        btnRegister.addActionListener(this);
        
//        Login();
    }
    
    private void Login(){
        new MainMenu();
        this.dispose();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == LoginButton){
            String Username = T2.getText();
            String Password = String.valueOf(T3.getPassword());
            if (userData.getResult(Username, Password)){
                JOptionPane.showMessageDialog(null, "Successfully logged In!", "Logged In",JOptionPane.INFORMATION_MESSAGE);
                Login();
            }
            else {
                JOptionPane.showMessageDialog(null, "Login failed. Please try again.", "Login Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if(e.getSource() == btnRegister){
            new RegisterMenu();
            this.dispose();
        }
    }
}
   