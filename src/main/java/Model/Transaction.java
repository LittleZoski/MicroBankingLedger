package Model;

import java.sql.Time;
import java.util.Date;


public class Transaction {

    public long transactionId;
    public long Account_ID;
    public double amount;
    //public String Merchant_Name;
    //public String Merchant_Type;
    public Date transactionDate;
    //public Time transactionTime;
    public Enum transactionType;

    public Customer customer;
    public recipient recipient;


}


