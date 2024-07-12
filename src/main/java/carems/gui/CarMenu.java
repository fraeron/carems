package carems.gui;

import carems.backend.DataService;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import carems.models.Car;

public class CarMenu extends JDialog implements ActionListener {
    
    // Init. optimizations.
    static JPanel pnlInputs = new JPanel(null);
    static Car currentData;

    // Init. colors.
    private final Color clrAshGrey = new Color(42, 42, 42);    
    private final Color clrMagmaOrange = new Color(255, 127, 39);
    
    // Init. fonts.
    static String[] carColors = {
        "Red", "Blue", "Green", "Orange", "Violet", "Yellow", "Black", "White",
        "Gray/Silver"
    };
    
    static String[] carCategories = {
        "Micro", "Sedan", "Van", "Sports", "Super", "Big Truck", "Coupe", "Muscle",
        "SUV", "Pickup", "Truck", "Mini Truck", "Mini Van", "Camper Van", "CUV"
    };
    
    static String[] fuelCategory = {
        "Gasoline", "Diesel", "Bio-diesel", "Ethanol", "Unleaded"
    };
    
    static String[] conditions = {
        "GOOD", "OK", "BAD"
    };
    
    static String[] avails = {"Yes", "No"};
    
    // Init. components.
    static JButton btnRegister = makeButton("REGISTER", 100);
    static JButton btnCancel = makeButton("CANCEL", 425);
    static JLabel lblid= createPanelQA("Car Record ID:", 15, 30);
    static JTextField fldModel= createPanelQAF("Brand and Model:", 15, 60);
    static JComboBox cbxColor= createPanelQAC(carColors, "Color:", 15, 90);
    static JTextField fldLic= createPanelQAF("License Plate:", 15, 120);
    static JComboBox cbxCar= createPanelQAC(carCategories, "Category:", 15, 150);
    static JComboBox cbxFuel= createPanelQAC(fuelCategory, "Fuel:", 400, 30);
    static JComboBox cbxAvail= createPanelQAC(avails, "Is Available:", 400, 60);
    static JComboBox cbxCondition= createPanelQAC(conditions, "Condition:", 400, 90);
    static JTextField fldPrice= createPanelQAF("Price/Day (in PHP):", 400, 120);
    static JLabel header = new JLabel("Add a Car Record");    
    static JLabel subheader = new JLabel("Please set all the information needed below to register.");
    
      
    public CarMenu() {
        // Header.
        header.setForeground(clrMagmaOrange);
        header.setFont(getFont(48));
        header.setHorizontalAlignment(JLabel.CENTER);
        header.setBounds(190, 20, 425, 50);
        
        // Subheader.
        subheader.setForeground(clrMagmaOrange);
        subheader.setHorizontalAlignment(JLabel.CENTER);
        subheader.setFont(getFont(12));
        subheader.setBounds(225, 55, 350, 50);
        
        this.add(header);
        this.add(subheader);
        
        pnlInputs.setBackground(null);
        pnlInputs.setBounds(0,100,800,200);
        this.add(pnlInputs);
        
        this.add(btnRegister);        
        this.add(btnCancel);
        
        btnRegister.addActionListener(this);
        btnCancel.addActionListener(this);
        
        this.setModal(true);
        this.setSize(800, 650);
        this.getContentPane().setBackground(clrAshGrey);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setVisible(false);
    }
    
    private void addMaintenancePanel(){
        
    }
    
    public static void setToAdd() {
        currentData = new Car();
        lblid.setText(String.valueOf(DataService.getAnId(2)));
        fldModel.setText(null);
        cbxColor.setSelectedItem(null);
        fldLic.setText(null);
        cbxCar.setSelectedItem(null);
        cbxFuel.setSelectedItem(null);
        cbxAvail.setSelectedItem(null);
        cbxCondition.setSelectedItem(null);
        fldPrice.setText("0.00");
        btnRegister.setText("REGISTER");
        header.setText("Add a Car Record");
        subheader.setText("Please set all the information needed below to register.");

    }
    public static void setToEdit(Car car) {
        currentData = car;
        lblid.setText(car.id);
        fldModel.setText(car.model);
        cbxColor.setSelectedItem(car.color);
        fldLic.setText(car.license_plate);
        cbxCar.setSelectedItem(car.category);
        cbxFuel.setSelectedItem(car.fuel_type);
        cbxAvail.setSelectedItem(car.is_available);
        cbxCondition.setSelectedItem(car.car_condition);
        fldPrice.setText(car.price_per_day);
        btnRegister.setText("UPDATE");
        header.setText("Update Car Record");        
        subheader.setText("Please set all the information needed below to update.");
    }

    
    private void getData(){
        currentData.id = lblid.getText();
        currentData.model = fldModel.getText();
        currentData.color = cbxColor.getSelectedItem().toString();
        currentData.license_plate = fldLic.getText();
        currentData.category = cbxCar.getSelectedItem().toString();
        currentData.fuel_type = cbxFuel.getSelectedItem().toString();
        currentData.is_available = cbxAvail.getSelectedItem().toString();
        currentData.car_condition = cbxCondition.getSelectedItem().toString();
        currentData.price_per_day = fldPrice.getText();
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRegister) {
            if (btnRegister.getText().equals("UPDATE")){
                getData();
                if (DataService.updateRecord(
                        Utils.toArrayString(currentData), 
                        Utils.toArrayStringKeys(currentData), 
                        "tbl_car")) {
                    JOptionPane.showMessageDialog(
                            null, 
                            "Car had been successfully updated.",
                            "Car Update Success", 
                            JOptionPane.INFORMATION_MESSAGE);
                    this.setVisible(false);
                    }
                else {
                    JOptionPane.showMessageDialog(
                        null, 
                        "Error in car update. "
                        +"Please make sure all inputs are valid and try again.",
                        "Car Update Failed", 
                        JOptionPane.WARNING_MESSAGE);
                    }
            } 
            else {
                if (DataService.addRecord(Utils.toArrayString(currentData), "tbl_car")) {
                JOptionPane.showMessageDialog(
                        null, 
                        "Car had been successfuly registered.",
                        "Car Registration Success", 
                        JOptionPane.INFORMATION_MESSAGE);
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
            CarPanel.refreshTable();
        }
        else if (e.getSource() == btnCancel) {
            this.setVisible(false);
        }
    }
    
    private static Font getFont(int size){
        return new Font("Arial", Font.PLAIN, size);
    }
    
    private static JComboBox createPanelQAC(String[] contents, String title, int x, int y) {
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
    
    private static JLabel createPanelQA(String title, int x, int y) {
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
    
    private static JTextField createPanelQAF(String title, int x, int y) {
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
    
    
    
    private static JButton makeButton(String title, int x) {
        JButton btn = new JButton(title);
        btn.setFont(getFont(20));
        btn.setBounds(x, 525, 250, 50);
        return btn;
    }
}