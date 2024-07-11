package carems.gui;

import carems.backend.DataService;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CarMenu extends JDialog implements ActionListener {

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
    static JButton btnRegister = makeButton("REGISTER", 100);
    static JButton btnCancel = makeButton("CANCEL", 425);
    
    static JLabel header = new JLabel("Add a Car Record");    
    static JLabel subheader = new JLabel("Please set all the information needed below to register.");
    
    // Init. optimizations.
    static ArrayList<JTextField> fldArray = new ArrayList();
    JPanel pnlInputs = new JPanel(null);
    JLabel lblStatus;
    
    String[] carColors = {
        "Red", "Blue", "Green", "Orange", "Violet", "Yellow", "Black", "White",
        "Gray/Silver"
    };
    
    String[] carCategories = {
        "Micro", "Sedan", "Van", "Sports", "Super", "Big Truck", "Coupe", "Muscle",
        "SUV", "Pickup", "Truck", "Mini Truck", "Mini Van", "Camper Van", "CUV"
    };
    
    String[] fuelCategory = {
        "Gasoline", "Diesel", "Bio-diesel", "Ethanol"
    };
    
    String[] conditions = {
        "GOOD", "OK", "BAD"
    };
    
    String[] avails = {"AVAILABLE", "UNAVAILABLE"};
      
    public CarMenu() {
        // Header.
        header.setForeground(clrMagmaOrange);
        header.setFont(getFont(48));
        header.setBounds(200, 20, 400, 50);
        
        // Subheader.
        subheader.setForeground(clrMagmaOrange);
        subheader.setFont(getFont(12));
        subheader.setBounds(225, 50, 350, 50);
        
        this.add(header);
        this.add(subheader);
        
        JLabel lblid = createPanelQA("Car Record ID:", 15, 30);
        JTextField fldModel = createPanelQAF("Model:", 15, 60);
        JComboBox cbxColor = createPanelQAC(carColors, "Color:", 15, 90);
        JTextField fldLic = createPanelQAF("License Plate:", 15, 120);
        JComboBox cbxCar = createPanelQAC(carCategories, "Category:", 15, 150);
        JComboBox cbxFuel = createPanelQAC(fuelCategory, "Fuel:", 400, 30);
        JComboBox cbxAvail = createPanelQAC(avails, "Availability:", 400, 60);
        JComboBox cbxCondition = createPanelQAC(conditions, "Condition:", 400, 90);
        JTextField fldPrice = createPanelQAF("Price/Day (in PHP):", 400, 120, 180);
        
        pnlInputs.setBackground(null);
        pnlInputs.setBounds(0,100,800,200);
        this.add(pnlInputs);
        
        this.add(btnRegister);        
        this.add(btnCancel);
        
        btnRegister.addActionListener(this);
        btnCancel.addActionListener(this);
        
        lblStatus = createStatus("Updates go here.");
        lblStatus.setBounds(275, 475, 200, 20);
        
        this.setModal(true);
        this.setSize(800, 650);
        this.getContentPane().setBackground(clrAshGrey);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
       
    }
    
    public static void setToAdd() {
        for (JTextField fld : fldArray) {
            fld.setText("");
        }
        btnRegister.setText("REGISTER");
        header.setText("Add a Car Record");
        subheader.setText("Please set all the information needed below to register.");

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
        header.setText("Update Car Record");        
        subheader.setText("Please set all the information needed below to update.");

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
            this.dispose();
        }
    }
    
    private static Font getFont(int size){
        return new Font("Arial", Font.PLAIN, size);
    }
    
    private JLabel createStatus(String title) {
        JLabel l = new JLabel(title);
        l.setFont(getFont(14));
        l.setForeground(clrMagmaOrange);
        l.setHorizontalAlignment(JLabel.CENTER);
        this.add(l);
        return l;
    }
    
    private JComboBox createPanelQAC(String[] contents, String title, int x, int y) {
        JLabel l = new JLabel(title);
        l.setFont(getFont(12));
        l.setForeground(Color.WHITE);
        l.setBounds(x, y + 5, 175, 20);
        pnlInputs.add(l);
        
        // Return a combo for the answer part.
        JComboBox a = new JComboBox(contents);
        a.setBounds(130 + x, y + 5, 200, 20);
        pnlInputs.add(a);
        return a;
    }
    
    private JLabel createPanelQA(String title, int x, int y) {
        JLabel l = new JLabel(title);
        l.setFont(getFont(12));
        l.setForeground(Color.WHITE);
        l.setBounds(x, y + 5, 175, 20);
        pnlInputs.add(l);
        
        // Return a label for the answer part.
        JLabel a = new JLabel("N/A");
        a.setHorizontalAlignment(JLabel.CENTER);
        a.setForeground(Color.WHITE);
        a.setBounds(130 + x, y + 5, 200, 20);
        pnlInputs.add(a);
        return a;
    }
    
    private JTextField createPanelQAF(String title, int x, int y) {
        JLabel l = new JLabel(title);
        l.setFont(getFont(12));
        l.setForeground(Color.WHITE);
        l.setBounds(x, y + 5, 175, 20);
        pnlInputs.add(l);
        
        // Return a field for the answer part.
        JTextField a = new JTextField("N/A");
        a.setHorizontalAlignment(JLabel.CENTER);
        a.setBounds(130 + x, y + 5, 200, 20);
        pnlInputs.add(a);
        return a;
    }
    
    private JTextField createPanelQAF(String title, int x, int y, int customFieldX) {
        JLabel l = new JLabel(title);
        l.setFont(getFont(12));
        l.setForeground(Color.WHITE);
        l.setBounds(x, y + 5, 175, 20);
        pnlInputs.add(l);
        
        // Return a field for the answer part.
        JTextField a = new JTextField("N/A");
        a.setHorizontalAlignment(JLabel.CENTER);
        a.setBounds(customFieldX + x, y + 5, 150, 20);
        pnlInputs.add(a);
        return a;
    }
    
    private static JButton makeButton(String title, int x) {
        JButton btn = new JButton(title);
        btn.setFont(getFont(20));
        btn.setBounds(x, 525, 250, 50);
        return btn;
    }
}