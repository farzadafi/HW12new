package service;

import entity.Account;
import entity.Transaction;
import entity.enumoration.TypeAccount;
import entity.enumoration.TypeTransaction;
import repository.AccountRepository;
import service.exception.InvalidNationalException;

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
    public int addAccount(String nationalIdClerk) {
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
        Account newAccount = new Account(null,codeBranch,nationalId,number,budget, TypeAccount.ACTIVE);
        accountRepository.add(newAccount);
        return 1;
    }

    public void showAccountForCustomer(String nationalIdCustomer) {
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

    public Account findAccountNumber(String number) {
        return accountRepository.findByAccountNumber(number);
    }

    public String returnAccountNumber(int id) {
        return accountRepository.findById(id);
    }


    public void update(Account account){
        accountRepository.update(account);
    }

    public void showAccountForClerk() {
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

    public boolean checkAccount(String numberAccount) {
        Account account = accountRepository.findByAccountNumber(numberAccount);
        if(account.getTypeAccount() == TypeAccount.ACTIVE)
            return true;
        else
            return false;
    }

    public void setInactiveAccount() {
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
        if(accountRepository.findByAccountNumber(accountNumber) == null ){
            System.out.println("You enter a wrong account Number!");
            return;
        }
        Account account = accountRepository.findByAccountNumber(accountNumber);
        account.setTypeAccount(TypeAccount.INACTIVE);
        accountRepository.update(account);
        System.out.println("This account seccessful inactived!");
    }

    public void DepositToAccount() {
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
        if(!checkAccount(accountNumber)){
            System.out.println("you enter a wrong account number!");
            return;
        }
        System.out.print("Enter amount for withdraw:");
        budget = input.nextDouble();
        Account account = accountRepository.findByAccountNumber(accountNumber);
        Account newAccount = new Account(account.getId(),account.getCodeBranch(),account.getNationalId(),account.getAccountNumber(),(account.getBudget()+budget),account.getTypeAccount());
        accountRepository.update(newAccount);
        LocalDate tempDate = LocalDate.now();
        Date date = Date.valueOf(tempDate);
        LocalTime tempTime = LocalTime.now();
        Time time = Time.valueOf(tempTime);
        Transaction plusTransaction = new Transaction(accountNumber,"Clerk","Clerk",String.valueOf(budget),date,time, TypeTransaction.CLERKWHITHDRAW);
        try {
            transactionService = new TransactionService();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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

    public void setInactiveAccountForBoss()  {
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
        if(accountRepository.findByAccountNumber(accountNumber) == null ){
            System.out.println("You enter a wrong account Number!");
            return;
        }
        //accountRepository.setInactiveAccount(accountNumber);
        Account account = accountRepository.findByAccountNumber(accountNumber);
        account.setTypeAccount(TypeAccount.INACTIVE);
        accountRepository.update(account);
        System.out.println("This account seccessful inactived!");
    }











}
