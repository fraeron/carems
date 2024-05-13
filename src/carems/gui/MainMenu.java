package carems.gui;

import javax.swing.JFrame;

public class MainMenu extends JFrame {
    public MainMenu(){  
      
        panel = new MainMenuJPanel();
        
        lblCarems = new JLabel();
        lblCarems.setText("CAREMS");
        lblCarems.setBounds(30, 125, 500, 100);
        lblCarems.setForeground(clrMagmaOrange);
        lblCarems.setFont(new Font("League Spartan", Font.BOLD, 30));
        
        btnBookingDetail = new JButton("BOOKING DETAILS");
        btnBookingDetail.setBounds(26, 300, 150, 30);
        btnBookingDetail.setBackground(clrMagmaOrange);
        btnBookingDetail.setFont(new Font("League Spartan", Font.BOLD, 12));
        btnBookingDetail.addActionListener(this);
        
        btnCustomer = new JButton("CUSTOMER");
        btnCustomer.setBounds(26, 340, 150, 30);
        btnCustomer.setBackground(clrMagmaOrange);
        btnCustomer.setFont(new Font("League Spartan", Font.BOLD, 12));
        btnCustomer.addActionListener(this);
        
        btnCar = new JButton("CARS");
        btnCar.setBounds(26, 380, 150, 30);
        btnCar.setBackground(clrMagmaOrange);
        btnCar.setFont(new Font("League Spartan", Font.BOLD, 12));
        btnCar.addActionListener(this);
        
        btnOwner = new JButton("OWNER");
        btnOwner.setBounds(26, 420, 150, 30);
        btnOwner.setBackground(clrMagmaOrange);
        btnOwner.setFont(new Font("League Spartan", Font.BOLD, 12));
        btnOwner.addActionListener(this);
        
        btnLogout = new JButton("LOGOUT");
        btnLogout.setBounds(26, 550, 150, 30);
        btnLogout.setBackground(clrMagmaOrange);
        btnLogout.setFont(new Font("League Spartan", Font.BOLD, 12));
        btnLogout.addActionListener(this);
        
        //JFRAME for Main Menu
        this.setTitle("Carems - Car Rental Management System");
        this.setSize(800,600);
        this.setResizable(false);
//        this.setLayout(null);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        
        this.add(lblCarems);
        this.add(btnBookingDetail);
        this.add(btnCustomer);
        this.add(btnCar);
        this.add(btnOwner);
        this.add(btnLogout);
        this.add(panel); //the panel and lines from MainMenuJPanel
        this.pack();
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
        this.setBackground(clrAshGrey); //BG Color
           
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnCustomer){
            
        }
        else if(e.getSource() == btnBookingDetail){
        
        }
        else if(e.getSource() == btnCar){
            CarMenu carMenu = new CarMenu();
            this.dispose();
        }
        else if(e.getSource() == btnOwner){
        
        }
        else if(e.getSource() == btnLogout){
        
        }

        setTitle("Carems");
        setSize(800,600);  
        setLayout(null);  
        setLocationRelativeTo(null);
        setVisible(true);  
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
}
