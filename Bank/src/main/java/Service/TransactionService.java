package Service;

import Entity.Transaction;
import Repository.TransactionRepository;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class TransactionService {
    private Scanner input = new Scanner(System.in);
    private AccountService accountService = new AccountService();
    private CustomerService customerService = new CustomerService();
    private TransactionRepository transactionRepository = new TransactionRepository();
    private String accountNumber;

    public TransactionService() throws SQLException, ClassNotFoundException {
    }

    public void addTransaction(Transaction transaction) throws SQLException {
        transactionRepository.add(transaction);
    }
    
    public void showTransaction(String nationalIdCustomer) throws SQLException {
        String name = customerService.findName(nationalIdCustomer);
        System.out.println(name + " dear you have this account:");
        accountService.showAccountForCustomer(nationalIdCustomer);
        if(!accountService.getCheck()){
            System.out.println("You dont have any account!");
            return;
        }
        System.out.print("Enter account number for view your Transaction:");
        accountNumber = input.nextLine();
        if ( accountService.findAccountNumber(accountNumber) == 0){
            System.out.println("This account number is not define before!");
            return;
        }
        System.out.print("Enter date(yyyy-mm-dd):");
        String date = input.nextLine();
        Date date1 = Date.valueOf(date);
        LocalDate tempDate = LocalDate.now();
        Date date2 = Date.valueOf(tempDate);
        if( date1.after(date2) ) {
            System.out.println("You enter a date after now!");
            return;
        }
        List<Transaction> transactionList = transactionRepository.findAllTransaction(accountNumber,date1);
        if( transactionList.isEmpty() ){
            System.out.println("This account doesn't have any any Transaction!");
            return;
        }
        for (Transaction transaction: transactionList) {
            System.out.println(transaction.toString());
        }
    }
}
