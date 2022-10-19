package service;


import Model.Account;

import java.sql.*;
import java.util.Random;
import java.util.Scanner;

//CRUD customer bank accounts - frank
public class CustomerAccountService {
    public static void SelectionPromptCrudAccount(){
        Scanner input =  new Scanner(System.in);
        System.out.println("Would you like to :\n" +
                "C - Create an account\n" +
                "U - Update an account\n" +
                "D - Delete an account\n");

        String userInput = input.nextLine();
        if(userInput.equalsIgnoreCase("c")){
            CreateAccount();
        } else if(userInput.equalsIgnoreCase("u")){
            updateAccount();
        } else if(userInput.equalsIgnoreCase("d")){
            DeleteAccount();
        }

    }


    public static void CreateAccount(){
        Account account = new Account();
        Scanner input = new Scanner(System.in);
        System.out.print("Please Enter Customer Name:");
        String customerName = input.nextLine();
        System.out.print("Please Enter Account Name:");
        String accountName = input.nextLine();
        Random randomGenerator = new Random();
        Long accountNum = Math.abs(randomGenerator.nextLong()*10000000000000000L + 10000000000000000L);
        System.out.println("Account created with account number " + accountNum);
        account.setAccountNum(accountNum);
        account.setName(accountName);
        account.setBalance(0);



        PreparedStatement pstmt = null;
        Statement stmt = null;
        ResultSet rs = null;
        //Connor's String URL = "jdbc:sqlite:C:\\Users\\bta91388\\IdeaProjects\\bankingproject\\MicroBankLedger.db";
        //Frank's String URL = "jdbc:sqlite:C:\\Users\\31243\\OneDrive\\Desktop\\Code\\MCC bootcamp formal\\bankingproject\\MicroBankLedger.db";
        String url = "jdbc:sqlite:C:\\Users\\bta91388\\IdeaProjects\\bankingproject\\MicroBankLedger.db";
        label: try(Connection conn  = DriverManager.getConnection(url)) {
            stmt = conn.createStatement();
            //get the customer from DB with name = cutomerName and return the customer ID
            rs = stmt.executeQuery("select * from Customer where LOWER(Customer.Name) = " +"LOWER(\'" + customerName + "\')");
            //handle situation where customer name no exist
            if(!rs.next()){
                System.out.println("Customer not found, please go ahead create a customer called " + customerName);
                break label;
            }
            account.setCustomer_ID(rs.getInt(1));

            pstmt = conn.prepareStatement("Insert into Account (Customer_ID, balance, name, accountNum) values(?,?,?,?)");
            pstmt.setLong(1, account.getCustomer_ID());
            pstmt.setDouble(2,0);
            pstmt.setString(3, account.getName());
            pstmt.setLong(4,account.getAccountNum());
            pstmt.execute();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateAccount(){

        Scanner input = new Scanner(System.in);
        System.out.print("Please Enter Account Number: ");
        long accountNum = input.nextLong();
        input.nextLine();
        System.out.print("Please Enter New Account Name: ");
        String accountName = input.nextLine();

        PreparedStatement pstmt = null;
        //Connor's String URL = "jdbc:sqlite:C:\\Users\\bta91388\\IdeaProjects\\bankingproject\\MicroBankLedger.db";
        //Frank's String URL = "jdbc:sqlite:C:\\Users\\31243\\OneDrive\\Desktop\\Code\\MCC bootcamp formal\\bankingproject\\MicroBankLedger.db";
        String url = "jdbc:sqlite:C:\\Users\\bta91388\\IdeaProjects\\bankingproject\\MicroBankLedger.db";
        label: try(Connection conn  = DriverManager.getConnection(url)) {
            pstmt = conn.prepareStatement("update Account set name = ? where accountNum =? ");
            pstmt.setString(1,accountName);
            pstmt.setLong(2, accountNum);
            pstmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void DeleteAccount(){
        Scanner input = new Scanner(System.in);
        System.out.print("Please Enter Account Number: ");
        long accountNum = input.nextLong();
        PreparedStatement pstmt = null;
        //Connor's String URL = "jdbc:sqlite:C:\\Users\\bta91388\\IdeaProjects\\bankingproject\\MicroBankLedger.db";
        //Frank's String URL = "jdbc:sqlite:C:\\Users\\31243\\OneDrive\\Desktop\\Code\\MCC bootcamp formal\\bankingproject\\MicroBankLedger.db";
        String url = "jdbc:sqlite:C:\\Users\\bta91388\\IdeaProjects\\bankingproject\\MicroBankLedger.db";
        label: try(Connection conn  = DriverManager.getConnection(url)) {
            pstmt = conn.prepareStatement("delete from Account where accountNum = ? ");
            pstmt.setLong(1, accountNum);
            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
