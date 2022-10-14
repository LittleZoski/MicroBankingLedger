package service;
import Model.Customer;
import java.sql.*;
import java.util.Scanner;


public class CustomerCreation {
    public static void customerCreation() {
        Customer customer = new Customer();

        Scanner customerCreation = new Scanner(System.in);

        Connection conn = null;
        //ResultSet rs;
        PreparedStatement stmt = null;
        //Connor's String url = "jdbc:sqlite:C:\\Users\\bta91388\\IdeaProjects\\bankingproject\\MicroBankLedger.db";
        //Frank's String url = "jdbc:sqlite:C:\\Users\\31243\\OneDrive\\Desktop\\Code\\MCC bootcamp formal\\bankingproject\\MicroBankLedger.db";
        String url = "jdbc:sqlite:C:\\Users\\bta91388\\IdeaProjects\\bankingproject\\MicroBankLedger.db";


        try {
            System.out.print("Please Enter Customer Name: ");
            customer.setName(customerCreation.nextLine());
            System.out.print("Please Enter Customer DOB: ");
            customer.setDOB(Date.valueOf(customerCreation.nextLine()));
            System.out.print("Please Enter Customer Phone Number: ");
            customer.setPhoneNumber(customerCreation.nextLine());
            System.out.print("Please Enter Customer Address: ");
            customer.setStreetAddress(customerCreation.nextLine());
            System.out.print("Please Enter Customer City: ");
            customer.setCity(customerCreation.nextLine());
            System.out.print("Please Enter Customer State: ");
            customer.setState(customerCreation.nextLine());
            System.out.print("Please Enter Customer Zip: ");
            customer.setZipCode(Integer.parseInt(customerCreation.nextLine()));

            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);

            stmt = conn.prepareStatement("Insert into Customer (Name, DOB, PhoneNumber, StreetAddress, City, State)" +
                    "VALUES(?, ?, ?, ?, ?, ?)" );
            stmt.setString(1, customer.getName());
            stmt.setDate(2, customer.getDOB());
            stmt.setString(3, customer.getPhoneNumber());
            stmt.setString(4, customer.getStreetAddress());
            stmt.setString(5, customer.getCity());
            stmt.setString(6, customer.getState());
            //need to add ZipCode
            stmt.execute();


        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
