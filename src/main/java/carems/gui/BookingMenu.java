package carems.gui;

import carems.backend.DataService;
import carems.models.Book;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BookingMenu extends JDialog implements ActionListener {
    static JPanel pnl = new JPanel();
    
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
    static JLabel lblHeader  = makeLabel("Add a Booking");
    static JLabel lblSubheader;
    static JLabel lblId = createPanelQA("Booking ID:", 150);
    
    // Init. optimizations.
    static ArrayList<JTextField> fldArray = new ArrayList();
    static int intFldHeight = 25;
    
    // Init. label helper.
    private static JLabel makeLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        pnl.add(label);
        return label;
    }
    
    // Init. field helper.
    private static JTextField makeField(JTextField fld) {
        fld = new JTextField();
        fldArray.add(fld);
        pnl.add(fld);
        return fld;
    }
    
    // Init. button helper.
    private static JButton makeButton(String text) {
        JButton btn = new JButton(text);
        pnl.add(btn);
        return btn;
    }
    
    static String[] status = {
        "ONGOING", "LATE RETURNED", "RETURNED"
    };
    
    static JTextField fldcarid = createPanelQAF("Booked Car ID:", 200);
    static JTextField fldcusid = createPanelQAF("Customer ID:", 250);
    static JButton btndate = createPanelQAD("Booked Date:", 300);
    static JButton btnreturn = createPanelQAD("Returned Date:", 350);
    static JComboBox cbxstatus = createPanelQAC(status, "Status:", 400);
      
    public BookingMenu() {        
        lblSubheader = makeLabel("To save, please press REGISTER.");
        lblHeader.setFont(fntSupHeader);
        lblSubheader.setFont(fntSubHeader);
      
        
        btnRegister.setBounds(175, 500, 150, 50);        
        btnCancel.setBounds(400, 500, 150, 50);
        
        btnRegister.addActionListener(this);
        btnCancel.addActionListener(this);
        
        lblHeader.setBounds(50,0, 500, 100);
        lblSubheader.setBounds(50, 100, 500, intFldHeight);
        
        pnl.add(lblHeader);
        pnl.add(lblSubheader);
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
    
    // Set to add the menu.
    public static void setToAdd() {
        btnRegister.setText("REGISTER");
        lblHeader.setText("Add Booking");
        lblId.setText(String.valueOf(DataService.bookings.size() + 1));
        fldcarid.setText("");
        fldcusid.setText("");
        btndate.setText("");
        btnreturn.setText("");
        cbxstatus.setSelectedItem(null);
    }
    
    // Set to edit the menu.
    public static void setToEdit(Book book) {
        lblId.setText(book.id);
        fldcarid.setText(book.booked_car_id);
        fldcusid.setText(book.customer_id);
        btndate.setText(book.booked_datetime);
        btnreturn.setText(book.return_datetime);
        cbxstatus.setSelectedItem(book.status);
        btnRegister.setText("UPDATE");
        lblHeader.setText("Update Booking");
    }
    
    // Get data from all fields.
    private ArrayList<String> getFldData(){
        ArrayList<String> fldData = new ArrayList();
        for (JTextField fld : fldArray) {
            fldData.add(fld.getText());
        }
        return fldData;
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRegister) {
            if (DataService.addBooking(getFldData())) {
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
            this.dispose();
        }
    }
    
    private static Font getFont(int size){
        return new Font("Arial", Font.PLAIN, size);
    }
    
    private static JComboBox createPanelQAC(String[] contents, String title, int y) {
        JLabel l = new JLabel(title);
        l.setFont(getFont(12));
        l.setForeground(Color.WHITE);
        l.setBounds(50, y + 5, 300, intFldHeight);
        pnl.add(l);
        
        // Return a combo for the answer part.
        JComboBox a = new JComboBox(contents);
        a.setBounds(150 + 50, y + 5, 300, intFldHeight);
        pnl.add(a);
        return a;
    }
    
    private static JLabel createPanelQA(String title, int y) {
        JLabel l = new JLabel(title);
        l.setFont(getFont(12));
        l.setForeground(Color.WHITE);
        l.setBounds(50, y + 5, 175, 20);
        pnl.add(l);
        
        // Return a label for the answer part.
        JLabel a = new JLabel("N/A");
        a.setHorizontalAlignment(JLabel.CENTER);
        a.setForeground(Color.WHITE);
        a.setBounds(150 + 50, y + 5, 200, 20);
        pnl.add(a);
        return a;
    }
    
    private static JButton createPanelQAD(String title, int y) {
        JLabel l = new JLabel(title);
        l.setFont(getFont(12));
        l.setForeground(Color.WHITE);
        l.setBounds(50, y, 175, 20);
        pnl.add(l);
        
        // Return a button (for datetime) for the answer part.
        JButton a = new JButton("Select Date");
        a.setBackground(Color.WHITE);
        a.setHorizontalAlignment(JLabel.CENTER);
        a.setBounds(150 + 50, y, 200, 20);
        
        a.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                a.setText(new DatePicker("").setPickedDate());
            }
        });
        pnl.add(a);
        return a;
    }
    
    private static JTextField createPanelQAF(String title, int y) {
        JLabel l = new JLabel(title);
        l.setFont(getFont(12));
        l.setForeground(Color.WHITE);
        l.setBounds(50, y, 175, 20);
        pnl.add(l);
        
        // Return a field for the answer part.
        JTextField a = new JTextField("N/A");
        a.setHorizontalAlignment(JLabel.CENTER);
        a.setBounds(150 + 50, y, 200, 20);
        pnl.add(a);
        return a;
    }
    
}
