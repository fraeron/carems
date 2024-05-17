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

    

    private final Dimension panelSize = new Dimension(800,600);
    
     // Init. button constants.
    private final byte btnHeight = 30;
    private final int btnWidth = 200;
    
    // Add color.
    private final Color clrAshGrey = new Color(42, 42, 42);    
    private final Color clrMagmaOrange = new Color(255, 127, 39);
    
    // Init. fonts.
    private final Font fntDefault = new Font("Arial", Font.BOLD, 12);
    
    public SidebarPanel(){
        this.setPreferredSize(panelSize);
        this.setBackground(clrAshGrey);  
        this.setLayout(null);

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
        btnHome.addActionListener(SidebarPanel.this);

        btnBookingDetail = new JButton("BOOKING DETAILS");
        btnBookingDetail.setBounds(0, 240, btnWidth, btnHeight);
        btnBookingDetail.setBackground(clrMagmaOrange);
        btnBookingDetail.setFont(fntDefault);
        btnBookingDetail.addActionListener(SidebarPanel.this);

        btnCustomer = new JButton("CUSTOMER");
        btnCustomer.setBounds(0, 280, btnWidth, btnHeight);
        btnCustomer.setBackground(clrMagmaOrange);
        btnCustomer.setFont(fntDefault);
        btnCustomer.addActionListener(SidebarPanel.this);
        
        btnCar = new JButton("CARS");
        btnCar.setBounds(0, 320, btnWidth, btnHeight);
        btnCar.setBackground(clrMagmaOrange);
        btnCar.setFont(fntDefault);
        btnCar.addActionListener(SidebarPanel.this);
        
        btnOwner = new JButton("OWNER");
        btnOwner.setBounds(0, 360, btnWidth, btnHeight);
        btnOwner.setBackground(clrMagmaOrange);
        btnOwner.setFont(fntDefault);
        btnOwner.addActionListener(SidebarPanel.this);
        
        btnLogout = new JButton("LOGOUT");
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
        
        // Add widgets.
        this.add(lblLogo);
        this.add(btnBookingDetail);
        this.add(btnCustomer);
        this.add(btnHome);
        this.add(btnCar);
        this.add(btnOwner);
        this.add(btnLogout);
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
