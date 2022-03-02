package repository;

import entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements Repository<Product> {
    private Connection connection;

    public ProductRepository() {
        try {
            this.connection = Singleton.getInstance().getConnection();
            String CreateTable = "CREATE TABLE IF NOT EXISTS Product(id serial PRIMARY KEY," +
                    "adminId integer REFERENCES UserTable(id)," +
                    "categoryId integer ," +
                    "brandId integer REFERENCES Category(id)," +
                    "nameProduct varchar(50)," +
                    "numberProduct integer," +
                    "price DECIMAL)";
            PreparedStatement preparedStatement = connection.prepareStatement(CreateTable);
            preparedStatement.execute();
        }catch (SQLException | ClassNotFoundException exception){
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public int add(Product product) throws SQLException {
            String add = "INSERT INTO Product(adminId,categoryId,brandId,nameProduct,numberProduct,price) VALUES (?,?,?,?,?,?) ";
            PreparedStatement preparedStatement = connection.prepareStatement(add);
            preparedStatement.setInt(1, product.getAdminId());
            preparedStatement.setInt(2, product.getCategoryId());
            preparedStatement.setInt(3, product.getBrandId());
            preparedStatement.setString(4, product.getName());
            preparedStatement.setInt(5, product.getNumber());
            preparedStatement.setDouble(6, product.getPrice());
            return preparedStatement.executeUpdate();
    }


        @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public int update(Product product) throws SQLException {
            String update = "UPDATE product SET numberProduct = numberProduct + ? , price = ? WHERE id = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setInt(1,product.getNumber());
            preparedStatement.setDouble(2,product.getPrice());
            preparedStatement.setInt(3,product.getId());
            return preparedStatement.executeUpdate();
    }

    @Override
    public int delete(int id) throws SQLException {
            String delete = "DELETE FROM product WHERE id = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setInt(1,id);
            return preparedStatement.executeUpdate();
    }

    public List<Product> showAdminProduct(int id) throws SQLException {
            String findAll = "SELECT * FROM Product WHERE adminId = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(findAll);
            preparedStatement.setInt(1,id);
            List<Product> productList = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    Product product = new Product();
                    product.setId(resultSet.getInt("id"));
                    product.setCategoryId(resultSet.getInt("categoryId"));
                    product.setBrandId(resultSet.getInt("brandId"));
                    product.setName(resultSet.getString("nameProduct"));
                    product.setNumber(resultSet.getInt("numberProduct"));
                    product.setPrice(resultSet.getDouble("price"));
                    productList.add(product);
                }
                return productList;
            } else
                return null;
    }

    public List<Product> showProductForCustomer(int idCategory,int idBrand) throws SQLException {
            String findAll = "SELECT * FROM Product WHERE categoryId = ? AND brandId = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(findAll);
            preparedStatement.setInt(1, idCategory);
            preparedStatement.setInt(2, idBrand);
            List<Product> productList = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    Product product = new Product();
                    product.setId(resultSet.getInt("id"));
                    product.setCategoryId(resultSet.getInt("categoryId"));
                    product.setBrandId(resultSet.getInt("brandId"));
                    product.setName(resultSet.getString("nameProduct"));
                    product.setNumber(resultSet.getInt("numberProduct"));
                    product.setPrice(resultSet.getDouble("price"));
                    productList.add(product);
                }
                return productList;
            } else
                return null;
    }

    public int returnNumberProduct(int id) throws SQLException {
            String number = "SELECT numberProduct FROM Product WHERE id = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(number);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
                return resultSet.getInt("numberProduct");
            else
                return 0;
    }

    public int minesNumberProduct(int id,int number) throws SQLException {
            String mines = "UPDATE Product SET numberProduct = numberProduct - ? WHERE id = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(mines);
            preparedStatement.setInt(1,number);
            preparedStatement.setInt(2,id);
            return preparedStatement.executeUpdate();
    }

    public int returnAdminId(int id) throws SQLException {
        String AdminId = "SELECT adminId FROM Product WHERE id = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(AdminId);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next())
            return resultSet.getInt("adminId");
        else
            return 0;
    }




    }
