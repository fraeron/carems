package carems.SampleData;

public class UserData {
    private final String[] sampleHeader = {
        "Username", "Password"};

    private final String[][] sampleData = {
        {"walterWhite", "Heisenberg"},
        {"unSya192", "sleezyRabbit"},
        {"RonATT", "ohRon"},
        {"OptimumPride", "autoBots"}
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