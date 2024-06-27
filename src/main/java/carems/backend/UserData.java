package carems.backend;

public class UserData {

    private final String[][] sampleData = {
        {"walterWhite", "Heisenberg"},
        {"unSya192", "sleezyRabbit"},
        {"RonATT", "ohRon"},
        {"OptimumPride", "autoBots"},
        {"Carems", "OOP"}
    };

    public Boolean getResult(String userName, String password) {
        for (String[] user : sampleData) {
            if (user[0].equalsIgnoreCase(userName) && user[1].equals(password)) {
                return true;
            }
        }
        return false;
    }

}