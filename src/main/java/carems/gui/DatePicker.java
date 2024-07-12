package carems.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

class DatePicker {

    int month = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH);
    int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
    JButton btnMonthYear = new JButton(""); // Selector for year as well.
    String day = "";
    JDialog d;
    JButton[] button = new JButton[49];
    
 
    public DatePicker(String initDate) {
        d = new JDialog();
        d.setModal(true);
        String[] header = { "Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat" };

        JPanel p2 = new JPanel(new GridLayout(1, 3));
        JButton previous = new JButton("Prev.");
        previous.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                month--;
                displayDate();
            }
        });
        p2.add(previous);

        p2.add(btnMonthYear);

        JButton next = new JButton("Next");
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                month++;
                displayDate();
            }
        });
        p2.add(next);

        JPanel p1 = new JPanel(new GridLayout(7, 7));
        p1.setPreferredSize(new Dimension(430, 120));

        // for (int x = 0; x <button> 6) {
        for (int x = 0; x < button.length; x++) {
            final int selection = x;
            button[x] = new JButton();
            button[x].setFocusPainted(false);
            button[x].setBackground(Color.white);
            if (x > 6) {
                button[x].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        day = button[selection].getActionCommand();
                        d.dispose();
                    }
                });
            }
            if (x < 7) {
                button[x].setText(header[x]);
                button[x].setForeground(Color.red);
            }
            p1.add(button[x]);
        }
        
        btnMonthYear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String givenYear = JOptionPane.showInputDialog(d, "Please input month/year (MM/YYY): ");
                try {
                    String[] splits = givenYear.split("/");
                    System.out.println(splits[0] + " | year: " +splits[1]);
                    month = Integer.parseInt(splits[0]);
                    year = Integer.parseInt(splits[1]);
                    displayDate();
                } catch (NumberFormatException | NullPointerException ex) {
                }
            }
        });

        d.add(p1, BorderLayout.CENTER);
        d.add(p2, BorderLayout.SOUTH);
        d.pack();
        d.setLocationRelativeTo(null);
        
        // Init.
        if (!initDate.isBlank()) {
            String[] splits = initDate.split("-");
            day = splits[0];
            month = Integer.parseInt(splits[1]);        
            year = Integer.parseInt(splits[2]);
        }

        displayDate();
        d.setVisible(true);
    }
 
    public void displayDate() {
        for (int x = 7; x < button.length; x++)
            button[x].setText("");
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                "MMMM yyyy");
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(year, month, 0);
        int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);
        int daysInMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
        for (int x = 6 + dayOfWeek, day = 1; day <= daysInMonth; x++, day++)
            button[x].setText("" + day);
        btnMonthYear.setText(sdf.format(cal.getTime()));
        d.setTitle("Date Picker");
    }
 
    public String setPickedDate() {
        if (day.equals(""))
            return day;
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                "dd-MM-yyyy");
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(year, month  - 1, Integer.parseInt(day));
        return sdf.format(cal.getTime());
    }
    
}


