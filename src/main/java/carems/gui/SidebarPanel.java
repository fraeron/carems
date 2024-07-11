package carems.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
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


class SidebarPanel extends JPanel implements ActionListener{
    private final JButton btnRentCar, btnReturnCar, btnCar, btnOwner, 
    btnCustomer, btnHome, btnLogout, btnBooking;
    
    // Init. logo.
    private BufferedImage tmpLogo;
    private final String logoPath = "img/carems_logo.png";
    private final JLabel lblLogo;

    private final Dimension panelSize = new Dimension(600,600);
    
     // Init. button constants.
    private final byte btnHeight = 40;
    private final int btnWidth = 200;
    
    // Add color.
    private final Color clrDarkGrey = new Color(28, 28, 28);    
    private final Color clrMagmaOrange = new Color(255, 127, 39);
    private final Color clrLabelHelper = Color.GRAY;
    
    // Init. fonts.
    private final Font fntDefault = new Font("Arial", Font.PLAIN, 14);     
    private final Font fntLogout = new Font("Arial", Font.PLAIN, 12);   
  
    private final Font fntHelper = new Font("Arial", Font.PLAIN, 10);


    // Init. parent menu.
    private final JFrame Parent;
    
    public SidebarPanel(JFrame ParentMenu){
        this.setPreferredSize(panelSize);
        this.setBackground(clrDarkGrey);  
        this.setLayout(null);

        // Set border.
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, clrMagmaOrange));

        // Hook parent menu.
        Parent = ParentMenu;

        // Resize and add logo.
        lblLogo = new JLabel();
        lblLogo.setBounds(10,20, 180, 75);
        try {
            tmpLogo = ImageIO.read(new File(logoPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = tmpLogo.getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(),
        Image.SCALE_SMOOTH);
        lblLogo.setIcon(new ImageIcon(dimg));
        
        // Add buttons.
        btnHome = new JButton("Home");
        btnHome.setBounds(0, 120, btnWidth, btnHeight);
        btnHome.setBackground(clrMagmaOrange);
        btnHome.setFont(fntDefault);
        btnHome.addActionListener(SidebarPanel.this);
        
        JLabel lblTransaction = new JLabel("<html><i>Transaction-related</i></html>");
        lblTransaction.setBounds(10, 180, btnWidth, 20);
        lblTransaction.setForeground(clrLabelHelper);
        lblTransaction.setFont(fntHelper);
        this.add(lblTransaction);

        btnRentCar = new JButton("Rent a Car");
        btnRentCar.setBounds(0, 200, btnWidth, btnHeight);
        btnRentCar.setBackground(clrMagmaOrange);
        btnRentCar.setFont(fntDefault);
        btnRentCar.addActionListener(SidebarPanel.this);
        
        btnReturnCar = new JButton("Return a Car");
        btnReturnCar.setBounds(0, 240, btnWidth, btnHeight);
        btnReturnCar.setBackground(clrMagmaOrange);
        btnReturnCar.setFont(fntDefault);
        btnReturnCar.addActionListener(SidebarPanel.this);

        JLabel lblDatabase = new JLabel("<html><i>Database-related</i></html>");
        lblDatabase.setBounds(10, 300, btnWidth, 10);
        lblDatabase.setForeground(clrLabelHelper);
        lblDatabase.setFont(fntHelper);
        this.add(lblDatabase);
        
        btnCar = new JButton("Manage Cars");
        btnCar.setBounds(0, 320, btnWidth, btnHeight);
        btnCar.setBackground(clrMagmaOrange);
        btnCar.setFont(fntDefault);
        btnCar.addActionListener(SidebarPanel.this);
        
        btnBooking = new JButton("Booking Records");
        btnBooking.setBounds(0, 360, btnWidth, btnHeight);
        btnBooking.setBackground(clrMagmaOrange);
        btnBooking.setFont(fntDefault);
        btnBooking.addActionListener(SidebarPanel.this);
        
        btnCustomer = new JButton("Customer Records");
        btnCustomer.setBounds(0, 400, btnWidth, btnHeight);
        btnCustomer.setBackground(clrMagmaOrange);
        btnCustomer.setFont(fntDefault);
        btnCustomer.addActionListener(SidebarPanel.this);
        
        btnOwner = new JButton("Location Records");
        btnOwner.setBounds(0, 440, btnWidth, btnHeight);
        btnOwner.setBackground(clrMagmaOrange);
        btnOwner.setFont(fntDefault);
        btnOwner.addActionListener(SidebarPanel.this);
        
        btnLogout = new JButton("Logout");
        btnLogout.setBounds(0, 550, btnWidth, btnHeight);
        switchBtnColors(btnLogout, 0);
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setFont(fntLogout);
        btnLogout.addActionListener(SidebarPanel.this);

        btnHome.setFocusable(false);
        btnRentCar.setFocusable(false);
        btnReturnCar.setFocusable(false);
        btnCustomer.setFocusable(false);
        btnOwner.setFocusable(false);
        btnBooking.setFocusable(false);
        btnCar.setFocusable(false);
        btnLogout.setFocusable(false);

        // Set flat-look.
        btnHome.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, clrMagmaOrange));
        btnRentCar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, clrMagmaOrange));
        btnReturnCar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, clrMagmaOrange));
        btnCustomer.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, clrMagmaOrange));
        btnOwner.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, clrMagmaOrange));
        btnCar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, clrMagmaOrange));
        btnLogout.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, clrMagmaOrange));        
        btnBooking.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, clrMagmaOrange));

        
        // Add widgets.
        this.add(lblLogo);
        this.add(btnRentCar);
        this.add(btnReturnCar);
        this.add(btnCustomer);
        this.add(btnHome);
        this.add(btnCar);
        this.add(btnOwner);
        this.add(btnLogout);        
        this.add(btnBooking);

    }

    public void switchBtnColors(JButton button, int face) {
        if (face == 1) {
            button.setBackground(clrMagmaOrange);
            button.setForeground(clrDarkGrey);
        } else {
            button.setBackground(clrDarkGrey);
            button.setForeground(Color.GRAY);
        }
    }
    
    public void switchBtnColor(String btnName) {
        if (btnName.equals("CAR")) {
            switchBtnColors(btnHome, 0);
            switchBtnColors(btnCustomer, 0);
            switchBtnColors(btnRentCar, 0);
            switchBtnColors(btnReturnCar, 0);
            switchBtnColors(btnBooking, 0);
            switchBtnColors(btnOwner, 0);
            switchBtnColors(btnCar, 1);            
        }
        else if (btnName.equals("HOME")) {
            switchBtnColors(btnHome, 1);
            switchBtnColors(btnCustomer, 0);
            switchBtnColors(btnRentCar, 0);
            switchBtnColors(btnReturnCar, 0);
            switchBtnColors(btnBooking, 0);
            switchBtnColors(btnOwner, 0);
            switchBtnColors(btnCar, 0);
        }
        else if (btnName.equals("CUSTOMER")) {
            switchBtnColors(btnHome, 0);
            switchBtnColors(btnCustomer, 1);
            switchBtnColors(btnRentCar, 0);
            switchBtnColors(btnReturnCar, 0);
            switchBtnColors(btnBooking, 0);
            switchBtnColors(btnOwner, 0);
            switchBtnColors(btnCar, 0);
        }
        else if (btnName.equals("BOOKING")) {
            switchBtnColors(btnHome, 0);
            switchBtnColors(btnCustomer, 0);
            switchBtnColors(btnRentCar, 0);
            switchBtnColors(btnReturnCar, 0);
            switchBtnColors(btnBooking, 1);
            switchBtnColors(btnOwner, 0);
            switchBtnColors(btnCar, 0);
        }
        else if (btnName.equals("LOCATION")) {
            switchBtnColors(btnHome, 0);
            switchBtnColors(btnCustomer, 0);
            switchBtnColors(btnRentCar, 0);
            switchBtnColors(btnReturnCar, 0);
            switchBtnColors(btnBooking, 0);
            switchBtnColors(btnOwner, 1);
            switchBtnColors(btnCar, 0);
        }
        else if (btnName.equals("RETURN")) {
            switchBtnColors(btnHome, 0);
            switchBtnColors(btnCustomer, 0);
            switchBtnColors(btnRentCar, 0);
            switchBtnColors(btnBooking, 0);
            switchBtnColors(btnReturnCar, 1);
            switchBtnColors(btnOwner, 0);
            switchBtnColors(btnCar, 0);
        }
        else if (btnName.equals("RENT")) {
            switchBtnColors(btnHome, 0);
            switchBtnColors(btnCustomer, 0);
            switchBtnColors(btnRentCar, 1);
            switchBtnColors(btnBooking, 0);
            switchBtnColors(btnReturnCar, 0);
            switchBtnColors(btnOwner, 0);
            switchBtnColors(btnCar, 0);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnCar){
            MainMenu.switchPanes("CAR");
        }
        else if(e.getSource() == btnHome){
            MainMenu.switchPanes("HOME");
        }
        else if(e.getSource() == btnCustomer){
            MainMenu.switchPanes("CUSTOMER");
        }
        else if(e.getSource() == btnBooking){
            MainMenu.switchPanes("BOOKING");
        }
        else if(e.getSource() == btnOwner){
            MainMenu.switchPanes("LOCATION");
        }
        else if (e.getSource() == btnRentCar){
            MainMenu.switchPanes("RENT");
        }
        else if (e.getSource() == btnReturnCar){
            MainMenu.switchPanes("RETURN");
        }
        else if(e.getSource() == btnLogout){
            int yesnoFX = JOptionPane.YES_NO_OPTION;
            if (JOptionPane.showConfirmDialog(
                null, 
            "Are you sure you want to log out?",
            "LOGGING OUT",
            yesnoFX
            ) == JOptionPane.YES_OPTION) {
                new LoginMenu();
                Parent.dispose();
            }
        }
    }
}
