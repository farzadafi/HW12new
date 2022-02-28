package Service;

import Entity.Bank;
import Repository.BankRepository;

import java.sql.SQLException;
import java.util.Scanner;

public class BankService {
    private Scanner input = new Scanner(System.in);
    private String nameBank;
    private BankRepository bankRepository = new BankRepository();

    public BankService() throws SQLException, ClassNotFoundException {
    }

    //::::>
    public int addBank() throws SQLException {
        System.out.print("Please enter you name Bank:");
        nameBank = input.nextLine();
        if( bankRepository.find(nameBank) == 1 )
            return 1;
        else{
            Bank bank = new Bank(nameBank);
            bankRepository.add(bank);
            return 2;
        }
    }

    public int findBankName(String name) throws SQLException {
        return bankRepository.find(name);
    }




















}
