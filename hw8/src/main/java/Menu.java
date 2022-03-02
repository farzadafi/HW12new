import entity.User;
import manager.AdminManager;
import manager.CustomerManager;
import service.LoginService;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private Scanner input = new Scanner(System.in);
    private boolean isTrue = true;
    private int command;
    private String username,password;
    private AdminManager adminManager = new AdminManager();
    private CustomerManager customerManager = new CustomerManager();
    private LoginService loginService = new LoginService();

    //::::>
    public int publicMenu(){
        System.out.println("***WELCOME***");
        System.out.println("1-signIn(Enter)");
        System.out.println("2-signUp(Register)");
        System.out.println("3-Exit");
        System.out.print("Please select a number:");
        while(true) {
            try {
                command = input.nextInt();
                input.nextLine();
                break;
            } catch (InputMismatchException exception) {
                input.nextLine();
                System.out.print("Enter a number:");
            }
        }
        switch(command){
            case 1:
                return 1;

            case 2:
                return 2;

            case 3:
                return 3;

            default:
                return 0;
        }
    }

    //:::::>
    public void RegisterMenu() throws SQLException {
        System.out.println("Who are you?");
        System.out.println("1-Admin.");
        System.out.println("2-Customer.");
        System.out.println("3-Cancel!");
        System.out.print("Please select a number:");
        try {
            command = input.nextInt();
        }catch (InputMismatchException exception){
            input.nextLine();
            System.out.println("You can just Enter number!");
            return;
        }
        input.nextLine();
        switch(command)
        {

            case 1:
                adminManager.addManager();
                break;

            case 2:
                customerManager.addCustomer();
                break;

            case 3:
                System.out.println("Good luck!");
                return;

            default:
                System.out.println("You enter a wrong number!");
        }
    }

    //::::>
    public void enterMenu() throws SQLException, ClassNotFoundException {
        System.out.print("Please enter your username:");
        username = input.nextLine();
        User user = loginService.returnUser(username);
        if(user == null){
            System.out.println("This user name not define before,Please sign up!");
            return;
        }
        System.out.print("Please enter your password:");
        password = input.nextLine();
        if(!password.equals(user.getPassword())){
            System.out.println("This password is wrong!");
            return;
        }
        if(String.valueOf(user.getTypeUser()).equals("ADMIN"))
            adminMenu(user.getId());
        else if(String.valueOf(user.getTypeUser()).equals("CUSTOMER"))
            customerMenu(user.getId());
        else
            System.out.println("It's very Strange!");
    }


    //::::>
    public void adminMenu(int id) throws SQLException {
        isTrue = true;
        while(isTrue) {
            System.out.println("*** Admin Menu ***");
            System.out.println("1-Edit my information.");
            System.out.println("2-Add category.");
            System.out.println("3-Add brand.");
            System.out.println("4-Add Product.");
            System.out.println("5-Add number to product or define new price.");
            System.out.println("6-Remove product.");
            System.out.println("7-Add off code(array).");
            System.out.println("8-View my sale.");
            System.out.println("10-Exit.");
            System.out.print("Please select a number:");
            while(true) {
                try {
                    command = input.nextInt();
                    input.nextLine();
                    break;
                } catch (InputMismatchException exception) {
                    input.nextLine();
                    System.out.print("Enter a number:");
                }
            }
            switch (command)
            {
                case 1:
                    adminManager.editManager(id);
                    break;

                case 2:
                    adminManager.addCategory();
                    break;

                case 3:
                    adminManager.addBrand();
                    break;

                case 4:
                    adminManager.addProduct(id);
                    break;

                case 5:
                    adminManager.editProduct(id);
                    break;

                case 6:
                    adminManager.removeProduct(id);
                    break;

                case 7:
                    customerManager.setOffCode();
                    break;

                case 8:
                    adminManager.saleProduct(id);
                    break;

                case 10:
                    System.out.println("Good luck!");
                    isTrue = false;
                    break;

                default:
                    System.out.println("you enter a wrong number!");
            }
        }
    }

    //::::>
    public void customerMenu(int id){
        isTrue = true;
        while(isTrue) {
            System.out.println("*** Customer Menu ***");
            System.out.println("1-Edit my information.");
            System.out.println("2-Add Product.");
            System.out.println("3-Remove product from Basket.");
            System.out.println("4-Add budget.");
            System.out.println("5-View my basket.");
            System.out.println("6-Final purchase.");
            System.out.println("7-View my bought Product.");
            System.out.println("10-Exit.");
            System.out.print("Please select a number:");
            while(true) {
                try {
                    command = input.nextInt();
                    input.nextLine();
                    break;
                } catch (InputMismatchException exception) {
                    input.nextLine();
                    System.out.print("Enter a number:");
                }
            }
            switch (command)
            {
                case 1:
                    customerManager.editCustomer(id);
                    break;

                case 2:
                    customerManager.addProduct(id);
                    break;

                case 3:
                    customerManager.removeProduct(id);
                    break;

                case 4:
                    customerManager.addBudget(id);
                    break;

                case 5:
                    customerManager.viewMyBasket(id);
                    break;


                case 6:
                    customerManager.finalPurchase(id);
                    break;

                case 7:
                    customerManager.saleProduct(id);
                    break;


                case 10:
                    System.out.println("Good luck!");
                    isTrue = false;
                    break;

                default:
                    System.out.println("you enter a wrong number!");
            }
        }
    }



}
