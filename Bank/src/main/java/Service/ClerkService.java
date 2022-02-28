package Service;

import Entity.Clerk;
import Entity.TypeUser;
import Repository.ClerkRepository;

import java.sql.SQLException;
import java.util.Scanner;

public class ClerkService {
    Scanner input = new Scanner(System.in);
    private String fullName,nationalId,codeBranch,password;
    ClerkRepository clerkRepository = new ClerkRepository();
    BankBranchService bankBranchService = new BankBranchService();
    LoginService loginService = new LoginService();

    public ClerkService() throws SQLException, ClassNotFoundException {
    }

    //::::>
    public String addClerk(String nationalIdBoss) throws SQLException {
        codeBranch = bankBranchService.findCodeBranch(nationalIdBoss);
        System.out.print("Enter full Name Clerk:");
        fullName = input.nextLine();
        while(true){
            System.out.print("Enter nationalId(username):");
            nationalId = input.nextLine();
            if( loginService.findNationalId(nationalId) == 1 ) {
                System.out.println("You enter a wrong national Id!");
            }
            else
                break;
        }
        System.out.print("Enter password for " + fullName + " :");
        password = input.nextLine();
        Clerk newClerk = new Clerk(fullName,nationalId,codeBranch,password,TypeUser.CLERK);
        clerkRepository.add(newClerk);
        loginService.addNewLogin(nationalId,password,TypeUser.CLERK);
        return fullName;
    }

    public String findCodeBranch(String nationalId) throws SQLException {
        return clerkRepository.findCodeBranch(nationalId);
    }


}
