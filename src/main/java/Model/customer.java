package Model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"Name","DOB","PhoneNumber","StreetAddress", "City","State","ZipCode","CreateDate"})
public class customer {
    private String id;
    private String Name;
    private int DOB;
    private int PhoneNumber;
    private String StreetAddress;
    private String City;
    private String State;
    private int ZipCode;
    private int CreatedDate;



}
