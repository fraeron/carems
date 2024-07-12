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
    JLabel lblcustomerid;
    JButton btndate, btnreturn;
    JLabel lblelapsed;
    JLabel lblfine;
    
    int dayDiff;

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
        System.out.println("wut");
        if (cbxbookid.getSelectedItem() != null) {
            String bookid = cbxbookid.getSelectedItem().toString();
            book = DataService.getBooking(bookid);
            cus = DataService.getCustomer(book.customer_id);
            lblcarid.setText(book.booked_car_id);
            lblcustomerid.setText(book.customer_id); 
            car = DataService.getCar(book.booked_car_id);
        }
    }
    
    private void resetInfo(){
        changeSelections(cbxbookid, DataService.getOngoingBooks());
        cbxbookid.setSelectedItem(null);
        btnreturn.setEnabled(false);
        lblcarid.setText("N/A");
        lblcustomerid.setText("N/A"); 
        lblelapsed.setText("N/A");
        btndate.setText("Select Date");
        lblfine.setText("N/A");
    }
    
    private void calculateInfo(){
        String startDate = book.booked_datetime;
        String endDate = btndate.getText();
        String actualDate = book.return_datetime;
        String[] ds1 = startDate.split("-");
        String[] ds2 = endDate.split("-");        
        String[] ds3 = actualDate.split("-");

        
        int correctDayDiff = Integer.parseInt(ds2[0]) - Integer.parseInt(ds1[0]);
        int actualDayDiff = Integer.parseInt(ds3[0]) - Integer.parseInt(ds1[0]);
        int lateDays = correctDayDiff - actualDayDiff;
        int fine = 250;
        float calculated = lateDays * fine;
        lblelapsed.setText((actualDayDiff + lateDays) + " Day(s)");
        if (lateDays < 0) {
            lblelapsed.setText("Less than target day.");
            lblfine.setText("<html>Ahead of time. No fine.</html>");  
        } else if (lateDays == 0){
            lblfine.setText("Arrived on time. No fine.");
        }
        else {
            lblfine.setText("PHP " + calculated); 
        }    
        btnreturn.setEnabled(true);
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
        lblcarid = createPanelQA(pnlSelCus, "Car ID:", 90);        
        lblcustomerid = createPanelQA(pnlSelCus, "Customer ID:", 130); 
        btndate = createPanelQAD(pnlSelCus, "Date Returned:", 170);
        lblelapsed = createPanelQA(pnlSelCus, "Days Elapsed:", 210);
        lblfine = createPanelQA(pnlSelCus, "Fine for late (in PHP):", 250);
        lblfine.setBounds(150, 250, 150, 30);
        
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
                DataService.refreshData();
                System.out.println("cbxbookid is being used");
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
                        true);
                
                resetInfo();
                refreshTable();
            }
        });
        this.add(btnreturn);
    }
    
    public void refreshTable(){
        DataService.refreshData();
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