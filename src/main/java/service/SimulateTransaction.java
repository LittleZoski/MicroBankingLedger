package service;
import Model.Transaction;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import java.io.IOException;



public class SimulateTransaction {

    private long id;

    public SimulateTransaction(long id){
        this.id = id;
    }
    public void loadTransaction(long id){
        ObjectMapper mapper = new ObjectMapper();
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet("https://mcc-java-transaction-api.herokuapp.com/transaction/"+id);

        try {
            Transaction transaction = client.execute(request, classicHttpResponse -> mapper.readValue(classicHttpResponse.getEntity().getContent(), Transaction.class));
            System.out.println("transaction id is: " + transaction.transactionId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


}
