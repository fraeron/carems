package carems.gui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class Return extends JPanel{
    // Init. colors.
    private final Color clrAshGrey = new Color(42, 42, 42);    
    private final Color clrMagmaOrange = new Color(255, 127, 39);
    
    JLabel header = new JLabel("Return a Car");    
    JLabel subheader = new JLabel("Please set all the information needed below "
            + "to return.");

    
    private Font getFont(int size){
        return new Font("Arial", Font.PLAIN, size);
    }
    
    public Return(){
        this.setBackground(clrAshGrey);
        this.setLayout(null);
        
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
    }
    
    private void initInfoPanel(){
        JPanel pnlSelCus = new JPanel();
        pnlSelCus.setLayout(null);
        pnlSelCus.add(createPanelHeader("Return Form"));
        pnlSelCus.setBackground(clrMagmaOrange);
        pnlSelCus.setBounds(20, 100, 350, 360);
        this.add(pnlSelCus);
        
        JComboBox cbxcarid = createPanelQAC(pnlSelCus, new String[]{}, 
                "Car ID:", 50);        
        JComboBox cbxcustomerid = createPanelQAC(pnlSelCus, new String[]{}, 
                "Customer ID:", 90); 
        JButton cbxdate = createPanelQAD(pnlSelCus, "Date Returned:", 130);
        JLabel lblelapsed = createPanelQA(pnlSelCus, "Days Elapsed:", 170);
        JLabel lblfine = createPanelQA(pnlSelCus, "Fine:", 210);

        JButton btnBook = new JButton("Return");
        btnBook.setFont(getFont(48));
        btnBook.setBounds(20, 475, 350, 100);
        this.add(btnBook);
    }
    
    private void initTablePanel(){
        String[] headers = {"Customer ID", "Car ID", "Return Date", "Elapsed", "Fine"};
        String[][]data = {{"1", "Dummy", "12/8/2024", "12", "1100.00"}};
        JTable table = new JTable(data, headers);
        JScrollPane sp = new JScrollPane(table);
        this.add(sp);
        sp.setBounds(380, 100, 390, 475);
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
