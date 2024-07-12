package carems.gui;

import carems.backend.DataService;
import carems.models.Car;
import carems.models.Customer;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import static javax.swing.WindowConstants.HIDE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;


public class AvailableCus extends JDialog implements ActionListener,
        MouseListener {

    // Init. colors.
    private final Color clrAshGrey = new Color(42, 42, 42);    
    private final Color clrMagmaOrange = new Color(255, 127, 39);
    
    JTable table;
    
    // Init. fonts.
    private final String defaultFont = "Arial";
    private final Font fntSubHeader = new Font(
            defaultFont, Font.PLAIN, 16);
    private final Font fntSupHeader = new Font(
            defaultFont, Font.PLAIN, 48);
    static DefaultTableModel model;
    static JButton btnSel;
    static JButton btnAdd;
    
    int currentlySelectedRow;

    
    private JButton makeButton(String text) {
        JButton btn = new JButton(text);
        this.add(btn);
        btn.addActionListener(this);
        return btn;
    }
    
    
    private static String[][] getCustomerAvailable(){
        ArrayList<Customer> availables = DataService.customers;
        return Utils.unpackCustomer(availables);
    }
    
    private final String[] headers = {
        "ID", "Model", "Color", "License Plate", "Category", "Fuel Type", 
        "Is Available", "Condition", "VIN", "Price Per Day"};
    
    private final String[] cusHeaders = {
        "ID", "Name", "Driver's License No.", "Credit Card No."
    };

      
    public AvailableCus() {
        this.setLayout(null);
        
        btnSel = makeButton("Select");
        btnAdd = makeButton("Add");
        
        btnSel.setBounds(0, 500, 400, 50);        
        btnAdd.setBounds(400, 500, 400, 50);
        btnSel.setEnabled(false);
        
        // Group table elements.
        DataService.refreshData();
        model = new DefaultTableModel(getCustomerAvailable(), cusHeaders) {
            @Override
            public boolean isCellEditable(int row, int column) {
               return false;
            }
         };
           
        
        table = new JTable(model);
        table.addMouseListener(this);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(0, 0, 785, 500);
        this.add(sp);
        
        this.getContentPane().setBackground(clrAshGrey);
        this.setSize(800, 600);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setModal(true);
        this.setLocationRelativeTo(null);
        this.setVisible(false);
    }
    
    public Customer pickCustomer(){
        Customer currentData = null;
        for (Customer cus : DataService.customers) {
            if (cus.id.equals(table.getValueAt(currentlySelectedRow, 
                    0).toString())) {
                currentData = cus;
            }
        }
        return currentData;
    }
    
    
    public static void refresh(){
        DataService.refreshData();
        model.setRowCount(0);
        for(String[] datum : getCustomerAvailable()){
            model.addRow(datum);
        }
    }
    
    public Car pickCar() {
        Car currentData = null;
        for (Car car : DataService.cars) {
            if (car.id.equals(table.getValueAt(currentlySelectedRow, 
                    0).toString())) {
                currentData = car;
            }
        }
        return currentData;
    }
    
    private static void setBtnStatus(boolean active) {
        if (active) {
            btnSel.setEnabled(true);
        }
        else {
            btnSel.setEnabled(false);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSel) {
            this.dispose();
        } if (e.getSource() == btnAdd) {
            CustomerPanel.menu.setToAdd();
            CustomerPanel.menu.setVisible(true);
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == table){
            currentlySelectedRow = table.rowAtPoint(e.getPoint());
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
