package carems.gui;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HomePanel extends JPanel implements ActionListener{
    private final JButton btnCustomer, btnCar, btnLocation, btnBooking; 
    private final JLabel lblHeader, lblSubheader, lblDbRelated, lblTransaction;
    private final JButton btnRent, btnReturn;
    private final JPanel pnlDbRelated;

    // Init. fonts.
    private final String defaultFont = "Arial";
    private final Font fntSubHeader = new Font(
            defaultFont, Font.PLAIN, 16);
    private final Font fntSupHeader = new Font(
            defaultFont, Font.PLAIN, 48);
    private final Font fntDefault = new Font(
            defaultFont, Font.PLAIN, 12
    );
    private final Font fntISwearToGodIfThisThingIsStillNotUserFriendlyEnoughIWillCommitTheUnthinkable
            = new Font(defaultFont, Font.PLAIN, 48);
    
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
        lblDbRelated = new JLabel("View, Add, Edit, or Remove Database Records:");
        lblTransaction = new JLabel("Rent or Return a Car:");
        lblSubheader = new JLabel("Hello! Please select your type of transaction below.");
        lblHeader.setAlignmentX(CENTER_ALIGNMENT);
        lblSubheader.setAlignmentX(CENTER_ALIGNMENT);

        // Set colors.
        lblHeader.setForeground(clrMagmaOrange);
        lblSubheader.setForeground(clrMagmaOrange);
        lblDbRelated.setForeground(clrMagmaOrange);
        lblTransaction.setForeground(clrMagmaOrange);

        // Set fonts.
        lblHeader.setFont(fntSupHeader);
        lblSubheader.setFont(fntSubHeader);
        lblDbRelated.setFont(fntDefault);
        lblTransaction.setFont(fntDefault);

        // Set layouts.
        pnlDbRelated = new JPanel();
        pnlDbRelated.setBackground(clrAshGrey);
        pnlDbRelated.setMaximumSize(new Dimension(250, 100));
        GridLayout grid = new GridLayout(4,1);
        grid.setVgap(10);
        pnlDbRelated.setLayout(grid);

        // Init. buttons.
        btnCustomer = new JButton("Customer", new ImageIcon("img/customer_logo.png"));
        btnCar = new JButton("Car", new ImageIcon("img/car_logo.png"));
        btnLocation = new JButton("Locations", new ImageIcon("img/owner_logo.png"));
        btnBooking = new JButton("Booking", new ImageIcon("img/booking_logo.png"));
        btnRent = new JButton("Rent or Book");
        btnReturn = new JButton("Return");

        // Control all sizes of all buttons.
        btnCustomer.setPreferredSize(new Dimension(150, 50));

        // Set font and color of buttons.
        btnRent.setFont(fntISwearToGodIfThisThingIsStillNotUserFriendlyEnoughIWillCommitTheUnthinkable);
        btnReturn.setFont(fntISwearToGodIfThisThingIsStillNotUserFriendlyEnoughIWillCommitTheUnthinkable);
        btnCustomer.setFont(fntDefault);
        btnCar.setFont(fntDefault);
        btnLocation.setFont(fntDefault);
        btnBooking.setFont(fntDefault);
        btnCustomer.setBackground(clrMagmaOrange);
        btnCar.setBackground(clrMagmaOrange);
        btnRent.setBackground(clrMagmaOrange);
        btnReturn.setBackground(clrMagmaOrange);
        btnLocation.setBackground(clrMagmaOrange);
        btnBooking.setBackground(clrMagmaOrange);
        
        btnRent.setForeground(Color.WHITE);
        btnReturn.setForeground(Color.WHITE);

        // Add action listener to buttons.
        btnCustomer.addActionListener(this);
        btnCar.addActionListener(this);
        btnLocation.addActionListener(this);
        btnBooking.addActionListener(this);
        btnRent.addActionListener(this);
        btnReturn.addActionListener(this);

        // Add widgets to frame selection.
        pnlDbRelated.add(btnCustomer);
        pnlDbRelated.add(btnCar);
        pnlDbRelated.add(btnLocation);
        pnlDbRelated.add(btnBooking);
        
        // Remove focus.
        btnCustomer.setFocusable(false);
        btnLocation.setFocusable(false);
        btnCar.setFocusable(false);
        btnBooking.setFocusable(false);
        btnRent.setFocusable(false);
        btnReturn.setFocusable(false);
        
        // Bound.
        lblHeader.setBounds(325, 50, 200, 50);
        lblSubheader.setBounds(225, 100, 500, 25);
        lblTransaction.setBounds(190, 150, 300, 25);
        btnRent.setBounds(50, 175, 400, 150);        
        btnReturn.setBounds(50, 350, 400, 150);
        lblDbRelated.setBounds(475, 150, 300, 25);
        pnlDbRelated.setBounds(500, 175, 200, 325);
        
        // Add widgets overall.
        this.add(lblHeader);
        this.add(lblSubheader);
        this.add(lblDbRelated);        
        this.add(lblTransaction);
        this.add(pnlDbRelated);
        this.add(btnRent);
        this.add(btnReturn);

        // Set panel properties.
        this.setLayout(null);
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
        else if(e.getSource() == btnLocation){
            MainMenu.switchPanes("LOCATION");
        }
        else if (e.getSource() == btnRent){
            MainMenu.switchPanes("RENT");
        }
        else if (e.getSource() == btnReturn){
            MainMenu.switchPanes("RETURN");
        }
    }
}
