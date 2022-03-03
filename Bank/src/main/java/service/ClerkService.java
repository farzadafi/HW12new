package service;

import entity.Clerk;
import entity.enumoration.TypeUser;
import repository.ClerkRepository;

import java.sql.SQLException;
import java.util.Scanner;

public class ClerkService {
    Scanner input = new Scanner(System.in);
    private String fullName,nationalId,codeBranch,password;
    ClerkRepository clerkRepository = new ClerkRepository();
    BankBranchService bankBranchService = new BankBranchService();
    LoginService loginService = new LoginService();
    int result = 0;

    public ClerkService() throws SQLException, ClassNotFoundException {
    }

    //::::>
    public String addClerk(String nationalIdBoss) {
        codeBranch = bankBranchService.findCodeBranch(nationalIdBoss);
        System.out.print("Enter full Name Clerk:");
        fullName = input.nextLine();
        while(true){
            System.out.print("Enter nationalId(username):");
            nationalId = input.nextLine();
                result = loginService.findNationalId(nationalId);
            if(result == 1 ) {
                System.out.println("You enter a wrong national Id!");
            }
            else
                break;
        }
        System.out.print("Enter password for " + fullName + " :");
        password = input.nextLine();
        Clerk newClerk = new Clerk(fullName,nationalId,codeBranch,password,TypeUser.CLERK);
        try {
            clerkRepository.add(newClerk);
            loginService.addNewLogin(nationalId,password,TypeUser.CLERK);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return fullName;
    }

    public String findCodeBranch(String nationalId) {
        try {
            return clerkRepository.findCodeBranch(nationalId);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }


}
