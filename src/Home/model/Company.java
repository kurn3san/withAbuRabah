package Home.model;
import java.sql.Date;
import java.util.Objects;

public class Company {
    private String name = "";
    private String address = "";
    private Date registerDate;

    //private int companyID;
    public Company() {
    }

    public Company(String name, String address, Date registerDate) {
        this.setAddress(address);
        this.setName(name);
        this.setRegisterDate(registerDate);
    }

    @Override
    public String toString() {
        return " Company: " +
                "name=' " + name +
                "', Address='" + address +
                "', registerDate=" + registerDate + "'.";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company)) return false;
        Company company = (Company) o;
        if (!this.getRegisterDate().equals(null)) {
            return getName().equals(company.getName()) &&
                    getAddress().equals(company.getAddress()) &&
                    getRegisterDate().equals(company.getRegisterDate());
        } else
            return getName().equals(company.getName()) &&
                    getAddress().equals(company.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAddress(), getRegisterDate());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

}
