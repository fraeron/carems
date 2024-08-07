package carems.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

class DatePicker {

    // Init. colors
    private final Color clrAshGrey = new Color(42, 42, 42);    
    private final Color clrMagmaOrange = new Color(255, 127, 39);
    
    // Init. calendar
    int month = Calendar.getInstance().get(Calendar.MONTH);
    int year = Calendar.getInstance().get(Calendar.YEAR);
    JButton btnMonthYear = new JButton(""); // Selector for year as well.
    String day = "";
    JDialog d;
    String[] headerDays = { "Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat"};
    
    // Init. all possible days as buttons
    JButton[] button = new JButton[49];
    
    public static long subtractDates(String date1, String date2) {
        try {
            // Make sure dates are in (DD-MM-YYYY)
            String europeanDatePattern = "dd-MM-yyyy";
            DateTimeFormatter euroFormat = DateTimeFormatter.ofPattern(europeanDatePattern);
            LocalDate d1 = LocalDate.parse(date1, euroFormat);
            LocalDate d2 = LocalDate.parse(date2, euroFormat);
            Duration diff = Duration.between(d1.atStartOfDay(), d2.atStartOfDay());
            return diff.toDays();
        } catch (DateTimeParseException ex) {
            System.out.println("Null date detected. Skipping.");
        }
        return 0;
    }
    
    public DatePicker(String initDate) {
        d = new JDialog();
        d.setModal(true);// Make the app lock until this is closed.

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

        for (int x = 0; x < button.length; x++) {
            final int selection = x;
            button[x] = new JButton();
            button[x].setFocusPainted(false);
            button[x].setBackground(Color.GRAY);
            if (x > 6) {
                button[x].setBackground(Color.GRAY);
//                button[x].setForeground(Color.WHITE);
                button[x].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        day = button[selection].getActionCommand();
                        d.dispose();
                    }
                });
            }
            if (x < 7) {
                button[x].setText(headerDays[x]);
                button[x].setBackground(clrAshGrey);
                button[x].setForeground(clrMagmaOrange);
            }
            p1.add(button[x]);
        }
        
        btnMonthYear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String givenYear = JOptionPane.showInputDialog(d, "Please input month/year (MM/YYY): ");
                try {
                    String[] splits = givenYear.split("/");
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
        
        // Set default date if ever constructor has argument.
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
        SimpleDateFormat sdf = new SimpleDateFormat(
                "MMMM yyyy");
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, 0);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int x = 6 + dayOfWeek, day = 1; day <= daysInMonth; x++, day++)
            button[x].setText("" + day);
        btnMonthYear.setText(sdf.format(cal.getTime()));
        d.setTitle("Carems - Please pick a date");
    }
 
    public String setPickedDate() {
        if (day.equals(""))
            return day;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar cal = Calendar.getInstance();
        cal.set(year, month  - 1, Integer.parseInt(day));
        return sdf.format(cal.getTime());
    }
    
}


