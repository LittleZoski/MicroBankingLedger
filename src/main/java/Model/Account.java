package Model;

public class Account {
    private int ID;
    private int Customer_ID;
    private double balance;
    private String PhoneNumber;
    private String StreetAddress;
    private String City;
    private String State;
    private String ZipCode;
    private String CreatedDate;



    public int getID() {
        return ID;
    }

    public int getCustomer_ID() {
        return Customer_ID;
    }

    public double getBalance() {
        return balance;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public String getStreetAddress() {
        return StreetAddress;
    }

    public String getCity() {
        return City;
    }

    public String getState() {
        return State;
    }

    public String getZipCode() {
        return ZipCode;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setCustomer_ID(int customer_ID) {
        Customer_ID = customer_ID;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public void setStreetAddress(String streetAddress) {
        StreetAddress = streetAddress;
    }

    public void setCity(String city) {
        City = city;
    }

    public void setState(String state) {
        State = state;
    }

    public void setZipCode(String zipCode) {
        ZipCode = zipCode;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }
}

