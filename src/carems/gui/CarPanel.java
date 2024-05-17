package carems.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;


import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import javax.swing.JPanel;


public class CarPanel extends JPanel implements ActionListener {
    private final JButton btnAdd, btnEdit, btnRemove, btnInvoice;
    private final JLabel lblFlow, lblHeader;
    private final JTextField txfSearch = new JTextField(16);
    private final JLabel lblSearch;
    private final JPanel pnlSearchBar = new JPanel();    
    private final JPanel pnlControlBar = new JPanel();
    private final JTable tblContent;
    
    // Sample data for demo. Replace by using database's.
    private final String[] sampleHeader = {
        "Model", "Color", "License Plate", "Category", "Fuel Type", "Is Available", "Condition"};
    private final String[][] sampleData = {
        {"Honda Civic", "Orange", "8QRA64", "Sedan", "Unleaded", "Yes", "Good"},
        {"Ford F-250", "Black", "NBC 1234", "Pickup", "Diesel", "Yes", "Good"},
        {"Volvo 240", "White", "TOM 369", "Wagon", "Unleaded", "Yes", "OK"},
        {"DMC DeLorean", "White", "OUTATIME", "Sports", "Unleaded", "No", "Bad"}
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

    public CarPanel() { 
        setPreferredSize(pnlSize); 
        setLayout(null);
        
        lblFlow = new JLabel("Home > Cars");
        lblHeader = new JLabel("Cars");       
        btnAdd = new JButton("Add Car");        
        btnEdit = new JButton("Edit Car");        
        btnRemove = new JButton("Delete Car");
        btnInvoice = new JButton("Generate Invoice");
        
        // Group table elements.
        tblContent = new JTable(sampleData, sampleHeader);
        JScrollPane spTable = new JScrollPane(tblContent);
        
        // Group search bar elements.
        lblSearch = new JLabel("Search by Car Name:");
        pnlSearchBar.add(lblSearch);        
        pnlSearchBar.add(txfSearch);    
        
        // Group control/action elements.
        pnlControlBar.add(btnAdd);
        pnlControlBar.add(btnEdit);
        pnlControlBar.add(btnRemove);
        pnlControlBar.add(btnInvoice);
        
        // Color elements (background).
        this.setBackground(clrAshGrey);
        btnAdd.setBackground(clrAshGrey);        
        btnEdit.setBackground(clrAshGrey);        
        btnRemove.setBackground(clrAshGrey);
        btnInvoice.setBackground(clrAshGrey);
        
        // Color elements (foreground).
        lblHeader.setForeground(clrMagmaOrange);
        lblFlow.setForeground(clrMagmaOrange);
        btnAdd.setForeground(clrMagmaOrange);        
        btnEdit.setForeground(clrMagmaOrange);        
        btnRemove.setForeground(clrMagmaOrange);
        btnInvoice.setForeground(clrMagmaOrange);
       
        // Set fonts per element.
        lblSearch.setFont(fntDefault);
        txfSearch.setFont(fntDefault);
        lblFlow.setFont(fntSubHeader);        
        lblHeader.setFont(fntSupHeader);
        btnAdd.setFont(fntDefault);
        btnEdit.setFont(fntDefault);
        btnRemove.setFont(fntDefault);
        btnInvoice.setFont(fntDefault);
        tblContent.setFont(fntDefault);
        spTable.setFont(fntDefault);
        
        // Bound elements.
        lblFlow.setBounds(0, 0, intMaxWidth, 25);
        lblHeader.setBounds(0, 25, intMaxWidth, 50);
        pnlSearchBar.setBounds(0, 75, intMaxWidth, 25);
        pnlControlBar.setBounds(0, 100, intMaxWidth, 50);
        spTable.setBounds(0, 150, intMaxWidth, 450);

        // Link buttons.
        btnInvoice.addActionListener(CarPanel.this);

        // Remove focus.
        btnAdd.setFocusable(false);
        btnEdit.setFocusable(false);
        btnInvoice.setFocusable(false);
        btnRemove.setFocusable(false);

        // Add elements.
        this.add(lblFlow);
        this.add(lblHeader);
        add(pnlControlBar);
        add(pnlSearchBar);
        add(spTable);

        setVisible(true);  
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnInvoice) {
            new InvoiceFrame();
        }
    }
}
