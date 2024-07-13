package carems.gui;

import carems.backend.DataService;
import carems.models.Book;
import carems.models.Car;
import carems.models.Customer;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.format.DateTimeParseException;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class Return extends JPanel{
    // Init. colors.
    private final Color clrAshGrey = new Color(42, 42, 42);    
    private final Color clrMagmaOrange = new Color(255, 127, 39);
    
    JComboBox cbxbookid;
    JLabel lblcarid;
    JLabel lblcarmodel;
    JLabel lblcustomerid;
    JLabel lblcusname;
    JButton btndate, btnreturn;
    JLabel lblelapsed;
    JLabel lblprice;
    JLabel lblfine;
    JLabel lbltotal;
    
    String startDate, endDate, actualDate;

    // Init. history
    Book book;
    Car car;
    Customer cus;
    DefaultTableModel model;
    
    JTable tbl;
    JLabel header = new JLabel("Return a Car");    
    JLabel subheader = new JLabel("Please set all the information needed below "
            + "to return.");

    private Font getFont(int size){
        return new Font("Arial", Font.PLAIN, size);
    }
    
    public Return(){
        this.setBackground(clrAshGrey);
        this.setLayout(null);
        this.setSize(800, 650);
        
        // Header.
        header.setForeground(clrMagmaOrange);
        header.setFont(getFont(48));
        header.setBounds(225, 20, 300, 50);
        
        // Subheader.
        subheader.setForeground(clrMagmaOrange);
        subheader.setFont(getFont(12));
        subheader.setBounds(225, 50, 350, 50);
        
        // Init.
        initInfoPanel();
        initTablePanel();

        this.add(header);
        this.add(subheader);
        
        btnreturn.setEnabled(false);
        
        changeSelections(cbxbookid, DataService.getOngoingBooks());
        cbxbookid.setSelectedItem(null);
    }
    
    private void changeSelections(JComboBox cbx, String[] selections) {
        DefaultComboBoxModel<String> model 
            = new DefaultComboBoxModel<>(selections);
        cbx.setModel(model);
    }
    
    private void showInfo(){
        if (cbxbookid.getSelectedItem() != null) {
            DataService.refreshData();
            String bookid = cbxbookid.getSelectedItem().toString();
            book = DataService.getBooking(bookid);
            cus = DataService.getCustomer(book.customer_id);
            car = DataService.getCar(book.booked_car_id);
            lblcarid.setText(book.booked_car_id);
            lblcustomerid.setText(book.customer_id); 
            lblcusname.setText(cus.name);
            lblcarmodel.setText(car.model);
            lblprice.setText(car.price_per_day);
        }
    }
    
    private void resetInfo(){
        changeSelections(cbxbookid, DataService.getOngoingBooks());
        cbxbookid.setSelectedItem(null);
        btnreturn.setEnabled(false);
        lblcarmodel.setText("N/A");
        lblcustomerid.setText("N/A");
        lblcusname.setText("N/A");
        lblelapsed.setText("N/A");
        lblprice.setText("N/A");
        lblfine.setText("N/A");
        lbltotal.setText("N/A");
        btndate.setText("Select Date");
    }
    
    private void calculateInfo(){
        startDate = book.booked_datetime;
        endDate = btndate.getText();
        actualDate = book.return_datetime;
        
        try{
            long correctDayDiff = DatePicker.subtractDates(startDate, endDate);
            long actualDayDiff = DatePicker.subtractDates(startDate, actualDate);
            long lateDays = correctDayDiff - actualDayDiff;
            long pastTravelled = correctDayDiff * -1;
            int fine = 250;
            float calculated = lateDays * fine;
            lblelapsed.setText((actualDayDiff + lateDays) + " Day(s)");
            if (pastTravelled > 0) {
                lblelapsed.setText("Incorrect date set.");
                btnreturn.setEnabled(false);
            }
            else if (lateDays < 0) {
                lblelapsed.setText("Less than target day.");
                lblfine.setText("<html><center>Ahead of time. No fine.</center></html>"); 
                lbltotal.setText("PHP " + (Float.valueOf(car.price_per_day) + calculated));
                btnreturn.setEnabled(true);
            } 
            else if (lateDays == 0){
                lblfine.setText("Arrived on time. No fine.");
                lbltotal.setText("PHP " + (Float.valueOf(car.price_per_day) + calculated));
                btnreturn.setEnabled(true);
            }
            else {
                lblfine.setText("<html><center>PHP " + calculated + 
                        ". Penalty of " + lateDays + " day(s).</center></html>"); 
                lbltotal.setText("PHP " + (Float.valueOf(car.price_per_day) + calculated));
                btnreturn.setEnabled(true);
            }
            
        } catch (DateTimeParseException ex) {
            lblelapsed.setText("N/A");
            btndate.setText("Select Date");
            lblfine.setText("N/A");
            lbltotal.setText("N/A");
            btnreturn.setEnabled(false);
        }
    }
        
    
    private void initInfoPanel(){
        JPanel pnlSelCus = new JPanel();
        pnlSelCus.setLayout(null);
        pnlSelCus.add(createPanelHeader("Return Form"));
        pnlSelCus.setBackground(clrMagmaOrange);
        pnlSelCus.setBounds(20, 100, 350, 360);
        this.add(pnlSelCus);
        
        cbxbookid = createPanelQAC(pnlSelCus, new String[]{}, 
                "Ongoing Book ID:", 50);    
        lblcarid = createPanelQA(pnlSelCus, "Car ID:", 80);     
        lblcarmodel= createPanelQA(pnlSelCus, "Car Model and Name:", 110);
        lblprice= createPanelQA(pnlSelCus, "Car Price Per Day:", 140);
        lblcustomerid = createPanelQA(pnlSelCus, "Customer ID:", 170); 
        lblcusname= createPanelQA(pnlSelCus, "Customer Name:", 200);
        btndate = createPanelQAD(pnlSelCus, "Date Returned:", 230);
        lblelapsed = createPanelQA(pnlSelCus, "Days Elapsed:", 260);
        lblfine = createPanelQA(pnlSelCus, "Fine for late (in PHP):", 290);
        lblfine.setBounds(150, 290, 150, 30);
        lbltotal = createPanelQA(pnlSelCus, "Total w/ Penalties:", 330);
        
        btndate.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                DataService.refreshData();
                if (e.getSource() == btndate) {
                    btndate.setText(new DatePicker("").setPickedDate());
                    calculateInfo();
                } 
            }
        });
        
        cbxbookid.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                showInfo();
            }
        });

        btnreturn = new JButton("Return");
        btnreturn.setFont(getFont(48));
        btnreturn.setBounds(20, 475, 350, 125);
        
        btnreturn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update book with date and status.
                book.return_datetime = btndate.getText();
                if (lblfine.getText().startsWith("PHP")) {
                    book.status = "LATE RETURNED";
                } else {
                    book.status = "RETURNED";
                }
                DataService.updateRecord(
                        Utils.toArrayString(book), 
                        Utils.toArrayStringKeys(book), 
                        "tbl_book");
                
                // Update car.
                car.is_available = "Yes";
                DataService.updateRecord(
                        Utils.toArrayString(car), 
                        Utils.toArrayStringKeys(car), 
                        "tbl_car");
                
                // Throw receipt.
                float price = Float.parseFloat(car.price_per_day);
                float total = price;
                try{
                    total = Float.parseFloat(lblfine.getText()) + price;
                } catch (NumberFormatException ex) {}
                new InvoiceFrame(
                        cus.name, 
                        car.model, 
                        price,
                        lblelapsed.getText(),
                        total,
                        startDate,
                        actualDate,
                        true);
                
                resetInfo();
                refreshTable();
            }
        });
        this.add(btnreturn);
    }
    
    public void refreshTable(){
        showInfo();
        changeSelections(cbxbookid, DataService.getOngoingBooks());
        model.setRowCount(0);
        String[][] data = Utils.unpackBook(DataService.bookings);
        for(String[] datum : data){
            model.addRow(datum);
        }
    }
    
    private void initTablePanel(){
        String[] headers = {
        "ID", "Booked Car ID", "Customer ID", "Booked Date/Time", "Return Date/Time", "Status"};
        String[][] data = Utils.unpackBook(DataService.bookings);
        // Group table elements.
        model = new DefaultTableModel(data, headers) {
            @Override
            public boolean isCellEditable(int row, int column) {
               return false;
            }
        };
        tbl = new JTable(model);
        JScrollPane sp = new JScrollPane(tbl);
        sp.setBounds(380, 100, 390, 435);
        this.add(sp);
        
        
        JButton btnRefresh = new JButton("Refresh Data");
        btnRefresh.setBounds(380, 550, 390, 50);
        btnRefresh.setBackground(clrMagmaOrange);
        btnRefresh.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if (e.getSource() == btnRefresh) {
                    refreshTable();
                }
            }
        });
        this.add(btnRefresh);
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
        a.setBounds(150, y, 150, 20);
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
        
        // Return a field for the answer part.
        JComboBox a = new JComboBox(contents);
        a.setBounds(130, y, 200, 20);
        parent.add(a);
        return a;
    }
}