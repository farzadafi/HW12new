package manager;

import entity.*;
import service.*;

import java.util.List;
import java.util.Scanner;

public class AdminManager {
    private AdminService adminService = new AdminService();
    private String fullName,nationalId,password,categoryName,brandName,productName;
    private Double price;
    private int number;
    private Utility utility = new Utility();
    private LoginService loginService = new LoginService();
    private CategoryService categoryService = new CategoryService();
    private Scanner input = new Scanner(System.in);
    private ProductService productService = new ProductService();
    private SaleProductService saleProductService = new SaleProductService();

    //::::>
    public void addManager(){
        fullName= utility.setFullName();
        while(true){
            nationalId = utility.setNationalId();
            if(loginService.returnUser(nationalId) == null)
                break;
            else
                System.out.println("this national Id is used before!");
        }
        password = utility.setPassword();
        Admin admin = new Admin(fullName,nationalId,password,TypeUser.ADMIN);
        if(adminService.add(admin) != 0 ){
            System.out.println(fullName + " successful added!");
        }
    }

    //::::>
    public void editManager(int id){
        fullName = utility.setFullName();
        password = utility.setPassword();
        Admin admin = new Admin(id,fullName,null,password,null);
        if(adminService.update(admin) != 0 ){
            System.out.println(fullName + " successful updated!");
        }
    }

    public void addCategory(){
        categoryName = utility.setCategoryName();
        Category category = new Category(0,categoryName);
        if(categoryService.add(category) != 0 )
            System.out.println(categoryName + " successful added!");
        else
            System.out.println("Something is wrong");
    }

    public void addBrand(){
        List<Category> categoryList = categoryService.showCategory(0);
        if(categoryList == null){
            System.out.println("You first have define category!");
            return;
        }
        int[] array = utility.returnIdCategory(categoryList);
        for (Category cat:categoryList
             ) {
            System.out.println(cat.toString());
        }
        int id = utility.setId(array);
        if(id == 0 ){
            return;
        }
        System.out.print("Enter name of Brand:");
        brandName = input.nextLine();
        Category category = new Category(id,brandName);
        if(categoryService.add(category) != 0 )
            System.out.println(brandName + " successful added!");
        }

        public void addProduct(int id){
            List<Category> categoryList = categoryService.showCategory(0);
            if(categoryList == null){
                System.out.println("You first have define category!");
                return;
            }
            int[] array = utility.returnIdCategory(categoryList);
            for (Category cat:categoryList
            ) {
                System.out.println(cat.toString());
            }
            int idCategory = utility.setId(array);
            if(idCategory == 0 ){
                return;
            }
            categoryList = categoryService.showCategory(idCategory);
            if(categoryList == null){
                System.out.println("You first have define brand!");
                return;
            }
            System.out.println("This brand we have:");
            for (Category cat:categoryList
            ) {
                System.out.println(cat.toString());
            }
            array = utility.returnIdCategory(categoryList);
            int idBrand = utility.setId(array);
            System.out.print("Enter name for Product:");
            productName = input.nextLine();
            number = utility.setNumberProduct(productName);
            price = utility.setPriceProduct(productName);
            Product product = new Product(id,idCategory,idBrand,productName,number,price);
            if(productService.add(product) != 0 )
                System.out.println(productName + " successful added!");
            else
                System.out.println("Something is wrong!");
        }


        public void editProduct(int id){
            List<Product> productList = productService.showAdminProduct(id);
            if(productList == null){
                System.out.println("You first have define Product!");
                return;
            }
            int[] array = utility.returnIdProduct(productList);
            for (Product pro:productList
            ) {
                System.out.println(pro.toString());
            }
            int idProduct = utility.setId(array);
            if(idProduct == 0 ){
                return;
            }
            number = utility.setNumberProduct("new");
            price = utility.setPriceProduct("new");
            Product product = new Product(idProduct,number,price);
            if(productService.update(product) != 0 )
                System.out.println("This operation is successful!");
            else
                System.out.println("Something is wrong!");
        }

        public void removeProduct(int id){
            List<Product> productList = productService.showAdminProduct(id);
            if(productList == null){
                System.out.println("You first have define Product!");
                return;
            }
            int[] array = utility.returnIdProduct(productList);
            for (Product pro:productList
            ) {
                System.out.println(pro.toString());
            }
            int idProduct = utility.setId(array);
            if(idProduct == 0 ){
                return;
            }
            if( productService.delete(idProduct) != 0 )
                System.out.println("This product successful Removed!");
            else
                System.out.println("Something is wrong!");
        }

    public void saleProduct(int id) {
        List<SaleProduct> saleProductList = saleProductService.saleProductByAdminId(id);
        if(saleProductList == null ) {
            System.out.println("You dont have any Sale!");
            return;
        }
        for (SaleProduct sale:saleProductList
        ) {
            System.out.println(sale.toString());
        }
    }






















}
