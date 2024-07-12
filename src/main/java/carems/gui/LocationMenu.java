package carems.gui;

import carems.backend.DataService;
import carems.models.Location;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LocationMenu extends JDialog implements ActionListener {
    
    static JPanel pnl = new JPanel(null);
    Location currentData = new Location();
    
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
    static JButton btnRegister = makeButton("REGISTER");
    static JButton btnCancel = makeButton("CANCEL");
    static JLabel lblHeader = makeLabel("");
    static JLabel lblSubheader = makeLabel("To save, please press REGISTER.");
    JLabel lblId;
    static JTextField fldCity, fldCar;
    
    // Init. optimizations.
    static ArrayList<JTextField> fldArray = new ArrayList();
    int intFldHeight = 25;
    
    private static JLabel makeLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        pnl.add(label);
        return label;
    }
   
    
    private static JButton makeButton(String text) {
        JButton btn = new JButton(text);
        pnl.add(btn);
        return btn;
    }
      
    public LocationMenu() {
        this.setLayout(null);
        this.setBackground(clrAshGrey);

        lblHeader.setFont(fntSupHeader);
        lblSubheader.setFont(fntSubHeader);

        lblId = createPanelQA("Location ID", 150);
        fldCity = createPanelQAF("City (to be used as category):", 200);
        fldCar = createPanelQAF("Full address located in this city:", 250);
        
        lblHeader.setBounds(50,0, 500, 100);
        lblSubheader.setBounds(50, 100, 500, intFldHeight);

        btnRegister.setBounds(175, 500, 150, 50);        
        btnCancel.setBounds(400, 500, 150, 50);
        
        btnRegister.addActionListener(this);        
        btnCancel.addActionListener(this);

        pnl.setSize(800, 650);
        pnl.setBackground(clrAshGrey);
        pnl.setLayout(null);
        this.setModal(true);
        this.setSize(800, 650);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setLayout(null);
        this.add(pnl);
        this.setLocationRelativeTo(null);
        this.setVisible(false);
    }
    
    public void setToAdd() {
        lblId.setText(DataService.getAnId(4));
        fldCity.setText("");
        fldCar.setText("");
        btnRegister.setText("REGISTER");
        lblHeader.setText("Add Location");
        lblSubheader.setText("To save, please press REGISTER.");
    }
    
    public void setToEdit(Location location) {
        currentData = location;
        lblId.setText(location.id);
        fldCity.setText(location.city);
        fldCar.setText(location.address);
        btnRegister.setText("UPDATE");
        lblHeader.setText("Update Location");
        lblSubheader.setText("To update, please press UPDATE.");
    }
    
    private void getData(){
        currentData.id = lblId.getText();
        currentData.city = fldCity.getText();
        currentData.address = fldCar.getText();
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
                        "tbl_location")) {
                    JOptionPane.showMessageDialog(
                            null, 
                            "Location had been successfully updated.",
                            "Location Update Success", 
                            JOptionPane.INFORMATION_MESSAGE);
                    this.setVisible(false);
                    }
                else {
                    JOptionPane.showMessageDialog(
                        null, 
                        "Error in location update."
                        +"Please make sure all inputs are valid and try again.",
                        "Location Update Failed", 
                        JOptionPane.WARNING_MESSAGE);
                    }
            } 
            else {
                if (DataService.addRecord(Utils.toArrayString(currentData), 
                        "tbl_location")) {
                JOptionPane.showMessageDialog(
                        null, 
                        "Location had been successfuly registered.",
                        "Location Registration Success", 
                        JOptionPane.INFORMATION_MESSAGE);
                    this.setVisible(false);;
                }
                else {
                        JOptionPane.showMessageDialog(
                            null, 
                            "Error in location registration."
                            +"Please make sure all inputs are valid and try again.",
                            "Location Registration Failed", 
                            JOptionPane.WARNING_MESSAGE);
                    }
                
            }
            LocationPanel.refreshTable();
        }
        else if (e.getSource() == btnCancel) {
            this.dispose();
        }
    }
    
     private JLabel createPanelQA( String title, int y) {
        JLabel l = new JLabel(title);
        l.setFont(Utils.getFont(12));
        l.setForeground(Color.WHITE);
        l.setBounds(50, y, 200, 20);
        pnl.add(l);
        
        // Return a label for the answer part.
        JLabel a = new JLabel("N/A");
        a.setHorizontalAlignment(JLabel.LEFT);
        a.setForeground(Color.WHITE);
        a.setBounds(275, y, 425, 20);
        pnl.add(a);
        return a;
    }
    
    private JTextField createPanelQAF(String title, int y) {
        JLabel l = new JLabel(title);
        l.setFont(Utils.getFont(12));
        l.setForeground(Color.WHITE);
        l.setBounds(50, y, 250, 20);
        pnl.add(l);
        
        // Return a field for the answer part.
        JTextField a = new JTextField("N/A");
        a.setHorizontalAlignment(JLabel.LEFT);
        a.setBounds(275, y, 425, 20);
        pnl.add(a);
        return a;
    }
}
