package Home.model;

import java.time.LocalDate;

public class Employee {
    private String firstName;
    private String lastName;
    private String username;
    private int level;
    private String title;
    private LocalDate certificateDate;
    private String password;


    public Employee(String firstName, String lastName, String username, int level, String title, LocalDate CDate, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.level = level;
        this.title = title;
        this.certificateDate = CDate;
        this.password = password;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String toString() {
        return ", username: " + this.getUsername() +
                ", first name: " + this.getFirstName() +
                ", last name: " + this.getLastName() +
                ", Level: " + this.getLevel() +
                ", Certificate date: " + this.getCertificateDate() + " .";
    }

    public LocalDate getCertificateDate() {
        return certificateDate;
    }

    public void setCertificateDate(LocalDate certificateDate) {
        this.certificateDate = certificateDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /////add equals method....
    public boolean equals(Employee employee) {
        return this.getUsername() == employee.getUsername();
    }
}
