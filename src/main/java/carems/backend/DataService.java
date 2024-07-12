package carems.backend;

import carems.gui.Utils;
import carems.models.*;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DataService {
    
    // No set password by default. Insert your DB password here if you have any.
    private static final String yourDBPassword = "";
    private static final String connectionString = 
            "jdbc:mysql://localhost:3306/db_carems";
    
    public static boolean isThereAFieldNull(Object object) {
        for (Field field : Object.class.getFields()) {
            try {
                if (field.get(object) == null) {
                    return true;
                }
            } catch (IllegalAccessException ex) {
            } 
        }
        return false;
    }
    
    public static ArrayList<User> users = new ArrayList(); // Admin list.    
    public static ArrayList<Customer> customers = new ArrayList();    
    public static ArrayList<Car> cars = new ArrayList();    
    public static ArrayList<Location> locations = new ArrayList();
    public static ArrayList<Book> bookings = new ArrayList();
    
    public DataService(){
        refreshData();
    }
    
    public static String getAnId(int type){
        /*
        type:
        1 -> Book
        2 -> Car
        3 -> Customer
        4 -> Location
        */
        refreshData();
        ArrayList<Integer> arr = new ArrayList();
        
        switch (type) {
            case 1 -> {
                for (Book book : bookings) {
                    arr.add(Integer.valueOf(book.id));
                }
                int result = Utils.getMissingNumber(bookings.size() + 1, arr);
                return String.valueOf(result);
            }
            case 2 -> {
                for (Car book : cars) {
                    arr.add(Integer.valueOf(book.id));
                }
                int result = Utils.getMissingNumber(cars.size() + 1, arr);
                return String.valueOf(result);
            }
            case 3 -> {
                for (Customer book : customers) {
                    arr.add(Integer.valueOf(book.id));
                }
                int result = Utils.getMissingNumber(customers.size() + 1, arr);
                return String.valueOf(result);
            }
            case 4 -> {
                for (Location book : locations) {
                    arr.add(Integer.valueOf(book.id));
                }
                int result = Utils.getMissingNumber(locations.size() + 1, arr);
                return String.valueOf(result);
            }
            default -> {
            }
        }
        return "1";
    }
    
    public static void refreshData(){
        getUsers();
        getCustomers();
        getCars();
        getLocations();
        getBookings();
    }
    
    private static boolean areRequirementsMet(Object object){
        if (isThereAFieldNull(object)){
            return false;
        }
        return true;
    }
    
    public static ArrayList<Car> getAvailableCars(){
        ArrayList<Car> available = new ArrayList();
        for (Car car : cars){
            if (car.is_available.equals("Yes")) {
                available.add(car);
            }
        }
        return available;
    }
    
    public static String[]getOngoingBooks(){
        refreshData();
        ArrayList<String> alFinal = new ArrayList();
        for (Book book : bookings) {
            if (!book.status.equals("RETURNED") && 
                    !book.status.equals("LATE RETURNED")){
                    alFinal.add(book.id);
                }
            }
        return alFinal.toArray(String[]::new);
    }
    
    public static boolean addCustomer(Customer customer){
        boolean result = false;
        if (areRequirementsMet(customer)) {
            try{
                Connection con = DriverManager.getConnection(
                        connectionString, "root", yourDBPassword);
                Field[] fields = Customer.class.getFields();
                String query = 
                        "INSERT INTO tbl_customer VALUES (";
                for (int x = 0; x < fields.length; x++) {
                    if (x == fields.length - 1){
                        query += "?)";
                    } else {
                        query += "?,";
                    }
                }
                PreparedStatement pst = con.prepareStatement(query);
                for (int i = 0; i < fields.length; i++) {
                    try{
                        pst.setString(i + 1, fields[i].get(customer).toString());
                    } catch (IllegalAccessException ex) {
                    }
                }

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
    
    public static boolean addBooking(ArrayList<String> bookingData) {
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
    
    public static boolean addBooking(Book book) {
        boolean result = false;
        try{
            Connection con = DriverManager.getConnection(
                    connectionString, "root", yourDBPassword);
            Field[] fields = Book.class.getFields();
            String query = 
                    "INSERT INTO tbl_book VALUES (";
            for (int x = 0; x < fields.length; x++) {
                if (x == fields.length - 1){
                    query += "?)";
                } else {
                    query += "?,";
                }
            }
            PreparedStatement pst = con.prepareStatement(query);
            for (int i = 0; i < fields.length; i++) {
                try{
                    pst.setString(i + 1, fields[i].get(book).toString());
                } catch (IllegalAccessException ex) {
                }
            }
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
        return result;
    }
    
    public static boolean addRecord (ArrayList<String> data, String tableName) {
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
                    if (datum.equals(data.get(data.size() - 1))) {
                        query += datum + "')";
                    }
                    else {
                        query += datum + "', '";
                    }
                }
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
    
    
    public static boolean updateRecord (ArrayList<String> data, 
            ArrayList<String> dataNames, String tableName) {
        
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).isEmpty()) {
                return false;
            }
        }
        
        try{
            Connection con = DriverManager.getConnection(
                    connectionString, "root", yourDBPassword);
            String query = 
                    "UPDATE " + tableName + " SET ";
            for (int i=1; i < data.size(); i++) {
                if (data.get(i).equals(data.get(data.size() - 1))) {
                    query += dataNames.get(i) + "='"+data.get(i) + "'";
                }
                else {
                    query += dataNames.get(i) + "='" + data.get(i) +"',";
                }
            }
            query += "WHERE id = '" + data.get(0) + "'"; // Id must always be on first index.
            PreparedStatement pst = con.prepareStatement(query);
            int rowsAffected = pst.executeUpdate();
            pst.close();
            con.close();
            return rowsAffected == 1;
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public static boolean updateRecordCar (ArrayList<String> data, 
            ArrayList<String> dataNames, String tableName) {
        
        for (int i = 0; i < data.size(); i++) {
            // Last index is always a photo for car.
            if (!data.get(i).equals(data.get(data.size() - 1))) {
                if (data.get(i).isEmpty()) {
                return false;
                }
            }
            
        }
        try{
            Connection con = DriverManager.getConnection(
                    connectionString, "root", yourDBPassword);
            String query = 
                    "UPDATE " + tableName + " SET ";
            for (int i=1; i < data.size(); i++) {
                if (data.get(i).equals(data.get(data.size() - 1))) {
                    query += dataNames.get(i) + "='"+data.get(i) + "'";
                }
                else {
                    query += dataNames.get(i) + "='" + data.get(i) +"',";
                }
            }
            query += "WHERE id = '" + data.get(0) + "'"; // Id must always be on first index.
            PreparedStatement pst = con.prepareStatement(query);
            int rowsAffected = pst.executeUpdate();
            pst.close();
            con.close();
            return rowsAffected == 1;
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public static boolean deleteData(String id, String tableName) {
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
    
    public static boolean deleteCustomer(String id) {
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
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
 
    public static boolean getResult(String userName, String password) {
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
    public static Boolean addUser(
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
    
    // START OF GETTERS FOR MODELS.
    
    public static void getLocations() {
        try{
            Connection con = DriverManager.getConnection(
                    connectionString, "root", yourDBPassword);
            String query = "SELECT * FROM tbl_location";
            Statement stat = con.createStatement();
            ResultSet results = stat.executeQuery(query);
            locations.clear();
            while (results.next()) {
                Location owner = new Location();
                owner.id = results.getString("id");
                owner.city = results.getString("city");
                owner.address = results.getString("address");
                locations.add(owner);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void getUsers(){
        try{
            Connection con = DriverManager.getConnection(
                    connectionString, "root", yourDBPassword);
            String query = "SELECT * FROM tbl_user";
            Statement stat = con.createStatement();
            ResultSet results = stat.executeQuery(query);
            users.clear();
            while (results.next()) {
                User user  = new User();
                user.username = results.getString("username"); 
                user.password  = results.getString("password"); 
                user.name = results.getString("name"); 
                user.email = results.getString("email"); 
                user.contact = results.getString("contact");
                users.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void getBookings() {
        try{
            Connection con = DriverManager.getConnection(
                    connectionString, "root", yourDBPassword);
            String query = "SELECT * FROM tbl_book";
            Statement stat = con.createStatement();
            ResultSet results = stat.executeQuery(query);
            bookings.clear();
            while (results.next()) {
                Book booking = new Book();
                booking.id = results.getString("id");                    
                booking.booked_car_id = results.getString("booked_car_id");
                booking.customer_id = results.getString("customer_id");
                booking.booked_datetime = results.getString("booked_datetime");
                booking.return_datetime = results.getString("return_datetime");
                booking.status = results.getString("status");
                bookings.add(booking);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void getCustomers() {
        try{
            Connection con = DriverManager.getConnection(
                    connectionString, "root", yourDBPassword);
            String query = "SELECT * FROM tbl_customer";
            Statement stat = con.createStatement();
            ResultSet results = stat.executeQuery(query);
            customers.clear();
            while (results.next()) {
                Customer customer  = new Customer();
                customer.id = results.getString("id");
                customer.name = results.getString("name");                
                customer.drivers_license_id = results.getString("drivers_license_id");
                customer.credit_card_no = results.getString("credit_card_no");
                customers.add(customer);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void getCars() {
        try{
            Connection con = DriverManager.getConnection(
                    connectionString, "root", yourDBPassword);
            String query = "SELECT * FROM tbl_car";
            Statement stat = con.createStatement();
            ResultSet results = stat.executeQuery(query);
            cars.clear();
            while (results.next()) {
                Car car = new Car();
                car.id = results.getString("id");
                car.model = results.getString("model");
                car.color = results.getString("color");
                car.license_plate = results.getString("license_plate");
                car.category = results.getString("category");
                car.fuel_type = results.getString("fuel_type");
                car.is_available = results.getString("is_available");
                car.car_condition = results.getString("car_condition");
                car.vin = results.getString("vin");
                car.price_per_day = results.getString("price_per_day");
                car.engine = results.getString("engine");
                car.oil = results.getString("oil");
                car.air = results.getString("air");
                car.coolant = results.getString("coolant");
                car.brake = results.getString("brake");
                car.tire = results.getString("tire");
                car.belt = results.getString("belt");
                car.steer = results.getString("steer");
                car.chassis = results.getString("chassis");
                car.photo_filepath = results.getString("photo_filepath");
                cars.add(car);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    /// END OF GETTERS FOR MODELS.
    
    // START OF GETTER FOR SINGLE MODELS.
    public static Car getCar(String carId) {
        Car car = new Car();
        try{
            Connection con = DriverManager.getConnection(
                    connectionString, "root", yourDBPassword);
            String query = "SELECT * FROM tbl_car WHERE id = '" + carId + "'";
            Statement stat = con.createStatement();
            ResultSet results = stat.executeQuery(query);
            while (results.next()) {
                car.id = results.getString("id");
                car.model = results.getString("model");
                car.color = results.getString("color");
                car.license_plate = results.getString("license_plate");
                car.category = results.getString("category");
                car.fuel_type = results.getString("fuel_type");
                car.is_available = results.getString("is_available");
                car.car_condition = results.getString("car_condition");
                car.price_per_day = results.getString("price_per_day");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return car;
    }
    
    public static Book getBooking(String bookid) {
        Book booking = new Book();
        try{
            Connection con = DriverManager.getConnection(
                    connectionString, "root", yourDBPassword);
            String query = "SELECT * FROM tbl_book WHERE id = '" + bookid + "'";
            Statement stat = con.createStatement();
            ResultSet results = stat.executeQuery(query);
            while (results.next()) {
                booking.id = results.getString("id");                    
                booking.booked_car_id = results.getString("booked_car_id");
                booking.customer_id = results.getString("customer_id");
                booking.booked_datetime = results.getString("booked_datetime");
                booking.return_datetime = results.getString("return_datetime");
                booking.status = results.getString("status");
                bookings.add(booking);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return booking;
    }
    
    public static Customer getCustomer(String cusId) {
        Customer customer = new Customer();
        try{
            Connection con = DriverManager.getConnection(
                    connectionString, "root", yourDBPassword);
            String query = "SELECT * FROM tbl_customer WHERE id='"+ cusId + "'";
            Statement stat = con.createStatement();
            ResultSet results = stat.executeQuery(query);
            while (results.next()) {
                customer.id = results.getString("id");
                customer.name = results.getString("name");                
                customer.drivers_license_id = results.getString("drivers_license_id");
                customer.credit_card_no = results.getString("credit_card_no");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return customer;
    }
}