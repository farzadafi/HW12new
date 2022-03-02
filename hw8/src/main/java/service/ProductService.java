package service;

import entity.Product;
import repository.ProductRepository;

import java.sql.SQLException;
import java.util.List;

public class ProductService implements Service<Product> {
    private ProductRepository productRepository = new ProductRepository();


    @Override
    public int add(Product product) {
        try {
            return productRepository.add(product);
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return 0;
    }

    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public int update(Product product) {
        try {
            return productRepository.update(product);
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return 0;
    }

    @Override
    public int delete(int id) {
        try {
            return productRepository.delete(id);
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return 0;
    }

    public int returnAdminId(int id){
        try {
            return productRepository.returnAdminId(id);
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return 0;
    }

    public List<Product> showAdminProduct(int id){
        try {
            return productRepository.showAdminProduct(id);
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return null;
    }

    public List<Product> showProductForCustomer(int idCategory,int idBrand){
        try {
            return productRepository.showProductForCustomer(idCategory,idBrand);
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return null;
    }

    public int returnNumberProduct(int id){
        try {
            return productRepository.returnNumberProduct(id);
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return 0;
    }

    public int minesNumberProduct(int id,int number){
        try {
            return productRepository.minesNumberProduct(id,number);
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return 0;
    }
}
