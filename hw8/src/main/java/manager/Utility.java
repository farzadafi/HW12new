package manager;

import entity.Category;
import entity.CustomerBasket;
import entity.Product;
import exception.InvalidName;
import exception.InvalidNationalId;
import exception.InvalidPassword;

import java.util.List;
import java.util.Scanner;

public class Utility {
    private Scanner input = new Scanner(System.in);
    private int number;
    private Double price;
    private String fullName,nationalId,password,categoryName;
    private InvalidName invalidName = new InvalidName();
    private InvalidNationalId invalidNationalId = new InvalidNationalId();
    private InvalidPassword invalidPassword = new InvalidPassword();


    public String setFullName(){
        while(true){
            System.out.print("Enter name(just alpha):");
            try {
                fullName = input.nextLine();
                checkName(fullName);
                break;
            }catch (InvalidName except){
                System.out.println(except.getMessage());
            }
        }
        return fullName;
    }

    public String setNationalId(){
        while(true){
            System.out.print("Enter National Id(just number):");
            try {
                nationalId = input.nextLine();
                nationalIdChecker(nationalId);
                break;
            }catch (InvalidNationalId except){
                System.out.println(except.getMessage());
            }
        }
        return nationalId;
    }

    public String setPassword(){
        while(true) {
            System.out.print("Enter your password:");
            try {
                password = input.nextLine();
                passwordCheck(password);
                break;
            } catch (InvalidPassword except) {
                System.out.println(except.getMessage());
            }
        }
        return password;
    }

    public String setCategoryName(){
        while(true){
            System.out.print("Enter name(just alpha):");
            try {
                categoryName = input.nextLine();
                checkName(categoryName);
                break;
            }catch (InvalidName except){
                System.out.println(except.getMessage());
            }
        }
        return categoryName;
    }

    public int setId(int[] array){
        int id;
        System.out.print("Enter your brand id:");
        try {
            id = input.nextInt();
            input.nextLine();
        }catch (Exception exception){
            input.nextLine();
            System.out.println("just Enter number Professor!");
            return 0;
        }
        if(id == 0 ){
            System.out.println("You enter a wrong Id");
            return 0;
        }
        boolean isContain = false;
        for (int number:array
        ) {
            if(number == id ) {
                isContain = true;
                break;
            }
        }
        if(!isContain) {
            System.out.println("This id is not find!");
            return 0;
        }
        else
            return id;
    }

    //::::>
    public int[] returnIdCategory(List<Category> categoryList){
        int[] arrayInt = new int[50];
        int index = 0;
        for (Category cat:categoryList
        ) {
            Category cat1 = new Category();
            cat1 = cat;
            arrayInt[index] = cat.getId();
            index++;
        }
        return arrayInt;
    }

    public int[] returnIdProduct(List<Product> productList){
        int[] arrayInt = new int[50];
        int index = 0;
        for (Product pro:productList
        ) {
            Product pro1 = new Product();
            pro1 = pro;
            arrayInt[index] = pro.getId();
            index++;
        }
        return arrayInt;
    }

    public int setNumberProduct(String name){
        while (true) {
            System.out.print("Please number of " + name + ":");
            while (true) {
                try {
                    number = input.nextInt();
                    input.nextLine();
                    break;
                } catch (Exception exception) {
                    input.nextLine();
                    System.out.print("Enter just number:");
                }
            }
            if (number < 0) {
                System.out.println("You have enter a number more than Zero!");
            }
            else
                break;
        }
        return number;
    }

    public Double setPriceProduct(String name){
        while(true){
            System.out.print("Please enter price for " + name + ":");
            while (true) {
                try {
                    price = input.nextDouble();
                    input.nextLine();
                    break;
                } catch (Exception exception) {
                    input.nextLine();
                    System.out.print("Enter just number:");
                }
            }
            if(price < 0 ){
                System.out.println("You have enter a price more than Zero!");
            }else
                break;
        }
        return price;
    }

    public int setNumberProduct(int idProduct,List<Product> productList){
        for (Product pro:productList
        ) {
            if(pro.getId() == idProduct ){
                return pro.getNumber();
            }
        }
        return -1;
    }

    public Double returnPriceProduct(int idProduct,List<Product> productList){
        for (Product pro:productList
        ) {
            if(pro.getId() == idProduct ){
                return pro.getPrice();
            }
        }
        return 0d;
    }

    public int[] returnIdCustomerBasket(List<CustomerBasket> customerBasketList){
        int[] arrayInt = new int[50];
        int index = 0;
        for (CustomerBasket cat:customerBasketList
        ) {
            CustomerBasket cat1 = new CustomerBasket();
            cat1 = cat;
            arrayInt[index] = cat.getId();
            index++;
        }
        return arrayInt;
    }

    public void checkName(String name){
        if(name.length() < 3 )
            throw new InvalidName("name should be more than 2 character!");
        for (Character ch:name.toCharArray()
        ) {
            if(Character.isDigit(ch))
                throw new InvalidName("name can not have number!");
        }
        for (Character ch:name.toCharArray()
        ) {
            if(!Character.isAlphabetic(ch))
                throw new InvalidName("name can't have Sign(!,@,#,%,...)");

        }
    }

    public void nationalIdChecker(String nationalId){
        if(nationalId.length() > 10 )
            throw new InvalidNationalId("national Id can't more than ten number!");
        if(nationalId.equals(""))
            throw new InvalidNationalId("dont enter space!");
        for (Character ch:nationalId.toCharArray()) {
            if(!Character.isDigit(ch))
                throw new InvalidNationalId("national Id should be just number!");
        }
    }

    public void passwordCheck(String password){
        if(password.length() < 3 )
            throw new InvalidPassword("password should be more than 2 ");
        char[] passwordArray = password.toCharArray();
        char[] signArray =  new char[] {'!','@','#','$','%','^','&','*','(',')','-','+','=','.',',','>','<','?','/','|',':',';'};
        int space = 0,lowerCase = 0,upperCase = 0,sign = 0,digit = 0;
        for(int i=0;i<passwordArray.length;i++)
            if(Character.isSpaceChar(passwordArray[i]))
                ++space;
        for(int i = 0;i<passwordArray.length;i++)
            if(Character.isUpperCase(passwordArray[i]))
                ++upperCase;
        for(int i = 0;i<passwordArray.length;i++)
            if(Character.isLowerCase(passwordArray[i]))
                ++lowerCase;
        for(int i = 0;i<passwordArray.length;i++)
            if(Character.isDigit(passwordArray[i]))
                ++digit;
        for(int i=0;i<signArray.length;i++)
            for(int j=0;j<passwordArray.length;j++)
                if(signArray[i] == passwordArray[j])
                    ++sign;
        if( (space == 0) || (lowerCase == 0) || (upperCase == 0) || (sign == 0) || (digit == 0) )
            throw new InvalidPassword("password should have space+lowerCase+upperCase+sign+digit!");
    }



}
