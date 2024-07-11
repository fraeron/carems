//package carems.backend;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//
//
//import carems.models.*;
//
//public class DataServiceMVC {
//    
//    // No set password by default. Insert your DB password here if you have any.
//    private final String yourDBPassword = "";
//    private final String connectionString = 
//            "jdbc:mysql://localhost:3306/db_carems";
//    
//    public ArrayList<User> users = new ArrayList(); // Admin list.    
//    public ArrayList<Customer> customers = new ArrayList();    
//    public ArrayList<Car> cars = new ArrayList();    
//    public ArrayList<Owner> owners = new ArrayList();
//    public ArrayList<Book> bookings = new ArrayList();
//    
//    
//    public DataServiceMVC(){
//        // Init.
//        getUsers();
//        getCustomers();
//        getCars();
//        getOwners();
//        getBookings(); // tbl_books
//    }
//
//    public boolean addCustomer(String id, String name, String carRentId) {
//        boolean result = false;
//        if (!(id.isEmpty() && name.isEmpty() && carRentId.isEmpty())) {
//            try{
//                Connection con = DriverManager.getConnection(
//                        connectionString, "root", yourDBPassword);
//                String query = 
//                        "INSERT INTO tbl_customer VALUES (?, ?, ?)";
//                PreparedStatement pst = con.prepareStatement(query);
//                pst.setString(1, id);
//                pst.setString(2, name);
//                pst.setString(3, carRentId);
//
//                int rowsAffected = pst.executeUpdate();
//                if (rowsAffected > 0) {
//                    result = true; // Insertion successful
//                } else {
//                    result = false; // Insertion failed
//                }
//                pst.close();
//                con.close();
//            } 
//            catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//        }
//        return result;
//    }
//
//    public boolean addBooking(ArrayList<String> bookingData) {
//        boolean result = false;
//        boolean emptyBooking = false;
//        for (int i = 0; i < bookingData.size(); i++) {
//            if (bookingData.get(i).isEmpty()) {
//                emptyBooking = true;
//            }
//        }
//        if (!emptyBooking) {
//            try{
//                Connection con = DriverManager.getConnection(
//                        connectionString, "root", yourDBPassword);
//                String query = 
//                        "INSERT INTO tbl_book VALUES ('";
//                for (String data : bookingData) {
//                    if (data.equals(bookingData.get(bookingData.size() - 1))) {
//                        query += data + "')";
//                    }
//                    else {
//                        query += data + "', '";
//                    }
//                }
//                System.out.println(query);
//                PreparedStatement pst = con.prepareStatement(query);
//                int rowsAffected = pst.executeUpdate();
//                if (rowsAffected > 0) {
//                    result = true; // Insertion successful
//                } else {
//                    result = false; // Insertion failed
//                }
//                pst.close();
//                con.close();
//            } 
//            catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//        }
//        return result;
//    }
//    
//    public boolean addRecord (ArrayList<String> data, String tableName) {
//        boolean result = false;
//        boolean emptyBooking = false;
//        for (int i = 0; i < data.size(); i++) {
//            if (data.get(i).isEmpty()) {
//                emptyBooking = true;
//            }
//        }
//        if (!emptyBooking) {
//            try{
//                Connection con = DriverManager.getConnection(
//                        connectionString, "root", yourDBPassword);
//                String query = 
//                        "INSERT INTO " + tableName + " VALUES ('";
//                for (String datum : data) {
//                    if (data.equals(data.get(data.size() - 1))) {
//                        query += data + "')";
//                    }
//                    else {
//                        query += data + "', '";
//                    }
//                }
//                System.out.println(query);
//                PreparedStatement pst = con.prepareStatement(query);
//                int rowsAffected = pst.executeUpdate();
//                if (rowsAffected > 0) {
//                    result = true; // Insertion successful
//                } else {
//                    result = false; // Insertion failed
//                }
//                pst.close();
//                con.close();
//            } 
//            catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//        }
//        return result;
//    }
//
//    public boolean deleteData(String id, String tableName) {
//        boolean result = false;
//        try{
//            Connection con = DriverManager.getConnection(
//                    connectionString, "root", yourDBPassword);
//            String query = 
//                    "DELETE FROM " + tableName +" WHERE (id=?)";
//            PreparedStatement pst = con.prepareStatement(query);
//            pst.setString(1, id);
//
//            int rowsAffected = pst.executeUpdate();
//            if (rowsAffected > 0) {
//                result = true; // Insertion successful
//            } else {
//                result = false; // Insertion failed
//            }
//            pst.close();
//            con.close();
//            System.out.println("finished");
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            System.out.println("failed");
//        }
//        return result;
//    }
//    
//    public boolean deleteCustomer(String id) {
//        boolean result = false;
//        try{
//            Connection con = DriverManager.getConnection(
//                    connectionString, "root", yourDBPassword);
//            String query = 
//                    "DELETE FROM tbl_customer WHERE (id=?)";
//            PreparedStatement pst = con.prepareStatement(query);
//            pst.setString(1, id);
//
//            int rowsAffected = pst.executeUpdate();
//            if (rowsAffected > 0) {
//                result = true; // Insertion successful
//            } else {
//                result = false; // Insertion failed
//            }
//            pst.close();
//            con.close();
//            System.out.println("finished");
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            System.out.println("failed");
//        }
//        return result;
//    }
// 
//
//    public boolean getResult(String userName, String password) {
//        boolean result = false;
//        try{
//            Connection con = DriverManager.getConnection(
//                    connectionString, 
//                    "root", yourDBPassword); 
//            String query = "SELECT username, password FROM tbl_user WHERE username=? and password=?";
//            PreparedStatement pst = con.prepareStatement(query);
//            pst.setString(1, userName);
//            pst.setString(2, password);
//            
//            ResultSet rs = pst.executeQuery();
//            if (rs.next()) {
//                result = true;
//            } else {
//                result = false;
//            }
//            rs.close();
//            pst.close();
//            con.close();
//            }catch(SQLException ex){
//                ex.printStackTrace();
//            }
//        return result;
//    }
//    public Boolean addUser(
//            String userName, String password, 
//            String name, String email, String contact) {
//        boolean result = false;
//        try{
//            Connection con = DriverManager.getConnection(
//                    connectionString, "root", yourDBPassword);
//            String query = 
//                    "INSERT INTO tbl_user (username, password, name, email, contact) VALUES (?, ?, ?, ?, ?)";
//            PreparedStatement pst = con.prepareStatement(query);
//            pst.setString(1, userName);
//            pst.setString(2, password);
//            pst.setString(3, name);
//            pst.setString(4, email);
//            pst.setString(5, contact);
//
//            int rowsAffected = pst.executeUpdate();
//            if (rowsAffected > 0) {
//                result = true; // Insertion successful
//            } else {
//                result = false; // Insertion failed
//            }
//            pst.close();
//            con.close();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return result;
//    }
//    
//    
//    // START OF GETTERS FOR MODELS.
//    
//    public void getOwners() {
//        try{
//            Connection con = DriverManager.getConnection(
//                    connectionString, "root", yourDBPassword);
//            String query = "SELECT * FROM tbl_owner";
//            Statement stat = con.createStatement();
//            ResultSet results = stat.executeQuery(query);
//            while (results.next()) {
//                Owner owner = new Owner();
//                owner.id = results.getString("id");
//                owner.name = results.getString("name");
//                owner.car = results.getString("car");
//                owners.add(owner);
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    }
//    
//    public void getUsers(){
//        
//        try{
//            Connection con = DriverManager.getConnection(
//                    connectionString, "root", yourDBPassword);
//            String query = "SELECT * FROM tbl_user";
//            Statement stat = con.createStatement();
//            ResultSet results = stat.executeQuery(query);
//            while (results.next()) {
//                User user  = new User();
//                user.username = results.getString("username"); 
//                user.password  = results.getString("password"); 
//                user.name = results.getString("name"); 
//                user.email = results.getString("email"); 
//                user.contact = results.getString("contact");
//                users.add(user);
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    }
//    
//    public void getBookings() {
//        ArrayList<Book> bookings = new ArrayList();
//        try{
//            Connection con = DriverManager.getConnection(
//                    connectionString, "root", yourDBPassword);
//            String query = "SELECT * FROM tbl_book";
//            Statement stat = con.createStatement();
//            ResultSet results = stat.executeQuery(query);
//            while (results.next()) {
//                Book booking = new Book();
//                booking.id = results.getString("id");                    
//                booking.booked_car_id = results.getString("booked_car_id");
//                booking.customer_id = results.getString("customer_id");
//                booking.booked_datetime = results.getString("booked_datetime");
//                booking.return_datetime = results.getString("return_datetime");
//                booking.status = results.getString("status");
//                bookings.add(booking);
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    }
//    
//    public void getCustomers() {
//        try{
//            Connection con = DriverManager.getConnection(
//                    connectionString, "root", yourDBPassword);
//            String query = "SELECT * FROM tbl_customer";
//            Statement stat = con.createStatement();
//            ResultSet results = stat.executeQuery(query);
//            while (results.next()) {
//                Customer customer  = new Customer();
//                customer.id = results.getString("id");
//                customer.name = results.getString("name");
//                customer.rented_car_id = results.getString("rented_car_id");
//                customers.add(customer);
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    }
//    
//    public void getCars() {
//        try{
//            Connection con = DriverManager.getConnection(
//                    connectionString, "root", yourDBPassword);
//            String query = "SELECT * FROM tbl_car";
//            Statement stat = con.createStatement();
//            ResultSet results = stat.executeQuery(query);
//            while (results.next()) {
//                Car car = new Car();
//                car.model = results.getString("model");
//                car.model = results.getString("color");
//                car.model = results.getString("license_plate");
//                car.model = results.getString("category");
//                car.model = results.getString("fuel_type");
//                car.model = results.getString("is_available");
//                car.model = results.getString("car_condition");
//                cars.add(car);
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    }
//    /// END OF GETTERS FOR MODELS.
//}