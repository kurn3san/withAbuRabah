package Home.model;

import java.time.LocalDate;

public class Employee {
    private String firstName;
    private String lastName;
    private String username;
    private int level;
    private String title;
    private LocalDate certificateDate;

    public Employee(String firstName, String lastName, String username, int level, String title, LocalDate CDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.level = level;
        this.title = title;
        this.certificateDate = CDate;
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
        return "Username: " + this.getUsername() +
                " First name: " + this.getFirstName() +
                " Last name: " + this.getLastName() +
                " Level: " + this.getLevel() +
                "Certificate date: " + this.getCertificateDate();
    }

    public LocalDate getCertificateDate() {
        return certificateDate;
    }

    public void setCertificateDate(LocalDate certificateDate) {
        this.certificateDate = certificateDate;
    }
    /*public StringProperty lastNameProperty() {
        return lastname;
    }

    public Object getDateOfBirth() {
        return dateofbirth.get();
    }

    public void setDateOfBirth(LocalDate dateofbirth) {
        this.dateofbirth.set(dateofbirth);
    }

    public ObjectProperty<LocalDate> dateOfBirthProperty() {
        return dateofbirth;
    }*/
}
