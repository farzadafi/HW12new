import Entity.Bank;
import Service.*;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    private Scanner input = new Scanner(System.in);
    private int command;
    private String username,password;
    private boolean isTrue = true;
    private Bank bank = new Bank();
    private BankService bankService = new BankService();
    private BankBranchService bankBranchService = new BankBranchService();
    private LoginService loginService = new LoginService();
    private ClerkService clerkService = new ClerkService();
    private CustomerService customerService = new CustomerService();
    private AccountService accountService = new AccountService();
    private CreditCardService creditCardService = new CreditCardService();
    private TransactionService transactionService = new TransactionService();


    public Menu() throws SQLException, ClassNotFoundException {
    }

    //::::>
    public int publicMenu(){
        System.out.println("***WELCOME***");
        System.out.println("1-signIn(Enter)");
        System.out.println("2-Exit");
        System.out.print("Please select a number:");
        command = input.nextInt();
        input.nextLine();
        switch(command){
            case 1:
                return 1;

            case 2:
                return 2;

            default:
                return 0;
        }
    }

    //::::>
    public void enterMenu() throws SQLException, ClassNotFoundException {
        System.out.print("Please enter your username:");
        username = input.nextLine();
        System.out.print("Please enter your password:");
        password = input.nextLine();
        if( username.equals("admin") && password.equals("admin") )
            adminMenu();
        else{
            String resultFind = loginService.findLogin(username,password);
            if (resultFind == null ) {
                System.out.println("you enter a wrong username and password,please be carefully!");
            }
            else if( resultFind.equals("BOSS"))
                bossMenu();
            else if( resultFind.equals("CLERK"))
                clerkMenu();
            else if( resultFind.equals("CUSTOMER"))
                customerMenu();
        }
    }

    //::::>
    public void adminMenu() throws SQLException {
        isTrue = true;
        while(isTrue) {
            System.out.println("*** Admin Menu ***");
            System.out.println("1-Add Bank.");
            System.out.println("2-add Bank Branch.");
            System.out.println("3-Exit.");
            command = input.nextInt();
            input.nextLine();
            switch (command)
            {
                case 1:
                    if( bankService.addBank() == 2 )
                        System.out.println("Bank you enter successful added!");
                    else
                        System.out.println("This name you enter before added!");
                    break;

                case 2:
                    int result = bankBranchService.addBankBranch();
                    if(result == 1 )
                        System.out.println("This name Bank you enter doesn't Exists!");
                    else if(result == 2)
                        System.out.println("This code Branch already Exists!");
                    else
                        System.out.println("This branch Bank you enter successful added!");
                    break;

                case 3:
                    System.out.println("Good luck!");
                    isTrue = false;
                    break;

                default:
                    System.out.println("you enter a wrong number!");
            }
        }
    }

    //::::>
    public void bossMenu() throws SQLException {
        isTrue = true;
        while(isTrue) {
            System.out.println("*** Boss Menu ***");
            System.out.println("1-add clerk.");
            System.out.println("2-Inactive account!");
            System.out.println("5-Exit.");
            System.out.print("Please select a number:");
            command = input.nextInt();
            input.nextLine();
            switch(command){
                case 1:
                    String result = clerkService.addClerk(username);
                    System.out.println( result + " successful added!");
                    break;

                case 2:
                    accountService.setInactiveAccountForBoss();
                    break;

                case 5:
                    System.out.println("Good luck!");
                    isTrue = false;
                    break;

                default:
                    System.out.println("you enter a wrong number");

            }
        }
    }

    public void clerkMenu() throws SQLException, ClassNotFoundException {
        isTrue = true;
        while(isTrue){
            System.out.println("*** Clerk Menu ***");
            System.out.println("1-Add Customer.");
            System.out.println("2-Add account Customer.");
            System.out.println("3-Add Credit Card Account.");
            System.out.println("4-View account with nationalId customer.");
            System.out.println("5-View All Credit card with numberAccount.");
            System.out.println("6-INACTIVE account!.");
            System.out.println("7-Deposit money to account number(try).");
            System.out.println("10-Exit.");
            System.out.print("Please select a number:");
            command = input.nextInt();
            input.nextLine();
            switch(command){
                case 1:
                    String result = customerService.addCustomer(username);
                    System.out.println( result + " successful added!");
                    break;

                case 2:
                    if( accountService.addAccount(username) == 1 )
                        System.out.println("This account successful added!");
                    break;

                case 3:
                    int result1 = creditCardService.addCard();
                    if(result1 == 1 )
                        System.out.println("This card is successful added!");
                    else
                        System.out.println("Something is wrong");
                    break;

                case 4:
                    accountService.showAccountForClerk();
                    break;

                case 5:
                    creditCardService.showCardForClerk();
                    break;

                case 6:
                    accountService.setInactiveAccount();
                    break;

                case 7:
                    accountService.DepositToAccount();
                    break;


                case 10:
                    System.out.println("Good luck!");
                    isTrue = false;
                    break;

                default:
                    System.out.println("you enter a wrong number!");
            }//switch
        }//while
    }//clerkMenu

    //::::>
    public void customerMenu() throws SQLException {
        isTrue = true;
        while(isTrue){
            System.out.println("*** Customer Menu ***");
            System.out.println("1-password operation.");
            System.out.println("2-Card To Card(move money).");
            System.out.println("3-Show my account(Active).");
            System.out.println("4-Show Transaction.");
            System.out.println("10-Exit.");
            System.out.print("Please enter a number:");
            command = input.nextInt();
            input.nextLine();
            switch(command){
                case 1:
                    creditCardService.setPassword(username);
                    break;

                case 2:
                    creditCardService.cardToCard(username);
                    break;

                case 3:
                    accountService.showAccountForCustomer(username);
                    break;

                case 4:
                    transactionService.showTransaction(username);
                    break;

                case 10:
                    System.out.println("Good luck!");
                    isTrue = false;

                default:
                    System.out.println("You enter a wrong number!");

            }
        }//while
    }//customerMenu

































}
