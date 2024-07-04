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

public class CarMenu extends JPanel implements ActionListener {

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
    JLabel lblModel, lblColor, lblLicPlate, lblCategory, 
            lblFuelType, lblAvailable, lblCondition;
    static JTextField fldModel, fldColor, fldLicPlate, fldCategory, 
            fldFuelType, fldAvailable, fldCondition;
    
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
      
    CarMenu() {
        this.setLayout(null);
        this.setBackground(clrAshGrey);
        
        lblHeader = makeLabel(lblHeader, "");
        lblSubheader = makeLabel(lblSubheader, "To save, please press REGISTER.");
        lblHeader.setFont(fntSupHeader);
        lblSubheader.setFont(fntSubHeader);
        
        lblModel = makeLabel(lblModel, "Model:");
        lblColor = makeLabel(lblColor, "Color:");
        lblLicPlate = makeLabel(lblLicPlate, "License Plate:");
        lblCategory = makeLabel(lblCategory, "Category:");
        lblFuelType = makeLabel(lblFuelType, "Fuel Type:");
        lblAvailable = makeLabel(lblAvailable, "Availability:");
        lblCondition = makeLabel(lblCondition, "Condition:");


        fldModel = makeField (fldModel);
        fldColor = makeField (fldColor);
        fldLicPlate = makeField (fldLicPlate);
        fldCategory = makeField (fldCategory);
        fldFuelType = makeField (fldFuelType);
        fldAvailable = makeField (fldAvailable);
        fldCondition = makeField (fldCondition);
        
        btnRegister = makeButton(btnRegister, "REGISTER");
        btnCancel = makeButton(btnCancel, "CANCEL");
        
        lblHeader.setBounds(50,0, 500, 100);
        lblSubheader.setBounds(50, 100, 500, intFldHeight);
        lblModel.setBounds(50,150,100,intFldHeight);
        lblColor.setBounds(50,200,300,intFldHeight);
        lblLicPlate.setBounds(50,250,300,intFldHeight);
        lblCategory.setBounds(50,300,300,intFldHeight);
        lblFuelType.setBounds(50,350,300,intFldHeight);
        lblAvailable.setBounds(50,400,300,intFldHeight);
        lblCondition.setBounds(50,450,300,intFldHeight);
        
        fldModel.setBounds(200,150,300,intFldHeight);
        fldColor.setBounds(200,200,300,intFldHeight);
        fldLicPlate.setBounds(200,250,300,intFldHeight);
        fldCategory.setBounds(200,300,300,intFldHeight);
        fldFuelType.setBounds(200,350,300,intFldHeight);
        fldAvailable.setBounds(200,400,300,intFldHeight);
        fldCondition.setBounds(200,450,300,intFldHeight);
        
        btnRegister.setBounds(175, 500, 150, 50);        
        btnCancel.setBounds(400, 500, 150, 50);
    }
    
    public static void setToAdd() {
        for (JTextField fld : fldArray) {
            fld.setText("");
        }
        btnRegister.setText("REGISTER");
        lblHeader.setText("Add a Car Record");
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
        lblHeader.setText("Update Car Record");
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
            if (service.addRecord(getFldData(), "tbl_car")) {
                JOptionPane.showMessageDialog(
                        null, 
                        "Owner had been successfuly registered.",
                        "Owner Registration Success", 
                        JOptionPane.INFORMATION_MESSAGE);
                }
            else {
                    JOptionPane.showMessageDialog(
                        null, 
                        "Error in customer registration. "
                        +"Please make sure all inputs are valid and try again.",
                        "Owner Registration Failed", 
                        JOptionPane.WARNING_MESSAGE);
                }
        }
        else if (e.getSource() == btnCancel) {
            MainMenu.switchPanes("CAR");
        }
    }
}