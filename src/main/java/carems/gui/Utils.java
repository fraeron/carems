package carems.gui;

import carems.models.*;
import java.awt.Font;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class Utils {
    public static Font getFont(int size){
        return new Font("Arial", Font.PLAIN, size);
    }
    
    public static int getMissingNumber (int target, ArrayList<Integer> arr) {
        int[] hash = new int[target+1];
        
        for (int i = 0; i < target - 1; i++) {
            hash[arr.get(i)]++;
        }
        for (int i = 1; i <= target; i++) {
            if (hash[i] == 0) {
                return i;
            }
        }
        return -1;
    }
    
    public static ArrayList<String> toArrayString(Car data){
        ArrayList<String> result = new ArrayList();
        Field[] fields = Car.class.getFields();
        for (int i = 0; i < fields.length; i++) {
            try {
                result.add(fields[i].get(data).toString());
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                System.out.println(ex);
            }
        }
        return result;
    }
    
    public static ArrayList<String> toArrayStringKeys(Car data){
        ArrayList<String> result = new ArrayList();
        Field[] fields = Car.class.getFields();
        for (int i = 0; i < fields.length; i++) {
            try {
                result.add(fields[i].getName());
            } catch (IllegalArgumentException ex) {
                System.out.println(ex);
            }
        }
        return result;
    }
    
    public static ArrayList<String> toArrayString(Customer data){
        ArrayList<String> result = new ArrayList();
        Field[] fields = Customer.class.getFields();
        for (int i = 0; i < fields.length; i++) {
            try {
                result.add(fields[i].get(data).toString());
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                System.out.println(ex);
            }
        }
        return result;
    }
    
    public static ArrayList<String> toArrayStringKeys(Customer data){
        ArrayList<String> result = new ArrayList();
        Field[] fields = Customer.class.getFields();
        for (int i = 0; i < fields.length; i++) {
            try {
                result.add(fields[i].getName());
            } catch (IllegalArgumentException ex) {
                System.out.println(ex);
            }
        }
        return result;
    }
    
    
    public static ArrayList<String> toArrayString(Book data){
        ArrayList<String> result = new ArrayList();
        Field[] fields = Book.class.getFields();
        for (int i = 0; i < fields.length; i++) {
            try {
                result.add(fields[i].get(data).toString());
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                System.out.println(ex);
            }
        }
        return result;
    }
    
    public static ArrayList<String> toArrayStringKeys(Book data){
        ArrayList<String> result = new ArrayList();
        Field[] fields = Book.class.getFields();
        for (int i = 0; i < fields.length; i++) {
            try {
                result.add(fields[i].getName());
            } catch (IllegalArgumentException ex) {
                System.out.println(ex);
            }
        }
        return result;
    }
    
    public static String[][] unpackUser(ArrayList<User> objects) {
        ArrayList<String[]> alObjects = new ArrayList();
        Field[] fields = User.class.getFields();
        for (int x = 0; x < objects.size(); x++) {
            String[] tempArray = new String[fields.length];
            for (int i = 0; i < fields.length; i++) {
                    try {
                    tempArray[i] = fields[i].get(objects.get(x)).toString();
                    } catch (IllegalArgumentException | IllegalAccessException ex) {
                        System.out.println(ex);
                    }
                }
            alObjects.add(tempArray);
        }  
        return alObjects.toArray(String[][]::new);
    }
    
    public static String[][] unpackCar(ArrayList<Car> objects) {
        ArrayList<String[]> alObjects = new ArrayList();
        Field[] fields = Car.class.getFields();
        for (int x = 0; x < objects.size(); x++) {
            String[] tempArray = new String[fields.length];
            
            for (int i = 0; i < fields.length; i++) {
                try {
                    tempArray[i] = fields[i].get(objects.get(x)).toString();

                } catch (IllegalArgumentException | IllegalAccessException ex) {
                    System.out.println(ex);
                }
            }
            alObjects.add(tempArray);
        }  
        return alObjects.toArray(String[][]::new);
    }
    public static String[][] unpackCustomer(ArrayList<Customer> objects) {
        ArrayList<String[]> alObjects = new ArrayList();
        Field[] fields = Customer.class.getFields();
        for (int x = 0; x < objects.size(); x++) {
            String[] tempArray = new String[fields.length];
            for (int i = 0; i < fields.length; i++) {
                try {
                    tempArray[i] = fields[i].get(objects.get(x)).toString();

                } catch (IllegalArgumentException | IllegalAccessException ex) {
                    System.out.println(ex);
                }
            }
            alObjects.add(tempArray);
        }  
        return alObjects.toArray(String[][]::new);
    }
    public static String[][] unpackBook(ArrayList<Book> objects) {
        ArrayList<String[]> alObjects = new ArrayList();
        Field[] fields = Book.class.getFields();
        for (int x = 0; x < objects.size(); x++) {
            String[] tempArray = new String[fields.length];
            for (int i = 0; i < fields.length; i++) {
                try {
                    tempArray[i] = fields[i].get(objects.get(x)).toString();

                } catch (IllegalArgumentException | IllegalAccessException ex) {
                    System.out.println(ex);
                }
            }
            alObjects.add(tempArray);
        }  
        return alObjects.toArray(String[][]::new);
    }
    public static String[][] unpackLocation(ArrayList<Location> objects) {
        ArrayList<String[]> alObjects = new ArrayList();
        Field[] fields = Location.class.getFields();
        for (int x = 0; x < objects.size(); x++) {
            String[] tempArray = new String[fields.length];
            for (int i = 0; i < fields.length; i++) {
                try {
                    tempArray[i] = fields[i].get(objects.get(x)).toString();

                } catch (IllegalArgumentException | IllegalAccessException ex) {
                    System.out.println(ex);
                }
            }
            alObjects.add(tempArray);
        }  
        return alObjects.toArray(String[][]::new);
    }
    
    
    public static String[] getTimeSet() {
        String[] time = new String[24];
        time[0] = "12:00 AM";
        for (int i = 1; i < 24; i++) {
            if (i < 12 ) time[i] = i +":00 AM";
            else time[i] = i +":00 PM";
        }
        
        return time;
    }
}
