package carems.gui;

import carems.backend.DataService;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CustomerMenu extends JPanel implements ActionListener {

    // Init. colors.
    private final Color clrAshGrey = new Color(42, 42, 42);    
    private final Color clrMagmaOrange = new Color(255, 127, 39);
    
    private final String defaultFont = "Arial";
    private final Font fntSubHeader = new Font(
            defaultFont, Font.PLAIN, 16);
    private final Font fntSupHeader = new Font(
            defaultFont, Font.PLAIN, 48);
    
    static JButton btnRegister, btnCancel;
    JLabel lblHeader, lblSubheader;
    JLabel lblId, lblName, lblRentCar;
    static JTextField fldId, fldName, fldRentCar;
    
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
        this.setBackground(clrAshGrey);
        
        lblHeader = makeLabel(lblHeader, "Add Customer");
        lblSubheader = makeLabel(lblSubheader, "To save, please press REGISTER.");
        lblHeader.setFont(fntSupHeader);
        lblSubheader.setFont(fntSubHeader);
        
        lblId = makeLabel(lblId, "Customer ID:");
        lblName = makeLabel(lblName, "Customer Full Name:");
        lblRentCar = makeLabel(lblRentCar, "Rented car:");
        
        fldId = makeField(fldId);
        fldName = makeField(fldName);
        fldRentCar = makeField(fldRentCar);
        
        btnRegister = makeButton(btnRegister, "REGISTER");
        btnCancel = makeButton(btnCancel, "CANCEL");
        
        lblHeader.setBounds(10,0, 500, 100);
        lblSubheader.setBounds(10, 100, 500, intFldHeight);
        lblId.setBounds(10, 150, 100, intFldHeight);
        lblName.setBounds(10, 200, 300, intFldHeight);
        lblRentCar.setBounds(10, 250, 300, intFldHeight);
        
        fldId.setBounds(200, 150, 500, intFldHeight);        
        fldName.setBounds(200, 200, 500, intFldHeight);
        fldRentCar.setBounds(200, 250, 500, intFldHeight);
        
        btnRegister.setBounds(175, 400, 150, 50);        
        btnCancel.setBounds(400, 400, 150, 50);
    }
    
    public static void setToAdd() {
        fldId.setText("");
        fldName.setText("");
        fldRentCar.setText("");
        btnRegister.setText("REGISTER");
    }
    public static void setToEdit(String[] userData) {
        fldId.setText(userData[0]);
        fldName.setText(userData[1]);
        fldRentCar.setText(userData[2]);
        btnRegister.setText("UPDATE");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        DataService service = new DataService();
        if (e.getSource() == btnRegister) {
            if (service.addCustomer(
                    fldId.getText(), 
                    fldName.getText(), 
                    fldRentCar.getText()
            )) {
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
            MainMenu.switchPanes("CUSTOMER");
        }
    }
    
}