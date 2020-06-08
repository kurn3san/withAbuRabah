package Home.model;

public class Company {
    private String name;
    private String Address;

    //private int companyID;
    public Company() {

    }

    public Company(String name, String address) {
        this.setAddress(address);
        this.setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
