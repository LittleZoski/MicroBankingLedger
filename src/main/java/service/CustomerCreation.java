package service;
import Model.Customer;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.Scanner;


public class CustomerCreation {
    public static void customerCreation() {
        Customer customer = new Customer();

        Scanner customerCreation = new Scanner(System.in);

        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        //Connor's String url = "jdbc:sqlite:C:\\Users\\bta91388\\IdeaProjects\\bankingproject\\MicroBankLedger.db";
        //Frank's String url = "jdbc:sqlite:C:\\Users\\31243\\OneDrive\\Desktop\\Code\\MCC bootcamp formal\\bankingproject\\MicroBankLedger.db";
        String url = "jdbc:sqlite:C:\\Users\\31243\\OneDrive\\Desktop\\Code\\MCC bootcamp formal\\bankingproject\\MicroBankLedger.db";


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
            //since customer id is autoIncrement we dont declare it here.
            pstmt = conn.prepareStatement("Insert into Customer (Name, DOB, PhoneNumber, StreetAddress, City, State)" +
                    "VALUES(?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, customer.getName());
            pstmt.setDate(2, customer.getDOB());
            pstmt.setString(3, customer.getPhoneNumber());
            pstmt.setString(4, customer.getStreetAddress());
            pstmt.setString(5, customer.getCity());
            pstmt.setString(6, customer.getState());
            pstmt.executeUpdate();
            //need to add ZipCode

            //to output the last index we have to use generateKeys method return the primary key auto generated to rs
            rs = pstmt.getGeneratedKeys();
            System.out.println("Customer Created with ID "+rs.getInt(1));

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
