package Model;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Account {
    private int account_ID;
    private int Customer_ID;
    private double balance;
    private String name;

    @JsonProperty("id")
    private long accountNum;

    public int getAccount_ID() {
        return account_ID;
    }

    public void setAccount_ID(int ID) {
        this.account_ID = ID;
    }

    public long getCustomer_ID() {
        return Customer_ID;
    }

    public void setCustomer_ID(int customer_ID) {
        Customer_ID = customer_ID;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(long accountNum) {
        this.accountNum = accountNum;
    }
}