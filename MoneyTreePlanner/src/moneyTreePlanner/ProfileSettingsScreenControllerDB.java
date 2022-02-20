package moneyTreePlanner;

public class ProfileSettingsScreenControllerDB {

    private String fName;
    private String lName;
    private String password;

    public ProfileSettingsScreenControllerDB(String fName, String lName, String password) {
        this.fName = fName;
        this.lName = lName;
        this.password = password;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getPassword() {
        return password;
    }
}
