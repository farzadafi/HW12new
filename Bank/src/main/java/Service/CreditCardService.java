package Service;

import Entity.*;
import Repository.CreditCardRepository;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class CreditCardService {
    private AccountService accountService = new AccountService();
    private Random random = new Random();
    private CustomerService customerService = new CustomerService();
    private String nationalId,name = "empty",accountNumber,cardNumber,cvv2,expireDate;
    private String originCardNumber,destinationCardNumber,password;
    private double amount;
    private Scanner input = new Scanner(System.in);
    private LoginService loginService = new LoginService();
    private CreditCardRepository creditCardRepository = new CreditCardRepository();
    private TransactionService transactionService = new TransactionService();

    public CreditCardService() throws SQLException, ClassNotFoundException {
    }

    public int addCard() throws SQLException {
        while(true){
            System.out.print("Enter national Id customer(cancel):");
            nationalId = input.nextLine();
            if( nationalId.equals("cancel"))
                return 0;
            if( loginService.findNationalId(nationalId) == 0 ) {
                System.out.println("You enter a wrong national Id!");
            }
            else
                break;
        }

        name = customerService.findName(nationalId);
        if ( name.equals("null")){
            System.out.println("you enter a wrong national id!");
            return 0;
        }
        System.out.println( name + " have this account :");
        int result = accountService.showAccount(nationalId);
        if( result == 0 ){
            System.out.println(name + " unfortunately doesn't have any active account!");
            return 0;
        }

        System.out.print("Enter number's account for add card:");
        int number = input.nextInt();
        input.nextLine();
        accountNumber = accountService.returnAccountNumber(number);
        if ( accountService.findAccountNumber(accountNumber) == 0 ){
            System.out.println("this account number is not define!");
            return 0;
        }
        if( creditCardRepository.findActiveCard(accountNumber) == 1 ){
            System.out.println("This account you enter have an active card!");
            return 0;
        }

        while(true){
            cardNumber = String.valueOf(random.nextLong(111111111111L,999999999999L));
            if( creditCardRepository.find(cardNumber) == 0 )
                break;
        }
        cvv2 = String.valueOf(random.nextInt(1111,9999));
        int year = java.time.LocalDate.now().getYear() + 4 ;
        int month = java.time.LocalDate.now().getMonthValue();
        expireDate = (String.valueOf(year)) + "-" + (String.valueOf(month));
        CreditCard newCreditCard = new CreditCard(accountNumber,cardNumber,cvv2,expireDate,TypeAccount.ACTIVE);
        creditCardRepository.add(newCreditCard);
        return 1;
    }

    //::::>
    public void setPassword(String nationalIdCustomer) throws SQLException {
        String[] result = creditCardRepository.show(nationalIdCustomer);
        if( result == null){
            System.out.println("You dont have any card!");
            return;
        }
        String number;
        System.out.print("Please select number for set password:");
        number = input.nextLine();
        boolean equal = false;
        for(int i=0;i<result.length;i++) {
            if (result[i] == null)
                break;
            if (result[i].equals(number)) {
                equal = true;
                break;
            }
        }
        if(!equal){
            System.out.println("We dont have any card with this number,please open your eyes!");
            return;
        }
        System.out.print("Enter password for set:");
        String password = input.nextLine();
        creditCardRepository.setPassword(Integer.parseInt(number),password);
        System.out.println("set password for " + number + "  ID cared successful!");
    }

    //::::>
    public void cardToCard(String nationalIdCustomer) throws SQLException {
        String[] result = creditCardRepository.show(nationalIdCustomer);
        if (result == null) {
            System.out.println("You dont have any card,Please call to clerk bank!");
            return;
        }
        String number;
        System.out.print("Please select number card for transfer money:");
        number = input.nextLine();
        boolean equal = false;
        for (int i = 0; i < result.length; i++) {
            if (result[i] == null)
                break;
            if (result[i].equals(number)) {
                equal = true;
                break;
            }
        }
        if (!equal) {
            System.out.println("We dont have any card with this number,please open your eyes!");
            return;
        }
        String[] result2 = creditCardRepository.returnInformationCard(Integer.parseInt(number));
        if(result2[2] == null){
            System.out.println("For this card is not define password,Please define password for it and try again!");
            return;
        }
        accountNumber = result2[0];
        if(!accountService.checkAccount(accountNumber)){
            System.out.println("This account is not Active!");
            return;
        }
        originCardNumber = result2[1];
        password = result2[2];
        String tempPassword;
        int numberFalse = 0;
        while(true){
            System.out.print("Enter password for this card:");
            tempPassword = input.nextLine();
            if(tempPassword.equals(password))
                break;
            else{
                if(numberFalse < 3 ){
                    ++numberFalse;
                    System.out.println("You enter " + numberFalse + " time false password");
                if(numberFalse == 3 ){
                    System.out.println("You enter 3 time incorrect password and I eat your card!(call to clerk)");
                    creditCardRepository.setInactiveCard(Integer.parseInt(number));
                    return;
                    }
                }
            }
        }
        System.out.print("Enter Expire date of your card:");
        expireDate = input.nextLine();
        if( !expireDate.equals(result2[3])){
            System.out.println("You enter a wrong Expire Date");
            return;
        }
        System.out.print("Enter cvv2 of your card:");
        cvv2 = input.nextLine();
        if( !cvv2.equals(result2[4])){
            System.out.println("You enter a wrong cvv2");
            return;
        }
        System.out.print("Enter Destination number card:");
        destinationCardNumber = input.nextLine();
        if( !creditCardRepository.checkCardNumber(destinationCardNumber)) {
            System.out.println("This number card is incorrect!");
            return;
        }
        String destinationAccount = creditCardRepository.returnAccountNumber(destinationCardNumber);
        if( !accountService.checkAccount(destinationAccount)){
            System.out.println("This account number is inactive!");
            return;
        }

        LocalDate tempDate = LocalDate.now();
        Date date = Date.valueOf(tempDate);
        LocalTime tempTime = LocalTime.now();
        Time time = Time.valueOf(tempTime);
        String amountAccount = accountService.returnAmount(accountNumber);
        System.out.println("You have " + amountAccount + " in this account");
        System.out.print("How much do you want to Transfer(600 for Transfer fee):");
        amount = input.nextDouble();
        if( (amount+600) > Double.parseDouble(amountAccount)){
            System.out.println("How are you,you dont have this amount for Transfer!");
            return;
        }
        accountService.depositCard(amount+600,accountNumber);
        accountService.withdrawCard(amount,destinationAccount);
        Transaction minesTransaction = new Transaction(accountNumber,originCardNumber,destinationCardNumber,String.valueOf(-amount),date,time, TypeTransaction.CARD_TO_CARD);
        transactionService.addTransaction(minesTransaction);
        Transaction feeTransaction = new Transaction(accountNumber,originCardNumber,destinationCardNumber,String.valueOf(-600),date,time, TypeTransaction.TRANSFER_FEE);
        transactionService.addTransaction(feeTransaction);
        Transaction plusTransaction = new Transaction(destinationAccount,originCardNumber,destinationCardNumber,String.valueOf(amount),date,time, TypeTransaction.CARD_TO_CARD);
        transactionService.addTransaction(plusTransaction);
        System.out.println("This card to card is successful!");
    }

    public void showCardForClerk() throws SQLException {
        System.out.print("Enter national Id customer:");
        nationalId = input.nextLine();
        String name = customerService.findName(nationalId);
        if(name.equals("null")){
            System.out.println("This national Id not found!");
            return;
        }
        System.out.println(name + " have this account:");
        accountService.showAccountForCustomer(nationalId);
        if(!accountService.getCheck()){
            return;
        }
        System.out.print("Enter account number for view card:");
        accountNumber = input.nextLine();
        List<CreditCard> creditCardList = creditCardRepository.findAllCard(accountNumber);
        for (CreditCard creditCard : creditCardList)
        {
            System.out.println(creditCard.toString());
        }
    }





}
