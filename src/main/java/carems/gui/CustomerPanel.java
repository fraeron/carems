package carems.gui;

import carems.backend.DataService;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JPanel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


public class CustomerPanel extends JPanel implements 
        ActionListener, 
        MouseListener {
    private final JButton btnAdd, btnEdit, btnRemove, btnInvoice;
    private final JLabel lblFlow, lblHeader;
    private final JTextField txfSearch = new JTextField(16);
    private final JLabel lblSearch;
    private final JPanel pnlSearchBar = new JPanel();    
    private final JPanel pnlControlBar = new JPanel();
    private final JTable tblContent;

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
    
    // Set value for JTable selections
    private int currentlySelectedRow;
    DataService service = new DataService();
    DefaultTableModel model;

    // Get database data.
    private final String[] headers = {"ID", "Name", "Rented Car ID"};
    private final String[][] data = service.getCustomers();

    
    public CustomerPanel() { 
        setPreferredSize(pnlSize); 
        setLayout(null);
        
        lblFlow = new JLabel("Home > Customer");
        lblHeader = new JLabel("Customer");       
        btnAdd = new JButton("Add Customer");        
        btnEdit = new JButton("Edit Customer");        
        btnRemove = new JButton("Delete Customer");
        btnInvoice = new JButton("Generate Invoice");
        
        // Group table elements.
        model = new DefaultTableModel(data, headers) {
            @Override
            public boolean isCellEditable(int row, int column) {
               return false;
            }
        };
        tblContent = new JTable(model);
        JScrollPane spTable = new JScrollPane(tblContent);
        
        // Group search bar elements.
        lblSearch = new JLabel("Search by Customer Name:");
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
        
        // Color elements (foreground).
        lblHeader.setForeground(clrMagmaOrange);
        lblFlow.setForeground(clrMagmaOrange);
        btnAdd.setForeground(clrMagmaOrange);        
        btnEdit.setForeground(clrMagmaOrange);        
        btnRemove.setForeground(clrMagmaOrange);
        btnInvoice.setBackground(clrAshGrey);
        btnInvoice.setForeground(clrMagmaOrange);
       
        // Set fonts per element.
        lblSearch.setFont(fntDefault);
        txfSearch.setFont(fntDefault);
        lblFlow.setFont(fntSubHeader);        
        lblHeader.setFont(fntSupHeader);
        btnInvoice.setFont(fntDefault);
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
        spTable.setBounds(0, 165, intMaxWidth - 10, 400);
        
        // Add elements.
        add(lblFlow);
        add(lblHeader);
        add(pnlControlBar);
        add(pnlSearchBar);
        add(spTable);
        
        // Add actions.
        btnAdd.addActionListener(this);
        btnEdit.addActionListener(this);
        btnRemove.addActionListener(this);
        btnInvoice.setFocusable(false);
        btnInvoice.addActionListener(this);
        tblContent.addMouseListener(this);
        initSearchBar();
        refreshTable();
        
        setVisible(true);  
    }

    private void setBtnStatus(boolean active) {
        if (active) {
            btnEdit.setEnabled(true);
            btnRemove.setEnabled(true);
            btnInvoice.setEnabled(true);
        }
        else {
            btnEdit.setEnabled(false);
            btnRemove.setEnabled(false);
            btnInvoice.setEnabled(false);
        }
    }

    private void refreshTable(){
        model.setRowCount(0);
        String[][] data = service.getOwners();
        for(String[] datum : data){
            model.addRow(datum);
        }
        setBtnStatus(false);
    }
    
    private String[] getUserData() {
        String[] userData = {
            tblContent.getValueAt(currentlySelectedRow, 0).toString(),
            tblContent.getValueAt(currentlySelectedRow, 1).toString(),            
            tblContent.getValueAt(currentlySelectedRow, 2).toString(),
        };
        return userData;
    }
    
    // Search bar functionality.
    private void initSearchBar() {
        String placeholderText = "Enter keywords or names to filter";
        TableRowSorter<TableModel> rowSorter
           = new TableRowSorter<>(tblContent.getModel());
        tblContent.setRowSorter(rowSorter);
        txfSearch.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = txfSearch.getText();
                if (!text.equals(placeholderText)){
                    if (text.trim().length() == 0) {
                        rowSorter.setRowFilter(null);
                    } else {
                        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                    }
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = txfSearch.getText();
                    if (!text.equals(placeholderText)){
                    if (text.trim().length() == 0) {
                        rowSorter.setRowFilter(null);
                    } else {
                        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                    }
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd){
            CustomerMenu.setToAdd();
            MainMenu.switchPanes("CUSMENU");
        }
        else if (e.getSource() == btnEdit) {
            String[] userData = getUserData();
            CustomerMenu.setToEdit(userData);
            MainMenu.switchPanes("CUSMENU");
        }
        else if (e.getSource() == btnRemove) {
            int yesnoFX = JOptionPane.YES_NO_OPTION;
            if (JOptionPane.showConfirmDialog(
                null, 
            "Warning, this action is irreversible! Do you really wish to delete this record?",
            "DELETING A RECORD",
            yesnoFX
            ) == JOptionPane.YES_OPTION) {
                String selectedID = tblContent.getValueAt(
                        currentlySelectedRow, 0).toString();
                service.deleteCustomer(selectedID);
                JOptionPane.showMessageDialog(null, 
                            "Customer had been successfuly deleted.",
                            "Customer deletion Success", 
                            JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else if (e.getSource() == btnInvoice) {
            String carname = tblContent.getValueAt(
                currentlySelectedRow, 2).toString();
            String name = tblContent.getValueAt(
                currentlySelectedRow, 1).toString();
            new InvoiceFrame(name, carname);
        }
    }

    // Set the buttons active if a table cell is clicked/selected.
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == tblContent ){
            currentlySelectedRow = tblContent.rowAtPoint(e.getPoint());
            setBtnStatus(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}