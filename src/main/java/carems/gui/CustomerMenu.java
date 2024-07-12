package carems.gui;

import carems.backend.DataService;
import carems.models.Customer;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class CustomerMenu extends JDialog implements ActionListener {
    static JPanel pnl = new JPanel(null);

    // Init. colors.
    private final Color clrAshGrey = new Color(42, 42, 42);    
    private final Color clrMagmaOrange = new Color(255, 127, 39);
    
    // Init. fonts.
    private final String defaultFont = "Arial";
    private final Font fntSubHeader = new Font(
            defaultFont, Font.PLAIN, 16);
    private final Font fntSupHeader = new Font(
            defaultFont, Font.PLAIN, 48);
    
    // Init. components.
    static JButton btnRegister, btnCancel;
    JLabel lblHeader, lblSubheader;
    static JLabel lblId = createPanelQA("Customer ID:", 150);
    static JTextField fldName = createPanelQAF("Customer Full Name:", 200);
    static JTextField fldDrivers = createPanelQAF("Driver's License Number:", 250);
    static JTextField fldCredit = createPanelQAF("Credit Card Number:", 300);
    
    Customer currentData = new Customer();
    
    int intFldHeight = 25;
    
    private JLabel makeLabel(JLabel label, String text) {
        label = new JLabel(text);
        label.setForeground(Color.WHITE);
        pnl.add(label);
        return label;
    }
    
    private JButton makeButton(JButton btn, String text) {
        btn = new JButton(text);
        pnl.add(btn);
        btn.addActionListener(this);
        return btn;
    }
      
    CustomerMenu() {
        this.setLayout(null);
        pnl.setBackground(null);
        pnl.setBounds(0,0,800, 600);
        this.add(pnl);
        
        lblHeader = makeLabel(lblHeader, "Add Customer");
        lblSubheader = makeLabel(lblSubheader, "To save, please press REGISTER.");
        lblHeader.setFont(fntSupHeader);
        lblSubheader.setFont(fntSubHeader);
        
        btnRegister = makeButton(btnRegister, "REGISTER");
        btnCancel = makeButton(btnCancel, "CANCEL");
        
        lblHeader.setBounds(50,0, 500, 100);
        lblSubheader.setBounds(50, 100, 500, intFldHeight);
        
        btnRegister.setBounds(175, 400, 150, 50);        
        btnCancel.setBounds(400, 400, 150, 50);
        
        this.getContentPane().setBackground(clrAshGrey);
        this.setSize(800, 600);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setModal(true);
        this.setLocationRelativeTo(null);
        this.setVisible(false);
    }
    
    public void setToAdd() {
        lblId.setText(DataService.getAnId(3));
        fldName.setText("");
        fldCredit.setText("");
        fldDrivers.setText("");
        btnRegister.setText("REGISTER");
        lblSubheader.setText("To save, please press REGISTER.");
    }
    
    public void setToEdit(Customer customer) {
        currentData = customer;
        lblId.setText(customer.id);
        fldName.setText(customer.name);
        fldCredit.setText(customer.credit_card_no);
        fldDrivers.setText(customer.drivers_license_id);
        btnRegister.setText("UPDATE");
        lblSubheader.setText("To update, please press UPDATE.");
    }
    
    private void getData(){
        currentData.id = lblId.getText();
        currentData.name = fldName.getText();
        currentData.credit_card_no = fldCredit.getText();
        currentData.drivers_license_id = fldDrivers.getText();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        DataService.refreshData();
        if (e.getSource() == btnRegister) {
            getData();
            if (btnRegister.getText().equals("UPDATE")){
                if (DataService.updateRecordCar(
                        Utils.toArrayString(currentData), 
                        Utils.toArrayStringKeys(currentData), 
                        "tbl_customer")) {
                    JOptionPane.showMessageDialog(
                            null, 
                            "Customer had been successfully updated.",
                            "Customer Update Success", 
                            JOptionPane.INFORMATION_MESSAGE);
                    this.setVisible(false);
                    }
                else {
                    JOptionPane.showMessageDialog(
                        null, 
                        "Error in customer update. "
                        +"Please make sure all inputs are valid and try again.",
                        "Customer Update Failed", 
                        JOptionPane.WARNING_MESSAGE);
                    }
            } 
            else {
                if (DataService.addRecord(Utils.toArrayString(currentData), "tbl_customer")) {
                JOptionPane.showMessageDialog(
                        null, 
                        "Customer had been successfuly registered.",
                        "Customer Registration Success", 
                        JOptionPane.INFORMATION_MESSAGE);
                    this.setVisible(false);;
                }
                else {
                        JOptionPane.showMessageDialog(
                            null, 
                            "Error in customer registration. "
                            +"Please make sure all inputs are valid and try again.",
                            "Car Registration Failed", 
                            JOptionPane.WARNING_MESSAGE);
                    }
                
            }
            CustomerPanel.refreshTable();
        }
        else if (e.getSource() == btnCancel) {
            this.dispose();
        }
    }
    
    private static JLabel createPanelQA( String title, int y) {
        JLabel l = new JLabel(title);
        l.setFont(Utils.getFont(12));
        l.setForeground(Color.WHITE);
        l.setBounds(50, y, 150, 20);
        pnl.add(l);
        
        // Return a label for the answer part.
        JLabel a = new JLabel("N/A");
        a.setHorizontalAlignment(JLabel.LEFT);
        a.setForeground(Color.WHITE);
        a.setBounds(250, y, 450, 20);
        pnl.add(a);
        return a;
    }
    
    private static JTextField createPanelQAF(String title, int y) {
        JLabel l = new JLabel(title);
        l.setFont(Utils.getFont(12));
        l.setForeground(Color.WHITE);
        l.setBounds(50, y, 150, 20);
        pnl.add(l);
        
        // Return a field for the answer part.
        JTextField a = new JTextField("N/A");
        a.setHorizontalAlignment(JLabel.LEFT);
        a.setBounds(250, y, 450, 20);
        pnl.add(a);
        return a;
    }
}
