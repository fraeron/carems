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
import javax.swing.JLabel;
import javax.swing.JPanel;


class SidebarPanel extends JPanel implements ActionListener{
    private final JButton btnBookingDetail, btnCar, btnOwner, 
    btnCustomer, btnHome, btnLogout;
    
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
    
    // Init. fonts.
    private final Font fntDefault = new Font("Arial", Font.PLAIN, 14);
    
    public SidebarPanel(){
        this.setPreferredSize(panelSize);
        this.setBackground(clrDarkGrey);  
        this.setLayout(null);

        // Set border.
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, clrMagmaOrange));

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
        btnHome = new JButton("Home");
        btnHome.setBounds(0, 200, btnWidth, btnHeight);
        btnHome.setBackground(clrMagmaOrange);
        btnHome.setFont(fntDefault);
        btnHome.addActionListener(SidebarPanel.this);

        btnBookingDetail = new JButton("Booking Details");
        btnBookingDetail.setBounds(0, 240, btnWidth, btnHeight);
        btnBookingDetail.setBackground(clrMagmaOrange);
        btnBookingDetail.setFont(fntDefault);
        btnBookingDetail.addActionListener(SidebarPanel.this);

        btnCustomer = new JButton("Customer");
        btnCustomer.setBounds(0, 280, btnWidth, btnHeight);
        btnCustomer.setBackground(clrMagmaOrange);
        btnCustomer.setFont(fntDefault);
        btnCustomer.addActionListener(SidebarPanel.this);
        
        btnCar = new JButton("Cars");
        btnCar.setBounds(0, 320, btnWidth, btnHeight);
        btnCar.setBackground(clrMagmaOrange);
        btnCar.setFont(fntDefault);
        btnCar.addActionListener(SidebarPanel.this);
        
        btnOwner = new JButton("Owner");
        btnOwner.setBounds(0, 360, btnWidth, btnHeight);
        btnOwner.setBackground(clrMagmaOrange);
        btnOwner.setFont(fntDefault);
        btnOwner.addActionListener(SidebarPanel.this);
        
        btnLogout = new JButton("Logout");
        btnLogout.setBounds(0, 500, btnWidth, btnHeight);
        btnLogout.setBackground(clrMagmaOrange);
        btnLogout.setFont(fntDefault);
        btnLogout.addActionListener(SidebarPanel.this);

        btnHome.setFocusable(false);
        btnBookingDetail.setFocusable(false);
        btnCustomer.setFocusable(false);
        btnOwner.setFocusable(false);
        btnCar.setFocusable(false);
        btnLogout.setFocusable(false);

        // Set flat-look.
        btnHome.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, clrMagmaOrange));
        btnBookingDetail.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, clrMagmaOrange));
        btnCustomer.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, clrMagmaOrange));
        btnOwner.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, clrMagmaOrange));
        btnCar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, clrMagmaOrange));
        btnLogout.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, clrMagmaOrange));
        
        // Add widgets.
        this.add(lblLogo);
        this.add(btnBookingDetail);
        this.add(btnCustomer);
        this.add(btnHome);
        this.add(btnCar);
        this.add(btnOwner);
        this.add(btnLogout);
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
            switchBtnColors(btnBookingDetail, 0);
            switchBtnColors(btnOwner, 0);
            switchBtnColors(btnCar, 1);
        }
        else if (btnName.equals("HOME")) {
            switchBtnColors(btnHome, 1);
            switchBtnColors(btnCustomer, 0);
            switchBtnColors(btnBookingDetail, 0);
            switchBtnColors(btnOwner, 0);
            switchBtnColors(btnCar, 0);
        }
        else if (btnName.equals("CUSTOMER")) {
            switchBtnColors(btnHome, 0);
            switchBtnColors(btnCustomer, 1);
            switchBtnColors(btnBookingDetail, 0);
            switchBtnColors(btnOwner, 0);
            switchBtnColors(btnCar, 0);
        }
        else if (btnName.equals("BOOKING")) {
            switchBtnColors(btnHome, 0);
            switchBtnColors(btnCustomer, 0);
            switchBtnColors(btnBookingDetail, 1);
            switchBtnColors(btnOwner, 0);
            switchBtnColors(btnCar, 0);
        }
        else if (btnName.equals("OWNER")) {
            switchBtnColors(btnHome, 0);
            switchBtnColors(btnCustomer, 0);
            switchBtnColors(btnBookingDetail, 0);
            switchBtnColors(btnOwner, 1);
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
        else if(e.getSource() == btnBookingDetail){
            MainMenu.switchPanes("BOOKING");
        }
        else if(e.getSource() == btnOwner){
            MainMenu.switchPanes("OWNER");
        }
    }
}
