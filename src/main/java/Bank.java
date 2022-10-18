import service.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Bank {
    public static void main(String[] args) {

        createBankDB();
        String selection = "0";
        while(!selection.equalsIgnoreCase("X")){
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

            selection = userInput();

            if(selection.equals("3")){
                SimulateTransaction.handleSimulatedTransaction();
            } else if (selection.equalsIgnoreCase("4")){
                CustomerCreation.customerCreation();
            } else if (selection.equalsIgnoreCase("5")){
                CustomerAccountService.SelectionPromptCrudAccount();
            } else if (selection.equalsIgnoreCase("6")){
                CustomerAccountActivity.SelectionPromptAccountFunds();
            } else if (selection.equalsIgnoreCase("1")){
                GenerateBalanceReport.StatementPromptLoop();
            } else if(selection.equalsIgnoreCase("2")){
                //GenerateStatementReport;
            }
        }

    }



    //create a SQLite db

    public static void createBankDB(){
        Connection conn = null;
        //ResultSet rs;
        Statement stmt = null;
        //Connor's String url = "jdbc:sqlite:C:\\Users\\bta91388\\IdeaProjects\\bankingproject\\MicroBankLedger.db";
        //Frank's String url = "jdbc:sqlite:C:\\Users\\31243\\OneDrive\\Desktop\\Code\\MCC bootcamp formal\\bankingproject\\MicroBankLedger.db";
        String url = "jdbc:sqlite:C:\\Users\\31243\\OneDrive\\Desktop\\Code\\MCC bootcamp formal\\bankingproject\\MicroBankLedger.db";
        try{
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);
            System.out.println("Created database successfully");
            stmt = conn.createStatement();
            stmt.executeUpdate("create table if not exists Customer" +"(" +
                           // in SQLite AUTOINCREMENT only work with integer type
                    "                    id integer primary key AUTOINCREMENT," +
                    "                    Name String," +
                    "                    DOB String," +
                    "                    PhoneNumber int," +
                    "                    StreetAddress String," +
                    "                    City String," +
                    "                    State String)");
            System.out.println("create table Customer successfully");

            stmt.executeUpdate("create table if not exists Account" +"(" +
                    "                    ID integer primary key AUTOINCREMENT," +
                    "                    Customer_ID int," +
                    "                    balance double," +
                    "                    name String," +
                    "                    accountNum long," +
                    "                    FOREIGN KEY(Customer_ID) REFERENCES Customer(id)" + ")");

            System.out.println("create table Account successfully");
            //Transaction is a keyword for sql use a diff name
            stmt.executeUpdate("create table if not exists TransactionTable" +"(" +
                    "                    transactionId String," +
                    "                    Account_ID long," +
                    "                    amount double," +
                    "                    transactionType String," +
                    "                    merchantName String," +
                    "                    merchantType String," +
                    "                    transactionDate String," +
                    "                    FOREIGN KEY(Account_ID) REFERENCES Account(accountNum)" +")");

            System.out.println("create table TransactionTable successfully");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // method to take user input on option
    public static String userInput(){
        Scanner input = new Scanner(System.in);
        System.out.println("Selection: ");
        String selection = input.nextLine();
        return selection;
    }





}
