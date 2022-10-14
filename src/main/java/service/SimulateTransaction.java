package service;
import Model.Transaction;
import Model.TransactionResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.DeserializationFeature;
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
    public static void loadTransaction(long id){
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet("https://mcc-java-transaction-api.herokuapp.com/transaction/"+id);

        try {
            TransactionResponse transactionResponse = client.execute(request, classicHttpResponse -> mapper.readValue(classicHttpResponse.getEntity().getContent(), TransactionResponse.class));
            System.out.println("transaction id is: " + transactionResponse.transactionId);
            System.out.println("customer id is: " +  transactionResponse.customer.getId());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
