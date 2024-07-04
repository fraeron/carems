package carems.gui;

import carems.backend.DataService;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BookingMenu extends JPanel implements ActionListener {

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
    static JLabel lblHeader, lblSubheader;
    JLabel lblId, lblBookedCarID, lblCustomerID, lblBookedDateTime, 
            lblReturnDT, lblStatus;
    static JTextField fldId, fldBookedCarID, fldCustomerID, 
            fldBookedDateTime, fldReturnDT, fldStatus;
    
    // Init. optimizations.
    static ArrayList<JTextField> fldArray = new ArrayList();
    
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
        fldArray.add(fld);
        return fld;
    }
    
    private JButton makeButton(JButton btn, String text) {
        btn = new JButton(text);
        this.add(btn);
        btn.addActionListener(this);
        return btn;
    }
      
    BookingMenu() {
        this.setLayout(null);
        this.setBackground(clrAshGrey);
        
        lblHeader = makeLabel(lblHeader, "");
        lblSubheader = makeLabel(lblSubheader, "To save, please press REGISTER.");
        lblHeader.setFont(fntSupHeader);
        lblSubheader.setFont(fntSubHeader);
        
        lblId = makeLabel(lblId, "Booking ID:");
        lblBookedCarID = makeLabel(lblBookedCarID, "Booked Card ID:");
        lblCustomerID = makeLabel(lblCustomerID, "Customer ID:");
        lblBookedDateTime = makeLabel(lblBookedDateTime, "Booked Date/Time:");
        lblReturnDT = makeLabel(lblReturnDT, "Return Date/Time:");
        lblStatus = makeLabel(lblStatus, "Status:");

        fldId = makeField(fldId);
        fldBookedCarID = makeField(fldBookedCarID);
        fldCustomerID = makeField(fldCustomerID);
        fldBookedDateTime = makeField(fldBookedDateTime);
        fldReturnDT = makeField(fldReturnDT);
        fldStatus = makeField(fldStatus);
        
        btnRegister = makeButton(btnRegister, "REGISTER");
        btnCancel = makeButton(btnCancel, "CANCEL");
        
        lblHeader.setBounds(50,0, 500, 100);
        lblSubheader.setBounds(50, 100, 500, intFldHeight);
        lblId.setBounds(50, 150, 100, intFldHeight);
        lblBookedCarID.setBounds(50, 200, 300, intFldHeight);
        lblCustomerID.setBounds(50, 250, 300,intFldHeight);
        lblBookedDateTime.setBounds(50, 300, 300,intFldHeight);
        lblReturnDT.setBounds(50, 350, 300,intFldHeight);
        lblStatus.setBounds(50, 400, 300,intFldHeight);
        
        fldId.setBounds(200,150, 100,intFldHeight);
        fldBookedCarID.setBounds(200,200, 300,intFldHeight);
        fldCustomerID.setBounds(200,250, 300,intFldHeight);
        fldBookedDateTime.setBounds(200,300, 300,intFldHeight);
        fldReturnDT.setBounds(200,350, 300,intFldHeight);
        fldStatus.setBounds(200,400, 300,intFldHeight);
        
        btnRegister.setBounds(175, 500, 150, 50);        
        btnCancel.setBounds(400, 500, 150, 50);
    }
    
    public static void setToAdd() {
        for (JTextField fld : fldArray) {
            fld.setText("");
        }
        btnRegister.setText("REGISTER");
        lblHeader.setText("Add Booking");
    }
    public static void setToEdit(String[] userData) {
        int i = 0;
        for (JTextField fld : fldArray) {
            if (i < fldArray.size()) {
                fld.setText(userData[i]);
                i++;
            }
        }
        btnRegister.setText("UPDATE");
        lblHeader.setText("Update Booking");
    }
    
    private ArrayList<String> getFldData(){
        ArrayList<String> fldData = new ArrayList();
        for (JTextField fld : fldArray) {
            fldData.add(fld.getText());
        }
        return fldData;
    }
 
    
    @Override
    public void actionPerformed(ActionEvent e) {
        DataService service = new DataService();
        if (e.getSource() == btnRegister) {
            if (service.addBooking(getFldData())) {
                JOptionPane.showMessageDialog(
                        null, 
                        "Booking had been successfuly added.",
                        "Booking Registration Success", 
                        JOptionPane.INFORMATION_MESSAGE);
                }
            else {
                    JOptionPane.showMessageDialog(
                        null, 
                        "Error in customer registration. "
                        +"Please make sure all inputs are valid and try again.",
                        "Booking Registration Failed", 
                        JOptionPane.WARNING_MESSAGE);
                }
        }
        else if (e.getSource() == btnCancel) {
            MainMenu.switchPanes("BOOKING");
        }
    }
    
}
