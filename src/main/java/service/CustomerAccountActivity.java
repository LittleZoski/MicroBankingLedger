package service;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;


public class CustomerAccountActivity {

    public static void SelectionPromptAccountFunds(){
        System.out.println("D - Deposit Funds\n" +
                "W - Withdrawal Funds\n");

        Scanner input =  new Scanner(System.in);
        String userInput = input.nextLine();
        if(userInput.equalsIgnoreCase("D")){
            depositFund();
        } else if(userInput.equalsIgnoreCase("W")){
            drawlFund();
        }
    }

    public static void depositFund(){
        long accountNum = 0;
        Double depositAmount = null;

        while(true){
            Scanner input = new Scanner(System.in);
            try{
                System.out.print("Please Enter Account Number:");
                accountNum = input.nextLong();
                System.out.print("Please Enter the Amount to Deposit:");
                depositAmount = input.nextDouble();
                break;
            } catch(InputMismatchException e){
                System.out.println("input is not of the correct type expected, re-enter input");
            }
        }


        PreparedStatement pstmt = null;
        Statement stmt = null;
        ResultSet rs = null;
        //Connor's String URL = "jdbc:sqlite:C:\\Users\\bta91388\\IdeaProjects\\bankingproject\\MicroBankLedger.db";
        //Frank's String URL = "jdbc:sqlite:C:\\Users\\31243\\OneDrive\\Desktop\\Code\\MCC bootcamp formal\\bankingproject\\MicroBankLedger.db";
        //Darla's String url = "jdbc:sqlite:C:\Users\l\Documents\MCC Code\BankingProject\Bank.db";
        String url = "jdbc:sqlite:C:\\Users\\31243\\OneDrive\\Desktop\\Code\\MCC bootcamp formal\\bankingproject\\MicroBankLedger.db";
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");

        label: try(Connection conn  = DriverManager.getConnection(url)) {

            pstmt = conn.prepareStatement("select * from Account where accountNum = ?");
            pstmt.setLong(1, accountNum);
            pstmt.execute();
            rs = pstmt.getResultSet();
            if(!rs.next()){
                System.out.println("Account ID not found, please re-enter account id");
                break label;
            }
            double currentBalance = rs.getDouble(3);
            System.out.println("previous balance is:" + currentBalance);
            pstmt.close();
            pstmt = conn.prepareStatement("update Account set balance = ? where accountNum = ?");
            pstmt.setDouble(1, currentBalance + depositAmount);
            System.out.println("Current balance is:" + (currentBalance + depositAmount));
            pstmt.setLong(2, accountNum);
            pstmt.executeUpdate();

            pstmt.close();
            pstmt = conn.prepareStatement("insert into TransactionTable (transactionId, Account_ID, amount, transactionType, merchantName, transactionDate)" +
                    "values(?,?,?,?,?,?)");
            pstmt.setString(1, "deposit_into_"+accountNum);
            pstmt.setLong(2, accountNum);
            pstmt.setDouble(3, depositAmount);
            pstmt.setString(4, "CREDIT");
            pstmt.setString(5, "Deposit");
            pstmt.setString(6,df.format(new Date()));
            pstmt.execute();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void drawlFund(){

        long accountNum = 0;
        Double withdrawAmount = null;

        while(true){
            Scanner input = new Scanner(System.in);
            try{
                System.out.print("Please Enter Account Number:");
                accountNum = input.nextLong();
                System.out.print("Please Enter the Amount to Withdraw:");
                withdrawAmount = input.nextDouble();
                break;
            } catch(InputMismatchException e){
                System.out.println("input is not of the correct type expected, re-enter input");
            }
        }


        PreparedStatement pstmt = null;
        Statement stmt = null;
        ResultSet rs = null;
        //Connor's String URL = "jdbc:sqlite:C:\\Users\\bta91388\\IdeaProjects\\bankingproject\\MicroBankLedger.db";
        //Frank's String URL = "jdbc:sqlite:C:\\Users\\31243\\OneDrive\\Desktop\\Code\\MCC bootcamp formal\\bankingproject\\MicroBankLedger.db";
        String url = "jdbc:sqlite:C:\\Users\\31243\\OneDrive\\Desktop\\Code\\MCC bootcamp formal\\bankingproject\\MicroBankLedger.db";
        DateFormat df = new SimpleDateFormat("yyyy/mm/dd");

        label: try(Connection conn  = DriverManager.getConnection(url)) {

            pstmt = conn.prepareStatement("select * from Account where accountNum = ?");
            pstmt.setLong(1, accountNum);
            pstmt.execute();
            rs = pstmt.getResultSet();
            if(!rs.next()){
                System.out.println("Account ID not found, please re-enter account id");
                break label;
            }
            double currentBalance = rs.getDouble(3);
            System.out.println("Previous balance is:" + currentBalance);
            pstmt.close();
            //account cant be over drafted
            if(currentBalance - withdrawAmount <=0 ){
                System.out.println("Account does not have enough fund to withdrawal the requested amount, please try again");
                break label;
            }
            pstmt = conn.prepareStatement("update Account set balance = ? where accountNum = ?");
            pstmt.setDouble(1, currentBalance - withdrawAmount);
            System.out.println("Current balance is:" + (currentBalance - withdrawAmount));
            pstmt.setLong(2, accountNum);
            pstmt.executeUpdate();
            pstmt.close();
            pstmt = conn.prepareStatement("insert into TransactionTable (transactionId, Account_ID, amount, transactionType, merchantName, transactionDate)" +
                    "values(?,?,?,?,?,?)");
            pstmt.setString(1, "withdrawal_from_"+accountNum);
            pstmt.setLong(2, accountNum);
            pstmt.setDouble(3, withdrawAmount);
            pstmt.setString(4, "Debit");
            pstmt.setString(5, "Withdrawal");
            pstmt.setString(6, df.format(new Date()));
            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
