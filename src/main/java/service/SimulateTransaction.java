package service;

import Model.TransactionResponse;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.sql.*;


public class SimulateTransaction {

    private long id;

    public SimulateTransaction(long id){
        this.id = id;
    }
    public static void loadTransaction(long id){
        ObjectMapper mapper = new ObjectMapper();
        //mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet("https://mcc-java-transaction-api.herokuapp.com/transaction/"+id);

        Connection conn = null;
        //ResultSet rs;
        PreparedStatement stmt = null;
        //Connor's String url = "jdbc:sqlite:C:\\Users\\bta91388\\IdeaProjects\\bankingproject\\MicroBankLedger.db";
        String url = "jdbc:sqlite:C:\\Users\\31243\\OneDrive\\Desktop\\Code\\MCC bootcamp formal\\bankingproject\\MicroBankLedger.db";

        try {
            TransactionResponse transactionResponse = client.execute(request, classicHttpResponse -> mapper.readValue(classicHttpResponse.getEntity().getContent(), TransactionResponse.class));
            System.out.println("transaction id is: " + transactionResponse.transactionId);
            System.out.println("customer id is: " +  transactionResponse.customer.getId());
            conn = DriverManager.getConnection(url);
            stmt = conn.prepareStatement("insert into TransactionTable " + "( transactionId, amount, transactionType, merchantName, merchantType ) " +
                    "values (?,?,?,?,?)");
            stmt.setString(1, transactionResponse.transactionId);
            //i don't think customer id should be passed to transaction account_ID, Account_ID has underscore, need a tick` to let sqlite know)
            //stmt.setLong(2, Long.parseLong(transactionResponse.customer.getId()));
            stmt.setDouble(2, transactionResponse.amount);
            stmt.setString(3, transactionResponse.transactionType);
            stmt.setString(4, transactionResponse.recipient.merchantName);
            stmt.setString(5, transactionResponse.recipient.merchantType);
            stmt.execute();


        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
