package Service;

import Entity.Account;
import Entity.Transaction;
import Entity.TypeAccount;
import Entity.TypeTransaction;
import Repository.AccountRepository;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class AccountService {
    private Scanner input = new Scanner(System.in);
    private ClerkService clerkService = new ClerkService();
    private LoginService loginService = new LoginService();
    private String codeBranch,nationalId,accountNumber;
    private AccountRepository accountRepository = new AccountRepository();
    private Double budget;
    private CustomerService customerService = new CustomerService();
    Random random = new Random();
    private static boolean check = true;
    private TransactionService transactionService;

    public AccountService() throws SQLException, ClassNotFoundException {
    }

    //::::>
    public int addAccount(String nationalIdClerk) throws SQLException {
        codeBranch = clerkService.findCodeBranch(nationalIdClerk);
        while(true){
            System.out.print("Enter nationalId(username):");
            nationalId = input.nextLine();
            if( loginService.findNationalId(nationalId) == 0 ) {
                System.out.println("You enter a wrong national Id!");
            }
            else
                break;
        }
        String number;
        while(true) {
            number = String.valueOf(random.nextInt(11111111,99999999));
            if( accountRepository.find(number) == 0 )
                break;
        }
        System.out.print("Enter budget:");
        budget = input.nextDouble();
        Account newAccount = new Account(codeBranch,nationalId,number,budget, TypeAccount.ACTIVE);
        accountRepository.add(newAccount);
        return 1;
    }

    public void showAccountForCustomer(String nationalIdCustomer) throws SQLException {
        check = true;
        List<Account> accountList = accountRepository.showAllAccount(nationalIdCustomer);
        if(accountList.isEmpty()) {
            check = false;
            System.out.println("This national id doesn't have any account!");
            return;
        }
        for (Account account : accountList)
        {
            if(account.getTypeAccount().toString().equals("ACTIVE")) {
                System.out.println(account.toString());
            }
        }
    }

    public int showAccount(String nationalId) throws SQLException {
        return accountRepository.showAccount(nationalId);
    }

    public int findAccountNumber(String number) throws SQLException {
        return accountRepository.find(number);
    }

    public String returnAccountNumber(int id) throws SQLException {
        return accountRepository.returnAccountNumber(id);
    }

    public String returnAmount(String accountNumber) throws SQLException {
        return accountRepository.returnAmount(accountNumber);
    }

    public void depositCard(Double amount,String accountNumber) throws SQLException {
        accountRepository.depositCard(amount,accountNumber);
    }

    public void withdrawCard(Double amount,String accountNumber) throws SQLException {
        accountRepository.withdrawCard(amount,accountNumber);
    }

    public void showAccountForClerk() throws SQLException {
        check = true;
        System.out.print("Enter national Id Customer:");
        nationalId = input.nextLine();
        String name = customerService.findName(nationalId);
        if( name.equals("null")){
            System.out.println("This national Id not found!");
            return;
        }
        System.out.println(name + " has this account:");
        List<Account> accountList = accountRepository.showAllAccount(nationalId);
        if(accountList.isEmpty()) {
            check = false;
            System.out.println("This national id doesn't have any account!");
            return;
        }
        for (Account account : accountList)
        {
                System.out.println(account.toString());
        }
    }

    public boolean getCheck(){
        return check;
    }

    public boolean checkAccount(String numberAccount) throws SQLException {
        return accountRepository.checkAccount(numberAccount);
    }

    public void setInactiveAccount() throws SQLException {
        System.out.print("Enter nationalId customer:");
        nationalId = input.nextLine();
        String name = customerService.findName(nationalId);
        if(name.equals("null")){
            System.out.println("This national id is not defined Before!");
            return;
        }
        List<Account> accountList = accountRepository.showAllAccount(nationalId);
        int i=0;
        for (Account account:accountList) {
            if(account.getTypeAccount().toString().equals("ACTIVE")){
                System.out.println(account.toString());
                i++;
            }
        }
        if(i == 1 ){
            System.out.println("This Customer just have an Active account!");
            return;
        }
        System.out.print("Enter the account number for INACTIVE:");
        accountNumber = input.nextLine();
        if(accountRepository.find(accountNumber) == 0 ){
            System.out.println("You enter a wrong account Number!");
            return;
        }
        accountRepository.setInactiveAccount(accountNumber);
        System.out.println("This account seccessful inactived!");
    }

    public void DepositToAccount() throws SQLException, ClassNotFoundException {
            try {
                System.out.print("Enter national Id customer:");
                nationalId = input.nextLine();
                nationalIdChecker(nationalId);
            } catch (InvalidNationalException e) {
                System.out.println("you enter a wrong national Id!");
                return;
            }
            String name = customerService.findName(nationalId);
            if (name.equals("null")) {
                System.out.println("This national Id is not defined before!");
                return;
            }
        System.out.println(name + " have this account in us Bank(active and inactive):");
        showAccountForCustomer(nationalId);
        System.out.print("Enter account number for deposit money:");
        accountNumber = input.nextLine();
        if(!accountRepository.checkAccount(accountNumber)){
            System.out.println("you enter a wrong account number!");
            return;
        }
        System.out.print("Enter amount for withdraw:");
        budget = input.nextDouble();
        accountRepository.withdrawCard(budget,accountNumber);
        LocalDate tempDate = LocalDate.now();
        Date date = Date.valueOf(tempDate);
        LocalTime tempTime = LocalTime.now();
        Time time = Time.valueOf(tempTime);
        Transaction plusTransaction = new Transaction(accountNumber,"Clerk","Clerk",String.valueOf(budget),date,time, TypeTransaction.CLERKWHITHDRAW);
        transactionService = new TransactionService();
        transactionService.addTransaction(plusTransaction);
        System.out.println(budget + " successful added to account " + name);
    }

    public void nationalIdChecker(String nationalId){
        if(nationalId.length() > 10 )
            throw new InvalidNationalException();
        for (Character character:nationalId.toCharArray()) {
            if(!Character.isDigit(character))
                throw new InvalidNationalException("National Id should contain only digits!");
        }
    }

    public void setInactiveAccountForBoss() throws SQLException {
        System.out.print("Enter nationalId customer:");
        nationalId = input.nextLine();
        String name = customerService.findName(nationalId);
        if(name.equals("null")){
            System.out.println("This national id is not defined Before!");
            return;
        }
        List<Account> accountList = accountRepository.showAllAccount(nationalId);
        int i=0;
        for (Account account:accountList) {
            if(account.getTypeAccount().toString().equals("ACTIVE")){
                System.out.println(account.toString());
                i++;
            }
        }
        if(i == 0 ){
            System.out.println("This Customer doesn't have any Account!");
            return;
        }
        System.out.print("Enter the account number for INACTIVE:");
        accountNumber = input.nextLine();
        if(accountRepository.find(accountNumber) == 0 ){
            System.out.println("You enter a wrong account Number!");
            return;
        }
        accountRepository.setInactiveAccount(accountNumber);
        System.out.println("This account seccessful inactived!");
    }











}
