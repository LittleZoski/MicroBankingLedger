package service;

import Model.TransactionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class SimulateTransaction {

    private long id;

    public SimulateTransaction(long id){
        this.id = id;
    }


    //method for user to input id for simulate transaction
    public static void handleSimulatedTransaction(){
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter customer account number:");
        long accountNum = input.nextLong();
        //if we make the service method static we don't need to instantiate the service class
        loadTransaction(accountNum);
    }

    public static void loadTransaction(long id){
        ObjectMapper mapper = new ObjectMapper();
        //mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        CloseableHttpClient client = HttpClients.createDefault();

        //HttpGet request = new HttpGet("https://mcc-java-transaction-api.herokuapp.com/transaction/"+id);
        HttpGet request = new HttpGet("https://mcc-java-transaction-api.herokuapp.com/transaction/"+id);
        Connection conn = null;
        ResultSet rs;
        PreparedStatement stmt = null;
        double currentBalance = 0;
        //Connor's String url = "jdbc:sqlite:C:\\Users\\bta91388\\IdeaProjects\\bankingproject\\MicroBankLedger.db";
        //Frank's String url = "jdbc:sqlite:C:\\Users\\31243\\OneDrive\\Desktop\\Code\\MCC bootcamp formal\\bankingproject\\MicroBankLedger.db";
        //Darla's String url = "jdbc:sqlite:C:\\Users\\l\\Documents\\MCC Code\\BankingProject\\Bank.db";
        String url = "jdbc:sqlite:C:\\Users\\31243\\OneDrive\\Desktop\\Code\\MCC bootcamp formal\\bankingproject\\MicroBankLedger.db";
        //transaction need a date
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");

        Label: try {
            //first parse api based on accountNum into transactionResponse object then write the object data to DB transaction table
            TransactionResponse transactionResponse = client.execute(request, classicHttpResponse -> mapper.readValue(classicHttpResponse.getEntity().getContent(), TransactionResponse.class));
            System.out.println("transaction id is: " + transactionResponse.transactionId);
            System.out.println("customer id is: " +  transactionResponse.customer.getAccountNum());
            conn = DriverManager.getConnection(url);
            stmt = conn.prepareStatement("insert into TransactionTable " + "( transactionId, Account_ID, amount, transactionType, merchantName, merchantType, transactionDate ) " +
                    "values (?,?,?,?,?,?,?)");
            stmt.setString(1, transactionResponse.transactionId);
            stmt.setLong(2, id);
            stmt.setDouble(3, transactionResponse.amount);
            stmt.setString(4, transactionResponse.transactionType);
            stmt.setString(5, transactionResponse.recipient.merchantName);
            stmt.setString(6, transactionResponse.recipient.merchantType);
            stmt.setString(7, df.format(new Date()));
            stmt.execute();

            stmt.close();
            //next update balance after transaction in Account Table
            stmt = conn.prepareStatement("select * from Account where accountNum = ? ");
            stmt.setLong(1, id);
            stmt.execute();
            rs = stmt.getResultSet();
            currentBalance = rs.getDouble(3);
            stmt.close();
            if(transactionResponse.transactionType.equalsIgnoreCase("credit")){
                stmt = conn.prepareStatement("update Account set balance = ? where accountNum = ?");
                stmt.setDouble(1, currentBalance + transactionResponse.amount);
                stmt.setLong(2, id);
                stmt.execute();
            }else if (transactionResponse.transactionType.equalsIgnoreCase("debit")
                    && currentBalance >= transactionResponse.amount){
                stmt = conn.prepareStatement("update Account set balance = ? where accountNum = ?");
                stmt.setDouble(1, currentBalance - transactionResponse.amount);
                stmt.setLong(2, id);
                stmt.execute();
            } else if (currentBalance < transactionResponse.amount){
                System.out.println("Account does not have enough fund, transaction denied");
                break Label;
            }
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
