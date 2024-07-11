package carems.gui;

import carems.backend.DataService;

import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;


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

import carems.models.Car;


public class CarPanel extends JPanel implements ActionListener, MouseListener {
    private static JButton btnAdd = new JButton("Add Car"); 
    private static JButton btnEdit = new JButton("Edit Car");
    private static JButton btnRemove = new JButton("Delete Car");
    private final JLabel lblFlow, lblHeader;
    private final JTextField txfSearch = new JTextField(16);
    private final JLabel lblSearch;
    private final JPanel pnlSearchBar = new JPanel();    
    private final JPanel pnlControlBar = new JPanel();
    private final JTable tblContent;
    
    // Init. tables functions.
    private int currentlySelectedRow;
    static DefaultTableModel model;
    
    private CarMenu menu = new CarMenu();
    
    // Sample data for demo. Replace by using database's.
    private final String[] headers = {
        "ID", "Model", "Color", "License Plate", "Category", "Fuel Type", 
        "Is Available", "Condition", "Price Per Day"};
    private final String[][] data = Utils.unpackCar(DataService.cars);

    // Init. fonts.
    private final String defaultFont = "Arial";
    private final Font fntSubHeader = new Font(
            defaultFont, Font.PLAIN, 16);
    private final Font fntSupHeader = new Font(
            defaultFont, Font.PLAIN, 48);
    private final Font fntDefault = new Font(
            defaultFont, Font.PLAIN, 12
    );
    
    private Car currentData;
    
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

    CarPanel() { 
        setPreferredSize(pnlSize); 
        setLayout(null);
        
        lblFlow = new JLabel("Home > Cars");
        lblHeader = new JLabel("Cars");       
        
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
        lblSearch = new JLabel("Search by Car Name:");
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
        spTable.setBounds(0, 115, intMaxWidth - 15, 450);
        pnlControlBar.setBounds(0, 565, intMaxWidth, 50);


        // Remove focus.
        btnAdd.setFocusable(false);
        btnEdit.setFocusable(false);
        btnRemove.setFocusable(false);

        // Add elements.
        this.add(lblFlow);
        this.add(lblHeader);
        add(pnlControlBar);
        add(pnlSearchBar);
        add(spTable);

        // Add action.
        btnAdd.addActionListener(this);
        btnEdit.addActionListener(this);
        btnRemove.addActionListener(this);
        tblContent.addMouseListener(this);
        initSearchBar();
        refreshTable();

        setVisible(true);  
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
    
    private Car getUserData() {
        for (Car car : DataService.cars) {
            if (car.id.equals(tblContent.getValueAt(currentlySelectedRow, 
                    0).toString())) {
                currentData = car;
            }
        }
        return currentData;
    }
    
    public static void refreshTable(){
        DataService.refreshData();
        model.setRowCount(0);
        String[][] data = Utils.unpackCar(DataService.cars);
        for(String[] datum : data){
            model.addRow(datum);
        }
        setBtnStatus(false);
    }
    
    private static void setBtnStatus(boolean active) {
        if (active) {
            btnEdit.setEnabled(true);
            btnRemove.setEnabled(true);
        }
        else {
            btnEdit.setEnabled(false);
            btnRemove.setEnabled(false);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd){
            CarMenu.setToAdd();
            menu.setVisible(true);
        }
        else if (e.getSource() == btnEdit) {
            CarMenu.setToEdit(getUserData());
            menu.setVisible(true);
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
                DataService.deleteData(selectedID, "tbl_car");
                JOptionPane.showMessageDialog(null, 
                            "Car record had been successfuly deleted.",
                            "Car Record Deletion Success", 
                            JOptionPane.INFORMATION_MESSAGE);
                refreshTable();
            }
        }
    }

    // Set the buttons active if a table cell is clicked/selected.
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == tblContent){
            currentlySelectedRow = tblContent.rowAtPoint(e.getPoint());
            setBtnStatus(true);
        }
        else {
            setBtnStatus(false);

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
