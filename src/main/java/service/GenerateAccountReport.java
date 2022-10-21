package service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class GenerateAccountReport {

        public static void AccountPromptLoop(){

            while(true){
                Scanner input = new Scanner(System.in);
                BufferedWriter bw = null;
                try {
                    System.out.println("Please enter a valid file path for me to create the file:");
                    String filePath = input.nextLine();
                    bw = new BufferedWriter(new FileWriter(filePath+ "\\AccountReport.txt"));
                    outputAccount(bw);
                    bw.close();
                    break;
                } catch (IOException e) {
                    System.out.println("Invalid Directory, Please Try again:");

                }
            }
        }

        public static void outputAccount(BufferedWriter bw){
            DateFormat df = new SimpleDateFormat("mm/dd/yyyy");
            //Connor's String url = "jdbc:sqlite:C:\\Users\\bta91388\\IdeaProjects\\bankingproject\\MicroBankLedger.db";
            //Frank's String url = "jdbc:sqlite:C:\\Users\\31243\\OneDrive\\Desktop\\Code\\MCC bootcamp formal\\bankingproject\\MicroBankLedger.db";
            //Darla's String url = "jdbc:sqlite:C:\Users\l\Documents\MCC Code\BankingProject\Bank.db";
            String url = "jdbc:sqlite:C:\\Users\\31243\\OneDrive\\Desktop\\Code\\MCC bootcamp formal\\bankingproject\\MicroBankLedger.db";
            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            ResultSet rs2 = null;

            double sum = 0;
            try {
                conn = DriverManager.getConnection(url);
                pstmt = conn.prepareStatement("select count(*) from Customer;");
                pstmt.execute();
                rs = pstmt.getResultSet();
                System.out.println("Total Customers: " + rs.getInt(1));
                bw.write("Total Customers " + rs.getInt(1) + "\n");
                pstmt.close();
                pstmt = conn.prepareStatement("select count(*) from Account;");
                pstmt.execute();
                rs = pstmt.getResultSet();
                System.out.println("Total Accounts: " + rs.getInt(1));
                bw.write("Total Accounts " + rs.getInt(1) + "\n");
                pstmt.close();
                pstmt = conn.prepareStatement("select * from Account;");
                pstmt.execute();
                rs = pstmt.getResultSet();
                while (rs.next()){
                    sum += rs.getDouble(3);
                }
                System.out.println("Total Balance: " + sum);
                bw.write("Total Balance " + sum + "\n");
                pstmt.close();


                //sql query transaction where accountID matches accountNum in Account and customer_ID matches ID from Customer
                pstmt = conn.prepareStatement("select * from Customer c inner join Account a where c.id = a.Customer_ID");
                pstmt.execute();
                rs = pstmt.getResultSet();
                while(rs.next()){
                    System.out.println(rs.getString(2)+ "\n" +
                            rs2.getString(12) + "-" + "$" + rs2.getDouble(11) + "\n");
                    bw.write(rs.getString(2)+ "\n" +
                            rs2.getString(12) + "-" + "$" + rs2.getDouble(11) + "\n");
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
