import service.CustomerCreation;
import service.SimulateTransaction;
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
                handleSimulatedTransaction();
            } else if (selection.equalsIgnoreCase("4")){
                CustomerCreation.customerCreation();
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


            stmt.executeUpdate("create table if not exists Account" +"(" +
                    "                    ID long,\n" +
                    "                    Customer_ID long,\n" +
                    "                    balance double,\n" +
                    "                    name String,\n" +
                    "                    accountNum long)");

            //Transaction is a keyword for sql use a diff name
            stmt.executeUpdate("create table if not exists TransactionTable" +"(" +
                    "                    transactionId String,\n" +
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
    public static String userInput(){
        Scanner input = new Scanner(System.in);
        System.out.println("Selection: ");
        String selection = input.nextLine();
        return selection;
    }


    //method for user to input id for simulate transaction

    public static void handleSimulatedTransaction(){
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter customer account number:");
        long accountNum = input.nextLong();
        //if we make the service method static we don't need to instantiate the service class
        SimulateTransaction.loadTransaction(accountNum);
    }

}
