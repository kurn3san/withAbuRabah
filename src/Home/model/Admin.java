package Home.model;

public class Admin {

    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private int level;

    public Admin(String firstName, String lastName, String userName, String password, int level) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.level = 1;
    }

    public Admin() {
        this.level = 1;

    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String toString() {
        return "First name = " + this.getFirstName() + " Last name = " +
                this.getLastName() + " Admin name = " + this.getUserName() +
                " Access level = " + this.getLevel() + " .";
    }
}
