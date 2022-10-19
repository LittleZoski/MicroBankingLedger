package service;
///generate balance reports for our entire Bank


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class GenerateBalanceReport {


    public static void StatementPromptLoop(){

        while(true){
            Scanner input = new Scanner(System.in);
            BufferedWriter bw = null;
            try {
                System.out.println("Please enter a valid file path for me to create the file:");
                String filePath = input.nextLine();
                bw = new BufferedWriter(new FileWriter(filePath));
                System.out.println("Please Enter Customer Name:");
                String customerName = input.nextLine();
                outputStatement(bw, customerName);
                bw.close();
                break;
            } catch (IOException e) {
                System.out.println("Invalid Directory, Please Try again:");
                throw new RuntimeException(e);
            }
        }
    }

    public static void outputStatement(BufferedWriter bw, String customerName){
        DateFormat df = new SimpleDateFormat("mm/dd/yyyy");
        //Connor's String url = "jdbc:sqlite:C:\\Users\\bta91388\\IdeaProjects\\bankingproject\\MicroBankLedger.db";
        //Frank's String url = "jdbc:sqlite:C:\\Users\\31243\\OneDrive\\Desktop\\Code\\MCC bootcamp formal\\bankingproject\\MicroBankLedger.db";
        String url = "jdbc:sqlite:C:\\Users\\bta91388\\IdeaProjects\\bankingproject\\MicroBankLedger.db";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(url);
            pstmt = conn.prepareStatement("select * from Account where Customer_ID in (select id from Customer where lower(Name) = lower(?));");
            pstmt.setString(1,customerName);
            pstmt.execute();
            rs = pstmt.getResultSet();
            double currentBalance = rs.getDouble(3);
            pstmt.close();

            bw.write("Statement For " + customerName + "\n");
            bw.write("Statement Date:" + df.format(new Date())+ "\n");
            bw.write("Current Balance: " + currentBalance+ "\n");
            //sql query tansaction where accountID matches accountNum in Account and custimer_ID matches ID from Customer
            pstmt = conn.prepareStatement("select * from TransactionTable " +
                    "where Account_ID in(select accountNum from Account " +
                    "where Customer_ID in(select id from Customer " +
                    "where LOWER(Customer.Name) = LOWER(?)));");

            pstmt.setString(1, customerName);
            pstmt.execute();
            rs = pstmt.getResultSet();
            long currentAccount_ID = 0;
            while(rs.next()){
                // we have to seperate the transaction based off accountNum since the same customer can have multiple account
                if(rs.getRow() == 1){
                    bw.write("account" + rs.getLong(2) + " transaction: \n");
                } else if(rs.getLong(2) != currentAccount_ID){
                    //this is for in the Resultset if the account_ID changed between transation that means we are going into a diff account under same customer
                    bw.write("account" + rs.getLong(2) + " transaction: \n");
                }
                currentAccount_ID = rs.getLong(2);

                char c = rs.getString(4).equalsIgnoreCase("deposit") ? '+' : rs.getString(4).equalsIgnoreCase("credit") ? '+' : '-';
                System.out.println(rs.getString(7) + " " + rs.getString(4) + c + "$" + rs.getDouble(3));
                bw.write(rs.getString(7) + " " + rs.getString(4) + c + "$" + rs.getDouble(3)+ "\n");
            }

        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                bw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }








}
