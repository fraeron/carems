package carems.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DataService {
    
    // No set password by default. Insert your DB password here if you have any.
    // Gelo's password -> password123!
    private final String yourDBPassword = "";
    private final String connectionString = 
            "jdbc:mysql://localhost:3306/db_carems";
    
    public boolean addCustomer(String id, String name, String carRentId) {
        boolean result = false;
        if (!(id.isEmpty() && name.isEmpty() && carRentId.isEmpty())) {
            try{
                Connection con = DriverManager.getConnection(
                        connectionString, "root", yourDBPassword);
                String query = 
                        "INSERT INTO tbl_customer VALUES (?, ?, ?)";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, id);
                pst.setString(2, name);
                pst.setString(3, carRentId);

                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    result = true; // Insertion successful
                } else {
                    result = false; // Insertion failed
                }
                pst.close();
                con.close();
            } 
            catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
    
    public boolean addBooking(ArrayList<String> bookingData) {
        boolean result = false;
        boolean emptyBooking = false;
        for (int i = 0; i < bookingData.size(); i++) {
            if (bookingData.get(i).isEmpty()) {
                emptyBooking = true;
            }
        }
        if (!emptyBooking) {
            try{
                Connection con = DriverManager.getConnection(
                        connectionString, "root", yourDBPassword);
                String query = 
                        "INSERT INTO tbl_book VALUES ('";
                for (String data : bookingData) {
                    if (data.equals(bookingData.get(bookingData.size() - 1))) {
                        query += data + "')";
                    }
                    else {
                        query += data + "', '";
                    }
                }
                System.out.println(query);
                PreparedStatement pst = con.prepareStatement(query);
                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    result = true; // Insertion successful
                } else {
                    result = false; // Insertion failed
                }
                pst.close();
                con.close();
            } 
            catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
    
    public boolean addRecord (ArrayList<String> data, String tableName) {
        boolean result = false;
        boolean emptyBooking = false;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).isEmpty()) {
                emptyBooking = true;
            }
        }
        if (!emptyBooking) {
            try{
                Connection con = DriverManager.getConnection(
                        connectionString, "root", yourDBPassword);
                String query = 
                        "INSERT INTO " + tableName + " VALUES ('";
                for (String datum : data) {
                    if (data.equals(data.get(data.size() - 1))) {
                        query += data + "')";
                    }
                    else {
                        query += data + "', '";
                    }
                }
                System.out.println(query);
                PreparedStatement pst = con.prepareStatement(query);
                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    result = true; // Insertion successful
                } else {
                    result = false; // Insertion failed
                }
                pst.close();
                con.close();
            } 
            catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
    
    
    public String[][] getBookings() {
        String[][] data = {};
        try{
            Connection con = DriverManager.getConnection(
                    connectionString, "root", yourDBPassword);
            String query = "SELECT * FROM tbl_book";
            Statement stat = con.createStatement();
            ResultSet results = stat.executeQuery(query);
            ArrayList<String[]> tempArray = new ArrayList();
            while (results.next()) {
                String[] tempList = {
                    results.getString("id"),                    
                    results.getString("booked_car_id"),
                    results.getString("customer_id"),
                    results.getString("booked_datetime"),
                    results.getString("return_datetime"),
                    results.getString("status_datetime")
                };
                tempArray.add(tempList);
            }
            data = tempArray.toArray(String[][]::new);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return data;
    }
    
    public String[][] getOwners() {
        String[][] data = {};
        try{
            Connection con = DriverManager.getConnection(
                    connectionString, "root", yourDBPassword);
            String query = "SELECT * FROM tbl_owner";
            Statement stat = con.createStatement();
            ResultSet results = stat.executeQuery(query);
            ArrayList<String[]> tempArray = new ArrayList();
            while (results.next()) {
                String[] tempList = {
                    results.getString("id"),                    
                    results.getString("name"),
                    results.getString("car")
                };
                tempArray.add(tempList);
            }
            data = tempArray.toArray(String[][]::new);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return data;
    }
    
    public String[][] getCars() {
        String[][] data = {};
        try{
            Connection con = DriverManager.getConnection(
                    connectionString, "root", yourDBPassword);
            String query = "SELECT * FROM tbl_car";
            Statement stat = con.createStatement();
            ResultSet results = stat.executeQuery(query);
            ArrayList<String[]> tempArray = new ArrayList();
            while (results.next()) {
                String[] tempList = {
                    results.getString("model"),
                    results.getString("color"),
                    results.getString("license_plate"),
                    results.getString("category"),
                    results.getString("fuel_type"),
                    results.getString("is_available"),
                    results.getString("car_condition")
                };
                tempArray.add(tempList);
            }
            data = tempArray.toArray(String[][]::new);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return data;
    }
    
    public boolean deleteData(String id, String tableName) {
        boolean result = false;
        try{
            Connection con = DriverManager.getConnection(
                    connectionString, "root", yourDBPassword);
            String query = 
                    "DELETE FROM " + tableName +" WHERE (id=?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, id);

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                result = true; // Insertion successful
            } else {
                result = false; // Insertion failed
            }
            pst.close();
            con.close();
            System.out.println("finished");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("failed");
        }
        return result;
    }
    
    public boolean deleteCustomer(String id) {
        boolean result = false;
        try{
            Connection con = DriverManager.getConnection(
                    connectionString, "root", yourDBPassword);
            String query = 
                    "DELETE FROM tbl_customer WHERE (id=?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, id);

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                result = true; // Insertion successful
            } else {
                result = false; // Insertion failed
            }
            pst.close();
            con.close();
            System.out.println("finished");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("failed");
        }
        return result;
    }
 
    public String[][] getCustomers() {
        String[][] customers = {};
        try{
            Connection con = DriverManager.getConnection(
                    connectionString, "root", yourDBPassword);
            String query = "SELECT * FROM tbl_customer";
            Statement stat = con.createStatement();
            ResultSet results = stat.executeQuery(query);
            ArrayList<String[]> tempArray = new ArrayList();
            while (results.next()) {
                String[] tempList = {
                    results.getString("id"),
                    results.getString("name"),
                    results.getString("rented_car_id")
                };
                tempArray.add(tempList);
            }
            customers = tempArray.toArray(String[][]::new);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return customers;
    }
    
    public boolean getResult(String userName, String password) {
        boolean result = false;
        try{
            Connection con = DriverManager.getConnection(
                    connectionString, 
                    "root", yourDBPassword); 
            String query = "SELECT username, password FROM tbl_user WHERE username=? and password=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, userName);
            pst.setString(2, password);
            
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                result = true;
            } else {
                result = false;
            }
            rs.close();
            pst.close();
            con.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        return result;
    }
    public Boolean addUser(
            String userName, String password, 
            String name, String email, String contact) {
        boolean result = false;
        try{
            Connection con = DriverManager.getConnection(
                    connectionString, "root", yourDBPassword);
            String query = 
                    "INSERT INTO tbl_user (username, password, name, email, contact) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, userName);
            pst.setString(2, password);
            pst.setString(3, name);
            pst.setString(4, email);
            pst.setString(5, contact);

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                result = true; // Insertion successful
            } else {
                result = false; // Insertion failed
            }
            pst.close();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
}