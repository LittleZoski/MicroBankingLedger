package Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Time;
import java.util.Date;

public class Transaction {

    public String ID;
    public long Account_ID;
    public double amount;
    public String Merchant_Name;
    public String Merchant_Type;
    public Date transactionDate;
    public String transactionType;


}


