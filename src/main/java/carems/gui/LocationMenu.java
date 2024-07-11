package carems.gui;

import carems.backend.DataService;
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
    static JTextField fldId, fldName, fldCar;
    
    // Init. optimizations.
    static ArrayList<JTextField> fldArray = new ArrayList();
    int intFldHeight = 25;
    
    private static JLabel makeLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        pnl.add(label);
        return label;
    }
    
    private JTextField makeField(JTextField fld) {
        fld = new JTextField();
        pnl.add(fld);
        fldArray.add(fld);
        return fld;
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
        fldName = createPanelQAF("City (to be used as category):", 200);
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
        this.setVisible(true);
    }
    
    public static void setToAdd() {
        for (JTextField fld : fldArray) {
            fld.setText("");
        }
        btnRegister.setText("REGISTER");
        lblHeader.setText("Add Location");
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
        lblHeader.setText("Update Location");
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
                        "Location had been successfuly registered.",
                        "Location Registration Success", 
                        JOptionPane.INFORMATION_MESSAGE);
                }
            else {
                    JOptionPane.showMessageDialog(
                        null, 
                        "Error in customer registration. "
                        +"Please make sure all inputs are valid and try again.",
                        "Location Registration Failed", 
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
        l.setBounds(50, y, 200, 20);
        this.add(l);
        
        // Return a label for the answer part.
        JLabel a = new JLabel("N/A");
        a.setHorizontalAlignment(JLabel.CENTER);
        a.setForeground(Color.WHITE);
        a.setBounds(275, y, 425, 20);
        this.add(a);
        return a;
    }
    
    private JTextField createPanelQAF(String title, int y) {
        JLabel l = new JLabel(title);
        l.setFont(Utils.getFont(12));
        l.setForeground(Color.WHITE);
        l.setBounds(50, y, 250, 20);
        this.add(l);
        
        // Return a field for the answer part.
        JTextField a = new JTextField("N/A");
        a.setHorizontalAlignment(JLabel.LEFT);
        a.setBounds(275, y, 425, 20);
        this.add(a);
        return a;
    }
}
