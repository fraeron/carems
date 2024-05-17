package carems.gui;

import carems.SampleData.UserData;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JLabel; 
import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton; 
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Login extends JFrame {

    private MainMenu mainMenu;
    
    private UserData userData = new UserData();
    
    private JTextField entryUsername;
    private JPasswordField entryPassword;
    private JLabel lblUsername, lblPassword, lblHeader, lblSubHeader;
    private JPanel pnlEntries;
    private JButton btnSubmit;
    
    //Result JFrame
    private JFrame resultFrame;
    private JLabel lblResult;
    private JButton btnProceed;
    private JPanel resultPanel;

    // Add color.
    private final Color clrAshGrey = new Color(42, 42, 42);    
    private final Color clrMagmaOrange = new Color(255, 127, 39);
    
    // Init. fonts.
    private final Font fntHeader = new Font("Arial", Font.BOLD, 50); 
    private final Font fntDefault = new Font("Arial", Font.BOLD, 12);

    public Login() {
        lblHeader = new JLabel("CAREMS");
        lblSubHeader = new JLabel("Please login your credentials to continue.");
        entryUsername = new JTextField();
        entryPassword = new JPasswordField();
        lblUsername = new JLabel("Username:");
        lblPassword = new JLabel("Password:");
        btnSubmit = new JButton("Login");
        
        lblHeader.setSize(100, 100);
        lblHeader.setFont(fntHeader);
        lblHeader.setForeground(clrMagmaOrange);
        lblHeader.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        lblSubHeader.setSize(100, 50);
        lblSubHeader.setFont(fntDefault);
        lblSubHeader.setForeground(clrMagmaOrange);
        lblSubHeader.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        // Add these label and entries to a panel.
        pnlEntries = new JPanel();
        pnlEntries.setLayout(new BoxLayout(pnlEntries, BoxLayout.Y_AXIS));
        pnlEntries.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        pnlEntries.setSize(100, 100);
        pnlEntries.setBackground(clrAshGrey);

        lblUsername.setSize(50, 50);
        lblUsername.setFont(fntDefault);
        lblUsername.setForeground(clrMagmaOrange);
        lblUsername.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        entryUsername.setSize(300, 25);
        entryUsername.setFont(fntDefault);
        entryUsername.setMaximumSize(entryUsername.getSize());
        entryUsername.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        lblPassword.setSize(50, 50);
        lblPassword.setFont(fntDefault);
        lblPassword.setForeground(clrMagmaOrange);
        lblPassword.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        entryPassword.setSize(300, 25);
        entryPassword.setFont(fntDefault);
        entryPassword.setMaximumSize(entryPassword.getSize());
        entryPassword.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        btnSubmit.setSize(400, 25); // !
        btnSubmit.setPreferredSize(btnSubmit.getSize());
        btnSubmit.setFont(fntDefault);
        btnSubmit.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        // Set color and fonts.
        btnSubmit.setForeground(clrAshGrey);
        btnSubmit.setBackground(clrMagmaOrange);

        // Add labels and entries to a panel.
        pnlEntries.add(lblUsername);
        pnlEntries.add(entryUsername);
        pnlEntries.add(Box.createVerticalGlue());
        pnlEntries.add(lblPassword);
        pnlEntries.add(entryPassword);
        
        // Add it all together in main JFrame.
        this.add(Box.createVerticalGlue());
        this.add(lblHeader);
        this.add(lblSubHeader);
        this.add(Box.createVerticalGlue());
        this.add(pnlEntries);
        this.add(Box.createVerticalGlue());
        this.add(btnSubmit);
        this.add(Box.createVerticalGlue());

        this.setTitle("LOGIN");
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.getContentPane().setBackground(clrAshGrey);
        this.setSize(600, 400);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        btnSubmit.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                
                String Username = entryUsername.getText();
                String Password = entryPassword.getText();
                
                if (userData.getResult(Username, Password)) {
                    displayResultFrame("Successfully Logged In!", "Login", Login.this, true);
                } else {
                    displayResultFrame("Login Failed. Please try again.", "Login Error", Login.this, false);
                }
            }
        });
    }
    
    private void displayResultFrame(String message, String title, JFrame Login, Boolean result) {
        resultFrame = new JFrame(title);
        resultFrame.setSize(300, 150);
        resultFrame.setResizable(false);
        resultFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        resultFrame.setLayout(new BorderLayout());
        resultFrame.setLocationRelativeTo(null);
        resultFrame.setBackground(clrAshGrey);
        
        resultFrame.getContentPane().setBackground(clrAshGrey);
        
        lblResult = new JLabel(message, SwingConstants.CENTER);
        lblResult.setForeground(clrMagmaOrange);
        lblResult.setFont(fntDefault);
        
        btnProceed = new JButton("PROCEED");
        btnProceed.setForeground(clrAshGrey);
        btnProceed.setBackground(clrMagmaOrange);
        btnProceed.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(result){
                    mainMenu = new MainMenu();
                    Login.dispose();
                    resultFrame.dispose();
                }
                else{
                    resultFrame.dispose();
                }
            }
        });
        resultPanel = new JPanel();
        resultPanel.setBackground(clrAshGrey);
        resultPanel.add(btnProceed);
        
        resultFrame.add(lblResult, BorderLayout.CENTER);
        resultFrame.add(resultPanel, BorderLayout.SOUTH);
        
        resultFrame.setVisible(true);
    }
    
}
   