package carems.gui;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class InvoiceFrame extends JFrame {
    public String generateSample() {
        String header = "\r\n\r\n /$$$$$$                               /$$                    \r\n|_  $$_/                              |__/                    \r\n  | $$   /$$$$$$$  /$$    /$$ /$$$$$$  /$$  /$$$$$$$  /$$$$$$ \r\n  | $$  | $$__  $$|  $$  /$$//$$__  $$| $$ /$$_____/ /$$__  $$\r\n  | $$  | $$  \\ $$ \\  $$/$$/| $$  \\ $$| $$| $$      | $$$$$$$$\r\n  | $$  | $$  | $$  \\  $$$/ | $$  | $$| $$| $$      | $$_____/\r\n /$$$$$$| $$  | $$   \\  $/  |  $$$$$$/| $$|  $$$$$$$|  $$$$$$$\r\n|______/|__/  |__/    \\_/    \\______/ |__/ \\_______/ \\_______/\r\n\n";

        String content = """
        ===============================================================================================

        BILLED TO:                                                                  INVOICE NO: UNKNOWN
        Juan Dela Cruz                                                                 <DATE TIME HERE>
        +63 912 345 6789
        Lot. 69 Malakas Road, Ambatusam, GA, Somewhere

        ===============================================================================================
        RENTAL DETAILS
        -----------------------------------------------------------------------------------------------
        Car Description     |   Rental Period      | Rate Per Day   |   Number of Days  |   Subtotal  |
        -----------------------------------------------------------------------------------------------
        Honda Civic         |  1/1/2020 - 1/3/2020 | PHP 2500.00    |      2 DAYS       | PHP 5000.00 |
        ===============================================================================================
        PAYMENT METHOD:
        Cash
        ===============================================================================================

        DATE: <DATE TIME HERE>
        <SIGNATURE HERE>
        PEDRO DELA CRUZ (SAMPLE OWNER OF CAREMS)


        ==============  THANK YOU FOR TRUSTING CAREMS. PLEASE COME AGAIN! =============================""";

        String sample = header + content;
        return sample;
    }

    InvoiceFrame() {
        this.setPreferredSize(new Dimension(750, 650));
        this.setTitle("Carems - Sample Invoice");
        JTextArea txtData = new JTextArea(28, 28);
        txtData.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        txtData.setVisible(true);
        txtData.setEditable(true);
        txtData.setText(generateSample());

        this.getContentPane().add(new JScrollPane(txtData));
        this.pack();
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
