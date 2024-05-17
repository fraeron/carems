package carems.gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import javax.swing.JFrame;
import javax.swing.JLabel; 
import javax.swing.JPanel;
import javax.swing.JButton; 


public class MainMenu extends JFrame implements ActionListener {
    private final JPanel pnlSidebar;
    private final JButton btnBookingDetail, btnCar, btnOwner, btnCustomer, 
            btnHome, btnLogout;
    
    // Init. logo.
    private BufferedImage tmpLogo;
    private final String logoPath = "CaremsLogoPic.png";
    private final JLabel lblLogo;

    // Init. history.
    private static CardLayout lytCard = new CardLayout();
    private static final JPanel MainMenuPanel = new JPanel(lytCard);

    // Init. button constants.
    private final byte btnHeight = 30;
    private final int btnWidth = 200;
    
    // Add color.
    private final Color clrAshGrey = new Color(42, 42, 42);    
    private final Color clrMagmaOrange = new Color(255, 127, 39);
    
    // Init. fonts.
    private final Font fntDefault = new Font("Arial", Font.BOLD, 12);
    
    public MainMenu(){ 
        // Init. sidebar.
        pnlSidebar = new SidebarPanel();

        // Add history.
        MainMenuPanel.add(new CarPanel(), "CAR");
        MainMenuPanel.add(new HomePanel(), "HOME");
        // MainMenuPanel.add(new CustomerPanel(), "CUSTOMER");
        // MainMenuPanel.add(new OwnerPanel(), "OWNER");
        // MainMenuPanel.add(new BookingPanel(), "BOOKING");
        
        // Resize and add logo.
        lblLogo = new JLabel();
        lblLogo.setBounds(-5, -5, 200, 200);
        try {
            tmpLogo = ImageIO.read(new File(logoPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = tmpLogo.getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(),
        Image.SCALE_SMOOTH);
        lblLogo.setIcon(new ImageIcon(dimg));
        
        // Add buttons.
        btnHome = new JButton("HOME");
        btnHome.setBounds(0, 200, btnWidth, btnHeight);
        btnHome.setBackground(clrMagmaOrange);
        btnHome.setFont(fntDefault);
        btnHome.addActionListener(MainMenu.this);

        btnBookingDetail = new JButton("BOOKING DETAILS");
        btnBookingDetail.setBounds(0, 240, btnWidth, btnHeight);
        btnBookingDetail.setBackground(clrMagmaOrange);
        btnBookingDetail.setFont(fntDefault);
        btnBookingDetail.addActionListener(MainMenu.this);

        btnCustomer = new JButton("CUSTOMER");
        btnCustomer.setBounds(0, 280, btnWidth, btnHeight);
        btnCustomer.setBackground(clrMagmaOrange);
        btnCustomer.setFont(fntDefault);
        btnCustomer.addActionListener(MainMenu.this);
        
        btnCar = new JButton("CARS");
        btnCar.setBounds(0, 320, btnWidth, btnHeight);
        btnCar.setBackground(clrMagmaOrange);
        btnCar.setFont(fntDefault);
        btnCar.addActionListener(MainMenu.this);
        
        btnOwner = new JButton("OWNER");
        btnOwner.setBounds(0, 360, btnWidth, btnHeight);
        btnOwner.setBackground(clrMagmaOrange);
        btnOwner.setFont(fntDefault);
        btnOwner.addActionListener(MainMenu.this);
        
        btnLogout = new JButton("LOGOUT");
        btnLogout.setBounds(0, 500, btnWidth, btnHeight);
        btnLogout.setBackground(clrMagmaOrange);
        btnLogout.setFont(fntDefault);
        btnLogout.addActionListener(MainMenu.this);
        
        // Add widgets.
        this.add(lblLogo);
        this.add(btnBookingDetail);
        this.add(btnCustomer);
        this.add(btnHome);
        this.add(btnCar);
        this.add(btnOwner);
        this.add(btnLogout);
        this.add(pnlSidebar);
        
        // Add Main Menu Panel.
        MainMenuPanel.setBounds(200, 0, 800, 600);
        this.getContentPane().setBackground(clrAshGrey);
        this.add(MainMenuPanel);
        lytCard.show(MainMenuPanel, "HOME");
        
        // Set Main Menu properties.
        this.setTitle("Carems - Car Rental Management System");
        Dimension size = new Dimension(1000, 600);
        this.setPreferredSize(size);
        this.setResizable(false);
        this.setLayout(null);
        this.pack();
        this.getContentPane().setBackground(clrAshGrey);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnHome){
            lytCard.show(MainMenuPanel, "HOME");
        }
        else if(e.getSource() == btnCustomer){
            lytCard.show(MainMenuPanel, "CUSTOMER");
        }
        else if(e.getSource() == btnBookingDetail){
            lytCard.show(MainMenuPanel, "BOOKING");
        }
        else if(e.getSource() == btnCar){
            lytCard.show(MainMenuPanel, "CAR");
        }
        else if(e.getSource() == btnOwner){
            lytCard.show(MainMenuPanel, "OWNER");
        }
        else if(e.getSource() == btnLogout){
            // new LoginMenu();
            this.dispose();
        }
    }

    public static void switchPanes(String panelName) {
        ArrayList<String> lstPanes = new ArrayList<>();
        lstPanes.add("HOME"); 
        lstPanes.add("CUSTOMER"); 
        lstPanes.add("BOOKING"); 
        lstPanes.add("CAR"); 
        lstPanes.add("OWNER"); 

        if (lstPanes.contains(panelName)) {
            lytCard.show(MainMenuPanel, panelName);
        }
    }
}
