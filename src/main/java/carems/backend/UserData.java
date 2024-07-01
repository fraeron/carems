package carems.backend;

import java.sql.*;

public class UserData {

//    private final String[][] sampleData = {
//        {"walterWhite", "Heisenberg"},
//        {"unSya192", "sleezyRabbit"},
//        {"RonATT", "ohRon"},
//        {"OptimumPride", "autoBots"},
//        {"Carems", "OOP"}
//    };

    public Boolean getResult(String userName, String password) {
        boolean result = false;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_carems", "root", "password123!");
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
            }catch(ClassNotFoundException | SQLException ex){
                ex.printStackTrace();
            }
        return result;
    }
    public Boolean addUser(String userName, String password, String name, String email, String contact) {
        boolean result = false;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_carems", "root", "password123!");
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
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
}