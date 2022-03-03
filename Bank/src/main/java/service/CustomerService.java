package service;

import entity.Customer;
import entity.enumoration.TypeUser;
import repository.CustomerRepository;

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
    public String addCustomer(String nationalIdClerk) {
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
        try {
            customerRepository.add(newClerk);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        loginService.addNewLogin(nationalId,password,TypeUser.CUSTOMER);
        return fullName;
    }

    //::::>
    public String findName(String nationalId) {
        try {
            return customerRepository.findName(nationalId);
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return "null";
        }
    }


}
