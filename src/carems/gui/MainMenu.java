package carems.gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

import javax.swing.JFrame;
import javax.swing.JLabel; 
import javax.swing.JPanel;
import javax.swing.JButton; 


public class MainMenu extends JFrame implements ActionListener {
    private final JPanel pnlSidebar;
    private final ImageIcon logoPic = new ImageIcon("CaremsLogoPic.png");
    private final JLabel lblCarems, caremsLogo;
    private final JButton btnBookingDetail, btnCar, btnOwner, btnCustomer, 
            btnLogout;
    
    // Init. history.
    private final JPanel MainMenuPanel = new JPanel(new CardLayout());

    // Init. button constants.
    private final byte btnHeight = 30;
    private final int btnWidth = 200;
    
    // Add color.
    private final Color clrAshGrey = new Color(42, 42, 42);    
    private final Color clrMagmaOrange = new Color(255, 127, 39);
    
    // Init. fonts.
    private final Font fntHeader = new Font("League Spartan", Font.BOLD, btnHeight); 
    private final Font fntDefault = new Font("League Spartan", Font.BOLD, 12);
    
    public MainMenu(){  

        pnlSidebar = new SidebarPanel();
        
        // Add history.
        MainMenuPanel.add(new CarPanel(), "");
        
        lblCarems = new JLabel();
        lblCarems.setText("CAREMS");
        lblCarems.setBounds(30, 125, 500, 100);
        lblCarems.setForeground(clrMagmaOrange);
        lblCarems.setFont(fntHeader);
        
        // Add logo.
        caremsLogo = new JLabel();
        caremsLogo.setBounds(-5, -5, 200, 200);
        caremsLogo.setIcon(logoPic);
        
        // Add buttons.
        btnBookingDetail = new JButton("BOOKING DETAILS");
        btnBookingDetail.setBounds(0, 200, btnWidth, btnHeight);
        btnBookingDetail.setBackground(clrMagmaOrange);
        btnBookingDetail.setFont(fntDefault);
        btnBookingDetail.addActionListener(MainMenu.this);
        
        btnCustomer = new JButton("CUSTOMER");
        btnCustomer.setBounds(0, 240, btnWidth, btnHeight);
        btnCustomer.setBackground(clrMagmaOrange);
        btnCustomer.setFont(fntDefault);
        btnCustomer.addActionListener(MainMenu.this);
        
        btnCar = new JButton("CARS");
        btnCar.setBounds(0, 280, btnWidth, btnHeight);
        btnCar.setBackground(clrMagmaOrange);
        btnCar.setFont(fntDefault);
        btnCar.addActionListener(MainMenu.this);
        
        btnOwner = new JButton("OWNER");
        btnOwner.setBounds(0, 320, btnWidth, btnHeight);
        btnOwner.setBackground(clrMagmaOrange);
        btnOwner.setFont(fntDefault);
        btnOwner.addActionListener(MainMenu.this);
        
        btnLogout = new JButton("LOGOUT");
        btnLogout.setBounds(0, 500, btnWidth, btnHeight);
        btnLogout.setBackground(clrMagmaOrange);
        btnLogout.setFont(fntDefault);
        btnLogout.addActionListener(MainMenu.this);
        
        // Set MainMenu properties.
        this.setTitle("Carems - Car Rental Management System");
        Dimension size = new Dimension(800, 600);
        this.setBackground(clrAshGrey);
        this.setPreferredSize(size);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        this.add(lblCarems);
        this.add(caremsLogo);
        this.add(btnBookingDetail);
        this.add(btnCustomer);
        this.add(btnCar);
        this.add(btnOwner);
        this.add(btnLogout);
        this.add(pnlSidebar);
        
        // Debug.
        MainMenuPanel.setBounds(200, 0, 600, 600);
        this.add(MainMenuPanel);
        
        this.pack();
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
             
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnCustomer){
        }
        else if(e.getSource() == btnBookingDetail){
        }
        else if(e.getSource() == btnCar){
        }
        else if(e.getSource() == btnOwner){
        }
        else if(e.getSource() == btnLogout){
        }
    }
}
