package Service;

import Entity.BankBranch;
import Entity.TypeUser;
import Repository.BankBranchRepository;

import java.sql.SQLException;
import java.util.Scanner;

public class BankBranchService {
    private BankService bankService = new BankService();
    private BankBranchRepository bankBranchRepository = new BankBranchRepository();
    private LoginService loginService = new LoginService();
    private Scanner input = new Scanner(System.in);
    private String nameBank,bossFullName,nationalId,password,codeBranch;

    public BankBranchService() throws SQLException, ClassNotFoundException {
    }

    //::::>
    public int addBankBranch() throws SQLException {
        System.out.print("Enter bank name:");
        nameBank = input.nextLine();
        if( bankService.findBankName(nameBank) == 0 ) {
            return 1;
        }
        System.out.print("Enter code Branch:");
        codeBranch = input.nextLine();
        if( bankBranchRepository.find(codeBranch) == 1 ){
            return 2;
        }
        System.out.print("Enter boss full name:");
        bossFullName = input.nextLine();
        while(true){
            System.out.print("Enter your national Id(username):");
            nationalId = input.nextLine();
            if( loginService.findNationalId(nationalId) == 1 ) {
                System.out.println("you enter a wrong national id");
                continue;
            }
            else
                break;
        }
        System.out.print("Enter password for " + bossFullName + ":" );
        password = input.nextLine();
        loginService.addNewLogin(nationalId,password, TypeUser.BOSS);
        BankBranch newBankBranch = new BankBranch(nameBank,codeBranch,bossFullName,nationalId,password);
        bankBranchRepository.add(newBankBranch);
        return 3;
    }

    //::::>
    public String findCodeBranch(String nationalId) throws SQLException {
        return bankBranchRepository.findCodeBranch(nationalId);
    }






}//
