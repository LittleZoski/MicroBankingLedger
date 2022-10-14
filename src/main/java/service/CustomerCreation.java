package service;
import java.sql.*;
import java.util.Scanner;


public class CustomerCreation {
    public static void main(String[] args) {

        Scanner customerCreation = new Scanner(System.in);

        System.out.print("Please Enter Customer Name: ");
        String customerName = customerCreation.nextLine();
        System.out.print("Please Enter Customer DOB: ");
        String customerDOB = customerCreation.nextLine();
        System.out.print("Please Enter Customer Phone Number: ");
        String customerTFN = customerCreation.nextLine();
        System.out.print("Please Enter Customer Address: ");
        String customerAddress = customerCreation.nextLine();
        System.out.print("Please Enter Customer City: ");
        String customerCity = customerCreation.nextLine();
        System.out.print("Please Enter Customer State: ");
        String customerState = customerCreation.nextLine();
        System.out.print("Please Enter Customer Zip: ");
        String customerZip = customerCreation.nextLine();

    }
}
