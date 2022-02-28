package Service;

import Entity.Clerk;
import Entity.Customer;
import Entity.TypeUser;
import Repository.CustomerRepository;

import java.sql.SQLException;
import java.util.Scanner;

public class CustomerService {
    private Scanner input = new Scanner(System.in);
    private String fullName,nationalId,codeBranch,password;
    private ClerkService clerkService = new ClerkService();
    private LoginService loginService = new LoginService();
    private CustomerRepository customerRepository = new CustomerRepository();

    public CustomerService() throws SQLException, ClassNotFoundException {
    }


    //::::>
    public String addCustomer(String nationalIdClerk) throws SQLException {
        codeBranch = clerkService.findCodeBranch(nationalIdClerk);
        System.out.print("Enter full Name customer:");
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
        Customer newClerk = new Customer(fullName,nationalId,codeBranch,password, TypeUser.CUSTOMER);
        customerRepository.add(newClerk);
        loginService.addNewLogin(nationalId,password,TypeUser.CUSTOMER);
        return fullName;
    }

    //::::>
    public String findName(String nationalId) throws SQLException {
        return customerRepository.findName(nationalId);
    }


}
