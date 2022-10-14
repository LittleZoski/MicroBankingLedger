package Model;

public class Account {
    private long ID;
    private long Customer_ID;
    private double balance;
    private String name;
    private long accountNum;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public long getCustomer_ID() {
        return Customer_ID;
    }

    public void setCustomer_ID(long customer_ID) {
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

