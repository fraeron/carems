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
    JLabel lblId;
    static JTextField fldId, fldName, driversLicense, creditCard;
    
    int intFldHeight = 25;
    
    private JLabel makeLabel(JLabel label, String text) {
        label = new JLabel(text);
        label.setForeground(Color.WHITE);
        this.add(label);
        return label;
    }
    
    private JTextField makeField(JTextField fld) {
        fld = new JTextField();
        this.add(fld);
        return fld;
    }
    
    private JButton makeButton(JButton btn, String text) {
        btn = new JButton(text);
        this.add(btn);
        btn.addActionListener(this);
        return btn;
    }
      
    CustomerMenu() {
        this.setLayout(null);

        lblHeader = makeLabel(lblHeader, "Add Customer");
        lblSubheader = makeLabel(lblSubheader, "To save, please press REGISTER.");
        lblHeader.setFont(fntSupHeader);
        lblSubheader.setFont(fntSubHeader);
        
        lblId = createPanelQA("Customer ID:", 150);
        fldName = createPanelQAF("Customer Full Name:", 200);
        driversLicense = createPanelQAF("Driver's License Number:", 250);
        creditCard = createPanelQAF("Credit Card Number:", 300);
        
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
        this.setVisible(true);
    }
    
    public static void setToAdd() {
        fldId.setText("");
        fldName.setText("");
        btnRegister.setText("REGISTER");
    }
    
    public static void setToEdit(String[] userData) {
        fldId.setText(userData[0]);
        fldName.setText(userData[1]);
        btnRegister.setText("UPDATE");
    }
    
    private Customer getData(){
        Customer customer = new Customer();
        customer.id = fldId.getText();
        customer.name = fldName.getText();
        return customer;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        DataService service = new DataService();
        if (e.getSource() == btnRegister) {
            if (service.addCustomer(getData())) {
                JOptionPane.showMessageDialog(
                        null, 
                        "Customer had been successfuly registered.",
                        "Customer Registration Success", 
                        JOptionPane.INFORMATION_MESSAGE);
                }
            else {
                    JOptionPane.showMessageDialog(
                        null, 
                        "Error in customer registration. "
                        +"Please make sure all inputs are valid and try again.",
                        "Customer Registration Failed", 
                        JOptionPane.WARNING_MESSAGE);
                }
        }
        else if (e.getSource() == btnCancel) {
            this.dispose();
        }
    }
    
    private JLabel createPanelQA( String title, int y) {
        JLabel l = new JLabel(title);
        l.setFont(Utils.getFont(12));
        l.setForeground(Color.WHITE);
        l.setBounds(50, y, 150, 20);
        this.add(l);
        
        // Return a label for the answer part.
        JLabel a = new JLabel("N/A");
        a.setHorizontalAlignment(JLabel.CENTER);
        a.setForeground(Color.WHITE);
        a.setBounds(250, y, 450, 20);
        this.add(a);
        return a;
    }
    
    private JTextField createPanelQAF(String title, int y) {
        JLabel l = new JLabel(title);
        l.setFont(Utils.getFont(12));
        l.setForeground(Color.WHITE);
        l.setBounds(50, y, 150, 20);
        this.add(l);
        
        // Return a field for the answer part.
        JTextField a = new JTextField("N/A");
        a.setHorizontalAlignment(JLabel.CENTER);
        a.setBounds(250, y, 450, 20);
        this.add(a);
        return a;
    }
}
