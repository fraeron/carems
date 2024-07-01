package carems.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DataService {
    
    // No set password by default. Insert your DB password here if you have any.
    // Gelo -> password123!
    private final String yourDBPassword = "";

    public Boolean getResult(String userName, String password) {
        boolean result = false;
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_carems", "root", yourDBPassword); 
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
    public Boolean addUser(String userName, String password, String name, String email, String contact) {
        boolean result = false;
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_carems", "root", yourDBPassword);
            String query = "INSERT INTO tbl_user (username, password, name, email, contact) VALUES (?, ?, ?, ?, ?)";
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