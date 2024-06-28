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
            String query = "SELECT userName, password FROM tbl_user WHERE username=? and password=?";
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

}