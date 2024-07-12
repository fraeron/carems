package carems.gui;

import carems.backend.DataService;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.ImageIcon;

import javax.swing.JFrame; 
import javax.swing.JPanel;


public class MainMenu extends JFrame {
    // Init. history.
    private static final CardLayout lytCard = new CardLayout();
    private static final JPanel MainMenuPanel = new JPanel(lytCard);
    private static SidebarPanel pnlSidebar;
    private static final ArrayList<String> lstPanes = new ArrayList<>();

    // Add logo path.
    ImageIcon logo = new ImageIcon("img/carems_icon.png");
    
    
       
    public MainMenu(){ 
        // Init.
        DataService.refreshData();
    
        // Add history (panels).
        addPane(new HomePanel(), "HOME");
        addPane(new CarPanel(), "CAR");
        addPane(new CustomerPanel(), "CUSTOMER");
        addPane(new LocationPanel(), "LOCATION");
        addPane(new BookingPanel(), "BOOKING");
        addPane(new Rent(), "RENT");
        addPane(new Return(), "RETURN");

        // Init. sidebar.
        pnlSidebar = new SidebarPanel(this);

        // Add sidebar.
        pnlSidebar.setBounds(0, 0, 200, 650);
        this.add(pnlSidebar);
        
        // Add Main Menu Panel.
        MainMenuPanel.setBounds(200, 0, 800, 650);
        this.getContentPane().setBackground(Color.GREEN);
        this.add(MainMenuPanel);
        lytCard.show(MainMenuPanel, "HOME");
        
        // Set Main Menu properties.
        this.setIconImage(logo.getImage());
        this.setTitle("Carems - Car Rental Management System");
        Dimension size = new Dimension(1000, 650);
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
    
    private void addPane(JPanel panel, String panelCode) {
        MainMenuPanel.add(panel, panelCode);
        lstPanes.add(panelCode);
    }

    public static void switchPanes(String panelName) {
        if (lstPanes.contains(panelName.toUpperCase())) {
            pnlSidebar.switchBtnColor(panelName);
            lytCard.show(MainMenuPanel, panelName);
        }
    }
}
