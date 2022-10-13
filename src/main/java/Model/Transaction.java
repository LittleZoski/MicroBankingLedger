package Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Time;
import java.util.Date;

@JsonIgnoreProperties(value = {"Account_ID","transactionDate"})
public class Transaction {

    public String transactionId;
    public long Account_ID;
    public double amount;
    //public String Merchant_Name;
    //public String Merchant_Type;
    public Date transactionDate;
    //public Time transactionTime;
    public String transactionType;

    public customer customer;
    public recipient recipient;


}


