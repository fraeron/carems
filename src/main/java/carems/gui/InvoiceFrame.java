package carems.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;



public class InvoiceFrame extends JFrame {
    Random rand = new Random();
    JTextArea txtData;

    private String headerInvoice = "\r\n\r\n /$$$$$$                               /$$                    \r\n|_  $$_/                              |__/                    \r\n  | $$   /$$$$$$$  /$$    /$$ /$$$$$$  /$$  /$$$$$$$  /$$$$$$ \r\n  | $$  | $$__  $$|  $$  /$$//$$__  $$| $$ /$$_____/ /$$__  $$\r\n  | $$  | $$  \\ $$ \\  $$/$$/| $$  \\ $$| $$| $$      | $$$$$$$$\r\n  | $$  | $$  | $$  \\  $$$/ | $$  | $$| $$| $$      | $$_____/\r\n /$$$$$$| $$  | $$   \\  $/  |  $$$$$$/| $$|  $$$$$$$|  $$$$$$$\r\n|______/|__/  |__/    \\_/    \\______/ |__/ \\_______/ \\_______/\r\n\n";
    private String headerReceipt = """
                                    /$$$$$$$  /$$$$$$$$  /$$$$$$  /$$$$$$$$ /$$$$$$ /$$$$$$$  /$$$$$$$$
                                   | $$__  $$| $$_____/ /$$__  $$| $$_____/|_  $$_/| $$__  $$|__  $$__/
                                   | $$  \\ $$| $$      | $$  \\__/| $$        | $$  | $$  \\ $$   | $$   
                                   | $$$$$$$/| $$$$$   | $$      | $$$$$     | $$  | $$$$$$$/   | $$   
                                   | $$__  $$| $$__/   | $$      | $$__/     | $$  | $$____/    | $$   
                                   | $$  \\ $$| $$      | $$    $$| $$        | $$  | $$         | $$   
                                   | $$  | $$| $$$$$$$$|  $$$$$$/| $$$$$$$$ /$$$$$$| $$         | $$   
                                   |__/  |__/|________/ \\______/ |________/|______/|__/         |__/   
                                   
                                   """;
    
    private String templateHead = """
        ===============================================================================================

        BILLED TO:                                                                  INVOICE NO: %d
        %s                                                                 %s
        +63 912 345 6789
        Lot. 69 Malakas Road, Ambatusam, GA, Somewhere

        ===============================================================================================
        RENTAL DETAILS
        -----------------------------------------------------------------------------------------------
        Car Description     |   Rental Period      | Rate Per Day   |   Number of Days  |   Subtotal  |
        -----------------------------------------------------------------------------------------------
            """;
    
    private String headReceipt = """
        ===============================================================================================

        BILLED TO:                                                                  RECEIPT NO: %d
        %s                                                                 %s
        +63 912 345 6789
        Lot. 69 Malakas Road, Ambatusam, GA, Somewhere

        ===============================================================================================
        RENTAL DETAILS
        -----------------------------------------------------------------------------------------------
        Car Description     |   Rental Period      | Rate Per Day   |   Number of Days  |   Subtotal  |
        -----------------------------------------------------------------------------------------------
            """;

    private String templateBody = """
        %s         |  %s - %s | PHP %.2f    |      %s DAYS       | PHP %.2f |
            """;
        
    private String templateFoot = """
        ===============================================================================================
        PAYMENT METHOD:
        Cash
        ===============================================================================================

        DATE: %s
        <CLIENT SIGNATURE>
        PEDRO DELA CRUZ (SAMPLE OWNER OF CAREMS)


        ==============  THANK YOU FOR TRUSTING CAREMS. PLEASE COME AGAIN! =============================

            """;

    private String generateInvoice(
            String name, String carname, 
            float ratePerDay, String days, 
            float cost, String startdate, String enddate) {
        LocalDate currentTime = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String dateString = currentTime.format(format);
        return
                headerInvoice +
                String.format(templateHead, rand.nextInt(1000), name, dateString) + 
                String.format(templateBody, carname, startdate, enddate, ratePerDay, days, cost) + 
                String.format(templateFoot, dateString);
    }
    
    private String generateReceipt(
            String name, String carname, 
            float ratePerDay, String days, 
            float cost, String startdate, String enddate){
        LocalDate currentTime = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String dateString = currentTime.format(format);
        return
                headerReceipt +
                String.format(headReceipt, rand.nextInt(1000), name, dateString) + 
                String.format(templateBody, carname, startdate, enddate, ratePerDay, days, cost) + 
                String.format(templateFoot, dateString);
    }

    private void initialize() {
        this.setPreferredSize(new Dimension(750, 650));
        this.setTitle("Carems - Invoice - Thank you for renting!");
        txtData = new JTextArea(28, 28);
        txtData.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        txtData.setVisible(true);
        txtData.setEditable(true);


        this.getContentPane().add(new JScrollPane(txtData));
        this.pack();
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }


    InvoiceFrame(
            String name, 
            String carname, 
            float ratePerDay, 
            String days, 
            float cost,
            String startDate,
            String endDate,
            boolean isReceipt) {
        
        initialize();
        if (isReceipt) {
            txtData.setText(generateReceipt(
                name, 
                carname, 
                ratePerDay, 
                days, 
                cost,
                startDate,
                endDate));
        } else {
            txtData.setText(generateInvoice(name, 
                carname, 
                ratePerDay, 
                days, 
                cost,
                startDate,
                endDate));
        }
        
    }
}
