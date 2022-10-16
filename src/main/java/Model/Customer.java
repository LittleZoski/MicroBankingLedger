package Model;


import java.util.Date;

//@JsonIgnoreProperties(value = {"Name","DOB","PhoneNumber","StreetAddress", "City","State","ZipCode","CreateDate"})
public class Customer {
    private int id;
    private String Name;
    private Date DOB;
    private String PhoneNumber;
    private String StreetAddress;
    private String City;
    private String State;
    private int ZipCode;
    private int CreatedDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getStreetAddress() {
        return StreetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        StreetAddress = streetAddress;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public int getZipCode() {
        return ZipCode;
    }

    public void setZipCode(int zipCode) {
        ZipCode = zipCode;
    }

    public int getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(int createdDate) {
        CreatedDate = createdDate;
    }
}
