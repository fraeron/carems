package carems.gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.ImageIcon;

import javax.swing.JFrame; 
import javax.swing.JPanel;


public class MainMenu extends JFrame {
    // Init. history.
    private static CardLayout lytCard = new CardLayout();
    private static final JPanel MainMenuPanel = new JPanel(lytCard);
    private static SidebarPanel pnlSidebar;

    // Add logo path.
    ImageIcon logo = new ImageIcon("img/carems_icon.png");
    
    public MainMenu(){ 
        // Add history.
        MainMenuPanel.add(new CarPanel(), "CAR");
        MainMenuPanel.add(new HomePanel(), "HOME");
        MainMenuPanel.add(new CustomerPanel(), "CUSTOMER");
        MainMenuPanel.add(new OwnerPanel(), "OWNER");
        MainMenuPanel.add(new BookingPanel(), "BOOKING");

        // Init. sidebar.
        pnlSidebar = new SidebarPanel(this);

        // Add sidebar.
        pnlSidebar.setBounds(0, 0, 200, 600);
        this.add(pnlSidebar);
        
        // Add Main Menu Panel.
        MainMenuPanel.setBounds(200, 0, 800, 600);
        this.getContentPane().setBackground(Color.GREEN);
        this.add(MainMenuPanel);
        lytCard.show(MainMenuPanel, "HOME");
        
        // Set Main Menu properties.
        this.setIconImage(logo.getImage());
        this.setTitle("Carems - Car Rental Management System");
        Dimension size = new Dimension(1000, 600);
        this.setPreferredSize(size);
        this.setResizable(false);
        this.setLayout(null);
        this.pack();
        this.getContentPane().setBackground(Color.GREEN);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Start with this panel.
        switchPanes("HOME");
    }

    public static void switchPanes(String panelName) {
        ArrayList<String> lstPanes = new ArrayList<>();
        lstPanes.add("HOME"); 
        lstPanes.add("CUSTOMER"); 
        lstPanes.add("BOOKING"); 
        lstPanes.add("CAR"); 
        lstPanes.add("OWNER"); 

        if (lstPanes.contains(panelName)) {
            pnlSidebar.switchBtnColor(panelName);
            lytCard.show(MainMenuPanel, panelName);
        }
    }
}
