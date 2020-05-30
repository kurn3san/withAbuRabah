package Home.model;

public class Employee {
    private String firstName;
    private String lastName;
    private String username;
    private int level;
    private String jobinfo;

    public Employee(String firstName, String lastName, String username, int level, String jobinfo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.level = level;
        this.jobinfo = jobinfo;
    }
    public Employee() {

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getJobinfo() {
        return jobinfo;
    }

    public void setJobinfo(String jobinfo) {
        this.jobinfo = jobinfo;
    }

    public String toString() {
        return "Username: " + this.getUsername() + " First name: " + this.getFirstName() + " Last name: " + this.getLastName() + " Level: " + this.getLevel();
    }
}
