package carems.gui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Rent extends JPanel{
    
    // Init. colors.
    private final Color clrAshGrey = new Color(42, 42, 42);    
    private final Color clrMagmaOrange = new Color(255, 127, 39);
    
    JLabel lblTotal = new JLabel("Total: PHP 0.00");
    JLabel header = new JLabel("Book a Car");    
    JLabel subheader = new JLabel("Please set all the information needed below to book.");

    
    private Font getFont(int size){
        return new Font("Arial", Font.PLAIN, size);
    }
    
    public Rent(){
        this.setBackground(clrAshGrey);
        this.setLayout(null);
        
        // Header.
        header.setForeground(clrMagmaOrange);
        header.setFont(getFont(48));
        header.setBounds(250, 20, 250, 50);
        
        // Subheader.
        subheader.setForeground(clrMagmaOrange);
        subheader.setFont(getFont(12));
        subheader.setBounds(225, 50, 350, 50);
        
        // Subheader.
        lblTotal.setForeground(Color.WHITE);
        lblTotal.setFont(getFont(42));
        lblTotal.setHorizontalAlignment(JLabel.CENTER);
        lblTotal.setBounds(400, 500, 350, 100);
        
        // Init.
        initSelectCar();
        initSelectCustomer();
        initPickUp();
        initDropOff();
        initControls();

        this.add(header);
        this.add(subheader);
        this.add(lblTotal);
    }
    
    private JLabel createPanelHeader(String title) {
        JLabel l = new JLabel(title);
        l.setFont(getFont(14));
        l.setForeground(clrAshGrey);
        l.setHorizontalAlignment(JLabel.CENTER);
        l.setBounds(5, 5, 340, 20);
        l.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, clrAshGrey));
        return l;
    }
    
    private JLabel createPanelQA(JPanel parent, String title, int y) {
        JLabel l = new JLabel(title);
        l.setFont(getFont(12));
        l.setForeground(clrAshGrey);
        l.setBounds(15, y, 175, 20);
        parent.add(l);
        
        // Return a label for the answer part.
        JLabel a = new JLabel("N/A");
        a.setHorizontalAlignment(JLabel.CENTER);
        a.setBounds(200, y, 150, 20);
        parent.add(a);
        return a;
    }
    
    private JButton createPanelQAD(JPanel parent, String title, int y) {
        JLabel l = new JLabel(title);
        l.setFont(getFont(12));
        l.setForeground(clrAshGrey);
        l.setBounds(15, y, 175, 20);
        parent.add(l);
        
        // Return a button (for datetime) for the answer part.
        JButton a = new JButton("Select Date");
        a.setBackground(Color.WHITE);
        a.setHorizontalAlignment(JLabel.CENTER);
        a.setBounds(130, y, 200, 20);
        parent.add(a);
        return a;
    }
    
    private JComboBox createPanelQAC(JPanel parent, String[] contents, 
            String title, int y) {
        JLabel l = new JLabel(title);
        l.setFont(getFont(12));
        l.setForeground(clrAshGrey);
        l.setBounds(15, y, 175, 20);
        parent.add(l);
        
        // Return a combo for the answer part.
        JComboBox a = new JComboBox(contents);
        a.setBounds(130, y, 200, 20);
        parent.add(a);
        return a;
    }
    
    private JButton createPanelButton(JPanel panel, String title, int y) {
        JButton btn = new JButton(title);
        btn.setBounds(75, y, 200, 25);
        panel.add(btn);
        return btn;
    }
    
    private void initSelectCar(){
        JPanel pnlSelCar = new JPanel();
        pnlSelCar.setLayout(null);
        pnlSelCar.add(createPanelHeader("Select a Car"));
        pnlSelCar.setBackground(clrMagmaOrange);
        pnlSelCar.setBounds(20, 100, 350, 175);
        this.add(pnlSelCar);

        JLabel lblcarid = createPanelQA(pnlSelCar, "Car ID:", 30);        
        JLabel lblcarmodel = createPanelQA(pnlSelCar, "Car Model:", 50);        
        JLabel lblcarlic  = createPanelQA(pnlSelCar, "Car Plate License:", 70);        
        JLabel lblcarprice  = createPanelQA(pnlSelCar, "Car Price Per Day (in PHP):", 90);        
        JLabel lblcarcond  = createPanelQA(pnlSelCar, "Last Inspected Car Condition:", 110);
        JButton btnSelCar = createPanelButton(pnlSelCar, "Select an Available Car", 140);
    }
    
    private void initSelectCustomer(){
        JPanel pnlSelCus = new JPanel();
        pnlSelCus.setLayout(null);
        pnlSelCus.add(createPanelHeader("Select a Customer"));
        pnlSelCus.setBackground(clrMagmaOrange);
        pnlSelCus.setBounds(20, 300, 350, 175);
        this.add(pnlSelCus);
        
        JLabel lblcusid = createPanelQA(pnlSelCus, "Customer ID:", 30);        
        JLabel lblcusname = createPanelQA(pnlSelCus, "Customer Name:", 50);
        JLabel lblcusdriv = createPanelQA(pnlSelCus, "Customer Driver's License No.:", 70);
        JLabel lblcredit = createPanelQA(pnlSelCus, "Customer's Credit Card No.:", 90);
        JButton btnSelCus = createPanelButton(pnlSelCus, "Select a Legible Customer", 140);
    }
    
    private void initPickUp(){
        JPanel pnlPickUp = new JPanel();
        pnlPickUp.setLayout(null);
        pnlPickUp.setBackground(clrMagmaOrange);
        pnlPickUp.add(createPanelHeader("Pickup Location & Date"));
        
        JComboBox cbxCity = createPanelQAC(pnlPickUp, new String[]{""}, "City to Pickup:", 40);  
        JComboBox cbxAddress = createPanelQAC(pnlPickUp, new String[]{""}, "Address to Pickup:", 70);        
        JButton btncusdate = createPanelQAD(pnlPickUp, "Date to Pickup:", 100);        
        JComboBox cbxtime = createPanelQAC(pnlPickUp, new String[]{""}, "Time to Pickup:", 130);        
        
        pnlPickUp.setBounds(400, 100, 350, 175);
        this.add(pnlPickUp);
    }
    
    private void initDropOff(){
        JPanel pnlDropOff = new JPanel();
        pnlDropOff.setLayout(null);
        pnlDropOff.setBackground(clrMagmaOrange);
        pnlDropOff.add(createPanelHeader("Drop Off Location & Date"));
        pnlDropOff.setBounds(400, 300, 350, 175);
        this.add(pnlDropOff);
        
        JComboBox cbxCity = createPanelQAC(pnlDropOff, new String[]{""}, "City to Drop off:", 40);  
        JComboBox cbxAddress = createPanelQAC(pnlDropOff, new String[]{""}, "Address to Drop off:", 70);        
        JButton btncusdate = createPanelQAD(pnlDropOff, "Date to Drop off:", 100);        
        JComboBox cbxtime = createPanelQAC(pnlDropOff, new String[]{""}, "Time to Drop off:", 130);        
    }
    
    private void initControls(){
        JPanel pnlControls = new JPanel();
        pnlControls.setLayout(null);
        pnlControls.setBackground(clrAshGrey);
        JButton btnBook = new JButton("Rent/Book");
        btnBook.setFont(getFont(48));
        btnBook.setBounds(0, 0, 350, 100);
        pnlControls.setBounds(20, 500, 350, 100); 
        pnlControls.add(btnBook);
        this.add(pnlControls);
    }
}
