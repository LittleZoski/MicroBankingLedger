package service;
import Model.Customer;

import java.sql.*;
import java.util.Scanner;


public class CustomerCreation {
    public static void customerCreation() {
        Customer customer = new Customer();

        Scanner customerCreation = new Scanner(System.in);

        System.out.print("Please Enter Customer Name: ");
        customer.setName(customerCreation.nextLine());
        System.out.print("Please Enter Customer DOB: ");
        customer.setDOB(Integer.parseInt(customerCreation.nextLine()));
        System.out.print("Please Enter Customer Phone Number: ");
        customer.setPhoneNumber(Integer.parseInt(customerCreation.nextLine()));
        System.out.print("Please Enter Customer Address: ");
        customer.setStreetAddress(customerCreation.nextLine());
        System.out.print("Please Enter Customer City: ");
        customer.setCity(); = customerCreation.nextLine();
        System.out.print("Please Enter Customer State: ");
        String customerState = customerCreation.nextLine();
        System.out.print("Please Enter Customer Zip: ");
        String customerZip = customerCreation.nextLine();



    }
}
