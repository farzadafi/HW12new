package service;

import entity.Bank;
import repository.BankRepository;

import java.sql.SQLException;
import java.util.Scanner;

public class BankService {
    private Scanner input = new Scanner(System.in);
    private String nameBank;
    private BankRepository bankRepository = new BankRepository();

    public BankService() throws SQLException, ClassNotFoundException {
    }

    //::::k>
    public int addBank() {
        System.out.print("Please enter you name Bank:");
        nameBank = input.nextLine();
        int result = 0;
        try {
            bankRepository.find(nameBank);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        if(result == 1 )
            return 1;
        else{
            Bank bank = new Bank(nameBank);
            try {
                bankRepository.add(bank);
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
            return 2;
        }
    }

    public int findBankName(String name) {
        try {
            return bankRepository.find(name);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return 0;
    }




















}
