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

    private String header = "\r\n\r\n /$$$$$$                               /$$                    \r\n|_  $$_/                              |__/                    \r\n  | $$   /$$$$$$$  /$$    /$$ /$$$$$$  /$$  /$$$$$$$  /$$$$$$ \r\n  | $$  | $$__  $$|  $$  /$$//$$__  $$| $$ /$$_____/ /$$__  $$\r\n  | $$  | $$  \\ $$ \\  $$/$$/| $$  \\ $$| $$| $$      | $$$$$$$$\r\n  | $$  | $$  | $$  \\  $$$/ | $$  | $$| $$| $$      | $$_____/\r\n /$$$$$$| $$  | $$   \\  $/  |  $$$$$$/| $$|  $$$$$$$|  $$$$$$$\r\n|______/|__/  |__/    \\_/    \\______/ |__/ \\_______/ \\_______/\r\n\n";


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

    private String templateBody = """
        %s         |  1/1/2020 - 1/3/2020 | PHP %.2f    |      2 DAYS       | PHP %.2f |
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

    private String generateInvoice(String name, String carname) {
        LocalDate currentTime = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String dateString = currentTime.format(format);
        int maxCost = 10000;
        int minCost = 5000;
        float randomCost = Math.round(rand.nextFloat(maxCost - minCost) + minCost);
        float ratePerDay = Math.round(randomCost / 2);
        return 
            header +
            String.format(templateHead, rand.nextInt(1000), name, dateString) + 
            String.format(templateBody, carname, ratePerDay, randomCost) + 
            String.format(templateFoot, dateString);
    }

    private String generateSample() {
        String name = "Juan Dela Cruz";
        String carname = "Honda Civic";
        String sample = generateInvoice(name, carname);
        return sample;
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

    InvoiceFrame() {
        initialize();

        // Generate sample if no arguments specified.
        txtData.setText( generateSample());
    }

    InvoiceFrame(String name, String carname) {
        initialize();
        txtData.setText(generateInvoice(name, carname));
    }


}
