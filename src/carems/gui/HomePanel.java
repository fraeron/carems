package carems.gui;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HomePanel extends JPanel implements ActionListener{
    private final JButton btnCustomer, btnCar, btnOwner, btnBooking;
    private final JLabel lblHeader, lblSubheader;
    private final JPanel pnlSelections;

    // Init. fonts.
    private final String defaultFont = "Arial";
    private final Font fntSubHeader = new Font(
            defaultFont, Font.PLAIN, 16);
    private final Font fntSupHeader = new Font(
            defaultFont, Font.PLAIN, 48);
    private final Font fntDefault = new Font(
            defaultFont, Font.PLAIN, 12
    );
    
    // Init. colors.
    private final Color clrAshGrey = new Color(42, 42, 42);    
    private final Color clrMagmaOrange = new Color(255, 127, 39);
    
    // Init. size.
    private final int intMaxWidth = 800;    
    private final int intMaxHeight = 600;
    private final Dimension pnlSize = new Dimension(
            intMaxWidth, 
            intMaxHeight
    );

    public HomePanel() { 
        // Add titles.
        lblHeader = new JLabel("Home");
        lblSubheader = new JLabel("Hello! Please select your type of transaction below.");
        lblHeader.setAlignmentX(CENTER_ALIGNMENT);
        lblSubheader.setAlignmentX(CENTER_ALIGNMENT);

        // Set colors.
        lblHeader.setForeground(clrMagmaOrange);
        lblSubheader.setForeground(clrMagmaOrange);

        // Set fonts.
        lblHeader.setFont(fntSupHeader);
        lblSubheader.setFont(fntSubHeader);

        // Set layouts.
        pnlSelections = new JPanel();
        pnlSelections.setBackground(clrAshGrey);
        pnlSelections.setMaximumSize(new Dimension(600, 400));
        GridLayout grid = new GridLayout(2,2);
        grid.setHgap(25);
        grid.setVgap(25);
        pnlSelections.setLayout(grid);

        // Init. buttons.
        btnCustomer = new JButton("Customer", new ImageIcon("customer_logo.png"));
        btnCar = new JButton("Car", new ImageIcon("car_logo.png"));
        btnOwner = new JButton("Owner", new ImageIcon("owner_logo.png"));
        btnBooking = new JButton("Booking", new ImageIcon("booking_logo.png"));

        btnCustomer.setPreferredSize(new Dimension(200, 200));

        // Set font and color of buttons.
        btnCustomer.setFont(fntDefault);
        btnCar.setFont(fntDefault);
        btnOwner.setFont(fntDefault);
        btnBooking.setFont(fntDefault);
        btnCustomer.setBackground(clrMagmaOrange);
        btnCar.setBackground(clrMagmaOrange);
        btnOwner.setBackground(clrMagmaOrange);
        btnBooking.setBackground(clrMagmaOrange);

        // Add action listener to buttons.
        btnCustomer.addActionListener(this);
        btnCar.addActionListener(this);
        btnOwner.addActionListener(this);
        btnBooking.addActionListener(this);

        // Add widgets to frame selection.
        pnlSelections.add(btnCustomer);
        pnlSelections.add(btnCar);
        pnlSelections.add(btnOwner);
        pnlSelections.add(btnBooking);

        

        // Add widgets overall.
        this.add(Box.createVerticalGlue());
        this.add(lblHeader);
        this.add(lblSubheader);
        this.add(Box.createVerticalGlue());
        this.add(pnlSelections);
        this.add(Box.createVerticalGlue());
        this.add(Box.createVerticalGlue());

        
        // Set panel properties.
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(clrAshGrey);
        this.setPreferredSize(pnlSize); 
        this.setVisible(true);  
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnCar){
            MainMenu.switchPanes("CAR");
        }
        else if(e.getSource() == btnCustomer){
            MainMenu.switchPanes("CUSTOMER");
        }
        else if(e.getSource() == btnBooking){
            MainMenu.switchPanes("BOOKING");
        }
        else if(e.getSource() == btnOwner){
            MainMenu.switchPanes("OWNER");
        }
    }
}
