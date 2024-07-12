package carems.gui;

import carems.backend.DataService;
import carems.models.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Rent extends JPanel implements ActionListener{
    
    // Init. colors.
    private final Color clrAshGrey = new Color(42, 42, 42);    
    private final Color clrMagmaOrange = new Color(255, 127, 39);
    
    JLabel lblTotal = new JLabel("PHP 0.00");
    JLabel lblTotalHeader = new JLabel("Net Total:");
    JLabel header = new JLabel("Book a Car");    
    JLabel subheader = new JLabel("Please set all the information needed below to book.");

    private static JLabel lblcarid;
    private static JLabel lblcarmodel;
    private static JLabel lblcarlic ;
    private static JLabel lblcarprice ;
    private static JLabel lblcarcond ;
    private static JButton btnSelCar;
    
    AvailableCar carMenu = new AvailableCar();
    AvailableCus cusMenu = new AvailableCus();
    
    long dayDiff;
    String startDate, endDate;
    
    JButton btnRent;
    
    static JLabel lblcusid;
    static JLabel lblcusname;
    static JLabel lblcusdriv;
    static JLabel lblcredit;
    static JButton btnSelCus;
    
    static JComboBox cbxCity;
    static JComboBox cbxAddress;
    static JButton btncusdate;
    static JComboBox cbxtime;
  
    static JComboBox cbxCityDrop;
    static JComboBox cbxAddressDrop;
    static JButton btncusdateDrop;
    static JComboBox cbxtimeDrop;
    
    private Font getFont(int size){
        return new Font("Arial", Font.PLAIN, size);
    }
    
    // For easier deletion.
    ArrayList<JLabel> labels = new ArrayList();
    
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
        
        // Total
        lblTotalHeader.setForeground(Color.WHITE);
        lblTotalHeader.setFont(getFont(18));
        lblTotalHeader.setHorizontalAlignment(JLabel.CENTER);
        lblTotalHeader.setBounds(395, 505, 350, 20);
        lblTotal.setForeground(Color.WHITE);
        lblTotal.setFont(getFont(42));
        lblTotal.setHorizontalAlignment(JLabel.CENTER);
        lblTotal.setBounds(400, 510, 350, 100);
        // Init.
        initSelectCar();
        initSelectCustomer();
        initPickUp();
        initDropOff();
        initControls();
        
        // One-time.
        changeAddress(cbxCity, cbxAddress);
        changeAddress(cbxCityDrop, cbxAddressDrop);
        
        this.add(header);
        this.add(subheader);
        this.add(lblTotal);        
        this.add(lblTotalHeader);

        
        btnRent.setEnabled(false);
        
        
    }
    
    private String[] getCityChoices(){
        ArrayList<String> tempCity = new ArrayList();
        for (Location location : DataService.locations) {
            if (!tempCity.contains(location.city)) {
                tempCity.add(location.city);
            }
        }
        return tempCity.toArray(String[]::new);
    }
    
    private String[] getAddressesByCity(String city){
        ArrayList<String> addressTemp = new ArrayList();
        for (Location location : DataService.locations) {
            if (location.city.equals(city)){
                System.out.println(location.address);
                addressTemp.add(location.address);
            }
        }
        return addressTemp.toArray(String[]::new);
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
        labels.add(a);
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
        a.addActionListener(new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == a ){
                        a.setText(new DatePicker("").setPickedDate());
                        updateTotal();
                    }
                }

        });
        parent.add(a);
        return a;
    }
    
    private void changeAddress(JComboBox cbx, JComboBox cbx2) {
        DefaultComboBoxModel<String> model 
            = new DefaultComboBoxModel<>(
                    getAddressesByCity(
                            cbx.getSelectedItem()
                                    .toString()));
        cbx2.setModel(model);
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
        
        a.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                DataService.refreshData();
                if (e.getSource() == cbxCity) {
                    changeAddress(cbxCity, cbxAddress);
                } 
                if (e.getSource() == cbxCityDrop) {
                    changeAddress(cbxCityDrop, cbxAddressDrop);
                }
            }
        });
        
        return a;
    }
    
    private JButton createPanelButton(JPanel panel, String title, int y) {
        JButton btn = new JButton(title);
        btn.setBounds(75, y, 200, 25);
        panel.add(btn);
        btn.addActionListener(this);
        return btn;
    }
    
    private void initSelectCar(){
        JPanel pnlSelCar = new JPanel();
        pnlSelCar.setLayout(null);
        pnlSelCar.add(createPanelHeader("Select a Car"));
        pnlSelCar.setBackground(clrMagmaOrange);
        pnlSelCar.setBounds(20, 100, 350, 175);
        this.add(pnlSelCar);

        lblcarid = createPanelQA(pnlSelCar, "Car ID:", 30);        
        lblcarmodel = createPanelQA(pnlSelCar, "Car Model:", 50);        
        lblcarlic  = createPanelQA(pnlSelCar, "Car Plate License:", 70);        
        lblcarprice  = createPanelQA(pnlSelCar, "Car Price Per Day (in PHP):", 90);        
        lblcarcond  = createPanelQA(pnlSelCar, "Last Inspected Car Condition:", 110);
        btnSelCar = createPanelButton(pnlSelCar, "Select an Available Car", 140);
    }
    
    private void initSelectCustomer(){
        JPanel pnlSelCus = new JPanel();
        pnlSelCus.setLayout(null);
        pnlSelCus.add(createPanelHeader("Select a Customer"));
        pnlSelCus.setBackground(clrMagmaOrange);
        pnlSelCus.setBounds(20, 300, 350, 175);
        this.add(pnlSelCus);
        
        lblcusid = createPanelQA(pnlSelCus, "Customer ID:", 30);        
        lblcusname = createPanelQA(pnlSelCus, "Customer Name:", 50);
        lblcusdriv = createPanelQA(pnlSelCus, "Customer Driver's License No.:", 70);
        lblcredit = createPanelQA(pnlSelCus, "Customer's Credit Card No.:", 90);
        btnSelCus = createPanelButton(pnlSelCus, "Select a Legible Customer", 140);
    }
    
    private void initPickUp(){
        JPanel pnlPickUp = new JPanel();
        pnlPickUp.setLayout(null);
        pnlPickUp.setBackground(clrMagmaOrange);
        pnlPickUp.add(createPanelHeader("Pickup Location & Date"));
        
        cbxCity = createPanelQAC(pnlPickUp, getCityChoices(), "City to Pickup:", 40);  
        cbxAddress = createPanelQAC(pnlPickUp, new String[]{""} ,"Address to Pickup:", 70);        
        btncusdate = createPanelQAD(pnlPickUp, "Date to Pickup:", 100);        
        cbxtime = createPanelQAC(pnlPickUp, Utils.getTimeSet(), "Time to Pickup:", 130);        
        
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
        
        cbxCityDrop = createPanelQAC(pnlDropOff, getCityChoices(), "City to Drop off:", 40);  
        cbxAddressDrop = createPanelQAC(pnlDropOff, new String[]{""}, "Address to Drop off:", 70);        
        btncusdateDrop = createPanelQAD(pnlDropOff, "Date to Drop off:", 100);        
        cbxtimeDrop = createPanelQAC(pnlDropOff, Utils.getTimeSet(), "Time to Drop off:", 130);        
    }
    
    private void initControls(){
        JPanel pnlControls = new JPanel();
        pnlControls.setLayout(null);
        pnlControls.setBackground(clrAshGrey);
        btnRent = new JButton("Rent/Book");
        btnRent.setFont(getFont(48));
        btnRent.setBounds(0, 0, 350, 100);
        btnRent.addActionListener(this);
        pnlControls.setBounds(20, 500, 350, 100); 
        pnlControls.add(btnRent);
        this.add(pnlControls);
    }
    
    private void addInfo(Car car) {
        lblcarid.setText(car.id);
        lblcarmodel.setText(car.model);
        lblcarlic.setText(car.license_plate);
        lblcarprice.setText(car.price_per_day);
        lblcarcond.setText(car.car_condition);
    }
    
    private void addInfo(Customer cus) {
        lblcusid.setText(cus.id);
        lblcusname.setText(cus.name);
        lblcusdriv.setText(cus.drivers_license_id);
        lblcredit.setText(cus.credit_card_no);
    }
    
    private void updateTotal() {
        
        if (!lblcarprice.getText().equals("N/A") && 
                !lblcusid.getText().equals("N/A")){
            startDate = btncusdate.getText();
            endDate = btncusdateDrop.getText();
            if (!startDate.equals("Select Date") &&
                    !endDate.equals("Select Date")){
               
                // Max rent is 30 days.    
                // Minimum one day rent policy.
                dayDiff = DatePicker.subtractDates(startDate, endDate);
                float calculated = Float.parseFloat(lblcarprice.getText()) * dayDiff;
                if (dayDiff > 0 && dayDiff <= 30) {
                    lblTotalHeader.setText("Net Total for " + dayDiff + " days:");
                    lblTotal.setText("PHP " + calculated);
                    btnRent.setEnabled(true);
                }
                else {
                    if (dayDiff < 1) {
                        lblTotalHeader.setText("Incorrect. Days cannot be less than 1.");
                    } else {
                        lblTotalHeader.setText("Incorrect. Days cannot be more than 30.");
                    }
                    lblTotal.setText("PHP 0.00");
                    btnRent.setEnabled(false);
                }
            } else {
                lblTotalHeader.setText("Please pick your dates.");
                lblTotal.setText("PHP 0.00");
                btnRent.setEnabled(false);
            }
        } else {
            lblTotalHeader.setText("Net Total:");
            lblTotal.setText("PHP 0.00");
            btnRent.setEnabled(false);
        }
    }
    
    private void resetInputs(){
        for (JLabel label : labels) {
            label.setText("N/A");
            btncusdate.setText("Select Date");
            btncusdateDrop.setText("Select Date");
        }
    }
    
    private void doRent(){
        DataService.refreshData();
        Book book = new Book();
        book.id = DataService.getAnId(1);
        book.booked_car_id = lblcarid.getText();
        book.customer_id = lblcusid.getText();
        book.booked_datetime = btncusdate.getText();
        book.return_datetime = btncusdateDrop.getText();
        book.status = "ONGOING";
        DataService.addBooking(book);
        Car currentCar = DataService.getCar(lblcarid.getText());
        currentCar.is_available = "No";
        DataService.updateRecord(Utils.toArrayString(currentCar),
                Utils.toArrayStringKeys(currentCar), "tbl_car");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        carMenu.refresh();        
        cusMenu.refresh();

        if (e.getSource() == btnSelCar) {
            carMenu.setVisible(true);
            addInfo(carMenu.pickCar()); // 1 -> Car
            updateTotal();
        } else if (e.getSource() == btnSelCus) {
            cusMenu.setVisible(true);
            addInfo(cusMenu.pickCustomer()); // 2 -> Customer
            updateTotal();
        } else if (e.getSource() == btnRent) {
            doRent();
            new InvoiceFrame(
                    lblcusname.getText(), 
                    lblcarmodel.getText(),
                    Float.parseFloat(lblcarprice.getText()),
                    String.valueOf(dayDiff),
                    Float.parseFloat(lblTotal.getText().replace("PHP ", "")),
                    startDate,
                    endDate,
                    false);
            resetInputs();
            updateTotal();
            btnRent.setEnabled(false);
        }
}   }
