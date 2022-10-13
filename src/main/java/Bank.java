import service.SimulateTransaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Bank {
    public static void main(String[] args) {

        createBankDB();
        System.out.println("Welcome to the Code School Bank of Awesomeness\n" +
                "-----------------------------------------------\n" +
                "\n" +
                "Select from the following: \n" +
                "1. Generate a Statement\n" +
                "2. Generate Accounting Reports\n" +
                "3. Simulate a transaction for Account\n" +
                "4. Create a Customer\n" +
                "5. CRUD a Customer Account\n" +
                "6. Deposit/Withdrawl funds\n" +
                "X. Exit\n");

        int selection = userInput();
        if(selection == 3){
            handleSimulatedTransaction();
        }

    }



    //create a SQLite db

    public static void createBankDB(){
        Connection conn = null;
        //ResultSet rs;
        Statement stmt = null;
        String url = "jdbc:sqlite:C:\\Users\\31243\\OneDrive\\Desktop\\Code\\MCC bootcamp formal\\bankingproject\\MicroBankLedger.db";
        try{
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);
            System.out.println("created database successfully");
            stmt = conn.createStatement();
            stmt.executeUpdate("create table Customer" +"(" +
                    "                    id int," +
                    "                    Name String," +
                    "                    DOB int," +
                    "                    PhoneNumber int," +
                    "                    StreetAddress String," +
                    "                    City String," +
                    "                    State String)");


            stmt.executeUpdate("create table Account" +"(" +
                    "                    ID long,\n" +
                    "                    Customer_ID long,\n" +
                    "                    balance double,\n" +
                    "                    name String,\n" +
                    "                    accountNum long)");

            //Transaction is a keyword for sql use a diff name
            stmt.executeUpdate("create table TransactionTable" +"(" +
                    "                    transactionId long,\n" +
                    "                    Account_ID long,\n" +
                    "                    amount double,\n" +
                    //"                    transactionDate TIMESTAMP,\n" +
                    "                    transactionType String,\n" +
                    "                    merchantName String,\n" +
                    "                    merchantType String)");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // method to take user input on option
    public static int userInput(){
        Scanner input = new Scanner(System.in);
        System.out.println("Selection: ");
        int selection = input.nextInt();
        return selection;
    }


    //method for user to input id for simulate transation

    public static void handleSimulatedTransaction(){
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter customer account number:");
        long accountNum = input.nextLong();
        SimulateTransaction simulateTransaction = new SimulateTransaction(accountNum);
        simulateTransaction.loadTransaction(accountNum);
    }

}
