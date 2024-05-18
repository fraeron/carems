package carems.gui;

import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;


import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JPanel;


public class CustomerPanel extends JPanel{
    private final JButton btnAdd, btnEdit, btnRemove;
    private final JLabel lblFlow, lblHeader;
    private final JTextField txfSearch = new JTextField(16);
    private final JLabel lblSearch;
    private final JPanel pnlSearchBar = new JPanel();    
    private final JPanel pnlControlBar = new JPanel();
    private final JTable tblContent;
    
    // Sample data for demo. Replace by using database's.
    private final String[] sampleHeader = {
        "ID", "Name", "Rented Car ID"};
    private final String[][] sampleData = {
        {"1", "Raven D. Mcmurray", "1"},
        {"2", "Kendrick L. Duckworth", "2"},
        {"3", "Aubrey Graham", "3"},
        {"4", "Fukuma S. Mizushi", "4"}
    };

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

    public CustomerPanel() { 
        setPreferredSize(pnlSize); 
        setLayout(null);
        
        lblFlow = new JLabel("Home > Customer");
        lblHeader = new JLabel("Customer");       
        btnAdd = new JButton("Add Customer");        
        btnEdit = new JButton("Edit Customer");        
        btnRemove = new JButton("Delete Customer");
        
        // Group table elements.
        tblContent = new JTable(sampleData, sampleHeader);
        JScrollPane spTable = new JScrollPane(tblContent);
        
        // Group search bar elements.
        lblSearch = new JLabel("Search by Customer Name:");
        pnlSearchBar.add(lblSearch);        
        pnlSearchBar.add(txfSearch);    
        
        // Group control/action elements.
        pnlControlBar.add(btnAdd);
        pnlControlBar.add(btnEdit);
        pnlControlBar.add(btnRemove);
        
        // Color elements (background).
        this.setBackground(clrAshGrey);
        btnAdd.setBackground(clrAshGrey);        
        btnEdit.setBackground(clrAshGrey);        
        btnRemove.setBackground(clrAshGrey);
        
        // Color elements (foreground).
        lblHeader.setForeground(clrMagmaOrange);
        lblFlow.setForeground(clrMagmaOrange);
        btnAdd.setForeground(clrMagmaOrange);        
        btnEdit.setForeground(clrMagmaOrange);        
        btnRemove.setForeground(clrMagmaOrange);
       
        // Set fonts per element.
        lblSearch.setFont(fntDefault);
        txfSearch.setFont(fntDefault);
        lblFlow.setFont(fntSubHeader);        
        lblHeader.setFont(fntSupHeader);
        btnAdd.setFont(fntDefault);
        btnEdit.setFont(fntDefault);
        btnRemove.setFont(fntDefault);
        tblContent.setFont(fntDefault);
        spTable.setFont(fntDefault);

        // Add padding to table.
        Border border = spTable.getBorder();
        Border margin = new EmptyBorder(10,10,10,10);
        spTable.setBorder(new CompoundBorder(margin, border));
        
        // Bound elements.
        lblFlow.setBounds(10, 0, intMaxWidth, 25);
        lblHeader.setBounds(10, 25, intMaxWidth, 60);
        pnlSearchBar.setBounds(0, 90, intMaxWidth, 25);
        pnlControlBar.setBounds(0, 115, intMaxWidth, 50);
        spTable.setBounds(0, 165, intMaxWidth - 15, 400);
        // Add elements.
        add(lblFlow);
        add(lblHeader);
        add(pnlControlBar);
        add(pnlSearchBar);
        add(spTable);

        setVisible(true);  
    }
}